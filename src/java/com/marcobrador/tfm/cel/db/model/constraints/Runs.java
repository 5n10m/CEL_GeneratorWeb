package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:Runs complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@DiscriminatorValue("runs")
@Table(name = "runs")
public class Runs extends Fact {

    @Column
    @XmlAttribute
    private int numberOfRuns;

    private Runs() {
        // Required by JAXB
    }

    public Runs(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Runs other = (Runs) o;

        return this.numberOfRuns == other.numberOfRuns;

    }

    @Override
    public int hashCode() {
        return new Integer(numberOfRuns).hashCode();
    }

    @Override
    public String toString() {
        return "Runs{" +
                "numberOfRuns=" + numberOfRuns +
                '}';
    }
}
