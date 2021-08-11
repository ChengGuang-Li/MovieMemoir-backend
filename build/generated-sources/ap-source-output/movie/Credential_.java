package movie;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import movie.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-07T00:20:09")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, Date> signupdate;
    public static volatile SingularAttribute<Credential, String> passwordhs;
    public static volatile SingularAttribute<Credential, Person> pId;
    public static volatile SingularAttribute<Credential, Integer> cId;
    public static volatile SingularAttribute<Credential, String> username;

}