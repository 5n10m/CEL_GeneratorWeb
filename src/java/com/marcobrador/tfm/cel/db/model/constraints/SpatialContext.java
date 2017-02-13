package com.marcobrador.tfm.cel.db.model.constraints;

import com.marcobrador.tfm.cel.db.Utils;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 * Class that represents the cel-ipre:SpatialContext complex type.
 */
@Entity
@DiscriminatorValue("spatial_context")
@Table(name = "spatial_contexts")
public class SpatialContext extends Fact {

    @ElementCollection
    @XmlElement(name = "Country", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private Set<String> countries;

    @ElementCollection
    @XmlElement(name = "Region", namespace = "urn:mpeg:mpeg21:cel:ipre:2015")
    private Set<String> regions;

    private SpatialContext() {
        // Required by JAXB
    }

    private SpatialContext(Builder builder) {
        countries = builder.countries;
        regions = builder.regions;
    }

    public Set<String> getCountries() {
        return countries;
    }

    public Set<String> getRegions() {
        return regions;
    }

    public SpatialContext clone() {
        SpatialContext clone = new SpatialContext();
        clone.countries = this.countries;
        clone.regions = this.regions;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpatialContext that = (SpatialContext) o;

        return Utils.lazyCollectionCompare(countries, that.countries)
                && Utils.lazyCollectionCompare(regions, that.regions);
    }

    @Override
    public int hashCode() {
        int result = countries != null ? countries.hashCode() : 0;
        result = 31 * result + (regions != null ? regions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpatialContext{" +
                "countries=" + countries +
                ", regions=" + regions +
                '}';
    }

    public static class Builder {
        private Set<String> countries;
        private Set<String> regions;

        public Builder addRegion(String region) {
            if (region == null) {
                throw new IllegalArgumentException("region cannot be null");
            }
            if (region.isEmpty()) {
                throw new IllegalArgumentException("region cannot be empty");
            }
            if (regions == null) {
                regions = new HashSet<String>();
            }
            regions.add(region);
            return this;
        }

        public Builder addCountry(String country) {
            if (country == null) {
                throw new IllegalArgumentException("country cannot be null");
            }
            if (country.isEmpty()) {
                throw new IllegalArgumentException("country cannot be empty");
            }
            if (countries == null) {
                countries = new HashSet<String>();
            }
            countries.add(country);
            return this;
        }

        public SpatialContext build() {
            if ((regions == null || regions.isEmpty())
                    && (countries == null || countries.isEmpty())) {
                throw new IllegalStateException("Either a region or a country must be set");
            }
            return new SpatialContext(this);
        }
    }
}
