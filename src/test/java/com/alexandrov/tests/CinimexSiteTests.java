package com.alexandrov.tests;
import com.alexandrov.tests.page.MainPage;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.io.File;
import java.util.List;
import java.util.stream.Stream;


@Owner("Artem Alexandrov")
@Feature("UI тесты")
@DisplayName("UI тесты для сайта cinimex")
@Tags({@Tag("WEB"), @Tag("MEDIUM"), @Tag("NORMAL")})
@Severity(SeverityLevel.NORMAL)
@Link(name = "cinimex", url = "https://www.cinimex.ru/")

public class CinimexSiteTests extends TestBase {

    MainPage mainPage = new MainPage();
    Faker faker = new Faker();
    int phoneNumberLength = 10;
    String firstName = faker.name().firstName()
            , userEmail = faker.internet().emailAddress()
            , mobileNumber = faker.phoneNumber().subscriberNumber(phoneNumberLength)
            , question = faker.harryPotter().location()
            , fileLocation = "src/test/resources/img/"
            , file = "1.png";

    @AllureId("14666")
    @Test
    @Tag("logo")
    @DisplayName("Проверка лого сайта на 'параметры CSS'")
    void cinimexLogoTest(){

        mainPage.openMainPage();
        mainPage.chechLogoFontSize();
    }

    @AllureId("14671")
    @DisplayName("Проверка перехода в разделы сайта.")
    @Tag("page")
    @ParameterizedTest(name = "Выполняется переход в раздел \"{0}\"")
    @CsvSource(value = {
            "О нас, Опытная команда для решения сложных задач",
            "Решения, Решения и услуги",
            "Пресс-центр, Пресс-центр",
            "Проекты, Проекты",
            "Контакты, Контакты",
    })
    void testWithCsvSource(String name, String disc) {
        mainPage.openMainPage();
        mainPage.goToPage(name);
        mainPage.checkPageTitle(String.valueOf(disc));
    }

    static Stream<Arguments> cinimexSiteMenuTest() {
        return Stream.of(
                Arguments.of("English", List.of("About\n" +
                        "Solutions\n" +
                        "Press room\n" +
                        "Projects\n" +
                        "Contacts\n" +
                        "Discuss your project\n" +
                        "По-русски"))
        );
    }

    @AllureId("14674")
    @DisplayName("Проверка наличия разделов сайта на Англ.языке.")
    @MethodSource
    @ParameterizedTest(name = "Для языка {0} отображаются разделы {1}")
    void cinimexSiteMenuTest(String lang, List<String> expectedTitle) {

        mainPage.openMainPage();
        mainPage.choiceLanguages(lang);
        mainPage.checkPageContent(lang, expectedTitle);
    }

    @AllureId("14673")
    @CsvSource(value = {
            "Вакансии, Список вакансий"
    })

    @Tag("search")
    @DisplayName("Проверка работы поиска.")
    @ParameterizedTest(name = "Результаты поиска содержат текст \"{1}\" для запроса \"{0}\"")
    void cinimexSearch(String testData, String expectedResult) {

        mainPage.openMainPage();
        mainPage.searchInput(testData);
        mainPage.checkSearchResult(expectedResult);
    }

    @AllureId("14675")
    @Tag("address")
    @CsvSource(value = {
            "Москва ; 115184, Москва, ул. Большая Татарская, д. 35, стр. 3",
            "Санкт-Петербург; 196084, Санкт-Петербург, ул. Ташкентская, д. 4, корп. 2 У, 1 этаж, офис № 1",
            "Оренбург; 460006, Оренбург, ул. Комсомольская, д. 133, 3 этаж",
            "Воронеж; 394026, Воронеж, проспект Труда, 65и, 3 этаж",
            "Самара; 443010, г. Самара, ул. Красноармейская, д.1Б, 3 этаж, офис. 313"
    },
    delimiter = ';')

    @DisplayName("Проверка адресов в городах")
    @ParameterizedTest(name = "Офис в городе \"{0}\" находится по адресу \"{1}\"")
    void cinimexCheckAddressCity(String City, String Address) {

        mainPage.openMainPage();
        mainPage.cinimexCheckAddress(City);
        mainPage.cinimexCheckAddressResult(Address);
    }

    @AllureId("14672")
    @Test
    @Tag("form")
    @DisplayName("Проверка заполнения формы 'Обсудить проект'")
    void cinimexDiscussYourProject(){

        mainPage.openMainPage();
        mainPage.goDiscussyourProject();
        mainPage.typeFirstName(firstName);
        mainPage.mobileNumber(mobileNumber);
        mainPage.userEmail(userEmail);
        mainPage.setPictureImg(new File(fileLocation + file));
        mainPage.userQuestion(question);
        mainPage.sendButton();
    }
}