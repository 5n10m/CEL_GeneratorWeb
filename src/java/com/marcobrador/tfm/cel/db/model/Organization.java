package com.marcobrador.tfm.cel.db.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:Organization complex type.
 */
@Entity
@Table(name = "organizations")
public class Organization extends PartyBasicGroup {
    
    private static PartyBasicGroup Signatory;
    
    private Organization() {
        // Required by JAXB
    }

    @Override
    public String getType() {
        return Organization.class.getSimpleName();
    }

    private Organization(Builder builder) {
        super(builder);
    }

    public static final class Builder extends PartyBasicGroup.Builder {

        public Builder(String name) {
            super(name);
        }
        public Builder setSignatory(PartyBasicGroup pbg) {
            Signatory = pbg;
            return this;
        }

        @Override
        public Organization build() {
            return new Organization(this);
        }
    }
}
