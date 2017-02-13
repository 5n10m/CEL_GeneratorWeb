package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:FactUnion complex type.
 */
@Entity
@DiscriminatorValue("fact_union")
@Table(name = "fact_unions")
public class FactUnion extends FactComposition {

    private FactUnion() {
        super();
        // Required by JAXB
    }

    private FactUnion(Builder builder) {
        super(builder);
    }

    public static class Builder extends FactComposition.Builder {
        @Override
        public FactComposition build() {
            if (facts.size() == 0) {
                throw new IllegalStateException("Facts cannot be empty");
            }
            return new FactUnion(this);
        }
    }
}
