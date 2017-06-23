package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;

@XStreamAlias("contact")
 @Entity
 @Table(name = "addressbook")
public class ContactData {

   @XStreamOmitField
   @Id
   @Column(name = "id")
  private int id =Integer.MAX_VALUE; ;
   @Expose
   @Column(name = "firstname")
  private String firstname;
   @Expose
   @Column(name = "lastname")
  private String lastname;
   @Expose
   @Column(name = "address")
   @Type(type = "text")
  private String address;
   @Expose
   @Column(name = "home")
   @Type(type = "text")
  private String homephone;
   @Expose
   @Column(name = "mobile")
   @Type(type = "text")
  private String mobilephone;
   @Expose
   @Column(name = "work")
   @Type(type = "text")
  private String workphone;
   @Transient
  private String allphones;
   @Expose
   @Column(name = "email")
   @Type(type = "text")
  private String email;
   @Expose
   @Column(name = "email2")
   @Type(type = "text")
  private String email2;
   @Expose
   @Column(name = "email3")
   @Type(type = "text")
  private String email3;
   @Transient
  private String allemails;
   @Expose
   @Transient
  private String group;
   //@Expose
   @Transient
   private String photo;


  public int getId() {
    return id;
  }
  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getAllphones() {
    return allphones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllemails() {
    return allemails;
  }

  public String getGroup() {
    return group;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllemails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

   @Override
   public String toString() {
     return "ContactData{" +
             "firstname='" + firstname + '\'' +
             ", lastname='" + lastname + '\'' +
             ", address='" + address + '\'' +
             ", homephone='" + homephone + '\'' +
             ", mobilephone='" + mobilephone + '\'' +
             ", workphone='" + workphone + '\'' +
             ", email='" + email + '\'' +
             ", email2='" + email2 + '\'' +
             ", email3='" + email3 + '\'' +
             ", group='" + group + '\'' +
             '}';
   }

 }
