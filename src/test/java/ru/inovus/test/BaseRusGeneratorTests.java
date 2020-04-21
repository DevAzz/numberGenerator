package ru.inovus.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.inovus.test.exceptions.InvalidFormatNumberException;
import ru.inovus.test.exceptions.InvalidGeneratorConfigurationExcpetion;
import ru.inovus.test.generator.GeneratorFactory;
import ru.inovus.test.generator.GeneratorType;
import ru.inovus.test.generator.INumberGenerator;
import ru.inovus.test.generator.Parameters;

/**
 * Тесты базового генератора номеров
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseRusGeneratorTests {

    /** Тестируемый генератор */
    private static INumberGenerator _numberGenerator;

    /** Общий паттерн номера */
    private static Pattern _baseNumberPattern;

    /**
     * Выполняет первичную настройку
     *
     * @throws InvalidGeneratorConfigurationExcpetionв случае ошибки
     *             инициализации генератора
     */
    @BeforeClass
    public static void setUp() throws InvalidGeneratorConfigurationExcpetion {
        String pattern = "(^[АЕТОРНУКХСВМ])(\\d{3}(?<!000))([АЕТОРНУКХСВМ]{2})\\s116\\sRUS$";
        _baseNumberPattern = Pattern.compile(pattern);

        Parameters params = Parameters.newBuilder()
                .withAlphabet(
                        Arrays.asList("А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"))
                .withBaseNumberPattern(pattern)
                .withDigitalNumberPattern("^(0{1})(\\d{2}(?<!0\\d))$|^(0{2})(\\d{1})$|^(\\d{3}$)")
                .withType(GeneratorType.BASE_RUS_CAR_NUMBER).build();
        _numberGenerator = new GeneratorFactory().createNumberGenerator(params);

    }

    /**
     * Тестирует успешную генерацию случайного номера
     *
     * @throws Exception в случае ошибки
     */
    @Test
    public void testSuccessGenerationRandomNumber() throws Exception {
        // Проверка
        String number = _numberGenerator.generateRandomNumber();
        Matcher m = _baseNumberPattern.matcher(number);
        assertThat(m.find(), is(true));
    }

    /**
     * Тестирует успешную генерацию следующего номера
     *
     * @throws Exception в случае ошибки
     */
    @Test
    public void testSuccessGenerationNextNumber() throws Exception {
        // Подготовка
        String previousNumber = "А001АА 116 RUS";

        // Проверка
        String nextNumber = _numberGenerator.generateNextNumber(previousNumber);
        assertThat("А002АА 116 RUS", is(nextNumber));
    }

    /**
     * Тестирует неудачную генерацию следующего номера
     *
     * @throws Exception в случае ошибки
     */
    @Test
    public void testFailGenerationNextNumber() throws Exception {
        // Подготовка
        String wrongReviousNumber = "Б001ЪЯ 116 RUS";

        // Проверка
        Throwable thrown = catchThrowable(() -> {
            _numberGenerator.generateNextNumber(wrongReviousNumber);
        });
        assertThat(thrown).isInstanceOf(InvalidFormatNumberException.class);
    }

}
