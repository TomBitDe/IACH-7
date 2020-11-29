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

    public String getUpdtDttm() {
        return updtDttm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getUpdtGuiUser() {
        return updtGuiUser;
    }

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
