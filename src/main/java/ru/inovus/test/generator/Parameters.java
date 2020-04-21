package ru.inovus.test.generator;

import java.util.List;

/**
 * Параметры генераторов
 */
public class Parameters {

    /** Тип генератора */
    private GeneratorType _type;

    /** Общий шаблон номера */
    private String _baseNumberPattern;

    /** Шаблон цифровой части номера */
    private String _digitalNumberPattern;

    /** Алфавит генератора */
    private List<String> _alphabet;

    /**
     * Возвращает значение поля {@link #_type type}
     *
     * @return значение поля
     */
    public GeneratorType getType() {
        return _type;
    }

    /**
     * Устанавливает новое значение полю {@link #_type type}
     *
     * @param aType значение, устанавливаемое полю
     */
    public void setType(GeneratorType aType) {
        _type = aType;
    }

    /**
     * Возвращает значение поля {@link #_baseNumberPattern baseNumberPattern}
     *
     * @return значение поля
     */
    public String getBaseNumberPattern() {
        return _baseNumberPattern;
    }

    /**
     * Устанавливает новое значение полю {@link #_baseNumberPattern
     * baseNumberPattern}
     *
     * @param aBaseNumberPattern значение, устанавливаемое полю
     */
    public void setBaseNumberPattern(String aBaseNumberPattern) {
        _baseNumberPattern = aBaseNumberPattern;
    }

    /**
     * Возвращает значение поля {@link #_digitalNumberPattern
     * digitalNumberPattern}
     *
     * @return значение поля
     */
    public String getDigitalNumberPattern() {
        return _digitalNumberPattern;
    }

    /**
     * Устанавливает новое значение полю {@link #_digitalNumberPattern
     * digitalNumberPattern}
     *
     * @param aDigitalNumberPattern значение, устанавливаемое полю
     */
    public void setDigitalNumberPattern(String aDigitalNumberPattern) {
        _digitalNumberPattern = aDigitalNumberPattern;
    }

    /**
     * Возвращает значение поля {@link #_alphabet alphabet}
     *
     * @return значение поля
     */
    public List<String> getAlphabet() {
        return _alphabet;
    }

    /**
     * Устанавливает новое значение полю {@link #_alphabet alphabet}
     *
     * @param aAlphabet значение, устанавливаемое полю
     */
    public void setAlphabet(List<String> aAlphabet) {
        _alphabet = aAlphabet;
    }

    /**
     * Создает экземпляр билдера
     *
     * @return билдер
     */
    public static Builder newBuilder() {
        return new Parameters().new Builder();
    }

    /**
     * Билдер настроек
     */
    public class Builder {

        /**
         * Конструктор
         */
        private Builder() {
            // Ничего не делаем
        }

        /**
         * Устанавливает тип генератора
         *
         * @param aStrategy тип генератора
         * @return билдер
         */
        public Builder withType(GeneratorType aStrategy) {
            Parameters.this._type = aStrategy;
            return this;
        }

        /**
         * Устанавливает общий шаблон номера
         *
         * @param aBaseNumberPattern общий шаблон номера
         * @return билдер
         */
        public Builder withBaseNumberPattern(String aBaseNumberPattern) {
            Parameters.this._baseNumberPattern = aBaseNumberPattern;
            return this;
        }

        /**
         * Устанавливает шаблон цифровой части номера
         *
         * @param aDigitalNumberPattern шаблон цифровой части номера
         * @return билдер
         */
        public Builder withDigitalNumberPattern(String aDigitalNumberPattern) {
            Parameters.this._digitalNumberPattern = aDigitalNumberPattern;
            return this;
        }

        /**
         * Устанавливает афлавит генератора
         *
         * @param aAlphabet афлавит генератора
         * @return билдер
         */
        public Builder withAlphabet(List<String> aAlphabet) {
            Parameters.this._alphabet = aAlphabet;
            return this;
        }

        /**
         * Создает экземпляр настроек
         *
         * @return экземпляр настроек
         */
        public Parameters build() {
            return Parameters.this;
        }
    }

    /**
     * Проверяет валидность настроек
     * 
     * @return {@code true} - в случае, если настройки валидны
     */
    public boolean isValid() {
        return (null != _type) && (null != _baseNumberPattern) && (null != _digitalNumberPattern)
                && (null != _alphabet) && (!_alphabet.isEmpty());
    }

}
