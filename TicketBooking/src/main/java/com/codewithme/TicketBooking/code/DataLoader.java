package com.codewithme.TicketBooking.code;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public void run(String... args) {
        // Check if stations are already loaded
        if (stationRepository.count() > 0) {
            return; // Avoid duplicate loading
        }

        // Create a list of stations
        List<Station> stations = new ArrayList<>();
        stations.add(new Station("A", 0, true, false));  // Start station
        stations.add(new Station("B", 5, false, false));
        stations.add(new Station("C", 15, false, false));
        stations.add(new Station("D", 50, false, true)); // Last station

        // Add more stations to make it 20
        for (int i = 5; i <= 20; i++) {
            stations.add(new Station("Station" + i, i * 10, false, i == 20)); // Last station for Station20
        }

        // Save stations to the database
        stationRepository.saveAll(stations);

        System.out.println("Station data loaded successfully!");
    }
}


