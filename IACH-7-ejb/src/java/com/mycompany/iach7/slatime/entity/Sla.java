package com.mycompany.iach7.slatime.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
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
    @NamedQuery(name = "Sla.findAll", query = "SELECT s FROM Sla s order by s.id")})
public class Sla extends GuiMasterData implements Serializable {
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
    private Set<SlaTime> slaTimeItems;

    public Sla() {
        super();
    }

    public Sla(String id) {
        super();

        this.id = id;
        this.descr = "";
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Sla(String id, String descr) {
        super();

        this.id = id;
        this.descr = descr;
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Sla(String id, String descr, String updtGuiUser) {
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

    public Set<SlaTime> getSlaTimeItems() {
        return slaTimeItems;
    }

    public void setSlaTimeItems(Set<SlaTime> slaTimeItems) {
        this.slaTimeItems = slaTimeItems;
    }

    public void addSlaTimeItem(SlaTime item) {
        this.slaTimeItems.add(item);
        item.setSla(this);
    }

    public void addSlaTimeItems(Set<SlaTime> items) {
        this.slaTimeItems.addAll(items);
        items.forEach(elem -> {
            elem.setSla(this);
        });
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
        final Sla other = (Sla) obj;
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
