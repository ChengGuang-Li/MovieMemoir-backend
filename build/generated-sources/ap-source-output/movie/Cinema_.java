package movie;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import movie.Memoir;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-07T00:20:09")
@StaticMetamodel(Cinema.class)
public class Cinema_ { 

    public static volatile SingularAttribute<Cinema, Integer> cNo;
    public static volatile SingularAttribute<Cinema, String> cName;
    public static volatile CollectionAttribute<Cinema, Memoir> memoirCollection;
    public static volatile SingularAttribute<Cinema, Long> location;

}