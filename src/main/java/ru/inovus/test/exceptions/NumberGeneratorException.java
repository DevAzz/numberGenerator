package ru.inovus.test.exceptions;

/**
 * Общее исключение генерации номера
 */
public class NumberGeneratorException extends Exception {

    /** Идентификатор для сериализации */
    private static final long serialVersionUID = -7570775109429315924L;

    /**
     * Конструктор
     */
    public NumberGeneratorException() {
        super();
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     */
    public NumberGeneratorException(String aMessage) {
        super(aMessage);
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     * @param aCause причина исключения
     */
    public NumberGeneratorException(String aMessage, Exception aCause) {
        super(aMessage, aCause);
    }

    /**
     * Конструктор
     *
     * @param aCause причина исключения
     */
    public NumberGeneratorException(Exception aCause) {
        super(aCause);
    }

}
