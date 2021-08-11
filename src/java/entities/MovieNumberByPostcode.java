/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChengGuang Li
 */
@XmlRootElement
public class MovieNumberByPostcode implements Serializable{
  Integer pId;
  long postcode;
  long number;
  
  public MovieNumberByPostcode()
  {
  
  }
  
  public MovieNumberByPostcode(long postcode,long number)
  {
   this.postcode = postcode;
   this.number = number;
  }
  
  public long getPostCode()
  {
   return postcode;
  }
  
  public long getNumber()
  {
   return number;
  }
  
  public void setPostCode(long postcode)
  {
   this.postcode = postcode;
  }
    
  public void setNumber(long number)
  {
    this.number = number;
  }    
  
   public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
    
    @Override
    public String toString(){
        return "entities.MovieNumberByPostcode[pId="+pId+ "]";
    }
     
}
