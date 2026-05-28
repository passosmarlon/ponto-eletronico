package com.ponto.eletronico.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;
@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration != null ? duration.toSeconds() : 0L;
    }

    @Override
    public Duration convertToEntityAttribute(Long value) {
        return value != null ? Duration.ofSeconds(value) : Duration.ZERO;
    }
}
