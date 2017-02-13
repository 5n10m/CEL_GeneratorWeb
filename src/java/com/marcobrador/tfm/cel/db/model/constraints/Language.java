package com.marcobrador.tfm.cel.db.model.constraints;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.*;

/**
 * Class that represents the cel-ipre:Language complex type.
 */
@Entity
@DiscriminatorValue("language")
@Table(name = "languages")
public class Language extends Fact {

    @ElementCollection
    @XmlAttribute
    private Set<String> languages;

    private Language() {
        // Required by JAXB
    }

    public Language(String language) {
        languages = new HashSet<String>();
        addLanguage(language);
    }

    public void addLanguage(String language) {
        if (language == null) {
            throw new IllegalArgumentException("language cannot be null");
        }
        if (language.isEmpty()) {
            throw new IllegalArgumentException("language cannot be empty");
        }
        languages.add(language);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        return languages.equals(language.languages);

    }

    @Override
    public int hashCode() {
        return languages.hashCode();
    }

    @Override
    public String toString() {
        return "Language{" +
                "languages=" + languages +
                '}';
    }
}
