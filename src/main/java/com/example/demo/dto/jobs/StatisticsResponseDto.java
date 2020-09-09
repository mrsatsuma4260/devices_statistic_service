package com.example.demo.dto.jobs;

import com.example.demo.utils.StatisticsLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class StatisticsResponseDto {

    @JsonProperty(value = "jobId")
    private Long id;

    @JsonProperty(value = "device")
    private String device;

    @JsonProperty(value = "user")
    private String user;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "amount")
    private Long amount;

    @JsonSerialize(using = StatisticsLocalDateTimeSerializer.class)
    @JsonProperty(value = "time")
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(final String device) {
        this.device = device;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(final LocalDateTime time) {
        this.time = time;
    }
}
