/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcobrador.tfm.cel.db.model;

import java.io.Serializable;

/**
 *
 * @author david
 */
public class Event implements Serializable {

    private String name;
    private RelatedIdentifier relatedIdentifier;

    private Event() {
        // Required by JAXB
    }

    protected Event(Builder builder) {
        name = builder.name;
        relatedIdentifier = builder.relatedIdentifier;
    }

    public String getName() {
        return name;
    }

    public RelatedIdentifier getRelatedIdentifier() {
        return relatedIdentifier;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Item)) {
            return false;
        }

        Event event = (Event) o;

        return name != null ? name.equals(event.name) : event.name == null
                && (relatedIdentifier != null ? relatedIdentifier.equals(event.relatedIdentifier) : event.relatedIdentifier == null);
    }

    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (relatedIdentifier != null ? relatedIdentifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (name == null ? "" : "name=\'" + name + "\', ")
                + "relatedIdentifier=" + relatedIdentifier;
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

        public Event build() {
            if (relatedIdentifier == null) {
                throw new IllegalStateException("relatedIdentifier cannot be null");
            }
            return new Event(this);
        }
    }
}
