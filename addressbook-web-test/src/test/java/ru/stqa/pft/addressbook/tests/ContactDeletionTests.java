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
public class ContactDeletionTests extends TestBase {

  @BeforeMethod(enabled = true)
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData("Irina", "Iva", "1234567890", "1234567890", "email@gmail.com", null), true);
    }
  }

  @Test(enabled = true)
  public void testContactDeletion() {

    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
