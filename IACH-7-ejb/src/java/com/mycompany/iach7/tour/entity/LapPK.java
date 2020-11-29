package com.mycompany.iach7.tour.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary Key for Lap entries
 */
@Embeddable
public class LapPK implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;

    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String tourId;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String lapdest;

    public LapPK() {
    }

    public LapPK(String tourId, String lapdest) {
        this.tourId = tourId;
        this.lapdest = lapdest;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tourId != null ? tourId.hashCode() : 0);
        hash += (lapdest != null ? lapdest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LapPK)) {
            return false;
        }
        LapPK other = (LapPK) object;
        if ((this.tourId == null && other.tourId != null) || (this.tourId != null && !this.tourId.equals(other.tourId))) {
            return false;
        }
        if ((this.lapdest == null && other.lapdest != null) || (this.lapdest != null && !this.lapdest.equals(other.lapdest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LapPK[ tourId=" + tourId + ", lapdest=" + lapdest + " ]";
    }
}
