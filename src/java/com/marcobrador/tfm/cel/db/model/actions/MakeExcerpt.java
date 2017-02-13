package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:MakeExcerpt action.
 */
@Entity
@Table(name = "actions_make_excerpt")
public class MakeExcerpt extends Action {
    @Override
    public int hashCode() {
        return getClass().toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof MakeExcerpt;
    }

    @Override
    public String toString() {
        return "MakeExcerpt";
    }
}
