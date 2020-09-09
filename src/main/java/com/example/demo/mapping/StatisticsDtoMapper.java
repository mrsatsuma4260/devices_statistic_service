package com.example.demo.mapping;

import com.example.demo.domain.Job;
import com.example.demo.dto.jobs.StatisticsResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StatisticsDtoMapper implements BaseMapper<Job, StatisticsResponseDto> {

    @Override
    public StatisticsResponseDto map(final Job source) {
        final StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();
        statisticsResponseDto.setId(source.getJobId());
        statisticsResponseDto.setDevice(source.getDevice());
        statisticsResponseDto.setUser(source.getUser());
        statisticsResponseDto.setType(source.getType().getValue());
        statisticsResponseDto.setAmount(source.getAmount());
        statisticsResponseDto.setTime(source.getTime());
        return statisticsResponseDto;
    }
}
