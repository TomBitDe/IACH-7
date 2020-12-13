package com.mycompany.iach7.loadingtackle.pallet.entity;

import com.mycompany.iach7.loadingtackle.entity.LoadingTackle;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "PALLET", schema = "IACH7")
public class Pallet extends LoadingTackle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private PalletType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PalletType getType() {
        return type;
    }

    public void setType(PalletType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Pallet other = (Pallet) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pallet{id=").append(id);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
