package com.bn.rabbitmq.service;

import com.bn.rabbitmq.common.CommonConverter;
import com.bn.rabbitmq.domain.Station;
import com.bn.rabbitmq.domain.Train;
import com.bn.rabbitmq.domain.Trip;
import com.bn.rabbitmq.domain.TripDetail;
import com.bn.rabbitmq.dto.TrainLocationMessage;
import com.bn.rabbitmq.repository.StationRepository;
import com.bn.rabbitmq.repository.TrainRepository;
import com.bn.rabbitmq.repository.TripDetailRepository;
import com.bn.rabbitmq.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class TripService {
    private final StationRepository stationRepository;

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();
    private final TripRepository tripRepository;
    private final TrainRepository trainRepository;
    private final TripDetailRepository tripDetailRepository;
    private double remainingDistance = 49.55;
    int a =1;

    private static final String CACHE_KEY = "station";

    private final RedisTemplate<String, Object> redisTemplate;

    public TripService(StationRepository stationRepository, RedisTemplate<String, Object> redisTemplate, RabbitTemplate rabbitTemplate, TripRepository tripRepository, TrainRepository trainRepository, TripDetailRepository tripDetailRepository) {
        this.stationRepository = stationRepository;
        this.redisTemplate = redisTemplate;
        this.rabbitTemplate = rabbitTemplate;
        this.tripRepository = tripRepository;
        this.trainRepository = trainRepository;
        this.tripDetailRepository = tripDetailRepository;
    }



    public void updateStatus(){
        List<Station> stations = stationRepository.findAll();
        redisTemplate.opsForValue().set(CACHE_KEY, stations, 10, TimeUnit.MINUTES);
    }

    public List<Station> getProductList() {
        return (List<Station>) redisTemplate.opsForValue().get(CACHE_KEY);
    }

//    private final RabbitTemplate rabbitTemplate;


    private static double lastDistance = 100.00; // Khoảng cách ban đầu


    public  double getRandomDistance() {
        if (lastDistance > 0) {
            double decrement = 0.1 + (2.0 * random.nextDouble()); // Giảm từ 0.1km đến 2.0km mỗi lần
            lastDistance = Math.max(0, lastDistance - decrement); // Đảm bảo không xuống dưới 0
        }
        return lastDistance;
    }
    @Scheduled(fixedRate = 500)
    public void runTaskWithCron() {
//        TrainLocationMessage message = new TrainLocationMessage("CODE-123", 1, 2, 60.0, 49.55, 183, 2);
        remainingDistance = Math.max(0, remainingDistance - random.nextDouble() * 2); // Đảm bảo không bị âm

        TrainLocationMessage updateJob = new TrainLocationMessage(
                "CODE-123-%d",
                1,
                2,
                60.0,
                getRandomDistance(),
                random.nextInt(200),
                random.nextInt(50)
        );


        String a = CommonConverter.convertToJson(updateJob);
        rabbitTemplate.convertAndSend("directExchange", "companyRatingRoutingKey", a);
        System.out.println("🚆 Sent: " + updateJob);
    }

    @Scheduled(fixedRate = 300000) // Cập nhật mỗi 5 phút
    public void refreshMapCache() {
        getProductList();
        System.out.println("refreshMapCache");
    }

    //Consumer nhận tọa độ từ RabbitMQ
//    @RabbitListener(queues = "train-location-queue")
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

    return stations.stream()
            .min(Comparator.comparingDouble(station ->
                    Math.pow(station.getX() - trainX, 2) + Math.pow(station.getY() - trainY, 2)))
            .filter(station -> Math.sqrt(
                    Math.pow(station.getX() - trainX, 2) + Math.pow(station.getY() - trainY, 2)) <= 5)
            .orElse(null);
}


    //✅ 4. Khi tàu đến gần ga, dừng 5 phút rồi chạy tiếp
    //📌 Cập nhật trạng thái "DỪNG" và chờ 5 phút đổi sang "CHẠY"
