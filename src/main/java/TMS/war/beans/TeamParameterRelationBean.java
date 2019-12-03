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
import javax.transaction.UserTransaction;
import persistence.TeamParameterRelation;
import persistence.UserTeamRelation;

/**
 *
 * @author apie
 */
@Named(value = "teamParameterRelationBean")
@RequestScoped
public class TeamParameterRelationBean {

    
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private String teamid;
    private String id;
    
    /**
     * Creates a new instance of TeamParameterRelationBean
     */
    public TeamParameterRelationBean() {
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public UserTransaction getUtx() {
        return utx;
    }

    public void setUtx(UserTransaction utx) {
        this.utx = utx;
    }
    
    public void addTeamToCourse(String teamid, String parameterId){
        TeamParameterRelation tpr = new TeamParameterRelation();
        tpr.setId(UUID.randomUUID().toString());
        tpr.setParameterid(parameterId);
        tpr.setTeamid(teamid);
        persist(tpr);
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
}