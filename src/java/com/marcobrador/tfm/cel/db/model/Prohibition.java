package com.marcobrador.tfm.cel.db.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class that represents the cel-core:Prohibition complex type.
 */
@Entity
@DiscriminatorValue("prohibition")
public class Prohibition extends DeonticStructuredClause {

    private Prohibition() {
        // Required by JAXB
    }

    private Prohibition(Builder builder) {
        super(builder);
    }

    public static final class Builder extends DeonticStructuredClause.Builder {

        public Builder(String id, Subject subject, Act act) {
            super(id, subject, act);
        }

        public Prohibition build() {
            return new Prohibition(this);
        }
    }
}
