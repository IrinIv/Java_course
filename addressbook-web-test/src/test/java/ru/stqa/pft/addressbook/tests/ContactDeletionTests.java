package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper().goToContactPage();
    if (!app.getContactHelper().isThereAreContact()) {
      app.getContactHelper().createContact(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", "test1"), true);
    }

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnHomePage();

  }


}
