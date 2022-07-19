package guru.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedTestHomeWork {

    @BeforeEach
    void precondition() {
        open("https://mvnrepository.com/");
    }

    @AfterEach
    void closeBrowser(){
        closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "Junit"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов для запроса \"{0}}\"")
    void firstTest(String testData){
        //в поле для поиска вводим запрос
        $("#query").setValue(testData);
        //нажимаем на кнопку поиска
        $(".button").click();
        //проверяем что нашелся нужный результат
        $(".im-header").sibling(0).shouldHave(text(testData));
    }

    @CsvSource({
            "Selenide, Selenide = concise API for Selenium WebDriver",
            "Junit, Module \"junit-jupiter-api\" of JUnit 5."
    })
    @ParameterizedTest(name = "Проверка отображения поисковых результатов для запроса \"{0}}\"")
    void secondTest(String testData, String expectedText){
        //в поле для поиска вводим запрос
        $("#query").setValue(testData);
        //нажимаем на кнопку поиска
        $(".button").click();
        //проверяем что нашелся нужный результат
        $(".im-header").sibling(0).shouldHave(text(expectedText));


    }
}
