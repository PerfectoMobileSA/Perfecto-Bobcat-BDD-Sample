package perfecto;

import com.google.inject.Inject;
import com.perfecto.reportium.test.result.TestResultFactory;
import cucumber.api.Result;
import cucumber.api.TestCase;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestCaseFinished;
import org.openqa.selenium.WebDriver;
import perfecto.reporting.ReportiumManager;

public class MyTestListener implements ConcurrentEventListener {


    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>In Listener.<<<<<<<<<<<<<<<<<<");
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        TestCase testCase = event.getTestCase();
        Result result = event.result;
        Throwable error = result.getError();
        if(error!=null)
            ReportiumManager.getReportiumClient().testStop(TestResultFactory.createFailure(error));
        ReportiumManager.getDriver().quit();
    }

}
