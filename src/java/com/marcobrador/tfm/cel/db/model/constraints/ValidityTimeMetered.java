package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.converters.DurationConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.Duration;
import java.util.Date;

/**
 * Class that represents the rel-sx:validityTimeMetered complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("validity_time_metered")
@Table(name = "validity_times_metered")
public class ValidityTimeMetered extends Fact {

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlElement(name = "duration", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS")
    private Duration duration;

    private ValidityTimeMetered() {
        // Required by JAXB
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidityTimeMetered that = (ValidityTimeMetered) o;

        return duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return duration.hashCode();
    }

    @Override
    public String toString() {
        return "ValidityTimeMetered{" +
                "duration=" + duration +
                '}';
    }
}
