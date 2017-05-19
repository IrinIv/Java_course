package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactHelper extends BaseHelper {


  public ContactHelper(FirefoxDriver wd) {
    super(wd);

  }

  public void submitContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));

  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFirstname());
    type(By.name("lastname"),contactData.getLastname() );
    type(By.name("home"),contactData.getHomephone());
    type(By.name("mobile"),contactData.getMobilephone());
    type(By.name("email"),contactData.getEmail());
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void returnHomePage() {
    click(By.linkText("home"));
  }

  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void updateContact() {

    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }
}
