/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movie;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ChengGuang Li
 */
@Entity
@Table(name = "MEMOIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memoir.findAll", query = "SELECT m FROM Memoir m")
    , @NamedQuery(name = "Memoir.findByMNo", query = "SELECT m FROM Memoir m WHERE m.mNo = :mNo")
    , @NamedQuery(name = "Memoir.findByMName", query = "SELECT m FROM Memoir m WHERE m.mName = :mName")
    , @NamedQuery(name = "Memoir.findByMrlsdate", query = "SELECT m FROM Memoir m WHERE m.mrlsdate = :mrlsdate")
    , @NamedQuery(name = "Memoir.findByWcdatetime", query = "SELECT m FROM Memoir m WHERE m.wcdatetime = :wcdatetime")
    , @NamedQuery(name = "Memoir.findByComment", query = "SELECT m FROM Memoir m WHERE m.comment = :comment")
    , @NamedQuery(name = "Memoir.findByMNameAndLocation", query = "SELECT m FROM Memoir m WHERE m.mName = :mName AND m.cNo.location = :location")
    , @NamedQuery(name = "Memoir.findByRScore", query = "SELECT m FROM Memoir m WHERE m.rScore = :rScore")})
public class Memoir implements Serializable {

    @Size(max = 100)
    @Column(name = "OMDBID")
    private String omdbid;
    @Size(max = 500)
    @Column(name = "IMAGE")
    private String image;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "M_NO")
    private Integer mNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "M_NAME")
    private String mName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MRLSDATE")
    @Temporal(TemporalType.DATE)
    private Date mrlsdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "WCDATETIME")
    @Temporal(TemporalType.DATE)
    private Date wcdatetime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "COMMENT")
    private String comment;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "R_SCORE")
    private BigDecimal rScore;
    @JoinColumn(name = "C_NO", referencedColumnName = "C_NO")
    @ManyToOne(optional = false)
    private Cinema cNo;
    @JoinColumn(name = "P_ID", referencedColumnName = "P_ID")
    @ManyToOne(optional = false)
    private Person pId;

    public Memoir() {
    }

    public Memoir(Integer mNo) {
        this.mNo = mNo;
    }

    public Memoir(Integer mNo, String mName, Date mrlsdate, Date wcdatetime, String comment, BigDecimal rScore) {
        this.mNo = mNo;
        this.mName = mName;
        this.mrlsdate = mrlsdate;
        this.wcdatetime = wcdatetime;
        this.comment = comment;
        this.rScore = rScore;
    }

    public Integer getMNo() {
        return mNo;
    }

    public void setMNo(Integer mNo) {
        this.mNo = mNo;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public Date getMrlsdate() {
        return mrlsdate;
    }

    public void setMrlsdate(Date mrlsdate) {
        this.mrlsdate = mrlsdate;
    }

    public Date getWcdatetime() {
        return wcdatetime;
    }

    public void setWcdatetime(Date wcdatetime) {
        this.wcdatetime = wcdatetime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getRScore() {
        return rScore;
    }

    public void setRScore(BigDecimal rScore) {
        this.rScore = rScore;
    }

    public Cinema getCNo() {
        return cNo;
    }

    public void setCNo(Cinema cNo) {
        this.cNo = cNo;
    }

    public Person getPId() {
        return pId;
    }

    public void setPId(Person pId) {
        this.pId = pId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mNo != null ? mNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memoir)) {
            return false;
        }
        Memoir other = (Memoir) object;
        if ((this.mNo == null && other.mNo != null) || (this.mNo != null && !this.mNo.equals(other.mNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "movie.Memoir[ mNo=" + mNo + " ]";
    }

    public String getOmdbid() {
        return omdbid;
    }

    public void setOmdbid(String omdbid) {
        this.omdbid = omdbid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
