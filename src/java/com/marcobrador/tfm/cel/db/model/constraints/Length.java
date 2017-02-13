package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.converters.DurationConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.datatype.Duration;

/**
 * Class that represents the cel-ipre:Length complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("length")
@Table(name = "lengths")
public class Length extends Fact {

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlAttribute
    private Duration maxLength;

    private Length() {
        // Required by JAXB
    }

    public Length(Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("duration cannot be null");
        }
        maxLength = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Length length = (Length) o;

        return !(maxLength != null ? !maxLength.equals(length.maxLength) : length.maxLength != null);

    }

    @Override
    public int hashCode() {
        return maxLength != null ? maxLength.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Length{" +
                "maxLength=" + maxLength +
                '}';
    }
}
