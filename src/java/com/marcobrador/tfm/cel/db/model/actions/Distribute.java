package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:Distribute action.
 */
@Entity
@Table(name = "actions_distribute")
public class Distribute extends Action {

    @Override
    public int hashCode() {
        return getClass().toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Distribute;
    }

    @Override
    public String toString() {
        return "Distribute";
    }
}
