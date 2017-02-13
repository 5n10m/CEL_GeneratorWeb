package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-core:Provide action.
 */
@Entity
@Table(name = "actions_provide")
public class Provide extends Action {

    @Column
    @XmlAttribute
    private String recipients;

    private Provide() {
        // Required by JAXB
    }

    public Provide(String recipients) {
        this.recipients = recipients;
    }

    @Override
    public int hashCode() {
        return recipients.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Provide)) {
            return false;
        }
        Provide other = (Provide) obj;
        return this.recipients.equals(other.recipients);
    }

    @Override
    public String toString() {
        return "Provide to " + recipients;
    }
}
