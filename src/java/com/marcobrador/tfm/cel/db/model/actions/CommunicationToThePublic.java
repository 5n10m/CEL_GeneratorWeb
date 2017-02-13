package com.marcobrador.tfm.cel.db.model.actions;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents the cel-ipre:CommunicationToThePublic action.
 */
@Entity
@Table(name = "actions_communication_to_the_public")
public class CommunicationToThePublic extends Action {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof CommunicationToThePublic);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "CommunicationToThePublic{}";
    }
}
