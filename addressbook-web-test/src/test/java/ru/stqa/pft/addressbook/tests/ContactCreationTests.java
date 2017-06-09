package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactCreationTests extends TestBase {



  @Test(enabled = true)
  public void testContactCreation() {
    app.contact().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().contactPage();
    ContactData contact = new ContactData().withFirstname("Iri").withLastname("I").withHomephone("1234567890").withMobilephone("1234567890").withEmail("email@gmail.com").withGroup("test1");

    app.contact().create((contact), true);

    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    System.out.println("before is: " + before);
    System.out.println("after is: " + after);
  }
}
