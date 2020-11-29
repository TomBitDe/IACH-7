package com.mycompany.iach7.tour.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
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
 * A Tour contains 1 up to N Laps (Etappen)
 */
@Entity
@Table(name = "TOUR", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Tour.findAll", query = "SELECT t FROM Tour t")})
public class Tour extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "TOURID")
    private String tourId;

    @Basic(optional = false)
    @Column(name = "TOURSTAT")
    @Enumerated(EnumType.ORDINAL)
    private Tourstat tourstat;

    @Basic(optional = false)
    @Column(name = "TRUCKID")
    private String truckId;

    @Basic(optional = false)
    @Column(name = "TRUCKERID")
    private String truckerId;

    @Basic(optional = false)
    @Column(name = "AGENT")
    private String agent;

    @Version
    @Basic(optional = false)
    private int version;

    public Tour() {
        super();

        this.tourId = "";
        this.tourstat = Tourstat.unknown;
        this.truckId = "";
        this.truckerId = "";
        this.agent = "";
        super.setUpdtDttm("");
        super.setUpdtGuiUser("");
    }

    public Tour(String tourId, Tourstat tourstat, String truckId, String truckerId, String agent, String updtGuiUser) {
        this.tourId = tourId;
        this.tourstat = tourstat;
        this.truckId = truckId;
        this.truckerId = truckerId;
        this.agent = agent;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Tour(String tourId, Tourstat tourstat, String truckId, String truckerId, String agent) {
        this.tourId = tourId;
        this.tourstat = tourstat;
        this.truckId = truckId;
        this.truckerId = truckerId;
        this.agent = agent;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Tour(String tourId, Tourstat tourstat, String truckId, String truckerId, String agent, String updtDttm, String updtGuiUser) {
        this.tourId = tourId;
        this.tourstat = tourstat;
        this.truckId = truckId;
        this.truckerId = truckerId;
        this.agent = agent;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Tour(String tourId, String tourstat, String truckId, String truckerId, String agent, String updtDttm, String updtGuiUser) {
        this.tourId = tourId;
        this.tourstat = Tourstat.valueOf(tourstat);
        this.truckId = truckId;
        this.truckerId = truckerId;
        this.agent = agent;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public Tourstat getTourstat() {
        return tourstat;
    }

    public void setTourstat(Tourstat tourstat) {
        this.tourstat = tourstat;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getTruckerId() {
        return truckerId;
    }

    public void setTruckerId(String truckerId) {
        this.truckerId = truckerId;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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
        hash = 29 * hash + Objects.hashCode(this.tourId);
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
        final Tour other = (Tour) obj;

        return !((this.tourId == null) ? (other.tourId != null) : !this.tourId.equals(other.tourId));
    }

    @Override
    public String toString() {
        return "Tour{" + "tourId=" + tourId + ", tourstat=" + tourstat + ", truckId=" + truckId + ", truckerId=" + truckerId + ", agent=" + agent + super.toString() + ", version=" + version + '}';
    }
}
