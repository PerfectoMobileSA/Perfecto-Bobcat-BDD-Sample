package perfecto.reporting;

import com.google.inject.Inject;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

import org.openqa.selenium.WebDriver;


public class ReportiumManager {

//    @Inject
//    private WebDriver webDriver;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void setDriver(WebDriver d){
        driver.set(d);
    }

    private static ThreadLocal<ReportiumClient> reportiumClientLocal = new ThreadLocal<>();

    public static void setReportiumClient(ThreadLocal<ReportiumClient> reportiumClient) {
        reportiumClientLocal = reportiumClient;
    }

    public static ReportiumClient getReportiumClient(){
        return reportiumClientLocal.get();
    }

    public static void createReportiumClient(WebDriver webDriver) {
        ReportiumClient reportiumClient = null;
        PerfectoExecutionContext perfectoExecutionContext;
        if(System.getProperty("reportium-job-name") != null) {
            String tags = System.getProperty("reportium-tags");
            if(tags==null || tags==""){
                tags = "tag1";
            }
            perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                    .withProject(new Project("My Project", "1.0"))
                    .withJob(new Job(System.getProperty("reportium-job-name") , Integer.parseInt(System.getProperty("reportium-job-number"))))
                    .withContextTags(tags)
                    .withWebDriver(webDriver)
                    .build();
        } else {
            perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                    .withProject(new Project("My Project", "1.0"))
                    .withContextTags("tag1")
                    .withWebDriver(webDriver)
                    .build();
        }
        reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
        if (reportiumClient == null) {
            throw new RuntimeException("Reportium client not created!");
        }
        setDriver(webDriver);
        reportiumClientLocal.set(reportiumClient);
    }
}
