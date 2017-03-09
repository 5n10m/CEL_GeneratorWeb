package com.marcobrador.tfm.cel.db.model.actions;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents the cel-core:Action complex type.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Action implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @XmlTransient
    public int id;
}
