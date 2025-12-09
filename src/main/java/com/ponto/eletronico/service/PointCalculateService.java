package com.ponto.eletronico.service;

import com.ponto.eletronico.entity.PointRecord;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@Service
public class PointCalculateService {

    public Duration calculateHours(PointRecord data) {
        LocalTime start = data.getStartTime();
        LocalTime end = data.getEndTime();

        if (start == null || end == null) {
            return Duration.ZERO;
        }

        Duration totalHours = Duration.between(start, end);


        return totalHours;
    }

}
