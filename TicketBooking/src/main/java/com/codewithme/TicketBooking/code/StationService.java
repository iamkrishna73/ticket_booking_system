package com.codewithme.TicketBooking.code;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void initializeStations(Map<String, Map<String, Object>> stations) {
        stations.forEach((name, details) -> {
            Station station = new Station();
            station.setName(name);
            station.setPrice((int) details.getOrDefault("price", 0));
            stationRepository.save(station);
        });
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}

