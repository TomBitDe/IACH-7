package com.mycompany.iach7.slatime.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * A Service Level Agreement
 */
@Entity
@Table(name = "SLA", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "ServiceLevelAgreement.findAll", query = "SELECT s FROM ServiceLevelAgreement s order by s.id")})
public class ServiceLevelAgreement extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "DESCR")
    @Basic(optional = false)
    private String descr;
    @Version
    @Basic(optional = false)
    private int version;

    @OneToMany(mappedBy = "sla", orphanRemoval = true)
    private List<SlaTime> slaTimeItems;

    public ServiceLevelAgreement() {
        super();
    }

    public ServiceLevelAgreement(String id) {
        this.id = id;
        this.descr = "";
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public ServiceLevelAgreement(String id, String descr) {
        this.id = id;
        this.descr = descr;
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public ServiceLevelAgreement(String id, String descr, String updtGuiUser) {
        this.id = id;
        this.descr = descr;
        super.setUpdtDttm(DttmMakeHelper.makeDttm17());
        super.setUpdtGuiUser(updtGuiUser);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesrc() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public List<SlaTime> getSlaTimeItems() {
        return slaTimeItems;
    }

    public void setSlaTimeItems(List<SlaTime> slaTimeItems) {
        this.slaTimeItems = slaTimeItems;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.descr);
        hash = 73 * hash + this.version;
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
        final ServiceLevelAgreement other = (ServiceLevelAgreement) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.descr, other.descr)) {
            return false;
        }
        return this.version == other.version;
    }

    @Override
    public String toString() {
        return "ServiceLevelAgreement{" + "id=" + id + ", descr=" + descr + ", version=" + version + '}';
    }
}
