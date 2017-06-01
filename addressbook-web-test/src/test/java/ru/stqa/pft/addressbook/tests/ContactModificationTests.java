package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getContactHelper().goToHomePage();
    if (!app.getContactHelper().isThereAreContact()) {
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", null), true);
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", null), false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}
