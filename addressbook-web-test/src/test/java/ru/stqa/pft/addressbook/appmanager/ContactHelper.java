package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.*;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactHelper extends BaseHelper {


  public ContactHelper(WebDriver wd) {
    super(wd);

  }

  public void submitContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));

  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void modify(ContactData contact) {
    //selectContactById(contact.getId());
    editContactById(contact.getId());
    fillContactForm((contact), false);
    updateContact();
    returnHomePage();
  }

  public void returnToContactPage() {

    wd.findElement(By.xpath("//div/div[4]/div/i/a[2]")).click();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectContactById(int id) {
   wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void closeAlert() {

    wd.switchTo().alert().accept();
  }

  public void returnHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;}
      click(By.linkText("home"));
    }


  public void editContact(int index) {
    wd.findElements(By.cssSelector("tr:nth-child(n) > td:nth-child(8)")).get(index).click();
  }

  public void editContactById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }



  public void updateContact() {

    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, true);
    submitContact();
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    closeAlert();
    returnHomePage();
  }

  public boolean isThereAreContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;}
    click(By.linkText("home"));
  }


  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String fname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(3)")).getText();
      String lname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(2)")).getText();
      contacts.add(new ContactData().withId(id).withFirstname(fname).withLastname(lname));
    }
    return contacts;
  }



}
