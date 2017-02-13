package com.marcobrador.tfm.cel.db.model;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents the cel-core:Contract complex type.
 */
@Entity
@Table(name = "Contracts")
@XmlRootElement(name = "Contract", namespace = "urn:mpeg:mpeg21:cel:core:2015")
public class Contract {

    @Id
    @Column
    @XmlAttribute
    private String contractId;

    @Column
    @XmlAttribute
    private String governingLaw;

    @Column
    @XmlAttribute
    private String court;

    @Column
    @XmlAttribute
    private boolean isCourtJurisdictionExclusive;

    @Column
    @XmlElement(name="TextVersion", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private String textVersion;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "contract_id", referencedColumnName = "contractId")},
            inverseJoinColumns = {@JoinColumn(name = "party_id", referencedColumnName = "dbId")}
    )
    @XmlElement(name="Party", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Set<Party> parties;

    @Embedded
    @XmlElement(name="Body", namespace = "urn:mpeg:mpeg21:cel:core:2015")
    private Body body;

    private Contract() {
        // Required by JAXB
    }

    private Contract(Builder builder) {
        contractId = builder.contractId;
        governingLaw = builder.governingLaw;
        court = builder.court;
        isCourtJurisdictionExclusive = builder.isCourtJurisdictionExclusive;
        textVersion = builder.textVersion;
        parties = builder.parties;
        body = builder.body;
    }

    public String getContractId() {
        return contractId;
    }

    public String getGoverningLaw() {
        return governingLaw;
    }

    public String getCourt() {
        return court;
    }

    public boolean isCourtJurisdictionExclusive() {
        return isCourtJurisdictionExclusive;
    }

    public Body getBody() {
        return body;
    }

    public String getTextVersion() {
        return textVersion;
    }

    public Set<Party> getParties() {
        return parties;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String basicInfo = "Contract ID: " + contractId + "\n"
                + "Governing Law: " + governingLaw + "\n"
                + "Court: " + court + "\n"
                + "Is court jurisdiction exclusive? " + isCourtJurisdictionExclusive + "\n";
        builder.append(basicInfo);
        builder.append("=================================\n");
        if (parties != null) {
            builder.append("Parties involved in the contract:\n");
            for (Party party : parties) {
                builder.append("[Party Start]\n");
                builder.append(party);
                builder.append("\n[Party End]\n\n");
            }
        } else {
            builder.append("Contract is a template, no parties involved\n");
        }
        builder.append(body);
        builder.append("Text Version: " + textVersion + "\n");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) obj;
        return this.contractId.equals(other.contractId)
                && Utils.safeCompare(this.governingLaw, other.governingLaw)
                && Utils.safeCompare(this.court, other.court)
                && this.isCourtJurisdictionExclusive == other.isCourtJurisdictionExclusive
                && this.textVersion.equals(other.textVersion)
                && this.parties.equals(other.parties)
                && this.body.equals(other.body);
    }

    public static final class Builder {
        private final String contractId;
        private String governingLaw;
        private String court;
        private boolean isCourtJurisdictionExclusive;
        private String textVersion;
        private Set<Party> parties;
        private final Body body;

        public Builder(String contractId, Body body) {
            this.contractId = contractId;
            this.body = body;
            parties = new HashSet<Party>();
        }

        public Builder setGoverningLaw(String val) {
            governingLaw = val;
            return this;
        }

        public Builder setCourt(String val) {
            court = val;
            return this;
        }

        public Builder setIsCourtJurisdictionExclusive(boolean val) {
            isCourtJurisdictionExclusive = val;
            return this;
        }

        public Builder setTextVersion(String val) {
            textVersion = val;
            return this;
        }

        public Builder addParty(Party party) {
            parties.add(party);
            return this;
        }

        public Contract build() {
            return new Contract(this);
        }
    }
}
