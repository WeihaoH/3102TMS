/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistence.TeamCandidaterRal;
import persistence.TeamParameter;
import persistence.UserAccount;
import persistence.UserTeamRelation;

/**
 *
 * @author 73987
 */
@Named(value = "userTeamRelationBean")
@SessionScoped
public class UserTeamRelationBean implements Serializable{
    private String teamid;
    private String id;
    private String status;
    private String uid;

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
    
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Creates a new instance of UserTeamRelationBean
     */
    public UserTeamRelationBean() {
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
        utr.setUserid(userid);
        utr.setTeamid(teamid);
        persist(utr);
    }
    
    
        public void acceptStudent(String tId,String uId){
        addMembertoTeam(tId,uId);
        try{
            Query query = em.createNativeQuery(
                "DELETE FROM TeamCandidaterRal t" +
                " WHERE t.teamid = :teamId AND t.candidaterid = candidaterId");
            query.setParameter("teamId",tId);
            query.setParameter("candidaterId",uId);
            query.executeUpdate();           
                         
       
        setStatus("New Student accepted.");
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            setStatus("Acceptence Faied.");
            }
        }
        
        public void showTeamMembers(){
            try{
            Query query = em.createQuery("SELECT u FROM UserTeamRelation u WHERE u.teamid = '"+this.teamid+"'",UserTeamRelation.class);
            List<UserAccount> sutInTeam = (List<UserAccount>) query.getResultList();
            } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            }
        }
   
}
