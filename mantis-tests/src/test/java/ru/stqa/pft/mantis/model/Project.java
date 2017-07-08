package ru.stqa.pft.mantis.model;

/**
 * Created by IrinaIv on 6/30/2017.
 */
public class Project {

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  private int id;
  private String name;

  @Override
  public String toString() {
    return "Project{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }
}
