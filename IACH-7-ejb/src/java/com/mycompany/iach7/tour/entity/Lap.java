package com.mycompany.iach7.tour.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Lap depends on a Tour.
 */
@Entity
@Table(name = "LAP", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Lap.findAll", query = "SELECT l FROM Lap l")})
public class Lap extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected LapPK id;

    @Column(name = "LAPSTAT", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Lapstat lapstat;

    /**
     * Sequence of lap; lowest order number is first
     */
    @Basic(optional = false)
    @Column(name = "LAPSEQN", nullable = false)
    private int lapSeqn;

    @Basic(optional = false)
    @Column(name = "LAPPRIO", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Prio lapPrio;

    /**
     * Agents scheduled time of arrival
     */
    @Basic(optional = false)
    @Column(name = "AGENTSTA", nullable = false, length = 14)
    private String agentSta;

    /**
     * Customers scheduled time of arrival
     */
    @Basic(optional = false)
    @Column(name = "CUSTOMERSTA", nullable = false, length = 14)
    private String customerSta;

    /**
     * Synchronized scheduled time of arrival between agent and customer
     */
    @Basic(optional = false)
    @Column(name = "SYNCSTA", nullable = false, length = 14)
    private String syncSta;

    /**
     * Estimated time of arrival at destination e.g. given by trucker
     */
    @Basic(optional = false)
    @Column(name = "ETA", nullable = false, length = 14)
    private String eta;

    /**
     * Unload duration in days:hours:minutes
     */
    @Basic(optional = false)
    @Column(name = "UNLDUR", nullable = false, length = 6)
    private String unldur;

    /**
     * Unload gate
     */
    @Basic(optional = false)
    @Column(name = "GATE", nullable = false, length = 10)
    private String gate;

    @Basic(optional = false)
    @Column(name = "COMMENT", nullable = false, length = 30)
    private String comment;

    @Basic(optional = false)
    @Column(name = "CUSTOMER", nullable = false, length = 30)
    private String customer;

    @Basic(optional = false)
    @Version
    private int version;

    public Lap() {
        super();

        this.id = new LapPK("", "");
        this.lapstat = Lapstat.unknown;
        this.lapSeqn = 0;
        this.lapPrio = Prio.normal;
        this.agentSta = "";
        this.customerSta = "";
        this.syncSta = "";
        this.eta = "";
        this.unldur = "";
        this.gate = "";
        this.comment = "";
        this.customer = "";
        super.setUpdtDttm("");
        super.setUpdtGuiUser("");
    }

    public Lap(String tourId, String hopdest) {
        super();

        this.id = new LapPK(tourId, hopdest);
        this.lapstat = Lapstat.unknown;
        this.lapSeqn = 0;
        this.lapPrio = Prio.normal;
        this.agentSta = "";
        this.customerSta = "";
        this.syncSta = "";
        this.eta = "";
        this.unldur = "";
        this.gate = "";
        this.comment = "";
        this.customer = "";
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Lap(String tourId, String hopdest, Lapstat lapstat, int lapSeqn, Prio lapPrio, String agentSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String customer, String updtDttm, String updtGuiUser) {
        this.id = new LapPK(tourId, hopdest);
        this.lapstat = lapstat;
        this.lapSeqn = lapSeqn;
        this.lapPrio = lapPrio;
        this.agentSta = agentSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.customer = customer;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Lap(String tourId, String hopdest, String lapstat, int lapSeqn, String lapPrio, String agentSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String customer, String updtDttm, String updtGuiUser) {
        this.id = new LapPK(tourId, hopdest);
        this.lapstat = Lapstat.valueOf(lapstat);
        this.lapSeqn = lapSeqn;
        this.lapPrio = Prio.valueOf(lapPrio);
        this.agentSta = agentSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.customer = customer;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Lap(LapPK tourhopPK, Lapstat lapstat, int lapSeqn, Prio lapPrio, String agentSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String customer, String updtDttm, String updtGuiUser) {
        this.id = tourhopPK;
        this.lapstat = lapstat;
        this.lapSeqn = lapSeqn;
        this.lapPrio = lapPrio;
        this.agentSta = agentSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.customer = customer;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public Lap(LapPK tourhopPK, String lapstat, int lapSeqn, String lapPrio, String agentSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String customer, String updtDttm, String updtGuiUser) {
        this.id = tourhopPK;
        this.lapstat = Lapstat.valueOf(lapstat);
        this.lapSeqn = lapSeqn;
        this.lapPrio = Prio.valueOf(lapPrio);
        this.agentSta = agentSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.customer = customer;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public LapPK getId() {
        return id;
    }

    public void setId(LapPK id) {
        this.id = id;
    }

    public int getLapSeqn() {
        return lapSeqn;
    }

    public void setLapSeqn(int lapSeqn) {
        this.lapSeqn = lapSeqn;
    }

    public Prio getLapPrio() {
        return lapPrio;
    }

    public void setLapPrio(Prio lapPrio) {
        this.lapPrio = lapPrio;
    }

    public String getAgentSta() {
        return agentSta;
    }

    public void setAgentSta(String agentSta) {
        this.agentSta = agentSta;
    }

    public Lapstat getLapstat() {
        return lapstat;
    }

    public void setLapstat(Lapstat lapstat) {
        this.lapstat = lapstat;
    }

    public String getCustomerSta() {
        return customerSta;
    }

    public void setCustomerSta(String customerSta) {
        this.customerSta = customerSta;
    }

    public String getSyncSta() {
        return syncSta;
    }

    public void setSyncSta(String syncSta) {
        this.syncSta = syncSta;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getUnldur() {
        return unldur;
    }

    public void setUnldur(String unldur) {
        this.unldur = unldur;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lap)) {
            return false;
        }
        Lap other = (Lap) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Tourhop{" + "tourhopPK=" + id + ", lapstat=" + lapstat + ", lapSeqn=" + lapSeqn + ", lapPrio=" + lapPrio + ", agentSta=" + agentSta + ", customerSta=" + customerSta + ", syncSta=" + syncSta + ", eta=" + eta + ", unldur=" + unldur + ", gate=" + gate + ", comment=" + comment + ", customer=" + customer + super.toString() + ", version=" + version + '}';
    }
}
