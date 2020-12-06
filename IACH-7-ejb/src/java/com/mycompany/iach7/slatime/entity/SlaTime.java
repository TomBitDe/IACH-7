package com.mycompany.iach7.slatime.entity;

import com.mycompany.iach7.util.GuiMasterData;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    private String slaTime;

    @Basic(optional = false)
    @Column(name = "COM1_TEXT")
    private String comment;

    @Version
    @Basic(optional = false)
    private int version;

    public SlaTimePK getId() {
        return id;
    }

    public void setId(SlaTimePK id) {
        this.id = id;
    }

    public String getSlaTime() {
        return slaTime;
    }

    public void setSlaTime(String slaTime) {
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
}
