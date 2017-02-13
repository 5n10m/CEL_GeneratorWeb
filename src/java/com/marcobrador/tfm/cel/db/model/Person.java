package com.marcobrador.tfm.cel.db.model;

import javax.persistence.*;

/**
 * Class that represents the cel-core:Person complex-type.
 */
@Entity
@Table(name = "persons")
public class Person extends PartyBasicGroup {

    private Person() {
        // Required by JAXB
    }

    @Override
    public String getType() {
        return Person.class.getSimpleName();
    }

    private Person(Builder builder) {
        super(builder);
    }

    public static final class Builder extends PartyBasicGroup.Builder {

        public Builder(String name) {
            super(name);
        }

        public Person build() {
            return new Person(this);
        }
    }
}

