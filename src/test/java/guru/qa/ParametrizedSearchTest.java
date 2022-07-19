package guru.qa;

import com.beust.ah.A;
import com.beust.jcommander.Strings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.in;

public class ParametrizedSearchTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "Jubit 5"} )
    @ParameterizedTest(name = "Проверка отображения поисковых результатов для запроса \"{0}}\"" )
    void commonSearchTest(String testData){
        Selenide.$("#text").setValue(testData);
        Selenide.$(".search3__button-svg").click();
        Selenide.$(".OrganicTitleContentSpan").shouldHave(Condition.text(testData));
    }


    @CsvSource({
            "Selenide, : concise UI tests in Java",
            "Junit 5, User Guide"
    })
    @ParameterizedTest(name = "Проверка отображения поисковых результатов для запроса \"{0}}\"" )
    void complexSearchTest(String testData, String expectedText){
        Selenide.$("#text").setValue(testData);
        Selenide.$(".search3__button-svg").click();
        Selenide.$(".OrganicTitleContentSpan").shouldHave(Condition.text(expectedText));
    }

    static Stream<Arguments> mixedArgumentsTestTestDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1,2,4), true),
                Arguments.of("Junit 5", List.of(5,6,7), false)
        );

    }
    @MethodSource(value = "mixedArgumentsTestTestDataProvider")
    @ParameterizedTest
    void mixedArgumentsTest (String firstArg, List<Integer> secondArg, boolean thirdArg) {
        System.out.println("String " + firstArg + " List " + secondArg + " boolean " + thirdArg );
    }


}
