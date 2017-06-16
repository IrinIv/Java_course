package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
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
  public Iterator<Object []> validContacts() throws IOException {
    List<Object []> list = new ArrayList<Object []>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while(line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1])
              .withAddress(split[2]).withHomephone(split[3])
              .withEmail(split[4]).withGroup(split[5])});
      line = reader.readLine();
    }

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
