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
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import persistence.TeamCandidaterRal;
import persistence.UserAccount;

/**
 *
 * @author 73987
 */
@Named(value = "teamCandidaterBean")
@RequestScoped
public class TeamCandidaterBean {

    private String teamid;
    
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

    /**
     * Creates a new instance of TeamCandidaterBean
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
    
    public void addtoCandidaterList(String teamId){
        System.out.println("TMS.war.beans.TeamCandidaterBean.addtoCandidaterList()");
        System.err.println(teamId);
        TeamCandidaterRal tcr = new TeamCandidaterRal();
        tcr.setId(UUID.randomUUID().toString());
        tcr.setTeamid(teamId);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            UserAccount userAcc = (UserAccount)session.getAttribute("Student");
            tcr.setCandidaterid(userAcc.getUserId());
        persist(tcr);
    }
    
}
