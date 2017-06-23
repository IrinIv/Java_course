package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod(enabled = true)
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.db().contacts().size() == 0 ) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Irina").withLastname("Iva").withAddress("address").withHomephone("1234567890").withMobilephone("1234567890").withWorkphone("2345").withEmail("email@gmail.com").withGroup("test1"), true);
    }
  }

  @Test(enabled = true)
  public void testContactModification(){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withFirstname("EEE").withLastname("WWW").withAddress("address123")
            .withHomephone("1234567890").withMobilephone("1234567890").withWorkphone("56 78").
            withEmail("email@gmail.com").withEmail2("email").withEmail3("email");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUi();

  }
}
