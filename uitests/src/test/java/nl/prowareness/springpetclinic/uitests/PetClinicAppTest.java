package nl.prowareness.springpetclinic.uitests;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PetClinicAppTest {

    @Test
    public void homepage_should_display_correct_title() throws IOException {
        String remoteUrl = System.getProperty("remoteUrl");
        WebDriver webDriver = remoteUrl != null ?
            new RemoteWebDriver(new URL(remoteUrl), DesiredCapabilities.firefox()) :
            new FirefoxDriver();
        try {
            webDriver.get(getAppUrl());
            File ss = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(ss, new File("ss.jpg"));
            Assert.assertEquals("PetClinic :: a Spring Framework demonstration", webDriver.getTitle());
        } finally {
            webDriver.quit();
        }
    }

    private String getAppUrl() {
        return System.getProperty("url");
    }
}
