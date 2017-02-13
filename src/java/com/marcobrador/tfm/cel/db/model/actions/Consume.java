package com.marcobrador.tfm.cel.db.model.actions;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:Consume complex type.
 */
@Entity
@Table(name = "actions_consume")
public class Consume extends Action {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Consume)) {
            return false;
        }
        Consume other = (Consume) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return new Integer(id).hashCode();
    }

    @Override
    public String toString() {
        return "Consume";
    }
}
