package ru.stqa.pft.github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by IrinaIv on 7/9/2017.
 */
public class GithubTests {

  @Test
  public void testCommits() throws IOException {

    Github github = new RtGithub("a947a08420a7dd80e5f74a6d8371a4514b72304c");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("IrinIv", "Java_course")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableBiMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());

    }
  }


}
