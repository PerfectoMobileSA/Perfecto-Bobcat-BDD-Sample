package perfecto.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject
public class WikipediaPage {

  private static final String URL =  "https://www.wikipedia.org";

  @Inject
  private WebDriver webDriver;

  @FindBy(id = "search-input")
  private SearchComponent searchComponent;

  public SearchComponent getSearchComponent() {
    return searchComponent;
  }

  public WikipediaPage open() {
    webDriver.get(URL);
    return this;
  }

}
