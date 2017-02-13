package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:Transform action.
 */
@Entity
@Table(name = "actions_transform")
public class Transform extends Action {
    @Override
    public int hashCode() {
        return getClass().toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Transform;
    }

    @Override
    public String toString() {
        return "Transform";
    }
}
