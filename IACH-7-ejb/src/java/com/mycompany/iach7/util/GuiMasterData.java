package com.mycompany.iach7.util;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Master data for all entities created by GUI dialogs.
 */
@MappedSuperclass
public class GuiMasterData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "UPDTDTTM")
    private String updtDttm;

    @Basic(optional = false)
    @Column(name = "UPDTGUIUSER")
    private String updtGuiUser;

    /**
     * Get the update DTTM
     *
     * @return the DTTM
     */
    public String getUpdtDttm() {
        return updtDttm;
    }

    /**
     * Set the update DTTM
     *
     * @param updtDttm the DTTM value
     */
    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    /**
     * Get the update GUI user name
     *
     * @return the users name
     */
    public String getUpdtGuiUser() {
        return updtGuiUser;
    }

    /**
     * Set the update GUI users name
     *
     * @param updtGuiUser the users name
     */
    public void setUpdtGuiUser(String updtGuiUser) {
        this.updtGuiUser = updtGuiUser;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GuiMasterData{updtDttm=").append(updtDttm);
        sb.append(", updtGuiUser=").append(updtGuiUser);
        sb.append('}');
        return sb.toString();
    }
}
