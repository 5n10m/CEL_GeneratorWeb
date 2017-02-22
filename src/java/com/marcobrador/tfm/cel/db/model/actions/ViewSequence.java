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

public class ViewSequence extends Action {

    @Column
    @XmlAttribute
    private Integer ChromosomeNumber;
    private Integer Offset;
    private Integer Size;

    private ViewSequence() {
        // Required by JAXB
    }

    public ViewSequence(Integer ChromosomeNumber, Integer Offset, Integer Size) {
        this.ChromosomeNumber = ChromosomeNumber;
        this.Offset = Offset;
        this.Size = Size;
    }

    @Override
    public int hashCode() {
        return ChromosomeNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Chromosome nº " + ChromosomeNumber + ", Offset "+ Offset +", Size "+ Size;
    }
}
