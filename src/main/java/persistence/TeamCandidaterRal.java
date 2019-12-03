/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author 73987
 */
@Entity
@Table(name="TeamCandidaterRal7952021")
public class TeamCandidaterRal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String uuid;
    private String teamid;
    private String candidaterid;

    public String getCandidaterid() {
        return candidaterid;
    }

    public void setCandidaterid(String candidaterid) {
        this.candidaterid = candidaterid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }
    
    public String getTeamid() {
        return teamid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uuid != null ? uuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamCandidaterRal)) {
            return false;
        }
        TeamCandidaterRal other = (TeamCandidaterRal) object;
        if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.TeamCandidaterRal[ id=" + uuid + " ]";
    }
    
}
