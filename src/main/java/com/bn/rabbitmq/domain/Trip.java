package com.bn.rabbitmq.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "trip")
public class Trip implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "route_id")
    private Long routeId;

    @Column(name = "start_station_id")
    private Long startStationId;

    @Column(name = "end_station_id")
    private Long endStationId;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "arrival_time_schedule")
    private Timestamp arrivalTimeSchedule;

    @Column(name = "departure_time_schedule")
    private Timestamp departureTimeSchedule;

    @Column(name = "arrival_time_actual")
    private Timestamp arrivalTimeActual;

    @Column(name = "departure_time_actual")
    private Timestamp departureTimeActual;

    @Column(name = "total_time_schedule")
    private Integer totalTimeSchedule;

    @Column(name = "total_time_actual")
    private Integer totalTimeActual;

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

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(Long startStationId) {
        this.startStationId = startStationId;
    }

    public Long getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(Long endStationId) {
        this.endStationId = endStationId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
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

    public Integer getTotalTimeSchedule() {
        return totalTimeSchedule;
    }

    public void setTotalTimeSchedule(Integer totalTimeSchedule) {
        this.totalTimeSchedule = totalTimeSchedule;
    }

    public Integer getTotalTimeActual() {
        return totalTimeActual;
    }

    public void setTotalTimeActual(Integer totalTimeActual) {
        this.totalTimeActual = totalTimeActual;
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

    public Trip() {
    }

    public Trip(Long id, String code, String name, Integer status, Long routeId, Long startStationId, Long endStationId, Long scheduleId, Timestamp arrivalTimeSchedule, Timestamp departureTimeSchedule, Timestamp arrivalTimeActual, Timestamp departureTimeActual, Integer totalTimeSchedule, Integer totalTimeActual, String note, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
        this.routeId = routeId;
        this.startStationId = startStationId;
        this.endStationId = endStationId;
        this.scheduleId = scheduleId;
        this.arrivalTimeSchedule = arrivalTimeSchedule;
        this.departureTimeSchedule = departureTimeSchedule;
        this.arrivalTimeActual = arrivalTimeActual;
        this.departureTimeActual = departureTimeActual;
        this.totalTimeSchedule = totalTimeSchedule;
        this.totalTimeActual = totalTimeActual;
        this.note = note;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
