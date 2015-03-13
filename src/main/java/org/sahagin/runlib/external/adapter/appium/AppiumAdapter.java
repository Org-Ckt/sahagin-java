package org.sahagin.runlib.external.adapter.appium;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.sahagin.runlib.external.adapter.Adapter;
import org.sahagin.runlib.external.adapter.AdapterContainer;
import org.sahagin.runlib.external.adapter.ResourceAdditionalTestDocsAdapter;
import org.sahagin.runlib.external.adapter.ScreenCaptureAdapter;
import org.sahagin.share.CommonPath;

public class AppiumAdapter implements Adapter {

    @Override
    public void initialSetAdapter() {
        AdapterContainer container = AdapterContainer.globalInstance();
        container.addAdditionalTestDocsAdapter(new AdditionalTestDocsAdapterImpl());
    }

    // can set null
    public static void setAdapter(final WebDriver driver) {
        AdapterContainer container = AdapterContainer.globalInstance();
        container.setScreenCaptureAdapter(new ScreenCaptureAdapterImpl(driver));
    }

    // the same as the one for the WebDriverAdapter
    private static class ScreenCaptureAdapterImpl implements
            ScreenCaptureAdapter {
        private WebDriver driver;

        public ScreenCaptureAdapterImpl(WebDriver driver) {
            this.driver = driver;
        }

        @Override
        public byte[] captueScreen() {
            if (driver == null) {
                return null;
            }
            if (!(driver instanceof TakesScreenshot)) {
                return null;
            }
            try {
                return ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
            } catch (SessionNotFoundException e) {
                // just do nothing if WebDriver instance is in invalid state
                return null;
            }
        }

    }

    private static class AdditionalTestDocsAdapterImpl extends
            ResourceAdditionalTestDocsAdapter {

        @Override
        public String resourceDirPath() {
            return CommonPath.standardAdapdaterLocaleResDirPath()
                    + "/appium";
        }

        @Override
        public void classAdd() {
        }

        @Override
        public void methodAdd() {
            // in alphabetical order
            methodAdd("io.appium.java_client.android.AndroidDriver", "findElementByAndroidUIAutomator");
            methodAdd("io.appium.java_client.android.AndroidDriver", "findElementsByAndroidUIAutomator");
            methodAdd("io.appium.java_client.android.AndroidDriver", "isLocked");
            methodAdd("io.appium.java_client.AppiumDriver", "findElementByAccessibilityId");
            methodAdd("io.appium.java_client.AppiumDriver", "findElementsByAccessibilityId");
            methodAdd("io.appium.java_client.AppiumDriver", "getOrientation");
            methodAdd("io.appium.java_client.AppiumDriver", "hideKeyboard");
            methodAdd("io.appium.java_client.AppiumDriver", "lockScreen");
            methodAdd("io.appium.java_client.AppiumDriver", "pinch", "int,int");
            methodAdd("io.appium.java_client.AppiumDriver", "pinch", "org.openqa.selenium.WebElement");
            methodAdd("io.appium.java_client.AppiumDriver", "swipe");
            methodAdd("io.appium.java_client.AppiumDriver", "tap", "int,int,int,int");
            methodAdd("io.appium.java_client.AppiumDriver", "tap", "int,org.openqa.selenium.WebElement,int");
            methodAdd("io.appium.java_client.AppiumDriver", "zoom", "int,int");
            methodAdd("io.appium.java_client.AppiumDriver", "zoom", "org.openqa.selenium.WebElement");
            methodAdd("io.appium.java_client.ios.IOSDriver", "findElementByIosUIAutomation");
            methodAdd("io.appium.java_client.ios.IOSDriver", "findElementsByIosUIAutomation");
            methodAdd("io.appium.java_client.ios.IOSDriver", "shake");
            methodAdd("io.appium.java_client.MobileBy", "AccessibilityId");
            methodAdd("io.appium.java_client.MobileBy", "AndroidUIAutomator");
            methodAdd("io.appium.java_client.MobileBy", "IosUIAutomation");
        }

    }

}

