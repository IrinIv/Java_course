package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.BaseHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stqa.pft.addressbook.tests.TestBase.app;

/**
 * Created by IrinaIv on 6/10/2017.
 */
public class ContactPhoneTests extends TestBase {

  @BeforeMethod(enabled = true)
  public void ensurePreconditions() {
    app.contact().homePage();
    if (app.contact().all().size() == 0) {
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Irina").withLastname("Iva").withAddress("address").withHomephone("1234567890").withMobilephone("1234567890").withWorkphone("2345").withEmail("email@gmail.com"), true);
    }
  }

  @Test
  public void testContactPhones() {

    app.contact().homePage();
    Contacts before = app.contact().all();
    ContactData contact = before.iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(cleaned(contactInfoFromEditForm.getAddress())));
    assertThat(contact.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllemails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  public static String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> s != null && ! s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomephone(), contact.getMobilephone(), contact.getWorkphone())
            .stream().filter((s) -> s != null && !s.equals(""))
            .map(ContactPhoneTests::cleanedphone)
            .collect(Collectors.joining("\n"));
  } //

  public static String cleanedphone(String phone) {

    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleaned(String phone) {

    return phone.replaceAll("[-()]", "");
  }



}
