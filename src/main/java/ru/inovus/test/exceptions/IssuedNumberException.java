package ru.inovus.test.exceptions;

/**
 * Исключение ранее выданного номера
 */
public class IssuedNumberException extends NumberGeneratorException {

    /** Идентификатор для сериализации */
    private static final long serialVersionUID = -1678976247800028219L;

    /**
     * Конструктор
     */
    public IssuedNumberException() {
        super();
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     */
    public IssuedNumberException(String aMessage) {
        super(aMessage);
    }

    /**
     * Конструктор
     *
     * @param aMessage текст исключения
     * @param aCause причина исключения
     */
    public IssuedNumberException(String aMessage, Exception aCause) {
        super(aMessage, aCause);
    }

    /**
     * Конструктор
     *
     * @param aCause причина исключения
     */
    public IssuedNumberException(Exception aCause) {
        super(aCause);
    }

}
