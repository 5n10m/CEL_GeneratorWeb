package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

/**
 * Class that represents the rel-r:validityInterval complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("validity_interval")
@Table(name = "validity_interval")
public class ValidityInterval extends Fact {

    @Column
    @XmlElement(name = "notBefore", namespace = "urn:mpeg:mpeg21:2003:01-REL-R-NS")
    private Date notBefore;

    @Column
    @XmlElement(name = "notAfter", namespace = "urn:mpeg:mpeg21:2003:01-REL-R-NS")
    private Date notAfter;

    private ValidityInterval() {
        // Required by JAXB
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValidityInterval that = (ValidityInterval) o;

        if (this.notBefore == null) {
            if (that.notBefore != null) {
                return false;
            }
        } else if (this.notBefore.getTime() != that.notBefore.getTime()) {
            return false;
        }
        if (this.notAfter == null) {
            if (that.notAfter != null) {
                return false;
            }
        } else if (this.notAfter.getTime() != that.notAfter.getTime()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = notBefore != null ? notBefore.hashCode() : 0;
        result = 31 * result + (notAfter != null ? notAfter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ValidityInterval{" +
                "notBefore=" + notBefore + ", notAfter=" + notAfter +
                '}';
    }
}
