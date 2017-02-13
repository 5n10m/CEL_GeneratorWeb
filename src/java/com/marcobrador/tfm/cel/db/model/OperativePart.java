package com.marcobrador.tfm.cel.db.model;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents the cel-core:OperativePart complex type.
 */
@Embeddable
public class OperativePart {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_contractId")
    @XmlElements(value = {
            @XmlElement(name = "Permission", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Permission.class),
            @XmlElement(name = "Obligation", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Obligation.class),
            @XmlElement(name = "Prohibition", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Prohibition.class),
            @XmlElement(name = "Permission", namespace = "urn:mpeg:mpeg21:cel:ipre:2015", type = IprePermission.class)
    })
    private Set<DeonticStructuredClause> clauses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_contractId")
    @XmlElement(name = "Statement", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Set<Statement> statements;

    private OperativePart() {
        // Required by JAXB
    }

    private OperativePart(Builder builder) {
        clauses = builder.clauses;
        statements = builder.statements;
    }

    public Set<DeonticStructuredClause> getClauses() {
        return clauses;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("========================\n");
        stringBuilder.append("Clauses of the contract:\n");
        for (DeonticStructuredClause clause : clauses) {
            stringBuilder.append("[Clause Start]\n");
            stringBuilder.append(clause);
            stringBuilder.append("\n");
            stringBuilder.append("[Clause End]\n\n");
        }
        if (statements != null) {
            stringBuilder.append("========================\n");
            stringBuilder.append("Statements in the contract:\n");
            for (Statement statement : statements) {
                stringBuilder.append("[Statement Start]\n");
                stringBuilder.append(statement);
                stringBuilder.append("[Statement End]\n\n");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OperativePart)) {
            return false;
        }
        OperativePart other = (OperativePart) obj;
        // DO NOT MODIFY! Reordering the first part of this statement (other.clauses.equals(this.clauses)) may result in
        // unexpected behavior!
        return other.clauses.equals(this.clauses)
                && Utils.lazyCollectionCompare(this.statements, other.statements);
    }

    public static final class Builder {
        private Set<DeonticStructuredClause> clauses;
        private Set<Statement> statements;

        public Builder() {
            clauses = new HashSet<DeonticStructuredClause>();
            statements = new HashSet<Statement>();
        }

        public Builder addClause(DeonticStructuredClause clause) {
            clauses.add(clause);
            return this;
        }

        public Builder addStatement(Statement statement) {
            statements.add(statement);
            return this;
        }

        public OperativePart build() {
            return new OperativePart(this);
        }
    }
}
