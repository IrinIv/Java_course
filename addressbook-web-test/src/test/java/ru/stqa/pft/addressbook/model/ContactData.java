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
   @Expose
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


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homephone != null ? !homephone.equals(that.homephone) : that.homephone != null) return false;
    if (mobilephone != null ? !mobilephone.equals(that.mobilephone) : that.mobilephone != null) return false;
    if (workphone != null ? !workphone.equals(that.workphone) : that.workphone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    return email3 != null ? email3.equals(that.email3) : that.email3 == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homephone != null ? homephone.hashCode() : 0);
    result = 31 * result + (mobilephone != null ? mobilephone.hashCode() : 0);
    result = 31 * result + (workphone != null ? workphone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    return result;
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
