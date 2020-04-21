package ru.inovus.test.generator;

import java.util.List;

import ru.inovus.test.exceptions.InvalidFormatNumberException;
import ru.inovus.test.exceptions.InvalidGeneratorConfigurationExcpetion;

/**
 * Базовый тип генератора номеров. Предоставляет номера в формате A111AA 116
 * RUS, где A - любой символ из списка [А, Е, Т, О, Р, Н, У, К, Х, С, В, М], 1 -
 * любая цифра, 116 RUS - константа.
 */
class BaseRusGeneratorImpl extends AbstractNumberGenerator {

    /**
     * Конструктор
     *
     * @param aParams параметры генератора
     * @throws InvalidGeneratorConfigurationExcpetion в случае, если настройки
     *             генератора не валидны
     */
    protected BaseRusGeneratorImpl(Parameters aParams)
            throws InvalidGeneratorConfigurationExcpetion {
        super(aParams);
    }

    /**
     * Возвращает следующий двойной символ номера по заданному
     *
     * @param aSymbol заданный двойной символ
     * @return следующий двойной символ номера
     */
    private String getNextDoubleSymbol(String aSymbol) {
        StringBuilder result = null;
        int size = alphabet.size() - 1;
        if (1 == aSymbol.length()) {
            result = new StringBuilder(getNextSingleSymbol(aSymbol));
        } else if (2 == aSymbol.length()) {
            String first = String.valueOf(aSymbol.charAt(0));
            String second = String.valueOf(aSymbol.substring(1));
            if (0 == (alphabet.indexOf(second) - size)) {
                result = new StringBuilder(getNextSingleSymbol(first));
                result.append(getNextSingleSymbol(second));
            } else {
                result = new StringBuilder(first);
                result.append(getNextSingleSymbol(second));
            }
        }
        return result.toString();
    }

    /**
     * Возвращает следующий единичный символ номера по заданному
     *
     * @param aSymbol заданный единичный символ
     * @return следующий единичный символ номера
     */
    private String getNextSingleSymbol(String aSymbol) {
        String result;
        int size = alphabet.size() - 1;
        int index = alphabet.indexOf(aSymbol);
        int nextIndex = ++index;
        if (!(nextIndex > (size))) {
            result = alphabet.get(nextIndex);
        } else {
            result = alphabet.get(nextIndex - alphabet.size());
        }
        return result;
    }

    /**
     * Генерирует цифровую часть номера
     *
     * @return цифровая
     */
    private String generateRandomDigitalPartNumber() {
        StringBuilder result = new StringBuilder();
        int num = random(0, 999);
        result.append(appendZeros(num));
        return result.toString();
    }

    /**
     * Добавляет нули к числовой части номера в случае необходимости
     *
     * @param aNumber числовая часть номера
     * @return строка с числовой частью номера
     */
    private String appendZeros(int aNumber) {
        StringBuilder result = new StringBuilder();
        int countDigits = countDigits(aNumber);
        LOGGER.info("Количество цифр " + countDigits + " Само число " + aNumber);
        switch (countDigits) {
        case 1:
            result.append("00");
            result.append(aNumber);
            break;
        case 2:
            result.append("0");
            result.append(aNumber);
            break;
        default:
            result.append(aNumber);
            break;
        }
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateRandomNumber() {
        StringBuilder result = new StringBuilder();
        String randomNum = generateRandomDigitalPartNumber();
        result.append(alphabet.get(random(0, alphabet.size() - 1)));
        result.append(randomNum);
        result.append(alphabet.get(random(0, alphabet.size() - 1)));
        result.append(alphabet.get(random(0, alphabet.size() - 1)));
        result.append(" 116 RUS");
        return result.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateNextNumber(String aNumber) throws InvalidFormatNumberException {
        StringBuilder result = null;
        int digitPartNum = 0;
        if (checkNumber(aNumber, baseNumberPattern)) {
            List<String> numberArr = parseNumber(aNumber, baseNumberPattern);
            if (3 == numberArr.size()) {
                result = new StringBuilder();
                String digitsPart = numberArr.get(1);
                List<String> digitsPartArr = parseNumber(digitsPart, digitalNumberPattern);
                switch (digitsPartArr.size()) {
                case 1:
                    digitPartNum = Integer.parseInt((digitsPartArr.get(0)));
                    if (999 == digitPartNum) {
                        if ("ХХ".equals(numberArr.get(2))) {
                            result.append(getNextDoubleSymbol(numberArr.get(0)));
                            result.append("001");
                            result.append(getNextDoubleSymbol(numberArr.get(2)));
                            result.append(" 116 RUS");
                        } else {
                            result.append(numberArr.get(0));
                            result.append("001");
                            result.append(getNextDoubleSymbol(numberArr.get(2)));
                            result.append(" 116 RUS");
                        }
                    } else {
                        result.append(numberArr.get(0));
                        result.append(appendZeros(++digitPartNum));
                        result.append(numberArr.get(2));
                        result.append(" 116 RUS");
                    }
                    break;
                case 2:
                    digitPartNum = Integer.parseInt((digitsPartArr.get(1)));
                    result.append(numberArr.get(0));
                    result.append(appendZeros(++digitPartNum));
                    result.append(numberArr.get(2));
                    result.append(" 116 RUS");
                    break;
                default:
                    break;
                }
                LOGGER.info("Тест последовательной генерации");
                LOGGER.info("Цифровая часть: " + digitsPart);
                LOGGER.info("Разобранное число: " + digitsPartArr);
            }
        } else {
            throw new InvalidFormatNumberException("Number has an invalid format!");
        }
        return result.toString();
    }

}
