package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDateTime;

@Entity
@Table(name = "jobs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"job_id", "device"})
})
public class Job {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "job_id", nullable = false)
    private Long jobId;

    @Column(name = "device", nullable = false)
    private String device;

    @Column(name = "user", nullable = false)
    private String user;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type", nullable = false)
    private JobType type;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(final Long jobId) {
        this.jobId = jobId;
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

    public JobType getType() {
        return type;
    }

    public void setType(final JobType type) {
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
