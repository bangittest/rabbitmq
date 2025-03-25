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
@Table(name = "schedule")
public class Schedule implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "type")
    private Integer type;

    @Column(name = "train_id")
    private Long trainId;

    @Column(name = "departure_time")
    private Timestamp departureTime;

    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @Column(name = "is_interval")
    private Integer isInterval;

    @Column(name = "is_daily")
    private Integer isDaily;

    @Column(name = "is_weekly")
    private Integer isWeekly;

    @Column(name = "monday")
    private Timestamp monday;

    @Column(name = "tuesday")
    private Timestamp tuesday;

    @Column(name = "wednesday")
    private Timestamp wednesday;

    @Column(name = "thursday")
    private Timestamp thursday;

    @Column(name = "friday")
    private Timestamp friday;

    @Column(name = "saturday")
    private Timestamp saturday;

    @Column(name = "sunday")
    private Timestamp sunday;

    @Column(name = "frequency")
    private Integer frequency;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getIsInterval() {
        return isInterval;
    }

    public void setIsInterval(Integer isInterval) {
        this.isInterval = isInterval;
    }

    public Integer getIsDaily() {
        return isDaily;
    }

    public void setIsDaily(Integer isDaily) {
        this.isDaily = isDaily;
    }

    public Integer getIsWeekly() {
        return isWeekly;
    }

    public void setIsWeekly(Integer isWeekly) {
        this.isWeekly = isWeekly;
    }

    public Timestamp getMonday() {
        return monday;
    }

    public void setMonday(Timestamp monday) {
        this.monday = monday;
    }

    public Timestamp getTuesday() {
        return tuesday;
    }

    public void setTuesday(Timestamp tuesday) {
        this.tuesday = tuesday;
    }

    public Timestamp getWednesday() {
        return wednesday;
    }

    public void setWednesday(Timestamp wednesday) {
        this.wednesday = wednesday;
    }

    public Timestamp getThursday() {
        return thursday;
    }

    public void setThursday(Timestamp thursday) {
        this.thursday = thursday;
    }

    public Timestamp getFriday() {
        return friday;
    }

    public void setFriday(Timestamp friday) {
        this.friday = friday;
    }

    public Timestamp getSaturday() {
        return saturday;
    }

    public void setSaturday(Timestamp saturday) {
        this.saturday = saturday;
    }

    public Timestamp getSunday() {
        return sunday;
    }

    public void setSunday(Timestamp sunday) {
        this.sunday = sunday;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
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

    public Schedule() {
    }

    public Schedule(Long id, String code, Integer status, Integer isActive, Integer type, Long trainId, Timestamp departureTime, Timestamp arrivalTime, Integer isInterval, Integer isDaily, Integer isWeekly, Timestamp monday, Timestamp tuesday, Timestamp wednesday, Timestamp thursday, Timestamp friday, Timestamp saturday, Timestamp sunday, Integer frequency, String note, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.isActive = isActive;
        this.type = type;
        this.trainId = trainId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.isInterval = isInterval;
        this.isDaily = isDaily;
        this.isWeekly = isWeekly;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.frequency = frequency;
        this.note = note;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
