package ru.stqa.pft.rest;


import com.google.gson.JsonElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


import static org.testng.Assert.assertEquals;

/**
 * Created by IrinaIv on 7/6/2017.
 */
public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = app.rest().getIssues();
    Issue newIssue = new Issue().withSubject("Test issue").withDescription("New test issue");
    int issueId = app.rest().createIssue(newIssue);
    Set<Issue> newIssues = app.rest().getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetIssueId() throws IOException {
    Set<Issue> allIssues = app.rest().getIssues();
    for (Issue issue : allIssues) {
      System.out.println(issue.getId());
    }
  }

  @Test
  public void testGetAllIssue() throws IOException {
    Set<Issue> issues = app.rest().getIssues();
    for (Issue issue : issues) {
      System.out.println(issue.toString());
    }
  }

  @Test
  public void testGetIssueStatus() throws IOException {
    String status = app.rest().getIssueStatus(1);

    System.out.println(status);

  }
}

