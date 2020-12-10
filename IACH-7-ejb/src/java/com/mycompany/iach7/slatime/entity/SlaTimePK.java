package com.mycompany.iach7.slatime.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Primary Key for SlaTime entries
 */
@Embeddable
public class SlaTimePK implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;

    protected String provider;
    protected String customer;

    public SlaTimePK() {
        super();
    }

    public SlaTimePK(String provider, String customer) {
        super();

        this.provider = provider;
        this.customer = customer;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.provider != null ? this.provider.hashCode() : 0);
        hash = 47 * hash + (this.customer != null ? this.customer.hashCode() : 0);
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
        final SlaTimePK other = (SlaTimePK) obj;
        if ((this.provider == null) ? (other.provider != null) : !this.provider.equals(other.provider)) {
            return false;
        }
        if ((this.customer == null) ? (other.customer != null) : !this.customer.equals(other.customer)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SlaTimePK{" + "provider=" + provider + ", customer=" + customer + '}';
    }
}
