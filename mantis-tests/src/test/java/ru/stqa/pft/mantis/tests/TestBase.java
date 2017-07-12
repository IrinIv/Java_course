package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

/**
 * Created by IrinaIv on 5/18/2017.
 */
public class TestBase {


  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));




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

  boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {

    MantisConnectPortType mc = app.soap().getMantisConnect();
    String status = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getStatus().getName();
    if(status.equals("closed") || status.equals("resolved")) {
      return false;
  }
  return true;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
  @Test
  public void testGetIssueStatus() throws IOException, ServiceException {
    skipIfNotFixed(3);
    System.out.println("This issue is already fixed");

  }


}
