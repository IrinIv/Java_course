package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by IrinaIv on 6/30/2017.
 */
public class ChangePasswordHelper extends BaseHelper {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void goToManageUsers() {
    click(By.xpath("//a[contains(.,'Manage Users')]"));
  }

  public void selectUserById(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void confirmReset(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void loginUser(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }


  public Users all() {
    List<WebElement> elements = wd.findElements(By.xpath("//table[@class='width100'][2]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("a")).getAttribute("id"));
      String username = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(1)")).getText();
      String email = element.findElement(By.cssSelector("tr:nth-child(n) > td:nth-child(3)")).getText();
    }
    return new Users();
  }


}




