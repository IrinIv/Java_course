package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper().goToContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", "test1"), true);
    app.getContactHelper().submitContact();
    app.returnToContactPage();
  }
}
