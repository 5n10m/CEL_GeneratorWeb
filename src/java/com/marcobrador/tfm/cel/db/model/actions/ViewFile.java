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

public class ViewFile extends Action {

    @Column
    @XmlAttribute
    private String FileIdentifier;

    private ViewFile() {
        // Required by JAXB
    }

    public ViewFile(String FileIdentifier) {
        this.FileIdentifier = FileIdentifier;
    }

    @Override
    public int hashCode() {
        return FileIdentifier.hashCode();
    }

    @Override
    public String toString() {
        return "File ID " + FileIdentifier;
    }
}
