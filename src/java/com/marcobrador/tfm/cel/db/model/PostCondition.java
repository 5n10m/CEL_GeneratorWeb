package com.marcobrador.tfm.cel.db.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author david
 */
public class PostCondition implements Serializable{
     @Id
    private Long id;

    public enum ActionStatus {
        ActionStarted,
        ActionDone
    }

    @Column
    @XmlAttribute
    private String idref;

    @Column
    @XmlAttribute
    private ActionStatus actionStatus;

    @ManyToOne
    private String withDelay;
    
    private String validity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCondition that = (PostCondition) o;

        if (!idref.equals(that.idref)) return false;
        return actionStatus == that.actionStatus;

    }

    @Override
    public int hashCode() {
        int result = idref.hashCode();
        result = 31 * result + actionStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PreCondition{" +
                "idref='" + idref + '\'' +
                ", actionStatus=" + actionStatus +
                '}';
    }

    public PostCondition( String idref, ActionStatus actionStatus, String withDelay, String validity) {
        this.idref = idref;
        this.actionStatus = actionStatus;
        this.withDelay = withDelay;
        this.validity = validity;
    }

    public String getIdref() {
        return idref;
    }

    public void setIdref(String idref) {
        this.idref = idref;
    }

    public ActionStatus getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatus actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getWithDelay() {
        return withDelay;
    }

    public void setWithDelay(String withDelay) {
        this.withDelay = withDelay;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
