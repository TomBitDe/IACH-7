package com.mycompany.iach7.slatime.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Service Level Agreement Time
 */
@Entity
@Table(name = "SLATIME", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "SlaTime.findAll", query = "SELECT s FROM SlaTime s order by s.id.provider, s.id.customer")})
public class SlaTime extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SlaTimePK id;

    @Basic(optional = false)
    @Column(name = "SLATIME")
    private int slaTime;

    @Basic(optional = false)
    @Column(name = "COMMENT")
    private String comment;

    @Version
    @Basic(optional = false)
    private int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SLA_ID")
    private ServiceLevelAgreement sla;

    public SlaTime() {
        super();

        this.id = new SlaTimePK("", "");
        this.slaTime = 0;
        this.comment = "";
        super.setUpdtDttm("");
        super.setUpdtGuiUser("");
    }

    public SlaTime(SlaTimePK id, int slaTime, String comment, String updtDttm, String updtGuiUser) {
        super();

        this.id = id;
        this.slaTime = slaTime;
        this.comment = comment;
        super.setUpdtDttm(updtDttm);
        super.setUpdtGuiUser(updtGuiUser);
    }

    public SlaTime(SlaTimePK id, int slaTime, String comment, String updtGuiUser) {
        super();

        this.id = id;
        this.slaTime = slaTime;
        this.comment = comment;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser(updtGuiUser);
    }

    public SlaTime(SlaTimePK id, int slaTime, String comment) {
        super();

        this.id = id;
        this.slaTime = slaTime;
        this.comment = comment;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser("SYSTEM");
    }

    public SlaTime(SlaTimePK id, int slaTime) {
        super();

        this.id = id;
        this.slaTime = slaTime;
        this.comment = "";
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser("SYSTEM");
    }

    public SlaTimePK getId() {
        return id;
    }

    public void setId(SlaTimePK id) {
        this.id = id;
    }

    /**
     * Get the SlaTime
     *
     * @return the time value in minutes
     */
    public int getSlaTime() {
        return slaTime;
    }

    /**
     * Set the SlaTime
     *
     * @param slaTime the value in minutes
     */
    public void setSlaTime(int slaTime) {
        this.slaTime = slaTime;
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

    public ServiceLevelAgreement getSla() {
        return sla;
    }

    public void setSla(ServiceLevelAgreement sla) {
        this.sla = sla;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.slaTime);
        hash = 97 * hash + Objects.hashCode(this.comment);
        hash = 97 * hash + this.version;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SlaTime other = (SlaTime) obj;
        if (this.version != other.version) {
            return false;
        }
        if (!Objects.equals(this.slaTime, other.slaTime)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SlaTime{id=").append(id);
        sb.append(", slaTime=").append(slaTime);
        sb.append(", comment=").append(comment);
        sb.append(", version=").append(version);
        sb.append(", sla=").append(sla);
        sb.append('}');
        return sb.toString();
    }
}
