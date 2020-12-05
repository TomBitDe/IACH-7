package com.mycompany.iach7.auditing.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

/**
 * Audit database interaction for further investigation.<br>
 * The audittrail record has a generated PrimaryKey
 */
@Entity
@Table(name = "AUDITTRAIL", schema = "IACH7")
@NamedQueries({
    @NamedQuery(name = "Audittrail.findAll", query = "SELECT a FROM Audittrail a order by a.dttm DESC"),
    @NamedQuery(name = "Audittrail.findByQuery", query = "SELECT a FROM Audittrail a WHERE a.principal like :principal and a.clazz like :clazz and a.method like :method and a.dttm like :dttm order by a.dttm desc"),
    @NamedQuery(name = "Audittrail.findByBetweenQuery", query = "SELECT a FROM Audittrail a WHERE a.principal like :principal and a.clazz like :clazz and a.method like :method and a.dttm >= :fromDttm and a.dttm <= :toDttm order by a.dttm desc"),
    @NamedQuery(name = "Audittrail.deleteBetween", query = "DELETE FROM Audittrail a WHERE a.dttm >= :fromDttm and a.dttm <= :toDttm")
})
public class Audittrail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @TableGenerator(name = "audittrailIdStore", table = "AUDITTRAIL_ID_STORE",
             pkColumnName = "AUDITTRAIL_SEQ_NAME", pkColumnValue = "AUDITTRAIL.ID",
             valueColumnName = "AUDITTRAIL_SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "audittrailIdStore")
    private long id;

    @Basic(optional = false)
    @Column(name = "APRINCIPAL")
    private String principal;

    @Basic(optional = false)
    @Column(name = "ACLAZZ")
    private String clazz;

    @Basic(optional = false)
    @Column(name = "AMETHOD")
    private String method;

    @Basic(optional = false)
    @Column(name = "DTTM")
    private String dttm;

    @Version
    @Basic(optional = false)
    private int version;

    public Audittrail() {
        super();
    }

    public Audittrail(String principal, String clazz, String method, String dttm) {
        this();

        this.principal = principal;
        this.clazz = clazz;
        this.method = method;
        this.dttm = dttm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDttm() {
        return dttm;
    }

    public void setDttm(String dttm) {
        this.dttm = dttm;
    }

    public int getVersion() {
        return version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 53 * hash + Objects.hashCode(this.principal);
        hash = 53 * hash + Objects.hashCode(this.clazz);
        hash = 53 * hash + Objects.hashCode(this.method);
        hash = 53 * hash + Objects.hashCode(this.dttm);
        hash = 53 * hash + this.version;
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
        final Audittrail other = (Audittrail) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.principal, other.principal)) {
            return false;
        }
        if (!Objects.equals(this.clazz, other.clazz)) {
            return false;
        }
        if (!Objects.equals(this.method, other.method)) {
            return false;
        }
        if (!Objects.equals(this.dttm, other.dttm)) {
            return false;
        }
        if (this.version != other.version) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Audittrail{" + "id=" + id + ", principal=" + principal + ", clazz=" + clazz + ", method=" + method + ", dttm=" + dttm + ", version=" + version + '}';
    }

}
