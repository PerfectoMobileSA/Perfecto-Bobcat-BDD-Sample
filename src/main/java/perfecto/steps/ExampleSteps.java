package perfecto.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.google.inject.Inject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import perfecto.example.DefinitionPage;
import perfecto.example.WikipediaPage;
import perfecto.reporting.ReportiumManager;

@ScenarioScoped
public class ExampleSteps {

  @Inject
  private WikipediaPage wikipediaPage;

  @Inject
  private DefinitionPage definitionPage;

  @Given("^I have opened main page$")
  public void I_have_opened_main_page() {
    ReportiumManager.getReportiumClient().stepStart("Navigate to Wiki page.");
    wikipediaPage.open();
  }

  @When("^I search for \"(.+)\"$")
  public void I_search_for(String query) {
    ReportiumManager.getReportiumClient().stepStart("Search for "+query);
    wikipediaPage.getSearchComponent().searchForQuery(query);
  }

  @Then("^Heading for page will be \"(.+)\"$")
  public void Heading_for_page_will_be(String heading) {
    ReportiumManager.getReportiumClient().stepStart("Verify search result");
    assertThat(definitionPage.getHeading(), is(heading));
  }

}
