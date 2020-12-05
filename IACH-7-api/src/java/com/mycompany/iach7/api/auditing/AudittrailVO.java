package com.mycompany.iach7.api.auditing;

import java.io.Serializable;
import java.util.Objects;

/**
 * Value Object for audittrail
 */
public class AudittrailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final long id;
    private String principal;
    private String clazz;
    private String method;
    private String dttm;

    public AudittrailVO(long id, String principal, String clazz, String method, String dttm) {
        this.id = id;
        this.principal = principal;
        this.clazz = clazz;
        this.method = method;
        this.dttm = dttm;
    }

    public long getId() {
        return id;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.principal);
        hash = 59 * hash + Objects.hashCode(this.clazz);
        hash = 59 * hash + Objects.hashCode(this.method);
        hash = 59 * hash + Objects.hashCode(this.dttm);
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
        final AudittrailVO other = (AudittrailVO) obj;
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
        return true;
    }

    @Override
    public String toString() {
        return "AuditingVO{" + "id=" + id + ", principal=" + principal + ", clazz=" + clazz + ", method=" + method + ", dttm=" + dttm + '}';
    }
}
