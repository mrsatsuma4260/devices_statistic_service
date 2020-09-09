package com.example.demo.dto.jobs;

import com.example.demo.domain.JobType;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobsRequestDto {

    @Valid
    @NotEmpty
    @XmlElement(name = "job")
    private List<JobDto> jobs;

    public List<JobDto> getJobs() {
        return jobs;
    }

    public void setJobs(final List<JobDto> jobs) {
        this.jobs = jobs;
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static final class JobDto {

        @NotNull
        @XmlAttribute(name = "id")
        private Long id;

        @NotNull(message = "Unknown type")
        @XmlElement(name = "type")
        private JobTypeDto type;

        @NotBlank
        @XmlElement(name = "user")
        private String user;

        @NotBlank
        @XmlElement(name = "device")
        private String device;

        @Min(1)
        @XmlElement(name = "amount")
        private Long amount;

        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public JobTypeDto getType() {
            return type;
        }

        public void setType(final JobTypeDto type) {
            this.type = type;
        }

        public String getUser() {
            return user;
        }

        public void setUser(final String user) {
            this.user = user;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(final String device) {
            this.device = device;
        }

        public Long getAmount() {
            return amount;
        }

        public void setAmount(final Long amount) {
            this.amount = amount;
        }
    }

    @XmlType
    @XmlEnum
    public enum JobTypeDto {
        @XmlEnumValue("print") PRINT(JobType.PRINT),
        @XmlEnumValue("copy") COPY(JobType.COPY),
        @XmlEnumValue("scan") SCAN(JobType.SCAN),
        @XmlEnumValue("fax") FAX(JobType.FAX);

        private final JobType value;

        JobTypeDto(final JobType value) {
            this.value = value;
        }

        public JobType getValue() {
            return value;
        }
    }
}
