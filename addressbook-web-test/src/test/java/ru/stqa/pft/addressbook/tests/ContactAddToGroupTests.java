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
public class ContactAddToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("test3"));
      }
    }

  @Test
  public void testContactAddToGroup() {

      app.contact().homePage();
      Groups groups = app.db().groups();
      Contacts before = app.db().contacts();
      ContactData selectedContact = before.iterator().next();
      app.contact().addContactToGroup(selectedContact);
      Contacts selected = app.db().contacts().withAddedSelected(selectedContact);

      assertThat(((app.db().contacts().withAddedSelected(selectedContact))).size(), equalTo(selected.size()));

      verifyContactListInUi();
    }
  }


