package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:Device complex type.
 */
@Entity
@DiscriminatorValue("device")
@Table(name = "devices")
public class Device extends Fact {

    public enum Type {
        Computer,
        MobileDevice,
        MobileBroadcastDevice,
        MobileTelecommunicationDevice,
        RobotDevice,
        StorageDevice,
        TelevisionDevice,
        TelevisionSet
    }

    @Column
    @XmlAttribute
    private String href;

    private Device() {
        // Required by JAXB
    }

    public Device(Type type) {
        href = typeToUri(type);
    }

    public Type getType() {
        return uriToType(href);
    }

    private static Type uriToType(String uri) {
        if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#Computer".equals(uri)) {
            return Type.Computer;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#MobileDevice".equals(uri)) {
            return Type.MobileDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#MobileBroadcastDevice".equals(uri)) {
            return Type.MobileBroadcastDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#MobileTelecommunicationDevice".equals(uri)) {
            return Type.MobileTelecommunicationDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#RobotDevice".equals(uri)) {
            return Type.RobotDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#StorageDevice".equals(uri)) {
            return Type.StorageDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#TelevisionDevice".equals(uri)) {
            return Type.TelevisionDevice;
        } else if ("urn:mpeg:mpeg21:cel:ipre:2015/Device#TelevisionSet".equals(uri)) {
            return Type.TelevisionSet;
        } else {
            throw new IllegalArgumentException("Invalid uri: " + uri);
        }
    }

    private static String typeToUri(Type type) {
        return "urn:mpeg:mpeg21:cel:ipre:2015/Device#" + type.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device that = (Device) o;

        return href.equals(that.href);

    }

    @Override
    public int hashCode() {
        return href.hashCode();
    }

    @Override
    public String toString() {
        return "Device{" +
                getType() +
                '}';
    }
}
