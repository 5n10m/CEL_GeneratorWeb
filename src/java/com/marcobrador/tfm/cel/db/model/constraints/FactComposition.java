package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.*;

/**
 * Class that represents the cel-core:FactComposition type.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FactComposition extends Fact {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElements(value = {
            // CEL CORE constraints
            @XmlElement(name = "ActionEventRelatedFact", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = ActionEventRelatedFact.class),
            @XmlElement(name = "FactIntersection", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactIntersection.class),
            @XmlElement(name = "FactNegation", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactNegation.class),
            @XmlElement(name = "FactUnion", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = FactUnion.class),
            @XmlElement(name = "TogetherWith", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = TogetherWith.class),
            // CEL IPRE constraints
            @XmlElement(name = "AccessPolicy", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = AccessPolicy.class),
            @XmlElement(name = "DeliveryModality", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = DeliveryModality.class),
            @XmlElement(name = "Device", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Device.class),
            @XmlElement(name = "Language", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Language.class),
            @XmlElement(name = "Length", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Length.class),
            @XmlElement(name = "MaterialFormat", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = MaterialFormat.class),
            @XmlElement(name = "Means", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Means.class),
            @XmlElement(name = "Runs", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = Runs.class),
            @XmlElement(name = "SpatialContext", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = SpatialContext.class),
            @XmlElement(name = "TemporalContext", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = TemporalContext.class),
            // CEL RELE constraints
            @XmlElement(name = "feePerInterval", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = FeePerInterval.class),
            @XmlElement(name = "validityTimeMetered", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = ValidityTimeMetered.class),
            @XmlElement(name = "validityTimePeriodic", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS", type = ValidityTimePeriodic.class),
            @XmlElement(name = "validityInterval", namespace = "urn:mpeg:mpeg21:2003:01-REL-R-NS", type = ValidityInterval.class)
    })
    private Set<Fact> facts;

    FactComposition() {
        // Required by JAXB
    }

    FactComposition(Builder builder) {
        facts = builder.facts;
    }

    public static abstract class Builder {
        Set<Fact> facts;

        public Builder() {
            facts = new HashSet<Fact>();
        }

        public Builder addFact(Fact fact) {
            if (fact == null) {
                throw new IllegalArgumentException("fact must not be null");
            }
            facts.add(fact);
            return this;
        }

        public abstract FactComposition build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FactComposition that = (FactComposition) o;

        return facts != null ? facts.equals(that.facts) : that.facts == null;

    }

    @Override
    public int hashCode() {
        return facts != null ? facts.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append("{");
        for (Fact fact :
                facts) {
            builder.append("\n");
            builder.append(fact);
        }
        builder.append("\n}");
        return builder.toString();
    }
}
