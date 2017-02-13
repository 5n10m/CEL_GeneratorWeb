package com.marcobrador.tfm.cel.db.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Class that represents the cel-ipre:Permission complex type.
 */
@Entity
@DiscriminatorValue("ipre-permission")
public class IprePermission extends Permission {

    @Column
    @XmlAttribute
    private boolean isExclusive = false;

    @Column
    @XmlAttribute
    private boolean sublicenceRight = false;

    private IprePermission() {
        // Required by JAXB
    }

    private IprePermission(Builder builder) {
        super(builder);
        this.isExclusive = builder.isExclusive;
        this.sublicenceRight = builder.sublicenceRight;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    @Override
    public String toString() {
        String parent = super.toString();
        StringBuilder builder = new StringBuilder(parent);
        if (!"\n".equals(parent.substring(parent.length() - 1))) {
            builder.append("\n");
        }
        builder.append("isExclusive: " + isExclusive + "\n");
        builder.append("sublicenceRight: " + sublicenceRight);
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj)
                && this.isExclusive == ((IprePermission) obj).isExclusive
                && this.sublicenceRight == ((IprePermission) obj).sublicenceRight;
    }

    @Override
    public int hashCode() {
        return super.hashCode()
                + ((Boolean) isExclusive).hashCode()
                + ((Boolean) sublicenceRight).hashCode();
    }

    public static final class Builder extends Permission.Builder {

        private boolean isExclusive = false;
        private boolean sublicenceRight = false;

        public Builder(String id, Subject subject, Act act) {
            super(id, subject, act);
        }

        public Builder setIsExclusive(boolean value) {
            isExclusive = value;
            return this;
        }

        public Builder setSublicenceRight(boolean value) {
            sublicenceRight = value;
            return this;
        }

        public IprePermission build() {
            return new IprePermission(this);
        }
    }
}
