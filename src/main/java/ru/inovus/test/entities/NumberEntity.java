package ru.inovus.test.entities;

/**
 * Сущность сгенерированного сервиса
 */
public class NumberEntity {

    /** Сгенерированный номер */
    private String _number;

    /**
     * Конструктор
     *
     * @param aNumber сгенерированный номер
     */
    public NumberEntity(String aNumber) {
        super();
        _number = aNumber;
    }

    /**
     * Возвращает значение поля {@link #_number number}
     *
     * @return значение поля
     */
    public String getNumber() {
        return _number;
    }

    /**
     * Устанавливает новое значение полю {@link #_number number}
     *
     * @param aNumber значение, устанавливаемое полю
     */
    public void setNumber(String aNumber) {
        _number = aNumber;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((_number == null) ? 0 : _number.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NumberEntity other = (NumberEntity) obj;
        if (_number == null) {
            if (other._number != null) {
                return false;
            }
        } else if (!_number.equals(other._number)) {
            return false;
        }
        return true;
    }

}
