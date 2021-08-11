package movie;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import movie.Cinema;
import movie.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-07T00:20:09")
@StaticMetamodel(Memoir.class)
public class Memoir_ { 

    public static volatile SingularAttribute<Memoir, String> image;
    public static volatile SingularAttribute<Memoir, Date> wcdatetime;
    public static volatile SingularAttribute<Memoir, Cinema> cNo;
    public static volatile SingularAttribute<Memoir, BigDecimal> rScore;
    public static volatile SingularAttribute<Memoir, String> comment;
    public static volatile SingularAttribute<Memoir, Person> pId;
    public static volatile SingularAttribute<Memoir, String> mName;
    public static volatile SingularAttribute<Memoir, String> omdbid;
    public static volatile SingularAttribute<Memoir, Integer> mNo;
    public static volatile SingularAttribute<Memoir, Date> mrlsdate;

}