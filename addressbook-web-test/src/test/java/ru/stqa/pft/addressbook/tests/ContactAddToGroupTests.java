package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

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
    Contacts allContacts = app.db().contacts();
    Groups allGroups = app.db().groups();
    Set<GroupData> contactGroups;
    Iterator <ContactData> contact = allContacts.iterator();
    GroupData group = allGroups.iterator().next();
    for (int i = 0; i < allContacts.size(); i++) {
      ContactData selectedContact = contact.next();

    if (group.getContacts().contains(selectedContact)) {
      app.contact().selectGroupContactsPage(group);
      app.contact().selectContactById(selectedContact.getId());
      app.contact().removeFromGroup();
      app.contact().homePage();
      app.contact().goToHomePageWithAllGroups();
      Groups groups = updatedContacts(selectedContact);
      Assert.assertFalse(groups.contains(group));
    }

    app.contact().selectContactById(selectedContact.getId());
    app.contact().addSelectedContactToGroup(group);

    Groups groups = updatedContacts(selectedContact);
    Assert.assertTrue(groups.contains(group));
  }}


  private Groups updatedContacts(ContactData contact) {

    Contacts newContacts = app.db().contacts();
    Set<ContactData> updatedContacts = newContacts.stream()
            .filter(c -> c.getId() == contact.getId())
            .collect(Collectors.toSet());
    return updatedContacts.iterator().next().getGroups();

  }
}











