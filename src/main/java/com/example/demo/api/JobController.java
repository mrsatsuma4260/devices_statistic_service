package com.example.demo.api;

import com.example.demo.domain.Job;
import com.example.demo.dto.jobs.JobsRequestDto;
import com.example.demo.dto.jobs.StatisticsResponseDto;
import com.example.demo.mapping.JobMapper;
import com.example.demo.mapping.StatisticsDtoMapper;
import com.example.demo.service.IJobService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class JobController {

    private final IJobService jobService;
    private final JobMapper jobMapper;
    private final StatisticsDtoMapper statisticsDtoMapper;

    public JobController(final IJobService jobService,
                         final JobMapper jobMapper,
                         final StatisticsDtoMapper statisticsDtoMapper) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
        this.statisticsDtoMapper = statisticsDtoMapper;
    }

    @PostMapping(value = "/jobs",
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Map<String, String> addJobs(@RequestBody @Valid JobsRequestDto request) {
        final LocalDateTime time = LocalDateTime.now();
        final List<Job> jobs = jobMapper.map(request.getJobs());
        final Map<String, Long> result = jobService.store(jobs, time);
        return result.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
    }

    @GetMapping(value = "/statistics")
    List<StatisticsResponseDto> statistics(
            @RequestParam(value = "device", required = false) final String device,
            @RequestParam(value = "user", required = false) final String user,
            @RequestParam(value = "type", required = false) final String type,
            @RequestParam(value = "timeFrom", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime from,
            @RequestParam(value = "timeTo", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime to) {

        final List<Job> result = jobService.find(device, user, type, from, to);
        return statisticsDtoMapper.map(result);
    }
}
