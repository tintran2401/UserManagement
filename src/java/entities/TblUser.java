/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TiTi
 */
@Entity
@Table(name = "tbl_User", catalog = "Lab_UserManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t")
    ,    @NamedQuery(name = "TblUser.findAllActive", query = "SELECT t FROM TblUser t WHERE t.status = 'active'")
    , @NamedQuery(name = "TblUser.findByUsername", query = "SELECT t FROM TblUser t WHERE t.username = :username AND t.status = 'active'")
    , @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password")
    , @NamedQuery(name = "TblUser.findByName", query = "SELECT t FROM TblUser t WHERE t.name LIKE :name AND t.status = 'active'")
    , @NamedQuery(name = "TblUser.findByEmail", query = "SELECT t FROM TblUser t WHERE t.email = :email")
    , @NamedQuery(name = "TblUser.findByPhone", query = "SELECT t FROM TblUser t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblUser.findByPhoto", query = "SELECT t FROM TblUser t WHERE t.photo = :photo")
    , @NamedQuery(name = "TblUser.findByGroup", query = "SELECT t FROM TblUser t WHERE t.photo = :group")
    , @NamedQuery(name = "TblUser.findByStatus", query = "SELECT t FROM TblUser t WHERE t.status = :status")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Username", nullable = false, length = 100)
    private String username;
    @Basic(optional = false)
    @Column(name = "Password", nullable = false, length = 100)
    private String password;
    @Column(name = "Name", length = 500)
    private String name;
    @Column(name = "Email", length = 100)
    private String email;
    @Column(name = "Phone", length = 15)
    private String phone;
    @Column(name = "Photo", length = 200)
    private String photo;
    @Column(name = "Status", length = 20)
    private String status;
    @JoinColumn(name = "GroupId", referencedColumnName = "Id")
    @ManyToOne
    private TblUserGroup groupId;
    @OneToMany(mappedBy = "username")
    private Collection<TblPromotionList> tblPromotionListCollection;

    public TblUser() {
    }

    public TblUser(String username) {
        this.username = username;
    }

    public TblUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TblUserGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(TblUserGroup groupId) {
        this.groupId = groupId;
    }

    @XmlTransient
    public Collection<TblPromotionList> getTblPromotionListCollection() {
        return tblPromotionListCollection;
    }

    public void setTblPromotionListCollection(Collection<TblPromotionList> tblPromotionListCollection) {
        this.tblPromotionListCollection = tblPromotionListCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TblUser[ username=" + username + " ]";
    }

}
