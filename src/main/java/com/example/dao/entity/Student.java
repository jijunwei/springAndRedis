package com.example.dao.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * student
 * @author 
 */
@Table(name="student")
public class Student implements Serializable {
    @NotEmpty
    private String SNO;

    @NotEmpty
    private String SNAME;

    @NotEmpty
    private String SSEX;

    private static final long serialVersionUID = 1L;

    public String getSNO() {
        return SNO;
    }

    public void setSNO(String SNO) {
        this.SNO = SNO;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getSSEX() {
        return SSEX;
    }

    public void setSSEX(String SSEX) {
        this.SSEX = SSEX;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Student other = (Student) that;
        return (this.getSNO() == null ? other.getSNO() == null : this.getSNO().equals(other.getSNO()))
            && (this.getSNAME() == null ? other.getSNAME() == null : this.getSNAME().equals(other.getSNAME()))
            && (this.getSSEX() == null ? other.getSSEX() == null : this.getSSEX().equals(other.getSSEX()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSNO() == null) ? 0 : getSNO().hashCode());
        result = prime * result + ((getSNAME() == null) ? 0 : getSNAME().hashCode());
        result = prime * result + ((getSSEX() == null) ? 0 : getSSEX().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", SNO=").append(SNO);
        sb.append(", SNAME=").append(SNAME);
        sb.append(", SSEX=").append(SSEX);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}