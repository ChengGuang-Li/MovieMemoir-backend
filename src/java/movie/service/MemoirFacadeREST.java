/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie.service;

import entities.MovieNumberByPostcode;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import movie.Cinema;
import movie.Memoir;
import movie.Person;

/**
 *
 * @author ChengGuang Li
 */
@Stateless
@Path("movie.memoir")
public class MemoirFacadeREST extends AbstractFacade<Memoir> {

    @PersistenceContext(unitName = "MoviePU")
    private EntityManager em;

    public MemoirFacadeREST() {
        super(Memoir.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Memoir entity) {
        super.create(entity);
    }
    
     @POST
    @Path("postMemoirCinema")
    @Consumes({ MediaType.APPLICATION_JSON})
    public void postPersonCredential(Memoir entity) {
       Query qc = em.createQuery("select max(m.mNo) from Memoir m");
       int maxMno = (int)qc.getResultList().get(0)+1;
         entity.setMNo(maxMno);      
        super.create(entity);
    }
    
    
    
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Memoir entity) {
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
    public Memoir find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Memoir> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByMName/{mName}")
    @Produces("application/json")
    public List<Memoir> findByMName (@PathParam ("mName") String mName)
    {
       Query q = em.createNamedQuery("Memoir.findByMName");
       q.setParameter("mName", mName);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByMrlsdate/{mrlsdate}")
    @Produces("application/json")
    public List<Memoir> findByMrlsdate (@PathParam ("mrlsdate") String mrlsdate)throws ParseException
    {
       Query q = em.createNamedQuery("Memoir.findByMrlsdate");
       DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
       Date date = fmt.parse(mrlsdate);
       q.setParameter("mrlsdate", date);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByWcdatetime/{wcdatetime}")
    @Produces("application/json")
    public List<Memoir> findByWcdatetime (@PathParam ("wcdatetime") String wcdatetime) throws ParseException
    {
       Query q = em.createNamedQuery("Memoir.findByWcdatetime");
       DateFormat fmt = new SimpleDateFormat("yyyy-mm-dd");
       Date date = fmt.parse(wcdatetime);
       q.setParameter("wcdatetime", date);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByComment/{comment}")
    @Produces("application/json")
    public List<Memoir> findByComment (@PathParam ("comment") String comment)
    {
       Query q = em.createNamedQuery("Memoir.findByComment");
       q.setParameter("comment", comment);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByRScore/{rScore}")
    @Produces("application/json")
    public List<Memoir> findByRScore (@PathParam ("rScore") Double rScore)
    {
       Query q = em.createNamedQuery("Memoir.findByRScore");
       q.setParameter("rScore", rScore);
       return q.getResultList();     
    }
    
    @GET
    @Path("findByMNameAndCName/{mName}/{cName}")
    @Produces("application/json")
    public List<Memoir> findByMNameAndCName (@PathParam ("mName")String mName,@PathParam ("cName") String cName)
    {
     TypedQuery<Memoir> q = em.createQuery("SELECT m FROM Memoir m WHERE m.cNo.cName = :cName AND m.mName = :mName",Memoir.class);
     q.setParameter("cName",cName);
     q.setParameter("mName", mName);
     return q.getResultList();  
    }
    
    @GET
    @Path("findByMNameAndLocation/{mName}/{location}")
    @Produces("application/json")
    public List<Memoir> findByMNameAndLocation (@PathParam ("mName")String mName,@PathParam ("location") Integer location)
    {
       Query q = em.createNamedQuery("Memoir.findByMNameAndLocation");
       q.setParameter("location", location);
       q.setParameter("mName", mName);
       return q.getResultList();
    }
    
    @GET
    @Path("task4_a_Question/{pId}/{statingdate}/{endingdate}")
    @Produces({MediaType.APPLICATION_JSON}) 
    public List<MovieNumberByPostcode> task4_a_Question (@PathParam("pId") Integer pId,@PathParam("startingdate") java.sql.Date startingdate,@PathParam("endingdate") java.sql.Date endingdate)
    {
       //TypedQuery<MovieNumberByPostcode> q = em.createQuery("SELECT new entities.MovieNumberByPostcode(m.cNo.location,"+"(Select count(m1.mNo) from Memoir m1 where m1.pId.pId = :pId and m1.wcdatetime BETWEEN :startingdate AND :endingdate AND m1.cNo.cNo = m.cNo.cNo))"
               //+"From Memoir m WHERE m.pId.pId = :pId AND m.wcdatetime >= :startingdate AND m.wcdatetime <= :endingdate",MovieNumberByPostcode.class); 
        TypedQuery<MovieNumberByPostcode> q = em.createQuery("SELECT new entities.MovieNumberByPostcode(m.cNo.location,count(m.mNo))FROM Memoir As m WHERE m.pId.pId = :pId AND m.wcdatetime >= :startdate AND m.wcdatetime <= :endDate GROUP BY m.cNo.location",MovieNumberByPostcode.class); 
            q.setParameter("pId",pId);
            q.setParameter("startdate",startingdate);
            q.setParameter("endDate",endingdate);
            return q.getResultList();
    }
            
            
    @GET
    @Path("task4_a_findNumMoviesPerPostcode/{pId}/{statingdate}/{endingdate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_a_findNumMoviesPerPostcode (@PathParam("pId") Integer pId,@PathParam("statingdate") java.sql.Date statingdate,@PathParam("endingdate")java.sql.Date endingdate)
    {  
      List<Object[]> queryList = em.createQuery("SELECT m.cNo.location,count(m.mNo)FROM Memoir As m WHERE m.pId.pId = :pId AND m.wcdatetime >= :startdate AND m.wcdatetime <= :endDate GROUP BY m.cNo.location",Object[].class)
                                                .setParameter("pId", pId)
                                                .setParameter("startdate",statingdate)
                                                .setParameter("endDate", endingdate)
                                                .getResultList();
      
       JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
       for(Object[] row : queryList)
       {
          JsonObject memoirObject = Json.createObjectBuilder().add("location",(Long)row[0]).add("numOfMovies",(Long)row[1]).build();
          arrayBuilder.add(memoirObject);     
       }
       JsonArray jArray = arrayBuilder.build();
       return jArray; 
      
    }
    
    
    @GET
    @Path("task4_b_findNumMoviesPerMonth/{pId}/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_b_findNumMoviesPerMonth (@PathParam("pId") Integer pId,@PathParam("year") Integer Year) throws ParseException
    {  
       // DateFormat fmt = new SimpleDateFormat("yyyy");
        //Date dateX = fmt.parse(Year);
        List<Object[]> queryList1 = em.createQuery("SELECT CASE WHEN Func('MONTH',m.wcdatetime) =01 THEN 'Jan'"   
                                                           + "WHEN Func('MONTH',m.wcdatetime) =02 THEN 'Feb'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =03 THEN 'Mar'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =04 THEN 'Apr'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =05 THEN 'May'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =06 THEN 'Jun'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =07 THEN 'Jul'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =08 THEN 'Aug'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =09 THEN 'Sep'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =10 THEN 'Oct'"
                                                           +"WHEN Func('MONTH',m.wcdatetime) =11 THEN 'Dec'"
                                                           +"ELSE 'Dec' END,Count(m.mNo) From Memoir m Where Func('MONTH',m.wcdatetime) IN (SELECT Func('MONTH',c.wcdatetime)FROM Memoir c Where Func('Year',c.wcdatetime)= :year) And m.pId.pId = :pId GROUP BY Func('MONTH',m.wcdatetime)",Object[].class)                                                          
                                                           .setParameter("pId", pId)
                                                           .setParameter("year",Year)                                           
                                                           .getResultList();
        
             JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
             for(Object[] rowX : queryList1)
             {
                JsonObject memoirObject1 = Json.createObjectBuilder().add("MonthName",(String)rowX[0]).add("numberOfMoviesPerMonth",(Long)rowX[1]).build();
                 arrayBuilder1.add(memoirObject1);
             }
             
            JsonArray jArray1 = arrayBuilder1.build();
             return jArray1; 
    }   
    
    @GET
    @Path("task4_c_findhighestRatingMovieNameAndScoreAndrlsdate/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_c_findhighestRatingMovieNameAndScoreAndrlsdate (@PathParam("pId") Integer pId)
    {
      List<Object[]> qm = em.createQuery("SELECT m.mName,m.rScore,m.mrlsdate FROM Memoir m WHERE m.pId.pId = :pId AND m.rScore = (SELECT max(n.rScore) FROM Memoir n WHERE n.pId.pId = :pId)",Object[].class)
                          .setParameter("pId", pId)
                           .getResultList();
              JsonArrayBuilder arrayBuilder3 = Json.createArrayBuilder();
              for (Object[] rowU : qm)
              {
                JsonObject scoreObject = Json.createObjectBuilder().add("MovieName",(String)rowU[0]).add("MovieScore",(BigDecimal)rowU[1]).add("ReleaseDate",(String)rowU[2].toString()).build();
                arrayBuilder3.add(scoreObject);
              }
              JsonArray jArray3 = arrayBuilder3.build();
              return jArray3;
    }
    
    @GET
    @Path("task4_d_findMovieNameAndRLYearSameAsWTYear/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_d_findMovieNameAndRLYearSameAsWTYear (@PathParam("pId") Integer pId)
    {
      List<Object[]> qp = em.createQuery("SELECT m.mName,Func('YEAR',m.mrlsdate) FROM Memoir m WHERE m.pId.pId = :pId AND Func('YEAR',m.mrlsdate) = Func('YEAR',m.wcdatetime)",Object[].class)
                            .setParameter("pId", pId)
                            .getResultList();
      JsonArrayBuilder arrayBuilder4 = Json.createArrayBuilder();
      for (Object[] rowT : qp)
      {
        JsonObject releaseObject = Json.createObjectBuilder().add("MovieName",(String)rowT[0]).add("ReleaseYear",(Integer)rowT[1]).build();
        arrayBuilder4.add(releaseObject);
      }
       JsonArray jArray4 = arrayBuilder4.build();
       return jArray4;
    
    }
    
    
    @GET
    @Path("task4_e_findMovieNameANDReleaseYear/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_e_findMovieNameANDReleaseYear (@PathParam("pId") Integer pId)
    {
      List<Object[]> qx = em.createQuery("SELECT m.mName,m.mrlsdate FROM Memoir m WHERE EXISTS (SELECT n.mNo FROM Memoir n WHERE n.mName = m.mName AND n.mrlsdate != m.mrlsdate) AND m.pId.pId = :pId",Object[].class)
                           .setParameter("pId", pId)
                           .getResultList();
      JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
      for(Object[] rowY :qx)
      {
        JsonObject nameObject = Json.createObjectBuilder().add("MovieName",(String)rowY[0]).add("ReleaseDate",(String)rowY[1].toString()).build();
         arrayBuilder2.add(nameObject);       
      }
        
       JsonArray jArray2 = arrayBuilder2.build();
      return jArray2;
    }
    
    @GET
    @Path("task4_f_findRecentYearTop5MovieNameAndRLYearAndScore/{pId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object task4_f_findRecentYearTop5MovieNameAndRLYearAndScore (@PathParam("pId") Integer pId) throws ParseException
    { 
        
      Calendar stime = Calendar.getInstance();
      stime.setTime(new Date());
      stime.add(Calendar.YEAR,-1);
      //Date rtYear = stime.getTime();     
      SimpleDateFormat fmt = new SimpleDateFormat("yyyy");
      String date = fmt.format(stime.getTime());
      Integer i = Integer.parseInt(date); 
      List<Object[]> qw = em.createQuery("SELECT m.mName,m.mrlsdate,m.rScore FROM Memoir m WHERE m.pId.pId = :pId AND Func('YEAR',m.mrlsdate) > :RecentYear order by m.rScore desc",Object[].class)
                           .setParameter("pId",pId)
                           .setParameter("RecentYear",i)
                           .setMaxResults(5)
                           .getResultList();
       JsonArrayBuilder arrayBuilder6 = Json.createArrayBuilder();
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        for(Object[] rowW :qw)
        {
           JsonObject lastObject = Json.createObjectBuilder().add("MovieName",(String)rowW[0]).add("ReleaseDate",(String)format.format(rowW[1]).toString()).add("MovieScore",(BigDecimal)rowW[2]).build();
           arrayBuilder6.add(lastObject);
        
        }
         JsonArray jArray6 =  arrayBuilder6.build();
         return jArray6;
        
    }
    
    
}
