package perfecto.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject
public class SearchComponent {

  @FindBy(id = "searchInput")
  private WebElement searchField;

  public void searchForQuery(String query) {

    searchField.sendKeys(query);
    searchField.submit();
  }
}
