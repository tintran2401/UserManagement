/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TiTi
 */
@Entity
@Table(name = "tbl_PromotionList", catalog = "Lab_UserManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPromotionList.findAll", query = "SELECT t FROM TblPromotionList t")
    , @NamedQuery(name = "TblPromotionList.findAllCurrentUsernames", query = "SELECT t FROM TblPromotionList t WHERE t.createdDate = (SELECT MAX(pl.createdDate) FROM TblPromotionList pl WHERE pl.username = t.username)")
    , @NamedQuery(name = "TblPromotionList.findById", query = "SELECT t FROM TblPromotionList t WHERE t.id = :id")
    , @NamedQuery(name = "TblPromotionList.findCurrentUsername", query = "SELECT t FROM TblPromotionList t WHERE t.username = :username ORDER BY t.createdDate DESC")
    , @NamedQuery(name = "TblPromotionList.findByUsername", query = "SELECT t FROM TblPromotionList t WHERE t.username = :username ORDER BY t.createdDate")
    , @NamedQuery(name = "TblPromotionList.findByRank", query = "SELECT t FROM TblPromotionList t WHERE t.rank = :rank")
    , @NamedQuery(name = "TblPromotionList.findByCreatedDate", query = "SELECT t FROM TblPromotionList t WHERE t.createdDate = :createdDate")
    , @NamedQuery(name = "TblPromotionList.findByAction", query = "SELECT t FROM TblPromotionList t WHERE t.action = :action")})
public class TblPromotionList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Rank")
    private Integer rank;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "Action", length = 50)
    private String action;
    @JoinColumn(name = "Username", referencedColumnName = "Username")
    @ManyToOne
    private TblUser username;

    public TblPromotionList() {
    }

    public TblPromotionList(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TblUser getUsername() {
        return username;
    }

    public void setUsername(TblUser username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPromotionList)) {
            return false;
        }
        TblPromotionList other = (TblPromotionList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblPromotionList[ id=" + id + " ]";
    }

}
