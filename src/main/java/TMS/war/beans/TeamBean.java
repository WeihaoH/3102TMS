/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import persistence.Team;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import persistence.UserAccount;
import persistence.TeamParameter;
import TMS.war.beans.UserTeamRelationBean;
import persistence.TeamParameter;
import persistence.TeamParameterRelation;


/**
 *
 * @author RoyLiu
 */
@Named(value = "teamBean")
@SessionScoped
public class TeamBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private String status;
    // For find all teams
    private List<Team> teams;
    //For team and user query
    private Team teamInstance;
    private List<UserAccount> members;
    private List<UserAccount> candidates;
    private List<TeamParameter> parameters;
    //For team creation form
    private String teamName;
    private String liaisonId;
    private String courseCode;

    /**
     * Creates a new instance of TeamBean
     */
    public TeamBean() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLiaisonId() {
        return liaisonId;
    }

    public void setLiaisonId(String liaisonId) {
        this.liaisonId = liaisonId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Team getTeamInstance() {
        return teamInstance;
    }

    public void setTeamInstance(Team teamInstance) {
        this.teamInstance = teamInstance;
    }

    public void createTeam() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        UserAccount userAcc = (UserAccount) session.getAttribute("Student");
        Team team = new Team(UUID.randomUUID().toString(), teamName, userAcc.getUserId(), courseCode);
        try {
            persist(team);
            setStatus("Created.");
            System.out.println(userAcc.getUserId());
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UserTeamRelationBean userTeamRelationBean = (UserTeamRelationBean) facesContext.getApplication().createValueBinding("#{userTeamRelationBean}").getValue(facesContext);
            userTeamRelationBean.addMembertoTeam(team.getId(), userAcc.getUserId());

            Query query = em.createQuery("SELECT p FROM TeamParameter p WHERE p.courseCode = '"+courseCode+"'",TeamParameter.class);
            TeamParameter teamPara = (TeamParameter) query.getSingleResult();
            TeamParameterRelationBean teamParameterRelationBean = (TeamParameterRelationBean)facesContext.getApplication().createValueBinding("#{teamParameterRelationBean}").getValue(facesContext);
            teamParameterRelationBean.addTeamToCourse(team.getId(),teamPara.getParameterId());    
   
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            setStatus("Creation failed.");
        }

    }

    public String findAllTeams() {
        try {
            Query query = em.createQuery("SELECT t FROM Team t");
            setTeams(query.getResultList());
        } catch (Exception e) {
            System.err.println(e);
        }
        return "viewTeams";
    }
    
    public String getTeamDetails(String teamId){
        try {
            Query query = em.createQuery(
                "SELECT t FROM Team t" +
                " WHERE t.teamId = :teamId");
            query.setParameter("teamId",teamId);
            setTeamInstance((Team)query.getSingleResult());
            // find members and candidates and parameters
            System.out.println(getTeamInstance().toString());
        } catch (Exception e) {
            System.err.println(e);
        }
        return "teamInfo";
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
