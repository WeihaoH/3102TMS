/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Haolin Xia
 */
@Entity
public class TeamParameter implements Serializable {

 private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String courseCode;
    private String parameterId;
    private int minSizeOfTeam;
    private int maxSizeOfTeam;
    private Date formDeadline;

    public TeamParameter() {

    }
 
    public TeamParameter(String id) {
        this.parameterId = id;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public int getMinSizeOfTeam() {
        return minSizeOfTeam;
    }

    public void setMinSizeOfTeam(int minSizeOfTeam) {
        this.minSizeOfTeam = minSizeOfTeam;
    }

    public int getMaxSizeOfTeam() {
        return maxSizeOfTeam;
    }

    public void setMaxSizeOfTeam(int maxSizeOfTeam) {
        this.maxSizeOfTeam = maxSizeOfTeam;
    }

    public Date getFormDeadline() {
        return formDeadline;
    }

    public void setFormDeadline(Date formDeadline) {
        this.formDeadline = formDeadline;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parameterId != null ? parameterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamParameter)) {
            return false;
        }
        TeamParameter other = (TeamParameter) object;
        if ((this.parameterId == null && other.parameterId != null) || (this.parameterId != null && !this.parameterId.equals(other.parameterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Assignment[ id=" + parameterId + " ]";
    }
}
