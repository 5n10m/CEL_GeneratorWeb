package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:CreativeTransform action.
 */
@Entity
@Table(name = "actions_creative_transform")
public class CreativeTransform extends Action {

    private CreativeTransform() {
        // Required by JAXB
    }

    @Override
    public int hashCode() {
        return this.getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreativeTransform);
    }

    @Override
    public String toString() {
        return "CreativeTransform";
    }
}
