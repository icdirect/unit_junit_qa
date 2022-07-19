package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleTest")
public class SimpleTest {

    @Test
    @DisplayName("Ожидаемый зеленый тест")
    void simpleGreenTest (){
        assertTrue(3 > 2);
    }

    @Test
    @DisplayName("Ожидаемый красный тест")
    void simpleRedTest (){
        assertTrue(3 < 2);
    }

    @Test
    @DisplayName("Ожидаемый желтый тест")
    void simpleBrokenTest (){
        throw new IllegalStateException("Broken");
    }
}
