package com.marcobrador.tfm.cel.db.model;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents the cel-core:PartyBasicGroup complex type.
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class PartyBasicGroup {
    // TODO: the ID in the database should belong to a legal ID (national ID number or similar)
    @Id
    @Column
    @GeneratedValue
    @XmlTransient
    private int dbId;

    @Column
    @XmlElement(name="Name", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private String name;

    @Column
    @XmlElement(name = "identifier", namespace = "http://purl.org/dc/elements/1.1/")
    private String identifier;

    @Column
    @XmlElement(name = "description", namespace = "http://purl.org/dc/elements/1.1/")
    private String description;

    @Column
    @XmlElement(name = "Details", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private String details;

    // TODO: missing signatory and signature

    protected PartyBasicGroup() {
        // Required by JAXB
    }

    protected PartyBasicGroup(Builder builder) {
        name = builder.name;
        identifier = builder.identifier;
        description = builder.description;
        details = builder.details;
    }

    /**
     * @return a String representing the name of the specific implementation of this party basic group.
     */
    public abstract String getType();
    
    public String getName() {
        return name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDescription() {
        return description;
    }

    public String getDetails() {
        return details;
    }
    

    @Override
    public String toString() {
        return "Type: " + getType() + "\n"
                + "Name: " + name + "\n"
                + "Identifier: " + identifier + "\n"
                + "Description: " + description
                + (details == null ? "" :  "\nDetails: " + details);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PartyBasicGroup)) {
            return false;
        }
        PartyBasicGroup other = (PartyBasicGroup) obj;
        return this.name.equals(other.name)
                && this.identifier.equals(other.identifier)
                && Utils.safeCompare(this.description, other.description)
                && Utils.safeCompare(this.details, other.details);
    }

    @Override
    public int hashCode() {
        return name.hashCode()
                + identifier.hashCode()
                + (description != null ? description.hashCode() : 0)
                + (details != null ? details.hashCode() : 0);
    }

    public static abstract class Builder {
        private final String name;
        private String identifier;
        private String description;
        private String details;

        public Builder(String name) {
            this.name = name;
        }

        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        // TODO: check why do we need it here and not in DeonticStructuredClause
        public abstract PartyBasicGroup build();
    }
}
