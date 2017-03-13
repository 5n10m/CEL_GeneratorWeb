package com.marcobrador.tfm.cel.db.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class that represents the cel-core:Prohibition complex type.
 */
@Entity
@DiscriminatorValue("prohibition")
public class Prohibition extends DeonticStructuredClause implements Serializable {

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

    public String getType() {
        return "Prohibition";
    }
}
