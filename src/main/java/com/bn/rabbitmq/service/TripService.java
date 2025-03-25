package com.bn.rabbitmq.service;

import com.bn.rabbitmq.common.CommonConverter;
import com.bn.rabbitmq.domain.Station;
import com.bn.rabbitmq.domain.Trip;
import com.bn.rabbitmq.dto.TrainLocationMessage;
import com.bn.rabbitmq.repository.StationRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Service
public class TripService {
    private final StationRepository stationRepository;

    private static final String CACHE_KEY = "station";

    private final RedisTemplate<String, Object> redisTemplate;

    public TripService(StationRepository stationRepository, RedisTemplate<String, Object> redisTemplate, RabbitTemplate rabbitTemplate) {
        this.stationRepository = stationRepository;
        this.redisTemplate = redisTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }



    public void updateStatus(){
        List<Station> stations = stationRepository.findAll();
        redisTemplate.opsForValue().set(CACHE_KEY, stations, 10, TimeUnit.MINUTES);
    }

    public List<Station> getProductList() {
        return (List<Station>) redisTemplate.opsForValue().get(CACHE_KEY);
    }

    private final RabbitTemplate rabbitTemplate;


    @Scheduled(cron = "*/30 * * * * *")
    public void runTaskWithCron() {
        TrainLocationMessage message = new TrainLocationMessage("CODE-123", 1, 2, 60.0, 49.55, 183, 2);
        String a = CommonConverter.convertToJson(message);
        rabbitTemplate.convertAndSend("directExchange", "companyRatingRoutingKey", a);
        System.out.println("🚆 Sent: " + message);
    }

    @Scheduled(fixedRate = 300000) // Cập nhật mỗi 5 phút
    public void refreshMapCache() {
        getProductList();
        System.out.println("refreshMapCache");
    }

    //Consumer nhận tọa độ từ RabbitMQ
    @RabbitListener(queues = "train-location-queue")
    public void receiveTrainLocation(TrainLocationMessage message) {
        try {
            Station nearestStation = findNearestStation(message.getX(), message.getY());

            if (nearestStation != null) {
                if (isFinalStation(nearestStation, message.getStationIdEnd())) {
                    completeTrainJourney(message);
                } else {
                    handleTrainStop(message, nearestStation);
                }
            }
        } catch (Exception e) {
//            log.error("Lỗi khi xử lý tọa độ tàu: " + e.getMessage());
        }
    }

    // 5. Khi đến ga cuối, cập nhật trạng thái "HOÀN THÀNH"
    private boolean isFinalStation(Station station, int stationIdEnd) {
        return station.getId() == stationIdEnd;
    }


// 3. Tìm ga gần nhất dựa trên tọa độ
//📌 Hàm tìm ga gần nhất dựa trên khoảng cách Euclidean
    private Station findNearestStation(Integer trainX, Integer trainY) {
        List<Station> stations = (List<Station>) redisTemplate.opsForValue().get(CACHE_KEY);

        Station nearestStation = null;
        double minDistance = Double.MAX_VALUE;

        for (Station station : stations) {
            double distance = Math.sqrt(Math.pow(station.getX() - trainX, 2) + Math.pow(station.getY() - trainY, 2));
            if (distance < minDistance) {
                minDistance = distance;
                nearestStation = station;
            }
        }
        return (minDistance <= 5) ? nearestStation : null; // Nếu khoảng cách <= 5m thì coi là gần ga
    }

    //✅ 4. Khi tàu đến gần ga, dừng 5 phút rồi chạy tiếp
    //📌 Cập nhật trạng thái "DỪNG" và chờ 5 phút đổi sang "CHẠY"
    private void handleTrainStop(TrainLocationMessage message, Station station) {
        String trainCode = message.getTrainCode();
//        log.info("🚆 Tàu {} đến gần ga {}, đổi trạng thái DỪNG", trainCode, station.getName());

//        trainService.updateStatus(trainCode, "DỪNG");

        // Sau 5 phút, đổi trạng thái thành "CHẠY"
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                trainService.updateStatus(trainCode, "CHẠY");
//                log.info("🚆 Tàu {} tiếp tục hành trình từ ga {}", trainCode, station.getName());
            }
        }, 5 * 60 * 1000); // 5 phút
    }

    //Khi đến ga cuối, cập nhật trạng thái "HOÀN THÀNH"
    private void completeTrainJourney(TrainLocationMessage message) {
//        log.info("🚆 Tàu {} đến ga cuối {}, đổi trạng thái HOÀN THÀNH", message.getTrainCode(), message.getStationIdEnd());

        Trip train = trainService.getTrainByCode(message.getTrainCode());
        if (train != null) {
            train.setStatus("HOÀN THÀNH");

            long startTime = train.getStartTime(); // Lấy thời gian bắt đầu từ DB
            long endTime = System.currentTimeMillis();
            long actualDuration = endTime - startTime;

            train.setEndTime(endTime);
            train.setActualDuration(actualDuration);

            // Nếu đến sớm, tính thời gian rảnh
            if (train.getExpectedDuration() > actualDuration) {
                long freeTime = train.getExpectedDuration() - actualDuration;
                train.setFreeTime(freeTime);
//                log.info("⏰ Tàu {} có {} phút thời gian rảnh.", message.getTrainCode(), freeTime / 60000);
            }

            trainService.updateTrain(train);
        }
    }
    //✅ Khi đến ga cuối, cập nhật trạng thái "HOÀN THÀNH"
    //✅ Tính toán thời gian thực tế và thời gian rảnh


}
