package persistence;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;


@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T21:30:04")

@StaticMetamodel(Team.class)
public class Team_ { 

    public static volatile SingularAttribute<Team, String> teamName;
    public static volatile SingularAttribute<Team, String> teamId;
    public static volatile SingularAttribute<Team, String> courseCode;
    public static volatile SingularAttribute<Team, String> liaison;
    public static volatile SingularAttribute<Team, Boolean> isFull;
    public static volatile SingularAttribute<Team, Date> creationDate;

}