package com.marcobrador.tfm.cel.db.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class that represents the cel-core:PermissionType complex type.
 */
@Entity
@DiscriminatorValue("permission")
public class Permission extends DeonticStructuredClause {

    protected Permission() {
        // Required by JAXB
    }

    protected Permission(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeonticStructuredClause.Builder {

        public Builder(String id, Subject subject, Act act) {
            super(id, subject, act);
        }

        public Permission build() {
            return new Permission(this);
        }
    }
}
