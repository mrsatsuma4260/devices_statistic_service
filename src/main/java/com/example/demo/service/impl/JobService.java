package com.example.demo.service.impl;

import com.example.demo.domain.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.specification.JobByDevice;
import com.example.demo.repository.specification.JobByTimeFrom;
import com.example.demo.repository.specification.JobByTimeTo;
import com.example.demo.repository.specification.JobByType;
import com.example.demo.repository.specification.JobByUser;
import com.example.demo.service.IJobService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
class JobService implements IJobService {

    private final JobRepository jobRepository;

    JobService(final JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    @Transactional
    public Map<String, Long> store(final List<Job> jobs, final LocalDateTime time) {
        jobs.forEach(job -> job.setTime(time));
        final List<Job> savedJobs = jobRepository.saveAll(jobs);
        return savedJobs.stream().collect(
                Collectors.groupingBy(Job::getUser, Collectors.summingLong(Job::getAmount)));
    }

    @Override
    @Transactional
    public List<Job> find(final String device,
                          final String user,
                          final String type,
                          final LocalDateTime from,
                          final LocalDateTime to) {

        final Specification<Job> specification = Specification.where(new JobByDevice(device))
                .and(new JobByUser(user))
                .and(new JobByType(type))
                .and(new JobByTimeFrom(from))
                .and(new JobByTimeTo(to));

        return jobRepository.findAll(specification, Sort.by(Sort.Direction.DESC, "time"));
    }
}
