package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.model.converters.DurationConverter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.Duration;

/**
 * Class that represents the rel-sx:feePerInterval complex type.
 */
@Entity
@DiscriminatorValue("fee_per_interval")
@Table(name = "fees_per_interval")
public class FeePerInterval extends Fact {

    @Embedded
    @XmlElement(name = "rate", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS")
    private Rate rate;

    @Column
    @Convert(converter = DurationConverter.class)
    @XmlElement(name = "per", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS")
    private Duration per;

    private FeePerInterval() {
        // Required by JAXB
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeePerInterval that = (FeePerInterval) o;

        return rate.equals(that.rate) && per.equals(that.per);

    }

    @Override
    public int hashCode() {
        return rate.hashCode() + per.hashCode();
    }

    @Override
    public String toString() {
        return "FeePerInterval{" +
                "rate=" + rate +
                ", per=" + per +
                '}';
    }

    @Embeddable
    private static class Rate {

        @Column
        @XmlElement(name = "amount", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS")
        private float amount;

        @Column
        @XmlElement(name = "currency", namespace = "urn:mpeg:mpeg21:2003:01-REL-SX-NS")
        private String currency;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Rate rate = (Rate) o;

            if (Float.compare(rate.amount, amount) != 0) return false;
            return currency.equals(rate.currency);

        }

        @Override
        public int hashCode() {
            int result = (amount != +0.0f ? Float.floatToIntBits(amount) : 0);
            result = 31 * result + currency.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Rate{" +
                    "amount=" + amount +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    }
}
