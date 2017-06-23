package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    //attach(By.name("photo"), contactData.getPhoto());

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
    contactCache = null;
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
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    closeAlert();
    contactCache = null;
    returnHomePage();
  }

  public boolean isThereAreContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void homePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;}
    click(By.linkText("home"));
  }
  private Contacts contactCache = null;

  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      String fname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(3)")).getText();
      String lname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(2)")).getText();
      String address = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(4)")).getText();
      String allPhones = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(6)")).getText();
      //String[] phones = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(6)")).getText().split("\n");
      //String email = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(5)")).getText();
      String allemails = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(5)")).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(fname).withLastname(lname).withAddress(address).withAllphones(allPhones).withAllemails(allemails));
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address).
            withHomephone(homephone).withMobilephone(mobilephone).withWorkphone(workphone).
            withEmail(email).withEmail2(email2).withEmail3(email3);

  }
}
