package com.mycompany.iach7.loadingtackle.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@MappedSuperclass
public class LoadingTackle implements Serializable {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "LoadingTackle{" + "xDim=" + xDim + ", yDim=" + yDim + ", zDim=" + zDim + ", weight=" + weight + ", nominalLoad=" + nominalLoad + ", maximumLoad=" + maximumLoad + ", optimalLoad=" + optimalLoad + ", material=" + material + ", comment=" + comment + '}';
    }
}
