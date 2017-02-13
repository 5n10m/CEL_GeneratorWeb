package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the rel-mx:enhance action.
 */
@Entity
@Table(name = "actions_enhance")
public class Enhance extends Action {

    private Enhance() {
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
        return (obj instanceof Enhance);
    }

    @Override
    public String toString() {
        return "Enhance";
    }
}
