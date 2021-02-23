package perfecto.hooks;

import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import cucumber.api.Result;
import cucumber.api.java.Before;
import cucumber.runtime.java.JavaHookDefinition;
//import cucumber.runtime.java8.Java8HookDefinition;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import cucumber.runtime.ScenarioImpl;

import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import perfecto.reporting.ReportiumManager;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * A helper class that can create a screenshot file and gather additional data.
 */
public class FinishReport {

  @Inject
  private WebDriver webDriver;

  /**
   * Creates screenshot and gathers additional information, embeds it in the scenario and closes webDriver.
   */
  @After
  public void addDataAndClose(Scenario scenario) throws ClassNotFoundException {
    if (scenario.isFailed()) {
      if(webDriver instanceof TakesScreenshot){
        addScreenshot(scenario);
      }
      addPageLink(scenario);
    }
    if(scenario.isFailed()){
//      ReportiumManager.getReportiumClient().testStop(TestResultFactory.createFailure("New Error"));
    }else if(scenario.getStatus().toString().toLowerCase().contains("passed")){
      ReportiumManager.getReportiumClient().testStop(TestResultFactory.createSuccess());
    }
//    webDriver.quit();
  }

  @Before
  public void before(Scenario scenario) {
    ReportiumManager.createReportiumClient(webDriver);
    ReportiumManager.getReportiumClient().testStart(scenario.getName(), new TestContext("bdd", "wiki") );
  }

  private void addPageLink(Scenario scenario) {
    scenario.write("Test page: " + "<a href=" + webDriver.getCurrentUrl() + ">link</a>");
  }

  private void addScreenshot(Scenario scenario) {
    byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    scenario.embed(screenshot, "image/png");
  }

}
