package com.mycompany.iach7.loadingtackle.pallet.entity;

import com.mycompany.iach7.util.GuiMasterData;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
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

    /**
     * The tare weight for this pallet type in [g]
     */
    @Basic(optional = false)
    @Column(name = "TAREWEIGHT")
    private long tareWeight;

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

    public long getTareWeight() {
        return tareWeight;
    }

    public void setTareWeight(long tareWeight) {
        this.tareWeight = tareWeight;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.palletType);
        hash = 67 * hash + (int) (this.tareWeight ^ (this.tareWeight >>> 32));
        hash = 67 * hash + this.version;
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
        final PalletType other = (PalletType) obj;
        if (this.tareWeight != other.tareWeight) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        if (!Objects.equals(this.palletType, other.palletType)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PalletType{palletType=").append(palletType);
        sb.append(", tareWeight=").append(tareWeight);
        sb.append(", version=").append(version);
        sb.append(", pallets=").append(pallets);
        sb.append('}');
        return sb.toString();
    }
}
