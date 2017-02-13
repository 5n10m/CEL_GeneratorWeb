package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-core:Match action.
 */
@Entity
@Table(name = "actions_match")
public class Match extends Action {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof Match);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Match{}";
    }
}
