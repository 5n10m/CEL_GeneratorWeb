package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.converters.DurationConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.datatype.Duration;

/**
 * Class that represents the cel-core:ActionEventRelatedFact complex type.
 */
@Entity
@DiscriminatorValue("action_event_related_fact")
@Table(name = "action_event_related_facts")
public class ActionEventRelatedFact extends Fact {

    public enum Status {
        Started,
        Done
    }

    @Column
    @XmlAttribute
    private String ref;

    @Column
    @XmlAttribute
    private Status status;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlAttribute
    private Duration duration;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlAttribute
    private Duration withDelay;

    private ActionEventRelatedFact() {
        // Required by JAXB
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionEventRelatedFact that = (ActionEventRelatedFact) o;

        if (!ref.equals(that.ref)) return false;
        if (status != that.status) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        return withDelay != null ? withDelay.equals(that.withDelay) : that.withDelay == null;

    }

    @Override
    public int hashCode() {
        int result = ref.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (withDelay != null ? withDelay.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActionEventRelatedFact{" +
                "ref='" + ref + '\'' +
                ", status=" + status +
                ", duration=" + duration +
                ", withDelay=" + withDelay +
                '}';
    }
}
