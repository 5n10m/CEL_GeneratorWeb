package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:DeliveryModality complex type.
 */
@Entity
@DiscriminatorValue("delivery_modality")
@Table(name = "delivery_modalities")
public class DeliveryModality extends Fact {

    public enum Type {
        Linear,
        Broadcasting,
        Webcasting,
        NonLinear,
        OnDemandBasis,
        OnDemandDownload,
        OnDemandStreaming
    }

    @Column
    @XmlAttribute
    private String href;

    private DeliveryModality() {
        // Required by JAXB
    }

    public DeliveryModality(Type type) {
        href = typeToUri(type);
    }

    public Type getType() {
        return uriToType(href);
    }

    private static Type uriToType(String uri) {
        if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#Linear".equals(uri)) {
            return Type.Linear;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#Broadcasting".equals(uri)) {
            return Type.Broadcasting;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#Webcasting".equals(uri)) {
            return Type.Webcasting;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#NonLinear".equals(uri)) {
            return Type.NonLinear;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#OnDemandBasis".equals(uri)) {
            return Type.OnDemandBasis;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#OnDemandDownload".equals(uri)) {
            return Type.OnDemandDownload;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#OnDemandStreaming".equals(uri)) {
            return Type.OnDemandStreaming;
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    private static String typeToUri(Type type) {
        return "urn:mpeg:mpeg21:cel:ipre:2015/DeliveryModality#" + type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryModality that = (DeliveryModality) o;

        return href.equals(that.href);

    }

    @Override
    public int hashCode() {
        return href.hashCode();
    }

    @Override
    public String toString() {
        return "DeliveryModality{" +
                getType() +
                '}';
    }
}
