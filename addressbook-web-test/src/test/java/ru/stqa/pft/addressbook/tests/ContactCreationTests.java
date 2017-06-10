package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactCreationTests extends TestBase {



  @Test(enabled = true)
  public void testContactCreation() {
    app.contact().homePage();
    Set<ContactData> before = app.contact().all();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withFirstname("Tanya").withLastname("I").withHomephone("1234567890").withMobilephone("1234567890").withEmail("email@gmail.com").withGroup("test3");

    app.contact().create((contact), true);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);

    Assert.assertEquals(before, after);
    System.out.println("before is: " + before);
    System.out.println("after is: " + after);
  }
}
