package com.marcobrador.tfm.cel.db.model.actions;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Class that represents the cel-pane:Payment action.
 */
@Entity
@Table(name = "actions_payment")
public class Payment extends Action {
    @Column
    @XmlAttribute
    private Double amount;

    @Column
    @XmlAttribute
    private String currency;

    @Column
    @XmlAttribute
    private Double incomePercentage;

    @Embedded
    @XmlElement(name="Beneficiary", namespace = "urn:mpeg:mpeg21:cel:pane:2015")
    private Beneficiary beneficiary;

    @Embedded
    @XmlElement(name="IncomeSource", namespace = "urn:mpeg:mpeg21:cel:pane:2015")
    private IncomeSource incomeSource;

    private Payment() {
        // Required by JAXB
    }

    public Payment(Builder builder) {
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.incomePercentage = builder.incomePercentage;
        this.beneficiary = builder.beneficiary;
        this.incomeSource = builder.incomeSource;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Payment)) {
            return false;
        }
        Payment other = (Payment) obj;
        return Utils.safeCompare(this.amount, other.amount)
                && Utils.safeCompare(this.currency, other.currency)
                && Utils.safeCompare(this.incomePercentage, other.incomePercentage)
                && this.beneficiary.equals(other.beneficiary)
                && Utils.safeCompare(this.incomeSource, other.incomeSource);
    }

    @Override
    public int hashCode() {
        return Utils.computeNullableHashcode(this.amount)
                + Utils.computeNullableHashcode(this.currency)
                + Utils.computeNullableHashcode(this.incomePercentage)
                + this.beneficiary.hashCode()
                + Utils.computeNullableHashcode(this.incomeSource);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", incomePercentage=" + incomePercentage +
                ", beneficiary=" + beneficiary +
                ", incomeSource=" + incomeSource +
                "}";
    }

    public static class Builder {
        private final Beneficiary beneficiary;

        private Double amount;
        private String currency;
        private Double incomePercentage;
        private IncomeSource incomeSource;

        public Builder(Beneficiary beneficiary) {
            this.beneficiary = beneficiary;
        }

        public Builder setAmount(double value) {
            if (value < 0) {
                throw new IllegalArgumentException("amount must be positive");
            }
            amount = value;
            return this;
        }

        public Builder setCurrency(String value) {
            currency = value;
            return this;
        }

        public Builder setIncomePercentage(double value) {
            if (value < 0 || value > 100) {
                throw new IllegalArgumentException("incomePercentage must be between 0 and 100");
            }
            incomePercentage = value;
            return this;
        }

        public Builder setIncomeSource(IncomeSource incomeSource) {
            this.incomeSource = incomeSource;
            return this;
        }

        public Payment build() {
            if (amount != null && incomePercentage != null) {
                throw new IllegalStateException("amount and income percentage can not both be present");
            }
            return new Payment(this);
        }
    }

    @Embeddable
    public static class Beneficiary {
        @Column
        @XmlAttribute
        private String partyRef;

        private Beneficiary() {
            // Required by JAXB
        }

        public Beneficiary(String partyRef) {
            if (partyRef == null) {
                throw new IllegalArgumentException("partyRef cannot be null");
            }
            this.partyRef = partyRef;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Beneficiary)) {
                return false;
            }
            Beneficiary other = (Beneficiary) obj;
            return this.partyRef.equals(other.partyRef);
        }

        @Override
        public int hashCode() {
            return partyRef.hashCode();
        }

        @Override
        public String toString() {
            return partyRef;
        }
    }

    @Embeddable
    public static class IncomeSource {
        @Column
        @XmlAttribute
        private String actRef;

        private IncomeSource() {
            // Required by JAXB
        }

        public IncomeSource(String actRef) {
            if (actRef == null) {
                throw new IllegalArgumentException("actRef cannot be null");
            }
            this.actRef = actRef;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof IncomeSource)) {
                return false;
            }
            IncomeSource other = (IncomeSource) obj;
            return this.actRef.equals(other.actRef);
        }

        @Override
        public int hashCode() {
            return actRef.hashCode();
        }

        @Override
        public String toString() {
            return actRef;
        }
    }
}
