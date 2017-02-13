package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-core:TogetherWith complex type.
 */
@Entity
@DiscriminatorValue("together_with")
@Table(name = "together_with")
public class TogetherWith extends Fact {

    @Column
    @XmlAttribute
    private String withIPEntitiesFrom;

    private TogetherWith() {
        // Required by JAXB
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TogetherWith that = (TogetherWith) o;

        return withIPEntitiesFrom.equals(that.withIPEntitiesFrom);

    }

    @Override
    public int hashCode() {
        return withIPEntitiesFrom.hashCode();
    }

    @Override
    public String toString() {
        return "TogetherWith{" +
                "withIPEntitiesFrom: " + withIPEntitiesFrom +
                '}';
    }
}
