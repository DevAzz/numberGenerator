package ru.inovus.test.generator;

import ru.inovus.test.exceptions.InvalidGeneratorConfigurationExcpetion;

/**
 * Фабрика создания генератора
 */
public class GeneratorFactory {

    /**
     * Создает генератор
     *
     * @param aParams параметры
     * @return генератор
     * @throws InvalidGeneratorConfigurationExcpetion в случае, если
     *             настройкигенератора не валидны
     */
    public INumberGenerator createNumberGenerator(Parameters aParams)
            throws InvalidGeneratorConfigurationExcpetion {
        INumberGenerator generator = null;
        if (null != aParams) {
            switch (aParams.getType()) {
            case BASE_RUS_CAR_NUMBER:
                generator = new BaseRusGeneratorImpl(aParams);
                break;
            default:
                break;
            }
        }
        return generator;
    }

}
