package com.marcobrador.tfm.cel.db.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class representing the cel-core:Body complex type.
 */
@Embeddable
public class Body {

    // TODO: missing textual part

    @Embedded
    @XmlElement(name="OperativePart", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private OperativePart operativePart;

    private Body() {
        // Required by JAXB
    }

    public Body(OperativePart operativePart) {
        this.operativePart = operativePart;
    }

    public OperativePart getOperativePart() {
        return operativePart;
    }

    @Override
    public String toString() {
        return operativePart.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Body)) {
            return false;
        }
        Body other = (Body) obj;
        return this.operativePart.equals(other.operativePart);
    }
}
