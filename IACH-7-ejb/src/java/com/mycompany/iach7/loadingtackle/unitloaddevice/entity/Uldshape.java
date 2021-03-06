package com.mycompany.iach7.loadingtackle.unitloaddevice.entity;

import com.mycompany.iach7.util.GuiMasterData;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * The Shape of an ULD
 */
@Entity
@Table(name = "ULDSHAPE", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Uldshape.findAll", query = "SELECT u FROM Uldshape u")})
public class Uldshape extends GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "SHAPE")
    private String shape;

    @Basic(optional = false)
    @Column(name = "DESCR")
    private String descr;

    @Basic(optional = false)
    @Column(name = "RATING")
    private String rating;

    @Basic(optional = false)
    @Column(name = "MAXGROSSWGHT")
    private int maxgrosswght;

    @Basic(optional = false)
    @Column(name = "TAREWGHT")
    private int tarewght;

    @Basic(optional = false)
    @Column(name = "INTERNALVOLUME")
    private int internalvolume;

    @Basic(optional = false)
    @Column(name = "INTLENG")
    private int intleng;

    @Basic(optional = false)
    @Column(name = "INTWDTH")
    private int intwdth;

    @Basic(optional = false)
    @Column(name = "INTHGHT")
    private int inthght;

    @Basic(optional = false)
    @Column(name = "ALLLENG")
    private int allleng;

    @Basic(optional = false)
    @Column(name = "ALLWDTH")
    private int allwdth;

    @Basic(optional = false)
    @Column(name = "ALLHGHT")
    private int allhght;

    @Lob
    @Column(name = "THUMBNAIL", length = 64000)
    private byte[] thumbnail;

    @Lob
    @Column(name = "BIGPIC", length = 128000)
    private byte[] bigpic;

    @Version
    @Basic(optional = false)
    private int version;

    @OneToMany(mappedBy = "shape", cascade = CascadeType.ALL)
    private Set<Uldtype> uldtypeSet;

    public Uldshape() {
    }

    public Uldshape(String shape) {
        this.shape = shape;
    }

    public Uldshape(String shape, String descr, String rating, int maxgrosswght, int tarewght, int internalvolume, int intleng, int intwdth, int inthght, int allleng, int allwdth, int allhght, String updtdttm, String updtuser) {
        this.shape = shape;
        this.descr = descr;
        this.rating = rating;
        this.maxgrosswght = maxgrosswght;
        this.tarewght = tarewght;
        this.internalvolume = internalvolume;
        this.intleng = intleng;
        this.intwdth = intwdth;
        this.inthght = inthght;
        this.allleng = allleng;
        this.allwdth = allwdth;
        this.allhght = allhght;
        super.setUpdtGuiUser(updtuser);
        super.setUpdtDttm(updtdttm);
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getMaxgrosswght() {
        return maxgrosswght;
    }

    public void setMaxgrosswght(int maxgrosswght) {
        this.maxgrosswght = maxgrosswght;
    }

    public int getTarewght() {
        return tarewght;
    }

    public void setTarewght(int tarewght) {
        this.tarewght = tarewght;
    }

    public int getInternalvolume() {
        return internalvolume;
    }

    public void setInternalvolume(int internalvolume) {
        this.internalvolume = internalvolume;
    }

    public int getIntleng() {
        return intleng;
    }

    public void setIntleng(int intleng) {
        this.intleng = intleng;
    }

    public int getIntwdth() {
        return intwdth;
    }

    public void setIntwdth(int intwdth) {
        this.intwdth = intwdth;
    }

    public int getInthght() {
        return inthght;
    }

    public void setInthght(int inthght) {
        this.inthght = inthght;
    }

    public int getAllleng() {
        return allleng;
    }

    public void setAllleng(int allleng) {
        this.allleng = allleng;
    }

    public int getAllwdth() {
        return allwdth;
    }

    public void setAllwdth(int allwdth) {
        this.allwdth = allwdth;
    }

    public int getAllhght() {
        return allhght;
    }

    public void setAllhght(int allhght) {
        this.allhght = allhght;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public byte[] getBigpic() {
        return bigpic;
    }

    public void setBigpic(byte[] bigpic) {
        this.bigpic = bigpic;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public Set<Uldtype> getUldtypes() {
        return uldtypeSet;
    }

    public void setUldtypes(Set<Uldtype> uldtypeCollection) {
        this.uldtypeSet = uldtypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.shape);
        hash = 67 * hash + this.version;
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
        final Uldshape other = (Uldshape) obj;
        if (!Objects.equals(this.shape, other.shape)) {
            return false;
        }
        return this.version == other.version;
    }

    @Override
    public String toString() {
        return "Uldshape{" + "shape=" + shape + '}';
    }
}
