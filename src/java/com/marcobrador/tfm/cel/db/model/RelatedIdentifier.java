package com.marcobrador.tfm.cel.db.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlValue;

/**
 * Class that represents the dii:RelatedIdentifier type.
 */
@Embeddable
public class RelatedIdentifier {

    @Column
    @XmlValue
    private String value;

    private RelatedIdentifier() {
        // Required by JAXB
    }

    public RelatedIdentifier(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelatedIdentifier that = (RelatedIdentifier) o;

        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return value;
    }
}
