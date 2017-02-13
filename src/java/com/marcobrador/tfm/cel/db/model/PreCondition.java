package com.marcobrador.tfm.cel.db.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents the cel-core:PreCondition complex type.
 */
@Entity
@Table(name = "pre_conditions")
public class PreCondition {

    enum ActionStatus {
        ActionStarted,
        ActionDone
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private int id;

    @Column
    @XmlAttribute
    private String idref;

    @Column
    @XmlAttribute
    private ActionStatus actionStatus;

    @ManyToOne
    private DeonticStructuredClause clause;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreCondition that = (PreCondition) o;

        if (!idref.equals(that.idref)) return false;
        return actionStatus == that.actionStatus;

    }

    @Override
    public int hashCode() {
        int result = idref.hashCode();
        result = 31 * result + actionStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PreCondition{" +
                "idref='" + idref + '\'' +
                ", actionStatus=" + actionStatus +
                '}';
    }
}
