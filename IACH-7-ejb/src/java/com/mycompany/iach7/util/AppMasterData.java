package com.mycompany.iach7.util;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Master data for all application managed entities (not created by GUI dialogs).
 */
@MappedSuperclass
public class AppMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "UPDTDTTM")
    private String updtDttm;

    @Basic(optional = false)
    @Column(name = "UPDTUSER")
    private String updtUser;

    public String getUpdtDttm() {
        return updtDttm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getUpdtUser() {
        return updtUser;
    }

    public void setUpdtUser(String updtUser) {
        this.updtUser = updtUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppMasterData{updtDttm=").append(updtDttm);
        sb.append(", updtUser=").append(updtUser);
        sb.append('}');
        return sb.toString();
    }

}
