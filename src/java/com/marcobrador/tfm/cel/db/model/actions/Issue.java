package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the rel-r:issue action.
 */
@Entity
@Table(name = "actions_issue")
public class Issue extends Action {

    private Issue() {
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
        return (obj instanceof Issue);
    }

    @Override
    public String toString() {
        return "Issue";
    }
}
