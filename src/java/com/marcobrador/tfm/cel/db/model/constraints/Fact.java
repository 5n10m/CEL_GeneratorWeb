package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.DeonticStructuredClause;

import javax.persistence.*;

/**
 * Class that represents the cel-core:Fact complex type and the rel-r:condition complex type.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "fact_type")
@Table(name = "facts")
public abstract class Fact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @ManyToOne
    private FactComposition composedFact;

    @ManyToOne
    private DeonticStructuredClause clause;
}
