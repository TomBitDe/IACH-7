package com.mycompany.iach7.loadingtackle.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 *
 */
@Entity
@Table(name = "LOADING_TACKLE", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "LoadingTackle.findAll", query = "SELECT lt FROM LoadingTackle lt")})
public class LoadingTackle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    /**
     * X dimension in [mm]
     */
    @Basic(optional = false)
    @Column(name = "XDIM")
    private int xDim;

    /**
     * Y dimension in [mm]
     */
    @Basic(optional = false)
    @Column(name = "YDIM")
    private int yDim;

    /**
     * Z dimension in [mm]
     */
    @Basic(optional = false)
    @Column(name = "ZDIM")
    private int zDim;

    /**
     * Weight in [g]
     */
    @Basic(optional = false)
    @Column(name = "WEIGHT")
    private int weight;

    /**
     * Nominal load in [g]
     */
    @Basic(optional = false)
    @Column(name = "NOMINALLOAD")
    private int nominalLoad;

    /**
     * Maximum load in [g]
     */
    @Basic(optional = false)
    @Column(name = "MAXIMUMLOAD")
    private int maximumLoad;

    /**
     * Optimal load in [g]
     */
    @Basic(optional = false)
    @Column(name = "OPTIMALLOAD")
    private int optimalLoad;

    /**
     * The material the loadingtackle is made from
     */
    @Basic(optional = false)
    @Column(name = "MATERIAL")
    private String material;

    /**
     * A comment assigned to the losdingtackle
     */
    @Basic(optional = false)
    @Column(name = "COMMENT")
    private String comment;

    @Version
    @Basic(optional = false)
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getXDim() {
        return xDim;
    }

    public void setXDim(int xDim) {
        this.xDim = xDim;
    }

    public int getYDim() {
        return yDim;
    }

    public void setYDim(int yDim) {
        this.yDim = yDim;
    }

    public int getZDim() {
        return zDim;
    }

    public void setZDim(int zDim) {
        this.zDim = zDim;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNominalLoad() {
        return nominalLoad;
    }

    public void setNominalLoad(int nominalLoad) {
        this.nominalLoad = nominalLoad;
    }

    public int getMaximumLoad() {
        return maximumLoad;
    }

    public void setMaximumLoad(int maximumLoad) {
        this.maximumLoad = maximumLoad;
    }

    public int getOptimalLoad() {
        return optimalLoad;
    }

    public void setOptimalLoad(int optimalLoad) {
        this.optimalLoad = optimalLoad;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
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
        if (!(object instanceof LoadingTackle)) {
            return false;
        }
        LoadingTackle other = (LoadingTackle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoadingTackle{" + "id=" + id + ", xDim=" + xDim + ", yDim=" + yDim + ", zDim=" + zDim + ", weight=" + weight + ", nominalLoad=" + nominalLoad + ", maximumLoad=" + maximumLoad + ", optimalLoad=" + optimalLoad + ", material=" + material + ", comment=" + comment + ", version=" + version + '}';
    }
}
