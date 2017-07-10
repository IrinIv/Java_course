package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Set;

/**
 * Created by IrinaIv on 7/9/2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();


  boolean isIssueOpen(int issueId) throws IOException {

    String state_name = null;
    if (state_name == "open") {
      return true;
    }
    return false;
  }

  @BeforeTest
  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
