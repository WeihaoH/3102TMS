/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TMS.war.beans;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import persistence.TeamParameter;

/**
 *
 * @author Haolin Xia
 */
@Named(value = "teamParameterBean")
@RequestScoped
public class TeamParameterBean {
    
    private String courseCode;
    private String teamParameterId;
    private int minSizeOfTeam;
    private int maxSizeOfTeam;
    private Date formDeadline;
    private String status;
    @PersistenceContext(unitName = "TMS-PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    /**
     * Creates a new instance of TeamParameterBean
     */
    public TeamParameterBean() {
    }

    public String getTeamParameterId() {
        return teamParameterId;
    }

    public void setTeamParameterId(String teamParameterId) {
        this.teamParameterId = teamParameterId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public void setParameter(){
        try {
            TeamParameter teamParameter = new TeamParameter(UUID.randomUUID().toString());
            teamParameter.setParameterId(teamParameterId);
            teamParameter.setCourseCode(courseCode);
            teamParameter.setFormDeadline(formDeadline);
            teamParameter.setMinSizeOfTeam(minSizeOfTeam);
            teamParameter.setMaxSizeOfTeam(maxSizeOfTeam);
            persist(teamParameter);
            setStatus("Team Structure Created.");
            
        } catch (RuntimeException ex ) {
            Logger.getLogger(TeamParameterBean.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
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
