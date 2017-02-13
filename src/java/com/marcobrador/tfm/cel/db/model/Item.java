package com.marcobrador.tfm.cel.db.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class that represents the cel-core:Item type.
 */
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @XmlAttribute
    private String name;

    @Embedded
    @XmlElement(name="RelatedIdentifier", namespace = "urn:mpeg:mpeg21:2002:01-DII-NS")
    private RelatedIdentifier relatedIdentifier;

    @OneToOne
    private DeonticStructuredClause clause;

    private Item() {
        // Required by JAXB
    }

    protected Item(Builder builder) {
        name = builder.name;
        relatedIdentifier = builder.relatedIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Item)) return false;

        Item item = (Item) o;

        return name != null ? name.equals(item.name) : item.name == null
                && (relatedIdentifier != null ? relatedIdentifier.equals(item.relatedIdentifier) : item.relatedIdentifier == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (relatedIdentifier != null ? relatedIdentifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (name == null ? "" : "name=\'" + name + "\', ") +
                "relatedIdentifier=" + relatedIdentifier;
    }

    public static class Builder {
        protected String name;
        protected RelatedIdentifier relatedIdentifier;

        public Builder setName(String value) {
            this.name = value;
            return this;
        }

        public Builder setRelatedIdentifier(RelatedIdentifier value) {
            this.relatedIdentifier = value;
            return this;
        }

        public Item build() {
            if (relatedIdentifier == null) {
                throw new IllegalStateException("relatedIdentifier cannot be null");
            }
            return new Item(this);
        }
    }
}
