package com.mycompany.iach7.trucker.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Trucker processes a Tour.
 */
@Entity(name = "Trk")
@Table(name = "TRUCKER", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Trucker.findAll", query = "SELECT t FROM Trk t"),
    @NamedQuery(name = "Trucker.findAllOrderdAsc", query = "SELECT t FROM Trk t ORDER BY t.truckerId"),
    @NamedQuery(name = "Trucker.findByTruckerId", query = "SELECT t FROM Trk T WHERE t.truckerId = :truckerId"),
    @NamedQuery(name = "Trucker.findLikeTruckerId", query = "SELECT t FROM Trk T WHERE t.truckerId LIKE :truckerId"),
    @NamedQuery(name = "Trucker.findLikeTruckerIdOrderedAsc", query = "SELECT t FROM Trk T WHERE t.truckerId LIKE :truckerId ORDER BY t.truckerId")
})
public class Trucker extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TRUCKERID")
    private String truckerId;

    @Version
    @Basic(optional = false)
    private int version;

    public Trucker() {
        truckerId = "";
        super.setUpdtDttm("");
        super.setUpdtGuiUser("");
    }

    public Trucker(String truckerId) {
        this.truckerId = truckerId;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Trucker(String truckerId, String updtGuiUser) {
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser(updtGuiUser);
    }

    public String getTruckerId() {
        return truckerId;
    }

    public void setTruckerId(String truckerId) {
        this.truckerId = truckerId;
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
        final Trucker other = (Trucker) obj;

        return !((this.truckerId == null) ? (other.truckerId != null) : !this.truckerId.equals(other.truckerId));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trucker{truckerId=").append(truckerId);
        sb.append(super.toString());
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