//    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//    private static final int MIN_STOP_TIME_SECONDS = 5;  // Tối thiểu dừng 5 giây
//    private static final int MAX_DELAY_THRESHOLD_SECONDS = 30; // Ngưỡng bỏ qua dừng
//
//    private void handleTrainStop(TrainLocationMessage message, Station station) {
//        String trainCode = message.getTrainCode();
//
//        long expectedArrivalTime = station.getExpectedArrivalTime(); // Giờ dự kiến
//        long actualArrivalTime = System.currentTimeMillis(); // Giờ thực tế
//        long delaySeconds = (actualArrivalTime - expectedArrivalTime) / 1000; // Độ lệch tính bằng giây
//
//        // Lấy thời gian dừng mặc định từ config
//        int adjustedStopDuration = station.getConfig().getStopDuration();
//
//        if (delaySeconds > 0) { // Nếu đến muộn
//            adjustedStopDuration = Math.max(MIN_STOP_TIME_SECONDS, adjustedStopDuration - (int) delaySeconds);
//            log.info("🚆 Tàu {} đến muộn {}s, giảm thời gian dừng còn {}s", trainCode, delaySeconds, adjustedStopDuration);
//        } else if (delaySeconds < 0) { // Nếu đến sớm
//            adjustedStopDuration += Math.abs((int) delaySeconds);
//            log.info("🚆 Tàu {} đến sớm {}s, tăng thời gian dừng lên {}s", trainCode, Math.abs(delaySeconds), adjustedStopDuration);
//        }
//
//        // Nếu trễ quá 30s, bỏ qua dừng
//        if (delaySeconds > MAX_DELAY_THRESHOLD_SECONDS) {
//            adjustedStopDuration = 0;
//            log.info("🚆 Tàu {} trễ {}s, bỏ qua dừng tại ga {}", trainCode, delaySeconds, station.getName());
//        }
//
//        if (adjustedStopDuration > 0) {
//            trainService.updateStatus(trainCode, "DỪNG");
//            log.info("🚆 Tàu {} dừng tại ga {} trong {}s", trainCode, station.getName(), adjustedStopDuration);
//
//            scheduler.schedule(() -> {
//                trainService.updateStatus(trainCode, "CHẠY");
//                log.info("🚆 Tàu {} tiếp tục hành trình từ ga {}", trainCode, station.getName());
//            }, adjustedStopDuration, TimeUnit.SECONDS);
//        } else {
//            trainService.updateStatus(trainCode, "CHẠY");
//            log.info("🚆 Tàu {} không dừng tại ga {}, tiếp tục hành trình", trainCode, station.getName());
//        }
//    }

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10); // Cho phép 10 tàu chạy song song

    public void handleTrainStop(TrainLocationMessage message, Station station) {
        String trainCode = message.getTrainCode();
        int delaySeconds = calculateDelay(message, station);

        // Lấy thời gian dừng mặc định của ga
        int baseStopDuration = station.getConfig().getStopDuration();

        // Nếu tàu đến muộn, giảm thời gian dừng nhưng không nhỏ hơn 5 giây
        int adjustedStopDuration = Math.max(5, baseStopDuration - delaySeconds);

        // Nếu trễ quá 30 giây, bỏ qua dừng
        if (delaySeconds > 30) {
            log.info("🚆 Tàu {} đến muộn {} giây, bỏ qua dừng, tiếp tục chạy ngay.", trainCode, delaySeconds);
            trainService.updateStatus(trainCode, "CHẠY");
            return;
        }

        log.info("🚆 Tàu {} đến ga {}, dừng trong {} giây", trainCode, station.getName(), adjustedStopDuration);
        trainService.updateStatus(trainCode, "DỪNG");

        scheduler.schedule(() -> {
            trainService.updateStatus(trainCode, "CHẠY");
            log.info("🚆 Tàu {} tiếp tục hành trình từ ga {}", trainCode, station.getName());
        }, adjustedStopDuration, TimeUnit.SECONDS);
    }

    // Hàm tính toán độ trễ dựa trên thời gian dự kiến và thực tế
    private int calculateDelay(TrainLocationMessage message, Station station) {
        Train train = trainRepository.getAllByCode(message.getTrainCode());
        Trip trip = tripRepository.getAllByTrainId(train.getId());
        TripDetail tripDetail = tripDetailRepository.findByTripIdAndStationId(trip.getId(),station.getId());
        long expectedArrivalTime = tripDetail.getArrivalTimeSchedule().getTime();
        long actualArrivalTime = System.currentTimeMillis();
        return (int) ((actualArrivalTime - expectedArrivalTime) / 1000); // Chuyển sang giây
    }



    //Khi đến ga cuối, cập nhật trạng thái "HOÀN THÀNH"
    private void completeTrainJourney(TrainLocationMessage message) {
//        log.info("🚆 Tàu {} đến ga cuối {}, đổi trạng thái HOÀN THÀNH", message.getTrainCode(), message.getStationIdEnd());

//        Trip train = trainService.getTrainByCode(message.getTrainCode());
//        if (train != null) {
//            train.setStatus("HOÀN THÀNH");
//
//            long startTime = train.getStartTime(); // Lấy thời gian bắt đầu từ DB
//            long endTime = System.currentTimeMillis();
//            long actualDuration = endTime - startTime;
//
//            train.setEndTime(endTime);
//            train.setActualDuration(actualDuration);
//
//            // Nếu đến sớm, tính thời gian rảnh
//            if (train.getExpectedDuration() > actualDuration) {
//                long freeTime = train.getExpectedDuration() - actualDuration;
//                train.setFreeTime(freeTime);
////                log.info("⏰ Tàu {} có {} phút thời gian rảnh.", message.getTrainCode(), freeTime / 60000);
//            }
//
//            trainService.updateTrain(train);
        }
    }
    //✅ Khi đến ga cuối, cập nhật trạng thái "HOÀN THÀNH"
    //✅ Tính toán thời gian thực tế và thời gian rảnh



