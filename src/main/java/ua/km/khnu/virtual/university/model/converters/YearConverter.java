package ua.km.khnu.virtual.university.model.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;

/**
 * @author Igor Rybak
 */
@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year year) {
        if (year == null) return null;
        return year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer year) {
        if (year == null) return null;
        return Year.of(year);
    }
}
