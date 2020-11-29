package com.mycompany.iach7.tour.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary Key for Tourevent entries
 */
@Embeddable
public class ToureventPK implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;

    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String tourId;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String lapdest;
    @Basic(optional = false)
    @Column(nullable = false, length = 17)
    private String evDttm;

    public ToureventPK() {
    }

    public ToureventPK(String tourId, String lapdest, String evDttm) {
        this.tourId = tourId;
        this.lapdest = lapdest;
        this.evDttm = evDttm;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getLapdest() {
        return lapdest;
    }

    public void setLapdest(String lapdest) {
        this.lapdest = lapdest;
    }

    public String getEvDttm() {
        return evDttm;
    }

    public void setEvDttm(String evDttm) {
        this.evDttm = evDttm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourId != null ? tourId.hashCode() : 0);
        hash += (lapdest != null ? lapdest.hashCode() : 0);
        hash += (evDttm != null ? evDttm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ToureventPK)) {
            return false;
        }
        ToureventPK other = (ToureventPK) object;
        if ((this.tourId == null && other.tourId != null) || (this.tourId != null && !this.tourId.equals(other.tourId))) {
            return false;
        }
        if ((this.lapdest == null && other.lapdest != null) || (this.lapdest != null && !this.lapdest.equals(other.lapdest))) {
            return false;
        }
        if ((this.evDttm == null && other.evDttm != null) || (this.evDttm != null && !this.evDttm.equals(other.evDttm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TourhopPK[ tourId=" + tourId + ", lapdest=" + lapdest + ", evDtttm=" + evDttm + " ]";
    }
}
