package com.marcobrador.tfm.cel.db.model.actions;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents the cel-pane:Notify complex type.
 */
@Entity
@Table(name = "actions_notify")
public class Notify extends Action {

    @Embedded
    @XmlElement(name = "Recipient", namespace = "urn:mpeg:mpeg21:cel:pane:2015")
    private Recipient recipient;

    @Embedded
    @XmlElement(name = "About", namespace = "urn:mpeg:mpeg21:cel:pane:2015")
    private About about;

    private Notify() {
        // Required by JAXB
    }

    @Override
    public int hashCode() {
        return recipient.partyRef.hashCode() + about.actRef.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Notify)) {
            return false;
        }
        Notify other = (Notify) obj;
        return this.recipient.partyRef.equals(other.recipient.partyRef) && this.about.actRef.equals(other.about.actRef);
    }

    @Override
    public String toString() {
        return "Notify{" +
                "recipient=" + recipient.partyRef +
                ", about=" + about.actRef +
                '}';
    }

    @Embeddable
    private static class Recipient {

        @Column
        @XmlAttribute
        private String partyRef;
    }

    @Embeddable
    private static class About {

        @Column
        @XmlAttribute
        private String actRef;
    }

}
