package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;

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
    boolean selected = false;
    app.contact().homePage();
    Groups allGroups = app.db().groups();
    Contacts before = app.db().contacts();
    for (ContactData selectedContact : before) {
      Groups beforeSelectedGroups = selectedContact.getGroups();
      
      for (GroupData group : allGroups) {
        if (!beforeSelectedGroups.contains(group)) {
          app.contact().select(selectedContact, group);
          selected = true;
          break;
        }
      }
    }
  }
}




