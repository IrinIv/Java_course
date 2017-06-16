package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactCreationTests extends TestBase {

  @Test(enabled = true)
  public void testContactCreation() {
    app.contact().homePage();
    Contacts before = app.contact().all();
    app.goTo().contactPage();
    File photo = new File("src/test/resources/java.png");
    ContactData contact = new ContactData().withFirstname("Tanya").withLastname("ly").withAddress("address").withHomephone("123-456-7890").withMobilephone("1234567890").withWorkphone("2345").withEmail("email@gmail.com").withGroup("test1").withPhoto(photo);
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
