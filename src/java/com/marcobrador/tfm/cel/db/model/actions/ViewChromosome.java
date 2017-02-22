/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marcobrador.tfm.cel.db.model.actions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;

public class ViewChromosome extends Action {

    @Column
    @XmlAttribute
    private Integer ChromosomeNumber;

    private ViewChromosome() {
        // Required by JAXB
    }

    public ViewChromosome(Integer ChromosomeNumber) {
        this.ChromosomeNumber = ChromosomeNumber;
    }

    @Override
    public int hashCode() {
        return ChromosomeNumber.hashCode();
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Provide)) {
            return false;
        }
        Provide other = (Provide) obj;
        return this.ChromosomeNumber.equals(other.ChromosomeNumber);
    }
*/
    @Override
    public String toString() {
        return "Provide to " + ChromosomeNumber;
    }
}
