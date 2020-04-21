package ru.inovus.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ru.inovus.test.entities.NumberEntity;

/**
 * Репозиторий номеров, управляет жизненным циклом номера
 */
@Repository
public class NumberRepository {

    /** Список сгенерированных номеров */
    private final List<NumberEntity> _numbers = new ArrayList<>();

    /**
     * Проверяет наличие номера в списке уже сгенерированных
     *
     * @param aNumber номер
     * @return {@code true} - в случае, если такой номер уже был выдан
     */
    public boolean contains(NumberEntity aNumber) {
        return _numbers.contains(aNumber);
    }

    /**
     * Удаляет номер
     *
     * @param aNumber номер
     */
    public void removeNumber(NumberEntity aNumber) {
        _numbers.remove(aNumber);
    }

    /**
     * Сохраняет номер
     *
     * @param aNumber сущность номера
     */
    public void saveNumber(NumberEntity aNumber) {
        if (!contains(aNumber)) {
            _numbers.add(aNumber);
        }
    }

}
