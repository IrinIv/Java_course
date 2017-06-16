package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object []> validContacts() {
    List<Object []> list = new ArrayList<Object []>();
    list.add(new Object[]{new ContactData().withFirstname("firstname 1").withLastname("lastname 1")
            .withAddress("address 1").withHomephone("homephone 1").withEmail("email 1").withGroup("test 1")});
    list.add(new Object[]{new ContactData().withFirstname("firstname 1").withLastname("lastname 1")
            .withAddress("address 2").withHomephone("homephone 2").withEmail("email 2").withGroup("test 1")});
    list.add(new Object[]{new ContactData().withFirstname("firstname 3").withLastname("lastname 3")
            .withAddress("address 3").withHomephone("homephone 1").withEmail("email 1").withGroup("test 1")});
    return list.iterator();
  }


  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.contact().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/java.png");
    app.contact().create((contact), true);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    File photo = new File("src/test/resources/java.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}
