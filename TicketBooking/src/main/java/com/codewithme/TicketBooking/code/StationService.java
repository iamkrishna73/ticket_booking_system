package com.codewithme.TicketBooking.code;

import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    // Get all stations
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    // Get station by name
    public Optional<Station> getStationByName(String name) {
        return stationRepository.findByName(name);
    }

    // Load stations from JSON (if required)
    public void loadStationsFromJson(List<Station> stations) {
        stationRepository.saveAll(stations);
    }
}

