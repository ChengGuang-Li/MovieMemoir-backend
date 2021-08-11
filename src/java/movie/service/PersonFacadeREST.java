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
import movie.Person;

/**
 *
 * @author ChengGuang Li
 */
@Stateless
@Path("movie.person")
public class PersonFacadeREST extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        System.out.print(entity.toString());
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
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
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByFirstname/{firstname}")
    @Produces("application/json")
    public List<Person> findByFirstname (@PathParam ("firstname") String firstname)
    {
       Query q = em.createNamedQuery("Person.findByFirstname");
       q.setParameter("firstname", firstname);
       return q.getResultList();     
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces("application/json")
    public List<Person> findBySurname (@PathParam ("surname") String surname)
    {
       Query q = em.createNamedQuery("Person.findBySurname");
       q.setParameter("surname", surname);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findByGender/{gender}")
    @Produces("application/json")
    public List<Person> findByGender (@PathParam ("gender") String gender)
    {
       Query q = em.createNamedQuery("Person.findByGender");
       q.setParameter("gender", gender);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findByDob/{dob}")
    @Produces("application/json")
    public List<Person> findByDob (@PathParam ("dob") String dob) throws ParseException
    {
       Query q = em.createNamedQuery("Person.findByDob");
        DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
        Date date = fmt.parse(dob);
       q.setParameter("dob", date);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findBySNumber/{sNumber}")
    @Produces("application/json")
    public List<Person> findBySNumber (@PathParam ("sNumber") Integer sNumber)
    {
       Query q = em.createNamedQuery("Person.findBySNumber");
       q.setParameter("sNumber", sNumber);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findBySName/{sName}")
    @Produces("application/json")
    public List<Person> findBySName (@PathParam ("sName") String sName)
    {
       Query q = em.createNamedQuery("Person.findBySName");
       q.setParameter("sName", sName);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findByStateOR/{stateOR}")
    @Produces("application/json")
    public List<Person> findByStateOR (@PathParam ("stateOR") String stateOR)
    {
       Query q = em.createNamedQuery("Person.findByStateOR");
       q.setParameter("stateOR", stateOR);
       return q.getResultList();     
    } 
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces("application/json")
    public List<Person> findByPostcode (@PathParam ("postcode") Integer postcode)
    {
       Query q = em.createNamedQuery("Person.findByPostcode");
       q.setParameter("postcode", postcode);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByCodeAndStateAndGender/{postcode}/{stateOR}/{gender}")
    @Produces("application/json")
    public List<Person> findByCodeAndStateAndGender (@PathParam ("postcode") Integer postcode,@PathParam ("stateOR") String stateOR,@PathParam ("gender") String gender)
    {
     TypedQuery<Person> q = em.createQuery("SELECT p FROM Person p WHERE p.postcode = :postcode AND p.stateOR = :stateOR AND p.gender = :gender",Person.class);
     q.setParameter("postcode",postcode);
     q.setParameter("stateOR",stateOR);
     q.setParameter("gender",gender);
     return q.getResultList();
    }
    
    @GET
    @Path("findByPId/{pId}")
    @Produces("application/json")
    public List<Person> findByPId (@PathParam ("pId") Integer pId)
    {
       Query q = em.createNamedQuery("Person.findByPId");
       q.setParameter("pId", pId);
       return q.getResultList();     
    }
    
    @GET
    @Path("findMaxPId")
    @Produces("application/json")
    public Object  findMaxPId(){
       List<Object[]> q = em.createQuery("select p.pId,p.firstname from Person p where p.pId = (select max(x.pId) from Person x)",Object[].class)
                             .getResultList();
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
       for(Object[] row : q)
       {
          JsonObject memoirObject = Json.createObjectBuilder().add("pId",(int)row[0]).add("firstname",(String)row[1]).build();
          arrayBuilder.add(memoirObject);     
       }
       JsonArray jArray = arrayBuilder.build();
       return jArray; 
}
    

    
}
