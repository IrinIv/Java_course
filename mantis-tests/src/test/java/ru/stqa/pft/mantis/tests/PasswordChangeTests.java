package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by IrinaIv on 6/30/2017.
 */
public class PasswordChangeTests extends TestBase {
  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws IOException, MessagingException {
    app.changePass().login("administrator", "root");
    app.changePass().goToManageUsers();
    app.changePass().selectUserById( 6);
    app.changePass().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    //String user = String.format("user%s");
    String email = String.format("user1498806668318@gmail.com");;
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.changePass().confirmReset(confirmationLink, "12345");
    app.changePass().loginUser("user1498806668318", "12345" );
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }




}
