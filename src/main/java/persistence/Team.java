/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Table;

/**
 *
 * @author RoyLiu
 */
@Entity
@Table(name="Team7952021")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String teamId;
    private String teamName;
    private Date creationDate;
    private String liaison;
    //private List<UserAccount> teamMembers;
    //private List<Integer> papameters;
    private String courseCode;
    //private List<UserAccount> candidates;
    //private Date formDeadline;
    private boolean isFull;
    

    public Team() {
    
    }

    public Team(String id, String teamName, String liaison, String courseCode) {
        this.teamId = id;
        setCreationDate(new Date());
        setCourseCode(courseCode);
        setliaison(liaison);
        setTeamName(teamName);
    }

//    public List getTeamMembers() {
//        return teamMembers;
//    }

//    public List<Integer> getPapameters() {
//        return papameters;
//    }

//    public List getCandidates() {
//        return candidates;
//    }

//    public Date getFormDeadline() {
//        return formDeadline;
//    }
    
    //    public void setTeamMembers(List teamMembers) {
//        this.teamMembers = teamMembers;
//    }

//    public void setPapameters(List<Integer> papameters) {
//        this.papameters = papameters;
//    }

//    public void setCandidates(List candidates) {
//        this.candidates = candidates;
//    }

//    public void setFormDeadline(Date formDeadline) {
//        this.formDeadline = formDeadline;
//    }

    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    public Date getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public String getId() {
        return teamId;
    }

    public void setId(String id) {
        this.teamId = id;
    }

    public String getCourseCode() {
        return courseCode;
    }
    
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getliaison() {
        return liaison;
    }

    public void setliaison(String liaison) {
        this.liaison = liaison;
    }

    public boolean isIsFull() {
        return isFull;
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teamId != null ? teamId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.teamId == null && other.teamId != null) || (this.teamId != null && !this.teamId.equals(other.teamId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "3102TMS.persistence.Team[ id = " + teamId + " ]";
    }

}