package com.marcobrador.tfm.cel.db.model;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Class that represents the cel-core:PartyType complex type.
 */
@Entity
@Table(name = "Parties")
public class Party {
    // TODO: the ID in the database should belong to a legal ID (national ID number or similar)
    @Id
    @Column
    @GeneratedValue
    @XmlTransient
    private int dbId;

    @Column
    @XmlAttribute
    private String id;

    @Column
    @XmlElement(name = "Address", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @XmlElements(value = {
            @XmlElement(name = "Person", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Person.class),
            @XmlElement(name = "Organization", namespace = "urn:mpeg:mpeg21:cel:core:2015", type = Organization.class)
    })
    private PartyBasicGroup partyBasicGroup;

    private Party() {
        // Required by JAXB
    }

    protected Party(Builder builder) {
        id = builder.id;
        address = builder.address;
        partyBasicGroup = builder.partyBasicGroup;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public PartyBasicGroup getPartyBasicGroup() {
        return partyBasicGroup;
    }

    @Override
    public String toString() {
        return "Party ID: " + id + "\n"
                + (address == null ? "" : "Address: " + address + "\n")
                + partyBasicGroup;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Party)) {
            return false;
        }
        Party other = (Party) obj;
        return this.id.equals(other.id)
                && Utils.safeCompare(this.address, other.address)
                && this.partyBasicGroup.equals(other.partyBasicGroup);
    }

    @Override
    public int hashCode() {
        return id.hashCode()
                + (address != null ? address.hashCode() : 0)
                + partyBasicGroup.hashCode();
    }


    public static class Builder {
        private final String id;
        private String address;
        private final PartyBasicGroup partyBasicGroup;

        public Builder(String id, PartyBasicGroup partyBasicGroup) {
            this.id = id;
            this.partyBasicGroup = partyBasicGroup;
        }

        public Builder setAddress(String val) {
            address = val;
            return this;
        }

        public Party build() {
            return new Party(this);
        }
    }
}
