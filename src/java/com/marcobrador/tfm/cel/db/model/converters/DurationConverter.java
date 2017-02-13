package com.marcobrador.tfm.cel.db.model.converters;

import javax.persistence.AttributeConverter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

/**
 * Converter for {@link javax.xml.datatype.Duration} objects.
 */
public class DurationConverter implements AttributeConverter<Duration, String> {
    public String convertToDatabaseColumn(Duration duration) {
        return duration == null ? "" : duration.toString();
    }

    public Duration convertToEntityAttribute(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return DatatypeFactory.newInstance().newDuration(value);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
