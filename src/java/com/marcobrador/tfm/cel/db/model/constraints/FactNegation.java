package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:FactNegation complex type.
 */
@Entity
@DiscriminatorValue("fact_negation")
@Table(name = "fact_negations")
public class FactNegation extends FactComposition {

    private FactNegation() {
        super();
        // Required by JAXB
    }

    private FactNegation(Builder builder) {
        super(builder);
    }

    public static class Builder extends FactComposition.Builder {
        @Override
        public FactComposition build() {
            if (facts.size() != 1) {
                throw new IllegalStateException("invalid number of facts: " + facts.size());
            }
            return new FactNegation(this);
        }
    }
}
