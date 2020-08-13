package generic;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reporting {

    static ExtentHtmlReporter htmlReport;
    protected static ExtentReports report;
    //static ExtentTest testCase;

    public void initializeReport(){
        String reportpath = System.getProperty("user.dir") + "/reports/report.html";
        htmlReport = new ExtentHtmlReporter(reportpath);
        htmlReport.config().setDocumentTitle("Multithreading Report");
        htmlReport.config().setReportName("Automation Report");

        report = new ExtentReports();
        report.attachReporter(htmlReport);
    }

    public void generateReport(){
        report.flush();
    }

}
