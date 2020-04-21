package ru.inovus.test.exceptions;

/**
 * Исключение невалидных параметров конфигурации генератора
 */
public class InvalidGeneratorConfigurationExcpetion extends NumberGeneratorException {

    /** Идентификатор для сериализации */
    private static final long serialVersionUID = 6209959923284927389L;

    /**
     * Конструктор
     */
    public InvalidGeneratorConfigurationExcpetion() {
        super();
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     */
    public InvalidGeneratorConfigurationExcpetion(String aMessage) {
        super(aMessage);
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     * @param aCause причина исключения
     */
    public InvalidGeneratorConfigurationExcpetion(String aMessage, Exception aCause) {
        super(aMessage, aCause);
    }

    /**
     * Конструктор
     *
     * @param aCause причина исключения
     */
    public InvalidGeneratorConfigurationExcpetion(Exception aCause) {
        super(aCause);
    }

}
