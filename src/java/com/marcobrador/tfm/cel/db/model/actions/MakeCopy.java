package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:MakeCopy action.
 */
@Entity
@Table(name = "actions_make_copy")
public class MakeCopy extends Action {

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof MakeCopy;
    }

    @Override
    public String toString() {
        return "MakeCopy";
    }
}
