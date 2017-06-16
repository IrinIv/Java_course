package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by IrinaIv on 6/15/2017.
 */
public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  //@Parameter(names = "-d", description = "Data format")
  //public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
    jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts =  generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for(ContactData contact : contacts) {
      writer.write(format("%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),
              contact.getLastname(), contact.getAddress(),
              contact.getHomephone(), contact.getEmail(), contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for(int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(format("firstname %s", i))
              .withLastname(format("lastname %s", i)).withAddress(format("address %s", i))
              .withHomephone(format("homephone %s", i)).withEmail(format("email %s", i)).withGroup(format("test %s", i)));
    }
    return contacts;
  }

}
