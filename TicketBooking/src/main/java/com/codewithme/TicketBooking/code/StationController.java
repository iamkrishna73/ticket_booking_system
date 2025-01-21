package com.codewithme.TicketBooking.code;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stations")
public class StationController {


    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/init")
    public String initializeStations(@RequestBody Map<String, Map<String, Object>> stationData) {
        stationService.initializeStations(stationData.get("stations"));
        return "Stations initialized successfully";
    }

    @PostMapping("/add-default")
    public String addDefaultStations() {
        Map<String, Map<String, Object>> defaultStations = Map.of(
                "A", Map.of("startStation", true, "price", 0),
                "B", Map.of("price", 5),
                "C", Map.of("price", 15),
                "D", Map.of("lastStation", true, "price", 50),
                "E", Map.of("price", 20),
                "F", Map.of("price", 30),
                "G", Map.of("price", 25),
                "H", Map.of("price", 35),
                "I", Map.of("price", 40),
                "J", Map.of("price", 45),
                "K", Map.of("price", 55),
                "L", Map.of("price", 60),
                "M", Map.of("price", 65),
                "N", Map.of("price", 70),
                "O", Map.of("price", 75),
                "P", Map.of("price", 80),
                "Q", Map.of("price", 85),
                "R", Map.of("price", 90),
                "S", Map.of("price", 95),
                "T", Map.of("lastStation", true, "price", 100)
        );

        stationService.initializeStations(defaultStations);
        return "Default stations added successfully";
    }

    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }
}
