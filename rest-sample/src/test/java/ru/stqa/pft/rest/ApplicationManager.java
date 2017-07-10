package ru.stqa.pft.rest;

/**
 * Created by IrinaIv on 7/9/2017.
 */
public class ApplicationManager {

  private RestHelper restHelper;

  public RestHelper rest() {
    if (restHelper == null) {
      restHelper = new RestHelper(this);
    }
    return restHelper;
  }
  }

