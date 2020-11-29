package com.mycompany.iach7.tour.entity;

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
 * Tour Event Tracking
 */
@Entity
@Table(name = "TOUREVENT", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Tourevent.findAll", query = "SELECT t FROM Tourevent t ORDER BY t.id")
    , @NamedQuery(name = "Tourevent.findByTourIdLapdest", query = "SELECT t FROM Tourevent t WHERE t.id.tourId LIKE :tourId AND t.id.lapdest LIKE :lapdest ORDER BY t.id.evDttm")
    , @NamedQuery(name = "Tourevent.findByTourId", query = "SELECT t FROM Tourevent t WHERE t.id.tourId = :tourId ORDER BY t.id.evDttm")
    , @NamedQuery(name = "Tourevent.findBetweenDates", query = "SELECT t.id.tourId FROM Tourevent t WHERE t.id.evDttm >= :minDttm AND t.id.evDttm <= :maxDttm ORDER BY t.id.evDttm DESC")
})
public class Tourevent implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected ToureventPK id;

    @Column(name = "LAPEVENTTYPE", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Lapeventtype lapeventtype;

    @Basic(optional = false)
    @Column(name = "EVENTTEXT", nullable = false)
    private String eventtext;

    @Basic(optional = false)
    @Column(name = "PROVIDERSTA", nullable = false, length = 14)
    private String providerSta;

    @Basic(optional = false)
    @Column(name = "CUSTOMERSTA", nullable = false, length = 14)
    private String customerSta;

    @Basic(optional = false)
    @Column(name = "SYNCSTA", nullable = false, length = 14)
    private String syncSta;

    @Basic(optional = false)
    @Column(name = "ETA", nullable = false, length = 14)
    private String eta;

    @Basic(optional = false)
    @Column(name = "UNLDUR", nullable = false, length = 6)
    private String unldur;

    @Basic(optional = false)
    @Column(name = "GATE", nullable = false, length = 10)
    private String gate;

    @Basic(optional = false)
    @Column(name = "COM1_TEXT", nullable = false)
    private String comment;

    @Column(name = "EVENTSTAT", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Eventstat eventstat;

    @Basic(optional = false)
    @Column(name = "EV_GUIUSER", nullable = false, length = 20)
    private String evGuiuser;

    @Basic(optional = false)
    @Version
    private int version;

    public Tourevent() {
        super();

        this.id = new ToureventPK("", "", "");
        this.lapeventtype = Lapeventtype.unknown;
        this.eventtext = "";
        this.providerSta = "";
        this.customerSta = "";
        this.syncSta = "";
        this.eta = "";
        this.unldur = "";
        this.gate = "";
        this.comment = "";
        this.eventstat = Eventstat.unknown;
        this.evGuiuser = "";
    }

    public Tourevent(String tourId, String lapdest, String evDttm, Lapeventtype lapeventtype, String eventtext, String providerSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, Eventstat eventstat, String evGuiuser) {
        this.id = new ToureventPK(tourId, lapdest, evDttm);
        this.lapeventtype = lapeventtype;
        this.eventtext = eventtext;
        this.providerSta = providerSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.eventstat = eventstat;
        this.evGuiuser = evGuiuser;
    }

    public Tourevent(String tourId, String lapdest, String evDttm, String lapeventtype, String eventtext, String providerSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String eventstat, String evGuiuser) {
        this.id = new ToureventPK(tourId, lapdest, evDttm);
        this.lapeventtype = Lapeventtype.valueOf(lapeventtype);
        this.eventtext = eventtext;
        this.providerSta = providerSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.eventstat = Eventstat.valueOf(eventstat);
        this.evGuiuser = evGuiuser;
    }

    public Tourevent(ToureventPK toureventPK, Lapeventtype lapeventtype, String eventtext, String providerSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, Eventstat eventstat, String evGuiuser) {
        this.id = toureventPK;
        this.lapeventtype = lapeventtype;
        this.eventtext = eventtext;
        this.providerSta = providerSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.eventstat = eventstat;
        this.evGuiuser = evGuiuser;
    }

    public Tourevent(ToureventPK toureventPK, String lapeventtype, String eventtext, String providerSta, String customerSta, String syncSta, String eta, String unldur, String gate, String comment, String eventstat, String evGuiuser) {
        this.id = toureventPK;
        this.lapeventtype = Lapeventtype.valueOf(lapeventtype);
        this.eventtext = eventtext;
        this.providerSta = providerSta;
        this.customerSta = customerSta;
        this.syncSta = syncSta;
        this.eta = eta;
        this.unldur = unldur;
        this.gate = gate;
        this.comment = comment;
        this.eventstat = Eventstat.valueOf(eventstat);
        this.evGuiuser = evGuiuser;
    }

    public ToureventPK getId() {
        return id;
    }

    public void setId(ToureventPK id) {
        this.id = id;
    }

    public Lapeventtype getLapeventtype() {
        return lapeventtype;
    }

    public void setLapeventtype(Lapeventtype lapeventtype) {
        this.lapeventtype = lapeventtype;
    }

    public String getEventtext() {
        return eventtext;
    }

    public void setEventtext(String eventtext) {
        this.eventtext = eventtext;
    }

    public String getProviderSta() {
        return providerSta;
    }

    public void setProviderSta(String providerSta) {
        this.providerSta = providerSta;
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

    public Eventstat getEventstat() {
        return eventstat;
    }

    public void setEventstat(Eventstat eventstat) {
        this.eventstat = eventstat;
    }

    public String getEvGuiuser() {
        return evGuiuser;
    }

    public void setEvGuiuser(String evGuiuser) {
        this.evGuiuser = evGuiuser;
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
        if (!(object instanceof Tourevent)) {
            return false;
        }
        Tourevent other = (Tourevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tourevent{" + "toureventPK=" + id + ", lapeventtype=" + lapeventtype + ", eventtext=" + eventtext + ", providerSta=" + providerSta + ", customerSta=" + customerSta + ", syncSta=" + syncSta + ", eta=" + eta + ", unldur=" + unldur + ", gate=" + gate + ", comment=" + comment + ", eventstat=" + eventstat + ", evGuiuser=" + evGuiuser + ", version=" + version + '}';
    }

}
