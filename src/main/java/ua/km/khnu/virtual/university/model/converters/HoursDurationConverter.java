package ua.km.khnu.virtual.university.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * @author Igor Rybak
 */
@Converter
public class HoursDurationConverter implements AttributeConverter<Duration,Integer> {
    @Override
    public Integer convertToDatabaseColumn(Duration attribute) {
        return null;
    }

    @Override
    public Duration convertToEntityAttribute(Integer dbData) {
        return null;
    }
}
