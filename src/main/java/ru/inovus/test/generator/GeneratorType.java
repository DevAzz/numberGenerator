package ru.inovus.test.generator;

/**
 * Тип генератора
 */
public enum GeneratorType {

    /**
     * Базовый тип генератора номеров. Предоставляет номера в формате A111AA 116
     * RUS, где A - любой символ из списка [А, Е, Т, О, Р, Н, У, К, Х, С, В, М],
     * 1 - любая цифра, 116 RUS - константа.
     */
    BASE_RUS_CAR_NUMBER;

}
