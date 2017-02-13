package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:Fixate action.
 */
@Entity
@Table(name = "actions_fixate")
public class Fixate extends Action {

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Fixate;
    }

    @Override
    public String toString() {
        return "Fixate";
    }
}
