//package tests;
//
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.Test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.Markup;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.model.Media;
//
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//import io.appium.java_client.service.local.flags.GeneralServerFlag;
//
//public class AppiumService {
//
//    private static AndroidDriver driver;
//    private static AppiumDriverLocalService service;
//    private static ExtentReports report;
//    private static ExtentTest test;
//    private static ExtentHtmlReporter reports;
//   
//    @BeforeClass
//    public static void startTest() throws IOException
//    {
//    	  report = new ExtentReports();
//    	 reports=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReports\\reports.html");
//    	 //reports.config().setChartVisibleOnOpen(true);
//    	 reports.config().setDocumentTitle("Extent Report Demo");
//    	 reports.config().setReportName("Automation Test Report");
//    	 //reports.config().setTestViewChartLocation("TOP");
//    	 reports.config().setTheme(Theme.STANDARD);
//    	 report.attachReporter(reports);
//    	
//
//    }
//    @BeforeClass
//    public void setup() {
//        try {
//            startAppiumServer();
//            initializeAndroidDriver();
//            System.out.println("Application started");
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void sample() {
//    	  System.out.println("This is a sample project");
//    	 test= report.createTest("BigOven LoginPage test");
//        test.log(Status.INFO,"application launched successfully");
//       
//        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.bigoven.android:id/bigoven_sign_in_button']")).click();
//        test.log(Status.INFO,"successfully clicked on sign in button");
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.bigoven.android:id/email']")).sendKeys("superadmin@gmail.com");
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.bigoven.android:id/password']")).sendKeys("Admin@123");
//        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.bigoven.android:id/bigoven_sign_in_button']")).click();
//        test.log(Status.INFO, "login successful");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//    }
//
//    @AfterSuite
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//        stopAppiumServer();
//        report.flush();
//       
//    }
//
//    private void startAppiumServer() {
//        AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.withIPAddress("127.0.0.1")
//                .usingPort(4723)
//                .withAppiumJS(new File("C:\\Users\\ruchitha.devulapally\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
//                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
//                .withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
//
//        service = AppiumDriverLocalService.buildService(builder);
//        service.start();
//    }
//
//    private void stopAppiumServer() {
//        if (service != null) {
//            service.stop();
//        }
//    }
//
//    private void initializeAndroidDriver() {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("PLATFORM_NAME", "ANDROID");
//        capabilities.setCapability("PLATFORM_VERSION", "14");
//        capabilities.setCapability("DEVICE_NAME", "emulator-5554");
//        capabilities.setCapability("APP", "/MobileAppiumProject/src/test/resources/apps/bigoven-recipes-6-1-37.apk");
//        capabilities.setCapability("NEW_COMMAND_TIMEOUT", 60);
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//        capabilities.setCapability("appPackage", "com.bigoven.android");
//        capabilities.setCapability("appActivity", "com.bigoven.android.LauncherActivity");
//        driver = new AndroidDriver(capabilities);   
//        
//    }
//    @AfterClass
//    public static void endTest() throws IOException, MessagingException {
//    	
//    	
//    	
//        // Capture the screenshot as a file object
//        TakesScreenshot screenshot = (TakesScreenshot) driver;
//       // String srcFile = screenshot.getScreenshotAs(OutputType.BASE64);
//        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
//
//        // Save the screenshot to a desired location
//        String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\AttachScreenshot.png";
//        FileUtils.copyFile(srcFile, new File(screenshotPath));
//        // Attach the screenshot to the Extent Report
//        test.addScreenCaptureFromPath(screenshotPath);
//     // Attach the screenshot to the Extent Report using MediaEntityBuilder
//        System.out.println("Screenshot Path: " + screenshotPath);
//        //test.log(Status.INFO, "screenshot captured",MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//        try {
//            test.log(Status.INFO, "Screenshot captured",
//                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//       
//       // sendEmail("ruchitha.d99@gmail.com", "Subject: Test Report", "Message body", screenshotPath);
//        sendEmail("ruchitha.d99@gmail.com", "Subject: Test Report", "Message body",reports);
//       
//    }
//
//    private static void sendEmail(String to, String subject, String body,ExtentHtmlReporter reports)
//            throws MessagingException, IOException {
//        // Email configuration
//        String from = "ruchitha.d99@gmail.com";
//        String host = "smtp.gmail.com"; // or your SMTP server
//        final String username = "ruchitha.d99@gmail.com";
//        final String password = "hyqv jatd scgl xgzv";
//
//        Properties properties = System.getProperties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "587");
//
//        // Create a Session object
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        // Create a MimeMessage object
//        MimeMessage message = new MimeMessage(session);
//
//        // Set From: header field
//        message.setFrom(new InternetAddress(from));
//
//        // Set To: header field
//        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//        // Set Subject: header field
//        message.setSubject(subject);
//
//        // Create a Multipart
//        Multipart multipart = new MimeMultipart();
//
//        // Attach the report file
//        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//        attachmentBodyPart.attachFile(new File(System.getProperty("user.dir")+"\\ExtentReports\\reports.html")); 
//        multipart.addBodyPart(attachmentBodyPart);
//
//        // Set the email body text
//        MimeBodyPart textBodyPart = new MimeBodyPart();
//        textBodyPart.setText(body);
//
//        // Add the text body part to the Multipart
//        multipart.addBodyPart(textBodyPart);
//
//        // Set the content of the message
//        message.setContent(multipart);
//
//        // Send the message
//        Transport.send(message);
//
//        System.out.println("Email sent successfully.");
//    }
//
//   
//
//}
//    
//
