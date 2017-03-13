package com.marcobrador.tfm.cel.db.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class that represents the cel-core:ObligactionType complex type.
 */
@Entity
@DiscriminatorValue("obligation")
public class Obligation extends DeonticStructuredClause  implements Serializable{

    private Obligation() {
        // Required by JAXB
    }

    protected Obligation(Builder builder) {
        super(builder);
    }

    public static final class Builder extends DeonticStructuredClause.Builder {

        public Builder(String id, Subject subject, Act act) {
            super(id, subject, act);
        }

        public Obligation build() {
            return new Obligation(this);
        }
    }
    
    public String getType() {
        return "Obligation";
    }
}
