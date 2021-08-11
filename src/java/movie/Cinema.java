/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ChengGuang Li
 */
@Entity
@Table(name = "CINEMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cinema.findAll", query = "SELECT c FROM Cinema c")
    , @NamedQuery(name = "Cinema.findByCNo", query = "SELECT c FROM Cinema c WHERE c.cNo = :cNo")
    , @NamedQuery(name = "Cinema.findByCName", query = "SELECT c FROM Cinema c WHERE c.cName = :cName")
    , @NamedQuery(name = "Cinema.findByLocation", query = "SELECT c FROM Cinema c WHERE c.location = :location")})
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "C_NO")
    private Integer cNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "C_NAME")
    private String cName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCATION")
    private long location;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cNo")
    private Collection<Memoir> memoirCollection;

    public Cinema() {
    }

    public Cinema(Integer cNo) {
        this.cNo = cNo;
    }

    public Cinema(Integer cNo, String cName, long location) {
        this.cNo = cNo;
        this.cName = cName;
        this.location = location;
    }

    public Integer getCNo() {
        return cNo;
    }

    public void setCNo(Integer cNo) {
        this.cNo = cNo;
    }

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public long getLocation() {
        return location;
    }

    public void setLocation(long location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Memoir> getMemoirCollection() {
        return memoirCollection;
    }

    public void setMemoirCollection(Collection<Memoir> memoirCollection) {
        this.memoirCollection = memoirCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cNo != null ? cNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cinema)) {
            return false;
        }
        Cinema other = (Cinema) object;
        if ((this.cNo == null && other.cNo != null) || (this.cNo != null && !this.cNo.equals(other.cNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "movie.Cinema[ cNo=" + cNo + " ]";
    }
    
}
