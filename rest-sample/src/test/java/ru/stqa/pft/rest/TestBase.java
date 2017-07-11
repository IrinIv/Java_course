package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

/**
 * Created by IrinaIv on 7/9/2017.
 */
public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager();


  boolean isIssueOpen(int issueId) throws IOException {
    String json = app.rest().getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/ " + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    String status = parsed.getAsJsonObject().get("state_name").getAsString();
    if (status == "Open") {
      return true;
    }
    return false;
  }

  //@BeforeTest
  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}