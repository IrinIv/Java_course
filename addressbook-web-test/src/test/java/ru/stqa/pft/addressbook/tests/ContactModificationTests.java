package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){

    app.getContactHelper().goToHomePage();
    if (!app.getContactHelper().isThereAreContact()) {
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("Irina", "Iva", null, null, null, null), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();

    //app.getContactHelper().selectContact(3);
    app.getContactHelper().editContact(before.size()-1);
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "5", "5", null, null, null, null);
    app.getContactHelper().fillContactForm((contact), false);
    app.getContactHelper().updateContact();
    app.getContactHelper().returnHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size()-1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    System.out.println("before is: " + before);
    System.out.println("after is: " + after);
  }
}
