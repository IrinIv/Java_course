package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    app.contact().selectGroup();
    Contacts after = app.contact().all();
    ContactData deletedContact = after.iterator().next();
    app.contact().deleteContactFromGroup(deletedContact);
    Contacts selected = app.contact().all().inSelectedGroup(deletedContact);
    Contacts withoutselected = app.contact().all().withOutSelected(deletedContact);

    assertThat(((app.contact().all().withOutSelected(deletedContact))).size(), equalTo(selected.size() - 1));

  }

}
