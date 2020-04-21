package ru.inovus.test.exceptions;

/**
 * Исключение неправильного формата номера
 */
public class InvalidFormatNumberException extends NumberGeneratorException {

    /** Идентификатор для сериализации */
    private static final long serialVersionUID = 7216936034629238189L;

    /**
     * Конструктор
     */
    public InvalidFormatNumberException() {
        super();
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     */
    public InvalidFormatNumberException(String aMessage) {
        super(aMessage);
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     * @param aCause причина исключения
     */
    public InvalidFormatNumberException(String aMessage, Exception aCause) {
        super(aMessage, aCause);
    }

    /**
     * Конструктор
     *
     * @param aCause причина исключения
     */
    public InvalidFormatNumberException(Exception aCause) {
        super(aCause);
    }

}
