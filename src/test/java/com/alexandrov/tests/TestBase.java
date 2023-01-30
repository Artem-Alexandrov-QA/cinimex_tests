package com.alexandrov.tests;
import com.alexandrov.attach.Attach;
import com.alexandrov.config.Browser;
import com.alexandrov.config.ConfigReader;
import com.alexandrov.config.ProjectConfiguration;
import com.alexandrov.config.WebConfig;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;



@ExtendWith({BrowserPerTestStrategyExtension.class})
public class TestBase {
    private static final WebConfig webConfig = ConfigReader.Instance.read();
    private static final ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        projectConfiguration.webConfig();
    }

    @AfterEach
    void addAttachments () {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
