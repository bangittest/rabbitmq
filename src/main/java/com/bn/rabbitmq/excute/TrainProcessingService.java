package com.bn.rabbitmq.excute;

import com.bn.rabbitmq.domain.Station;
import com.bn.rabbitmq.domain.Train;
import com.bn.rabbitmq.dto.TrainLocationMessage;
import com.bn.rabbitmq.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TrainProcessingService {
    private final TrainRepository trainRepository;

    public TrainProcessingService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }


    private final AtomicReference<Instant> lastUpdateTime = new AtomicReference<>(null);
    private final AtomicReference<Double> lastDistance = new AtomicReference<>(null);
    //Consumer nhận tọa độ từ RabbitMQ
//    @RabbitListener(queues = "train-location-queue")
    public void processTrain(TrainLocationMessage message) {
        try {

            Instant currentTime = Instant.now();
            Double currentDistance = message.getDistance();
            System.out.printf("🔍 Debug: lastDistance=%.2f, currentDistance=%.2f%n",
                    lastDistance.get(), currentDistance);


            // Lần đầu tiên nhận dữ liệu, khởi tạo thời gian
            if (lastUpdateTime.get() == null || lastDistance.get() == null) {
                lastUpdateTime.set(currentTime);
                lastDistance.set(currentDistance);
                System.out.println("🚆 Bắt đầu theo dõi tàu: " + message.getTrainCode());
                return;
            }

            // Tính thời gian giữa 2 lần cập nhật (giây)
            double deltaTime = (currentTime.toEpochMilli() - lastUpdateTime.get().toEpochMilli()) / 1000.0;
            if (deltaTime == 0) {
                System.out.println("⚠️ Thời gian cập nhật quá nhanh, bỏ qua tính toán.");
                return;
            }

            // Tính quãng đường đã đi
            double distanceTraveled = lastDistance.get() - currentDistance;
            if (distanceTraveled < 0) {
                System.out.printf("⚠️ Lỗi: Khoảng cách cập nhật bị ngược! (last=%.2f, current=%.2f)\n", lastDistance.get(), currentDistance);
                return;
            }

            // Tính tốc độ thực tế (km/h)
            double actualSpeed = (distanceTraveled / deltaTime) * 3600;

            // Cập nhật lại thời gian và khoảng cách
            lastUpdateTime.set(currentTime);
            lastDistance.set(currentDistance);

            // Debug log
            System.out.printf("[%s] 📡 Cập nhật tàu: %s - Delta Time: %.2f s - Quãng đường đi: %.2f km - Tốc độ thực tế: %.2f km/h - Quãng đường còn lại: %.2f km%n",
                    currentTime, message.getTrainCode(), deltaTime, distanceTraveled, actualSpeed, currentDistance);


//            System.out.println(message.getTrainCode());
//            System.out.println(message.getStationIdStart());
//            System.out.println(message.getStationIdEnd());
//            System.out.println(message.getAverageSpeed());
//            System.out.println(message.getDistance());
//            System.out.println(message.getX());
//            System.out.println(message.getY());
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
//        List<Station> stations = (List<Station>) redisTemplate.opsForValue().get(CACHE_KEY);

        Station nearestStation = null;
        double minDistance = Double.MAX_VALUE;

//        for (Station station : stations) {
//            double distance = Math.sqrt(Math.pow(station.getX() - trainX, 2) + Math.pow(station.getY() - trainY, 2));
//            if (distance < minDistance) {
//                minDistance = distance;
//                nearestStation = station;
//            }
//        }
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
