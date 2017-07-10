package ru.stqa.pft.rest;

import java.io.IOException;
import java.util.Set;

/**
 * Created by IrinaIv on 7/9/2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();


  boolean isIssueOpen(int issueId) throws IOException {

    Set<Issue> oldIssues = app.rest().getIssues();



  }


}
