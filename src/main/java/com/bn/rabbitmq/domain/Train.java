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
@Table(name = "train")

public class Train implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = true, unique = true)
    private String code;

    @Column(name = "status")
    private Integer status;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "type")
    private Integer type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "company")
    private String company;

    @Column(name = "max_speed")
    private Integer maxSpeed;

    @Column(name = "num_of_carriages")
    private Integer numOfCarriages;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "length")
    private Float length;

    @Column(name = "width")
    private Float width;

    @Column(name = "height")
    private Float height;

    @Column(name = "operating_region")
    private String operatingRegion;

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getNumOfCarriages() {
        return numOfCarriages;
    }

    public void setNumOfCarriages(Integer numOfCarriages) {
        this.numOfCarriages = numOfCarriages;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getOperatingRegion() {
        return operatingRegion;
    }

    public void setOperatingRegion(String operatingRegion) {
        this.operatingRegion = operatingRegion;
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

    public Train() {
    }

    public Train(Long id, String code, Integer status, Integer isActive, Integer type, Integer capacity, String company, Integer maxSpeed, Integer numOfCarriages, String color, Float weight, Float length, Float width, Float height, String operatingRegion, String note, Long createdBy, Timestamp createdAt, Long updatedBy, Timestamp updatedAt) {
        this.id = id;
        this.code = code;
        this.status = status;
        this.isActive = isActive;
        this.type = type;
        this.capacity = capacity;
        this.company = company;
        this.maxSpeed = maxSpeed;
        this.numOfCarriages = numOfCarriages;
        this.color = color;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.operatingRegion = operatingRegion;
        this.note = note;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}