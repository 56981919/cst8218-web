/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.appuser4;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bruce JIN
 */


@Entity
@Table(name = "APPUSER")
@XmlRootElement

//@DatabaseIdentityStoreDefinition(
//   dataSourceLookup = "${'java:comp/DefaultDataSource'}",
//   callerQuery = "#{'select password from app.AppUser where userid = ?'}",
//   groupsQuery = "select groupname from app.AppUser where userid = ?",
//   hashAlgorithm = PasswordHash.class,
//   priority = 10
//)

//@NamedQueries({
//    @NamedQuery(name = "AppUser.findAll", query = "SELECT c FROM AppUser c"),
//    @NamedQuery(name = "AppUser.findById", query = "SELECT c FROM AppUser c WHERE c.id = :id"),
//    @NamedQuery(name = "AppUser.findByBirthday", query = "SELECT c FROM AppUser c WHERE c.userID = :userID"),
//    @NamedQuery(name = "AppUser.findByEmail", query = "SELECT c FROM AppUser c WHERE c.password = :password"),
//    @NamedQuery(name = "AppUser.findByFirstname", query = "SELECT c FROM AppUser c WHERE c.groupName = :groupName"),
//    @NamedQuery(name = "AppUser.findByHomephone", query = "SELECT c FROM AppUser c WHERE c.homephone = :homephone"),
//    @NamedQuery(name = "AppUser.findByLastname", query = "SELECT c FROM AppUser c WHERE c.lastname = :lastname"),
//    @NamedQuery(name = "AppUser.findByMobilephone", query = "SELECT c FROM AppUser c WHERE c.mobilephone = :mobilephone")})

public class AppUser implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1)
    @Column(name = "USERID")
    private String userID;
    
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    
    @NotNull
    @Size(min = 1)
    @Column(name = "GROUPNAME")
    private String groupname;

    
    
    public AppUser() {
    }

    public AppUser(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userid) {
        this.userID = userid;
    }
    
    public String getPassword() {
    //        return password;
            return "";
    }

    public void setPassword(String pass) {  
        if(pass.equals("")&&!this.password.equals("")){
            return;
        }
        
     // initialize a PasswordHash object which will generate password hashes
     Instance<? extends PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
     PasswordHash passwordHash = instance.get();
     passwordHash.initialize(new HashMap<String,String>());  // todo: are the defaults good enough?
     // now we can generate a password entry for a given password
     String passwordEntry = pass; //pretend the user has chosen a password mySecretPassword
     passwordEntry = passwordHash.generate(passwordEntry.toCharArray());
     //at this point, passwordEntry refers to a salted/hashed password entry corresponding to mySecretPassword
    this.password =passwordEntry;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String name) {
        this.groupname = name;
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
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.appuser.APPUSER[ id=" + id + " ]";
    }
    
}
