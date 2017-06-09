package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod(enabled = true)
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.contact().list().size() == 0 ) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Irina").withLastname("Iva").withHomephone("1234567890").withMobilephone("1234567890").withEmail("email@gmail.com").withGroup("test1"), true);
    }
  }
  @Test(enabled = true)
  public void testContactModification(){

    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contact = new ContactData().withId(before.get(index).getId()).withFirstname("Lena").withLastname("Y");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());


    before.remove(index);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    System.out.println("before is: " + before);
    System.out.println("after is: " + after);
  }


}
