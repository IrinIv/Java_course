package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeAlert();
    app.getContactHelper().returnHomePage();

  }


}
