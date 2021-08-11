/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import movie.Credential;
import movie.Person;

/**
 *
 * @author ChengGuang Li
 */
@Stateless
@Path("movie.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }
    
    @POST
    @Path("postPersonCredential")
    @Consumes({ MediaType.APPLICATION_JSON})
    public void postPersonCredential(Credential entity) {
        Person p  = entity.getPId();
        Query q = em.createQuery("select max(p.pId) from Person p");
         int maxPid =   (int) q.getResultList().get(0)   +1;
         p.setPId(maxPid);
         em.persist(p);
         em.flush();
         Query qc = em.createQuery("select max(c.cId) from Credential c");
         int maxCid = (int)qc.getResultList().get(0)   +1;
         entity.setCId(maxCid);
        
        super.create(entity);
    }
    
    

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("findByUsername/{username}")
    @Produces("application/json")
    public List<Credential> findByUsername (@PathParam ("username") String username)
    {
       Query q = em.createNamedQuery("Credential.findByUsername");
       q.setParameter("username", username);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findByPasswordhs/{passwordhs}")
    @Produces("application/json")
    public List<Credential> findByPasswordhs (@PathParam ("passwordhs") String passwordhs)
    {
       Query q = em.createNamedQuery("Credential.findByPasswordhs");
       q.setParameter("passwordhs", passwordhs);
       return q.getResultList();     
    }
    
    @GET
    @Path("findBySignupdate/{signupdate}")
    @Produces("application/json")
    public List<Credential> findBySignupdate (@PathParam ("signupdate") String sign ) throws ParseException
    {
       Query q = em.createNamedQuery("Credential.findBySignupdate");
       DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
       Date date = fmt.parse(sign);
       q.setParameter("signupdate", date);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByUserNameAndPassword/{username}/{password}")
    @Produces("application/json")
    public List<Person> findByUserNameAndPassword (@PathParam ("username")String username,@PathParam ("password") String password)
    {
      TypedQuery<Person> query = em.createQuery("SELECT c.pId FROM Credential c where c.username= :username And c.passwordhs=:password",Person.class)
                                    .setParameter("username",username)
                                    .setParameter("password",password);
      return query.getResultList();
    }  
    
    @GET
    @Path("findMaxCId")
    @Produces("application/json")
    public Object  findMaxCId(){
       List<Object[]> q = em.createQuery("select c.cId,c.username from Credential c where c.cId = (select max(d.cId) from Credential d)",Object[].class)
                             .getResultList();
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
       for(Object[] row : q)
       {
          JsonObject memoirObject = Json.createObjectBuilder().add("cId",(int)row[0]).add("username",(String)row[1]).build();
          arrayBuilder.add(memoirObject);     
       }
       JsonArray jArray = arrayBuilder.build();
       return jArray; 
}
}
