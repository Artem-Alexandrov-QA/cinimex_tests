package com.alexandrov.tests.page;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import java.io.File;
import java.util.List;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MainPage {

    private SelenideElement
            firstName = $("#form-5-SIMPLE_QUESTION_538"),
            discussyourProject = $(byText("Обсудить проект")),
            mobileNumb = $("#form-5-SIMPLE_QUESTION_243"),
            email = $("#form-5-SIMPLE_QUESTION_190"),
            picture = $("#form-5-FILE"),
            userQuestion = $("#form-5-SIMPLE_QUESTION_131"),
            eng = $(".lang");


    public MainPage openMainPage() {
        step("Открыть главную страницу сайта \"cinimex\"", () ->
                open(""));
        return this;
    }

    public MainPage goToPage(String name) {
        step(String.format("Перейти на страницу раздела \"%s\"", name), () ->
                $(byText(name)).click());
        return this;
    }

    public MainPage checkPageTitle(String disc) {
        step(String.format("Проверить заголовок на странице раздела \"%s\"", disc), () ->
                $(".head-title").shouldHave(text(disc)));
        return this;
    }

    public MainPage choiceLanguages(String lang) {
        step(String.format("Выбрать язык \"%s\"", lang), () -> {
            eng.click();
        });
        return this;
    }

    public MainPage checkPageContent(String lang, List<String> expectedTitle) {
        step(String.format("Проверить отображение разделов %s", expectedTitle), () ->
                $$(".header__wrapper").filter(visible).shouldHave(CollectionCondition.texts(expectedTitle)));
        return this;
    }

    public MainPage searchInput(String testData) {
        step(String.format("Ввести поисковой запрос \"%s\"", testData), () -> {
            $(".icon-search").click();
            $x("/html/body/header/div[2]/div/form/div/input").setValue(testData).pressEnter();
        });
        return this;
    }

    public MainPage checkSearchResult(String expectedResult) {
        step(String.format("Проверить, что результат поиска содержит ответ \"%s\"", expectedResult), () ->
                $x("/html/body/main/section/div[3]/div[1]/div[1]/div[1]/h4").shouldHave(text(expectedResult)));
        return this;
    }

    public MainPage cinimexCheckAddress(String City) {
        step(String.format("Выбрать город \"%s\"", City), () -> {
            $(byText("Контакты")).click();
            $(byText(City)).click();
        });
        return this;
    }

    public MainPage cinimexCheckAddressResult(String Address) {
        step(String.format("Проверить адрес города \"%s\"", Address), () ->
                $(".map-block__text-wrapper").shouldHave(text(Address)));
        return this;
    }

    public MainPage chechLogoFontSize(){
        step(String.format("Проверить лого сайта 'параметры СSS'"), () ->
                $(".logo").shouldHave(cssValue("font-weight", "400")));
                $(".logo").shouldHave(cssValue("font-size", "16px"));
                $(".logo").shouldHave(cssValue("text-align", "left"));
                $(".logo").shouldHave(cssValue("color", "rgba(17, 17, 17, 1)"));
                $(".logo").shouldHave(cssValue("font-family", "Montserrat, -apple-system," +
                        " BlinkMacSystemFont, \"Segoe UI\", \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif," +
                        " \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\""));
        return this;
    }

    public MainPage goDiscussyourProject() {
        step(String.format("Открыть форму заполнения 'Обсудить проект' "), () ->
                discussyourProject.click());
        return this;
    }

    public MainPage typeFirstName(String value) {
        step(String.format("Заполнить поле Имя"), () ->
                firstName.setValue(value));
        return this;
    }

    public MainPage mobileNumber(String mobileNumber) {
        step(String.format("Заполнить поле Номер"), () ->
                mobileNumb.setValue(mobileNumber));
        return this;
    }

    public MainPage userEmail(String userEmail) {
        step(String.format("Заполнить поле Email"), () ->
                email.setValue(userEmail));
        return this;
    }

    public MainPage setPictureImg(File file) {
        step(String.format("Добавить файл png. "), () ->
                picture.uploadFile(file));
        return this;
    }

    public MainPage userQuestion(String question) {
        step(String.format("Заполнить поле вопроса"), () ->
                userQuestion.setValue(question));
        return this;
    }

    public MainPage sendButton() {
        step(String.format("Нажать кнопку Отправить"), () ->
                $x("//body/div[@id='discuss']/div[1]" +
                        "/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/div[3]").click());
        return this;
    }
}
