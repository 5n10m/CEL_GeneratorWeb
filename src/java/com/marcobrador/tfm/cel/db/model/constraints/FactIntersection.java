package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:FactIntersection complex type.
 */
@Entity
@DiscriminatorValue("fact_intersection")
@Table(name = "fact_intersections")
public class FactIntersection extends FactComposition {

    private FactIntersection() {
        super();
        // Required by JAXB
    }

    private FactIntersection(Builder builder) {
        super(builder);
    }

    public static class Builder extends FactComposition.Builder {
        @Override
        public FactComposition build() {
            if (facts.size() == 0) {
                throw new IllegalStateException("Facts cannot be empty");
            }
            return new FactIntersection(this);
        }
    }
}
