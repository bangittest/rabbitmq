//package com.bn.rabbitmq.controller;
//
//import com.bn.rabbitmq.domain.Station;
//import com.bn.rabbitmq.service.TripService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController()
//public class StationController {
//    private final TripService tripService;
//
//    public StationController(TripService tripService) {
//        this.tripService = tripService;
//    }
//    @GetMapping("/station")
//    public void  psh(){
//        tripService.updateStatus();
//    }
//
//    @GetMapping("/list")
//    public List<Station> list(){
//
//        return tripService.getProductList();
//    }
//
//}
