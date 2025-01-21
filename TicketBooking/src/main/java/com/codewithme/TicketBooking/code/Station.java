package com.codewithme.TicketBooking.code;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



import jakarta.persistence.*;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "is_start_station", nullable = false)
    private boolean isStartStation = false;

    @Column(name = "is_last_station", nullable = false)
    private boolean isLastStation = false;

    public Station() {
    }

    public Station(String name, int price, boolean isStartStation, boolean isLastStation) {
        this.name = name;
        this.price = price;
        this.isStartStation = isStartStation;
        this.isLastStation = isLastStation;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStartStation() {
        return isStartStation;
    }

    public void setStartStation(boolean startStation) {
        isStartStation = startStation;
    }

    public boolean isLastStation() {
        return isLastStation;
    }

    public void setLastStation(boolean lastStation) {
        isLastStation = lastStation;
    }
}

