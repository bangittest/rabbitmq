package com.bn.rabbitmq.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "trip_detail")
public class TripDetail implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "arrival_time_schedule")
    private Timestamp arrivalTimeSchedule;

    @Column(name = "departure_time_schedule")
    private Timestamp departureTimeSchedule;

    @Column(name = "free_time_schedule")
    private Timestamp freeTimeSchedule;

    @Column(name = "arrival_time_actual")
    private Timestamp arrivalTimeActual;

    @Column(name = "departure_time_actual")
    private Timestamp departureTimeActual;

    @Column(name = "free_time_actual")
    private Timestamp freeTimeActual;

    @Column(name = "distance")
    private Integer distance = 0;

    @Column(name = "note")
    private String note;

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private Long createdBy;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Timestamp getArrivalTimeSchedule() {
        return arrivalTimeSchedule;
    }

    public void setArrivalTimeSchedule(Timestamp arrivalTimeSchedule) {
        this.arrivalTimeSchedule = arrivalTimeSchedule;
    }

    public Timestamp getDepartureTimeSchedule() {
        return departureTimeSchedule;
    }

    public void setDepartureTimeSchedule(Timestamp departureTimeSchedule) {
        this.departureTimeSchedule = departureTimeSchedule;
    }

    public Timestamp getFreeTimeSchedule() {
        return freeTimeSchedule;
    }

    public void setFreeTimeSchedule(Timestamp freeTimeSchedule) {
        this.freeTimeSchedule = freeTimeSchedule;
    }

    public Timestamp getArrivalTimeActual() {
        return arrivalTimeActual;
    }

    public void setArrivalTimeActual(Timestamp arrivalTimeActual) {
        this.arrivalTimeActual = arrivalTimeActual;
    }

    public Timestamp getDepartureTimeActual() {
        return departureTimeActual;
    }

    public void setDepartureTimeActual(Timestamp departureTimeActual) {
        this.departureTimeActual = departureTimeActual;
    }

    public Timestamp getFreeTimeActual() {
        return freeTimeActual;
    }

    public void setFreeTimeActual(Timestamp freeTimeActual) {
        this.freeTimeActual = freeTimeActual;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TripDetail() {
    }

    public TripDetail(Long id, Long tripId, Long stationId, Integer sortOrder, Timestamp arrivalTimeSchedule, Timestamp departureTimeSchedule, Timestamp freeTimeSchedule, Timestamp arrivalTimeActual, Timestamp departureTimeActual, Timestamp freeTimeActual, Integer distance, String note, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {
        this.id = id;
        this.tripId = tripId;
        this.stationId = stationId;
        this.sortOrder = sortOrder;
        this.arrivalTimeSchedule = arrivalTimeSchedule;
        this.departureTimeSchedule = departureTimeSchedule;
        this.freeTimeSchedule = freeTimeSchedule;
        this.arrivalTimeActual = arrivalTimeActual;
        this.departureTimeActual = departureTimeActual;
        this.freeTimeActual = freeTimeActual;
        this.distance = distance;
        this.note = note;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
