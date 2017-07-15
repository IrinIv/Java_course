package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by IrinaIv on 6/23/2017.
 */
public class ContactDeleteFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
    }
    app.contact().homePage();
    Groups groups = app.db().groups();
    if (app.db().contacts().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("I").withLastname("V")
              .withAddress("ad").withHomephone("1234").withMobilephone("1234")
              .withWorkphone("2345").withAllphones("").withEmail("email@gmail.com")
              .withEmail2("email").withEmail3("email").inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactDeleteFromGroup() {
    app.contact().homePage();
    Set<GroupData> contactGroups;
    Contacts before = app.db().contacts();
    Iterator<ContactData> idG = before.iterator();
    for (int i = 0; i < app.db().contacts().size(); i++) {
      ContactData selectedContact = idG.next();
      Set<GroupData> allGroups = app.db().groups();
      contactGroups = selectedContact.getGroups();
//      if (contactGroups.size() == 0) {
//        app.contact().goToHomePageWithAllGroups(selectedContact, allGroups);
//        app.contact().addSelectedContactToGroup(selectedContact, allGroups);
//        app.contact().deleteContactFromGroup(selectedContact, allGroups);}
//      if (contactGroups.size() > 0) {
//        app.contact().deleteContactFromGroup(selectedContact, allGroups);
//      }
//      if (contactGroups.size() == allGroups.size() ) {
//        app.goTo().groupPage();
//        app.group().create(new GroupData().withName("test4"));
//        app.contact().homePage();
//        app.contact().goToHomePageWithAllGroups(selectedContact, allGroups);
//        app.contact().addSelectedContactToGroup(selectedContact, allGroups);
//        app.contact().deleteContactFromGroup(selectedContact, allGroups);
//      }
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withOut(selectedContact).withAdded(selectedContact)));

      }
    }
  }



