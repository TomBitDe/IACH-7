package com.mycompany.iach7.loadingtackle.unitloaddevice.entity;

import com.mycompany.iach7.util.GuiMasterData;
import com.mycompany.iach7.util.dttm.DttmMakeHelper;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The Type of an ULD
 */
@Entity
@Table(name = "ULDTYPE", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Uldtype.findAll", query = "SELECT u FROM Uldtype u")})
public class Uldtype extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ULDTYPE")
    private String uldtype;

    @Basic(optional = false)
    @Column(name = "DESCR")
    private String descr;

    @Basic(optional = false)
    @Column(name = "THEOLENG")
    private int theoleng;

    @Basic(optional = false)
    @Column(name = "THEOHGHT")
    private int theohght;

    @Basic(optional = false)
    @Column(name = "TAREWGHT")
    private int tarewght;

    @Basic(optional = false)
    @Column(name = "NELLENG")
    private int nelleng;

    @Basic(optional = false)
    @Column(name = "WELLENG")
    private int welleng;

    @Basic(optional = false)
    @Column(name = "DOORSIDE")
    private int doorside;

    @Version
    @Basic(optional = false)
    private int version;

    @JoinColumn(name = "SHAPE", referencedColumnName = "SHAPE")
    @ManyToOne(optional = false)
    private Uldshape shape;

    public Uldtype() {
        super();
    }

    public Uldtype(String uldtype) {
        this.uldtype = uldtype;
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public Uldtype(String uldtype, String descr, int theoleng, int theohght, int tarewght, int nelleng, int welleng, int doorside, String updtdttm, String updtuser, Uldshape shape) {
        this.uldtype = uldtype;
        this.descr = descr;
        this.theoleng = theoleng;
        this.theohght = theohght;
        this.tarewght = tarewght;
        this.nelleng = nelleng;
        this.welleng = welleng;
        this.doorside = doorside;
        this.shape = shape;
        super.setUpdtDttm(updtdttm);
        super.setUpdtGuiUser(updtuser);
    }

    public Uldtype(String uldtype, String descr, int theoleng, int theohght, int tarewght, int nelleng, int welleng, int doorside, Uldshape shape) {
        this.uldtype = uldtype;
        this.descr = descr;
        this.theoleng = theoleng;
        this.theohght = theohght;
        this.tarewght = tarewght;
        this.nelleng = nelleng;
        this.welleng = welleng;
        this.doorside = doorside;
        this.shape = shape;
        super.setUpdtDttm(DttmMakeHelper.makeDttm());
        super.setUpdtGuiUser("SYSTEM");
    }

    public String getUldtype() {
        return uldtype;
    }

    public void setUldtype(String uldtype) {
        this.uldtype = uldtype;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getTheoleng() {
        return theoleng;
    }

    public void setTheoleng(int theoleng) {
        this.theoleng = theoleng;
    }

    public int getTheohght() {
        return theohght;
    }

    public void setTheohght(int theohght) {
        this.theohght = theohght;
    }

    public int getTarewght() {
        return tarewght;
    }

    public void setTarewght(int tarewght) {
        this.tarewght = tarewght;
    }

    public int getNelleng() {
        return nelleng;
    }

    public void setNelleng(int nelleng) {
        this.nelleng = nelleng;
    }

    public int getWelleng() {
        return welleng;
    }

    public void setWelleng(int welleng) {
        this.welleng = welleng;
    }

    public int getDoorside() {
        return doorside;
    }

    public void setDoorside(int doorside) {
        this.doorside = doorside;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public Uldshape getShape() {
        return shape;
    }

    public void setShape(Uldshape shape) {
        this.shape = shape;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.uldtype);
        hash = 79 * hash + this.version;
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
        final Uldtype other = (Uldtype) obj;
        if (!Objects.equals(this.uldtype, other.uldtype)) {
            return false;
        }
        return this.version == other.version;
    }

    @Override
    public String toString() {
        return "Uldtype{" + "uldtype=" + uldtype + ", descr=" + descr + ", shape=" + shape.getShape() + '}';
    }
}
