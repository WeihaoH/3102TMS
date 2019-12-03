package persistence;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T19:50:05")
@StaticMetamodel(TeamParameter.class)
public class TeamParameter_ { 

    public static volatile SingularAttribute<TeamParameter, Integer> minSizeOfTeam;
    public static volatile SingularAttribute<TeamParameter, String> parameterId;
    public static volatile SingularAttribute<TeamParameter, Date> formDeadline;
    public static volatile SingularAttribute<TeamParameter, Integer> maxSizeOfTeam;

}