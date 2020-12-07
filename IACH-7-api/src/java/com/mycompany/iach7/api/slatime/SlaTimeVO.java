package com.mycompany.iach7.api.slatime;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Service Level Agreement Time Value Object
 */
public class SlaTimeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String provider;
    private String customer;
    private int slaTime;
    private String comment;
    private String updtDttm;
    private String updtGuiUser;

    public SlaTimeVO(String provider, String customer, int slaTime, String comment, String updtDttm, String updtGuiUser) {
        this.provider = provider;
        this.customer = customer;
        this.slaTime = slaTime;
        this.comment = comment;
        this.updtDttm = updtDttm;
        this.updtGuiUser = updtGuiUser;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getSlaTime() {
        return slaTime;
    }

    public void setSlaTime(int slaTime) {
        this.slaTime = slaTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdtDttm() {
        return updtDttm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getUpdtGuiUser() {
        return updtGuiUser;
    }

    public void setUpdtTrmuser(String updtGuiUser) {
        this.updtGuiUser = updtGuiUser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.provider);
        hash = 89 * hash + Objects.hashCode(this.customer);
        hash = 89 * hash + Objects.hashCode(this.slaTime);
        hash = 89 * hash + Objects.hashCode(this.comment);
        hash = 89 * hash + Objects.hashCode(this.updtDttm);
        hash = 89 * hash + Objects.hashCode(this.updtGuiUser);
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
        final SlaTimeVO other = (SlaTimeVO) obj;
        if (!Objects.equals(this.provider, other.provider)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.slaTime, other.slaTime)) {
            return false;
        }
        if (!Objects.equals(this.comment, other.comment)) {
            return false;
        }
        if (!Objects.equals(this.updtDttm, other.updtDttm)) {
            return false;
        }
        if (!Objects.equals(this.updtGuiUser, other.updtGuiUser)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SlaTimeVO{provider=").append(provider);
        sb.append(", customer=").append(customer);
        sb.append(", slaTime=").append(slaTime);
        sb.append(", comment=").append(comment);
        sb.append(", updtDttm=").append(updtDttm);
        sb.append(", updtGuiUser=").append(updtGuiUser);
        sb.append('}');
        return sb.toString();
    }
}
