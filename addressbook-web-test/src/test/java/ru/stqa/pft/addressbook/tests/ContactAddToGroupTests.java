package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by IrinaIv on 6/23/2017.
 */
public class ContactAddToGroupTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
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
  public void testContactAddToGroup() {
    app.contact().homePage();
    Set<GroupData> contactGroups;
    Contacts before = app.db().contacts();
    Set<GroupData> allGroups = app.db().groups();
    ContactData contact = before.iterator().next();
    GroupData group = allGroups.iterator().next();
    if (group.getContacts().contains(contact)) {
      app.contact().selectGroupContactsPage(group);
      app.contact().selectContactById(contact.getId());
      app.contact().removeFromGroup();
      app.contact().homePage();
      app.contact().goToHomePageWithAllGroups();
      Groups groups = selectedContacts(contact);
      Assert.assertFalse(groups.contains(group));
    }
    if (group.getContacts().size() == allGroups.size()) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test3"));
      app.contact().homePage();
      app.contact().selectContactById(contact.getId());
      app.contact().addSelectedContactToGroup(group);

    }
    app.contact().selectContactById(contact.getId());
    app.contact().addSelectedContactToGroup(group);

    Groups groups = selectedContacts(contact);
    Assert.assertTrue(groups.contains(group));
  }


  private Groups selectedContacts(ContactData contact) {

    Contacts newContacts = app.db().contacts();
    Set<ContactData> selectedContacts = newContacts.stream()
            .filter(c -> c.getId() == contact.getId())
            .collect(Collectors.toSet());
    return selectedContacts.iterator().next().getGroups();

  }
}











