package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the rel-mx:modify complex type.
 */
@Entity
@Table(name = "actions_modify")
public class Modify extends Action {
    @Override
    public int hashCode() {
        return getClass().toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Modify;
    }

    @Override
    public String toString() {
        return "Modify";
    }
}
