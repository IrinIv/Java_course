package ru.stqa.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

   public void modify(int index, ContactData contact) {
    editContact(index);
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

  public void updateContact() {

    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact, boolean b) {
    fillContactForm(contact, true);
    submitContact();
    returnToContactPage();
  }

  public void delete(int index) {
    selectContact(index);
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

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String fname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(3)")).getText();
      String lname = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(2)")).getText();
      ContactData contact = new ContactData(id, fname, lname,null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
