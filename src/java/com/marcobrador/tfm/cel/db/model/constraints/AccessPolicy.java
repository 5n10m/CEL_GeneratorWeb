package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:AccessPolicy complex type.
 */
@Entity
@DiscriminatorValue("access_policy")
@Table(name = "access_policies")
public class AccessPolicy extends Fact {

    public enum Type {
        FreeOfCharge,
        Pay,
        PayPerPackage,
        PayPerView,
        Subscription
    }

    @Column
    @XmlAttribute
    private String href;

    private AccessPolicy() {
        // Required by JAXB
    }

    public AccessPolicy(Type type) {
        href = typeToUri(type);
    }

    public Type getType() {
        return uriToType(href);
    }

    private static Type uriToType(String uri) {
        if ("urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#FreeOfCharge".equals(uri)) {
            return Type.FreeOfCharge;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#Pay".equals(uri)) {
            return Type.Pay;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#PayPerPackage".equals(uri)) {
            return Type.PayPerPackage;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#PayPerView".equals(uri)) {
            return Type.PayPerView;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#Subscription".equals(uri)) {
            return Type.Subscription;
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    private static String typeToUri(Type type) {
        return "urn:mpeg:mpeg21:cel:ipre:2015/AccessPolicy#" + type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccessPolicy that = (AccessPolicy) o;

        return href.equals(that.href);

    }

    @Override
    public int hashCode() {
        return href.hashCode();
    }

    @Override
    public String toString() {
        return "AccessPolicy{" +
                getType() +
                '}';
    }
}
