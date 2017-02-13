package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:Means complex type.
 */
@Entity
@DiscriminatorValue("means")
@Table(name = "means")
public class Means extends Fact {

    public enum Type {
        Videogram,
        TransmissionTechnology,
        BroadcastTechnology,
        Cable,
        IPNetwork,
        MobileBroadcastTechnology,
        Satellite,
        Terrestrial,
        Internet,
        MobileTechnology,
        MobileTelecommunicationTechnology
    }

    @Column
    @XmlAttribute
    private String href;

    private Means() {
        // Required by JAXB
    }

    public Means(Type type) {
        href = typeToUri(type);
    }

    public Type getType() {
        return uriToType(href);
    }

    private static Type uriToType(String uri) {
        if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#Videogram".equals(uri)) {
            return Type.Videogram;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#TransmissionTechnology".equals(uri)) {
            return Type.TransmissionTechnology;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#BroadcastTechnology".equals(uri)) {
            return Type.BroadcastTechnology;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#Cable".equals(uri)) {
            return Type.Cable;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#IPNetwork".equals(uri)) {
            return Type.IPNetwork;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#MobileBroadcastTechnology".equals(uri)) {
            return Type.MobileBroadcastTechnology;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#Satellite".equals(uri)) {
            return Type.Satellite;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#Terrestrial".equals(uri)) {
            return Type.Terrestrial;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#Internet".equals(uri)) {
            return Type.Internet;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#MobileTechnology".equals(uri)) {
            return Type.MobileTechnology;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Means#MobileTelecommunicationTechnology".equals(uri)) {
            return Type.MobileTelecommunicationTechnology;
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    private static String typeToUri(Type type) {
        return "urn:mpeg:mpeg21:cel:ipre:2015/Means#" + type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Means that = (Means) o;

        return href.equals(that.href);

    }

    @Override
    public int hashCode() {
        return href.hashCode();
    }

    @Override
    public String toString() {
        return "Means{" +
                getType() +
                '}';
    }
}
