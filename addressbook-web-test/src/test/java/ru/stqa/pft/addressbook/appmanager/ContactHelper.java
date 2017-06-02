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


  public void editContact() {
    click(By.cssSelector("img[alt='Edit']"));
  }

  public void updateContact() {

    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void createContact(ContactData contact, boolean b) {
    fillContactForm(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", "test1"), true);
    submitContact();
    returnToContactPage();


  }

  public boolean isThereAreContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void goToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;}
    click(By.linkText("home"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, "Irina", "Iva",null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
