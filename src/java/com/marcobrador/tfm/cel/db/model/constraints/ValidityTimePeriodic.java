package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.converters.DurationConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.Duration;
import java.util.Date;

/**
 * Class that represents the rel-sx:validityTimePeriodic complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("validity_time_periodic")
@Table(name = "validity_times_periodic")
public class ValidityTimePeriodic extends Fact {

    @Column
    @XmlElement
    private Date start;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlElement
    private Duration period;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlElement
    private Duration phase;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlElement
    private Duration duration;

    @Column
    @XmlElement
    private int periodCount;

    private ValidityTimePeriodic() {
        // Required by JAXB
    }

    public ValidityTimePeriodic(Builder builder) {
        start = builder.start;
        period = builder.period;
        phase = builder.phase;
        duration = builder.duration;
        periodCount = builder.periodCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidityTimePeriodic that = (ValidityTimePeriodic) o;

        if (periodCount != that.periodCount) {
            return false;
        }
        if ((start != null) ? (start.getTime() != that.start.getTime()) : (that.start != null)) {
            return false;
        }
        if ((period != null) ? !period.equals(that.period) : (that.period != null)) {
            return false;
        }
        if ((phase != null) ? !phase.equals(that.phase) : (that.phase != null)) {
            return false;
        }
        return duration != null ? duration.equals(that.duration) : that.duration == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (period != null ? period.hashCode() : 0);
        result = 31 * result + (phase != null ? phase.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + periodCount;
        return result;
    }

    @Override
    public String toString() {
        return "ValidityTimePeriodic{" +
                "start=" + start +
                ", period=" + period +
                ", phase=" + phase +
                ", duration=" + duration +
                ", periodCount=" + periodCount +
                '}';
    }

    public static class Builder {
        private Date start;
        private Duration period;
        private Duration phase;
        private Duration duration;
        private int periodCount;

        public Builder(Date start, Duration period, Duration duration) {
            if (start == null) {
                throw new IllegalArgumentException("start cannot be null");
            }
            if (period == null) {
                throw new IllegalArgumentException("period cannot be null");
            }
            if (duration == null) {
                throw new IllegalArgumentException("duration cannot be null");
            }
            this.start = start;
            this.period = period;
            this.duration = duration;
        }

        public Builder setPhase(Duration phase) {
            if (phase == null) {
                throw new IllegalArgumentException("phase cannot be null");
            }
            this.phase = phase;
            return this;
        }

        public Builder setPeriodCount(int periodCount) {
            if (periodCount <= 0) {
                throw new IllegalArgumentException("periodCount must be positive");
            }
            this.periodCount = periodCount;
            return this;
        }

        public ValidityTimePeriodic build() {
            return new ValidityTimePeriodic(this);
        }
    }
}
