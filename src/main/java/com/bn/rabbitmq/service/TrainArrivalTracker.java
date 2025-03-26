package com.bn.rabbitmq.service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicReference;

public class TrainArrivalTracker {
    private final AtomicReference<Instant> lastArrivalTime = new AtomicReference<>(null);

    public void updateTrainArrival(String stationName) {
        Instant currentTime = Instant.now();

        if (lastArrivalTime.get() == null) {
            System.out.printf("🚉 Tàu đến ga %s lần đầu vào lúc: %s%n", stationName, currentTime);
        } else {
            long deltaTime = currentTime.toEpochMilli() - lastArrivalTime.get().toEpochMilli();
            System.out.printf("🚉 Tàu cập nhật thời gian đến ga %s: %s (Sau %d giây)%n",
                    stationName, currentTime, deltaTime / 1000);
        }

        // Cập nhật thời gian đến ga
        lastArrivalTime.set(currentTime);
    }

    public static void main(String[] args) throws InterruptedException {
        TrainArrivalTracker tracker = new TrainArrivalTracker();

        // Giả lập tàu đến các ga khác nhau
        tracker.updateTrainArrival("Ga A");
        Thread.sleep(2000); // Giả lập 2 giây sau
        tracker.updateTrainArrival("Ga B");
        Thread.sleep(3000); // Giả lập 3 giây sau
        tracker.updateTrainArrival("Ga C");
    }
}
