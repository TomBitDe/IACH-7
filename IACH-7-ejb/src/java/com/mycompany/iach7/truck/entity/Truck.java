package com.mycompany.iach7.truck.entity;

import com.mycompany.iach7.util.GuiMasterData;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Truck is used to process a Tour.
 */
@Entity
@Table(name = "TRUCK", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Truck.findAll", query = "SELECT t FROM Truck t")})
public class Truck extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TRUCKID")
    private String truckId;

    @Basic(optional = false)
    @Column(name = "TYPE")
    @Enumerated(EnumType.ORDINAL)
    private Trucktype type;

    @Basic(optional = false)
    @Column(name = "LOAD")
    @Enumerated(EnumType.ORDINAL)
    private Truckload load;

    @Basic(optional = false)
    @Column(name = "COM1TEXT")
    private String comment;

    @Version
    @Basic(optional = false)
    private int version;

    public Truck() {
        super();

        this.truckId = "";
        this.type = Trucktype.large;
        this.load = Truckload.back;
        this.comment = "";
        super.setUpdtDttm("");
        super.setUpdtGuiUser("");
    }

    public Truck(String truckId, Trucktype type, Truckload load, String comment, String updtDttm, String updtGuiUser) {
        this.truckId = truckId;
        this.type = type;
        this.load = load;
        this.comment = comment;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Truck(String truckId, String type, String load, String comment, String updtDttm, String updtGuiUser) {
        this.truckId = truckId;
        this.type = Trucktype.valueOf(type);
        this.load = Truckload.valueOf(load);
        this.comment = comment;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public Trucktype getType() {
        return type;
    }

    public void setType(Trucktype type) {
        this.type = type;
    }

    public Truckload getLoad() {
        return load;
    }

    public void setLoad(Truckload load) {
        this.load = load;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.truckId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Truck other = (Truck) obj;

        return !((this.truckId == null) ? (other.truckId != null) : !this.truckId.equals(other.truckId));
    }

    @Override
    public String toString() {
        return "Truck{" + "truckId=" + truckId + ", type=" + type + ", load=" + load + ", comment=" + comment + super.toString() + ", version=" + version + '}';
    }

}
