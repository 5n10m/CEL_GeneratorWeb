package com.marcobrador.tfm.cel.db.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

/**
 * Class that represents the cel-core:Statement complex type.
 */
@Entity
@Table(name = "statements")
public class Statement {

    @Id
    @Column
    @GeneratedValue
    @XmlTransient
    private int dbId;

    @Column
    @XmlAttribute
    private String id;

    @Column(length = 100000)
    @XmlValue
    private String value;

    @ManyToOne
    private Contract contract;

    private Statement() {
        // Required by JAXB
    }

    public Statement(String id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + value + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Statement)) {
            return false;
        }
        Statement other = (Statement) obj;
        return this.id.equals(other.id)
                && this.value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return id.hashCode() + value.hashCode();
    }
}
