/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.UserTeamRelation;

/**
 *
 * @author 73987
 */
@Named(value = "teamCandidaterBean")
@RequestScoped
public class TeamCandidaterBean {
    private String teamid;
    private String candidaterid;
    
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;


    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getCandidaterid() {
        return candidaterid;
    }

    public void setCandidaterid(String candidaterid) {
        this.candidaterid = candidaterid;
    }

    /**
     * Creates a new instance of UserTeamRelationBean
     */
    public TeamCandidaterBean() {
    }
    
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void addMembertoTeam(String teamid, String userid){
        UserTeamRelation utr = new UserTeamRelation();
        utr.setUuid(UUID.randomUUID().toString());
        utr.setTeamid(teamid);
        persist(utr);
    }
    
}
