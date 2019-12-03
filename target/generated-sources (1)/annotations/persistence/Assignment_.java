package persistence;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-12-02T17:41:14")
@StaticMetamodel(Assignment.class)
public class Assignment_ { 

    public static volatile SingularAttribute<Assignment, Integer> minSizeOfTeam;
    public static volatile SingularAttribute<Assignment, Date> formDeadline;
    public static volatile SingularAttribute<Assignment, String> assignmentId;
    public static volatile SingularAttribute<Assignment, String> assignmentName;
    public static volatile SingularAttribute<Assignment, Integer> maxSizeOfTeam;

}