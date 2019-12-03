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

    public void addMembertoTeam(String teamid, String userid) {
        UserTeamRelation utr = new UserTeamRelation();
        utr.setUuid(UUID.randomUUID().toString());
        utr.setUserid(userid);
        utr.setTeamid(teamid);
        persist(utr);
    }

    public void addMembertoTeam() {
        UserTeamRelation utr = new UserTeamRelation();
        utr.setUuid(UUID.randomUUID().toString());
        utr.setUserid(uid);
        utr.setTeamid(teamid);
        persist(utr);
    }

    public void acceptStudent(String uId, String tId) {

        try {
            UserTeamRelation newJoin = new UserTeamRelation();
            newJoin.setUuid(UUID.randomUUID().toString());

            //在前端显示并获取uid和teamid，放到这个bean的uid和teamid里
            addMembertoTeam(uId, tId);
            setStatus("New Student accepted.");
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            setStatus("Acceptence Faied.");
        }
    }

    //不确定放在哪个bean里，先放在这儿
    public void showTeamMembers() {
        try {
            Query query = em.createQuery("SELECT u FROM UserTeamRelation u WHERE u.teamid = '" + this.teamid + "'", UserTeamRelation.class);
            List<UserAccount> sutInTeam = (List<UserAccount>) query.getResultList();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
        }
    }
    
    public boolean checkInTeam(String uid){
        try {
            Query query = em.createQuery(
                "SELECT u FROM UserTeamRelation u" +
                " WHERE u.userid = :userId");
            query.setParameter("teamId",uid);
            if (query.getSingleResult() != null) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
    }
}
