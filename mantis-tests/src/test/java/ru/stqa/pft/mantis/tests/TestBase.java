package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  private boolean isFixedIssue;



  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bac");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bac", "config_inc.php");
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

  boolean isIssueOpen(int issueId) throws RemoteException {
    if(isFixedIssue == false) {
   return true;
  }
  return false;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
