package ru.inovus.test.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.inovus.test.exceptions.InvalidGeneratorConfigurationExcpetion;

/**
 * Абстрактная реализация генератора номеров
 */
abstract class AbstractNumberGenerator implements INumberGenerator {

    /** Логгер */
    protected static final Logger LOGGER = LogManager.getLogger(AbstractNumberGenerator.class);

    /** Тип генератора */
    private final GeneratorType _type;

    /** Общий шаблон номера */
    protected final Pattern baseNumberPattern;

    /** Шаблон цифровой части номера */
    protected final Pattern digitalNumberPattern;

    /** Алфавит генератора */
    protected final List<String> alphabet;

    /**
     * Конструктор
     *
     * @param aParams параметры генератора
     */
    protected AbstractNumberGenerator(Parameters aParams)
            throws InvalidGeneratorConfigurationExcpetion {
        super();
        if ((null != aParams) && aParams.isValid()) {
            _type = aParams.getType();
            baseNumberPattern = Pattern.compile(aParams.getBaseNumberPattern());
            digitalNumberPattern = Pattern.compile(aParams.getDigitalNumberPattern());
            alphabet = aParams.getAlphabet();
        } else {
            throw new InvalidGeneratorConfigurationExcpetion("Generator settings are not valid!");
        }
    }

    /**
     * Вычисляет количество цифр в числе
     *
     * @param aNumber число
     * @return количество цифр
     */
    protected int countDigits(int aNumber) {
        return (int) Math.ceil(Math.log10(aNumber + 0.5));
    }

    /**
     * Возвращает случайное число в заданном диапазоне
     *
     * @param aMin минимальное значение
     * @param aMax максимальное значение
     * @return случайное число
     */
    protected int random(int aMin, int aMax) {
        aMax -= aMin;
        return (int) (Math.random() * ++aMax) + aMin;
    }

    /**
     * Проверяет номер на соответствие заданному шаблону
     *
     * @param aNumber проверяемый номер
     * @param aPattern шаблон
     * @return {@code true} - если номер соответствует заданному шаблону,
     *         {@code false} - в противном случае
     */
    protected boolean checkNumber(String aNumber, Pattern aPattern) {
        Matcher matcher = null;
        if ((null != aNumber) && !aNumber.isEmpty()) {
            matcher = aPattern.matcher(aNumber);
        }
        return matcher.find();
    }

    /**
     * Разбирает номер по заданному шаблону
     *
     * @param aNumber разбираемый номер
     * @param aPattern шаблон
     * @return список строк, состоящий из групп символов, заданных шаблоном
     *         aPattern
     */
    protected List<String> parseNumber(String aNumber, Pattern aPattern) {
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder(aNumber);
        Matcher m = aPattern.matcher(builder.toString());
        int groupCount = m.groupCount();
        if (m.matches()) {
            for (int i = 1; i < (groupCount + 1); i++) {
                String group = m.group(i);
                if (null != group) {
                    list.add(group);
                }
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GeneratorType getType() {
        return _type;
    }

}
