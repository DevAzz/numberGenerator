package ru.inovus.test.generator;

import ru.inovus.test.exceptions.InvalidFormatNumberException;
import ru.inovus.test.exceptions.IssuedNumberException;

/**
 * Контракт генератора номеров
 */
public interface INumberGenerator {

    /**
     * Генерирует случайный номер
     *
     * @return случайно сгенерированный номер
     * @throws IssuedNumberException в случае, если номер был выдан ранее
     */
    public String generateRandomNumber() throws IssuedNumberException;

    /**
     * Генерирует номер на основе заданного
     *
     * @param aNumber заданный номер, на основе которого выполняется генерация
     * @throws InvalidFormatNumberException в случае, если заданный номер имеет
     *             неверный формат
     * @return номер
     */
    public String generateNextNumber(String aNumber) throws InvalidFormatNumberException;

    /**
     * Возвращает тип генератора
     *
     * @return тип генератора
     */
    public GeneratorType getType();

}
