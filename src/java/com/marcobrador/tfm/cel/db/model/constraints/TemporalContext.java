package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.Calendar;
import java.util.Date;

/**
 * Class that represents the cel-ipre:TemporalContext complex type.
 */
@Entity
@DiscriminatorValue("temporal_context")
@Table(name = "temporal_contexts")
public class TemporalContext extends Fact {

    @Column
    @XmlAttribute
    private Date afterDate;

    @Column
    @XmlAttribute
    private Date beforeDate;

    private TemporalContext() {
        // Required by JAXB
    }

    public TemporalContext(Date afterDate, Date beforeDate) {
        if (afterDate == null) {
            throw new IllegalArgumentException("afterDate cannot be null");
        }
        // Cut off milliseconds information from input data to avoid issues with MySQL databas
        this.afterDate = removeMilliseconds(afterDate);
        if (beforeDate == null) {
            throw new IllegalArgumentException("beforeDate cannot be null");
        }
        this.beforeDate = removeMilliseconds(beforeDate);
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public TemporalContext clone() {
        TemporalContext clone = new TemporalContext();
        clone.afterDate = this.afterDate;
        clone.beforeDate = this.beforeDate;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TemporalContext that = (TemporalContext) o;

        if (afterDate.getTime() != that.afterDate.getTime()) {
            return false;
        }
        return beforeDate.getTime() == that.beforeDate.getTime();

    }

    @Override
    public int hashCode() {
        int result = afterDate.hashCode();
        result = 31 * result + beforeDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TemporalContext{" +
                "afterDate=" + afterDate +
                ", beforeDate=" + beforeDate +
                '}';
    }

    private Date removeMilliseconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
