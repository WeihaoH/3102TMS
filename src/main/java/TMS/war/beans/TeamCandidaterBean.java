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
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import persistence.TeamCandidaterRal;
import persistence.TeamParameter;
import persistence.UserAccount;

/**
 *
 * @author 73987
 */
@Named(value = "teamCandidaterBean")
@SessionScoped
public class TeamCandidaterBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String teamid;
    private String candidaterid;
    private List<TeamCandidaterRal> candidaters;

    public List<TeamCandidaterRal> getCandidaters() {
        return candidaters;
    }

    public void setCandidaters(List<TeamCandidaterRal> candidaters) {
        this.candidaters = candidaters;
    }
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

    public void addMembertoTeam(String teamid){
        System.out.println("TCRBEAN"+ teamid);
        TeamCandidaterRal tcr = new TeamCandidaterRal();
        tcr.setUuid(UUID.randomUUID().toString());
        tcr.setTeamid(teamid);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount userAcc = (UserAccount) session.getAttribute("Student");
        tcr.setCandidaterid(userAcc.getUserId());
        persist(tcr);
    }
    
        public String findAllCandidater(String teamid){
        try {
            
            
            System.out.println("TCRBEAN find"+ teamid);            
            Query query = em.createQuery(
                "SELECT t FROM TeamCandidaterRal t" +
                " WHERE t.teamid = :teamId");
            query.setParameter("teamId",teamid);
            setCandidaters((List<TeamCandidaterRal>)query.getResultList());
                        
            setTeamid(teamid);
            
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            System.err.println(e);
        }
        return "candidateList";
    }
    
    
}
