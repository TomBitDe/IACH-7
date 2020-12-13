package com.mycompany.iach7.loadingtackle.pallet.entity;

import com.mycompany.iach7.util.GuiMasterData;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 */
@Entity
@Table(name = "PALLETTYPE", schema = "IACH7")
public class PalletType extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PALLETTYPE")
    private String palletType;

    @Version
    @Basic(optional = false)
    private int version;

    @OneToMany(mappedBy = "type", orphanRemoval = true)
    private List<Pallet> pallets;

    public String getPalletType() {
        return palletType;
    }

    public void setPalletType(String palletType) {
        this.palletType = palletType;
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
        hash += (palletType != null ? palletType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PalletType)) {
            return false;
        }
        PalletType other = (PalletType) object;
        if ((this.palletType == null && other.palletType != null) || (this.palletType != null && !this.palletType.equals(other.palletType))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PalletType{palletType=").append(palletType);
        sb.append(super.toString());
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
