package ru.inovus.test.controller;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ru.inovus.test.entities.NumberEntity;
import ru.inovus.test.exceptions.InvalidFormatNumberException;
import ru.inovus.test.exceptions.InvalidGeneratorConfigurationExcpetion;
import ru.inovus.test.exceptions.IssuedNumberException;
import ru.inovus.test.generator.GeneratorFactory;
import ru.inovus.test.generator.GeneratorType;
import ru.inovus.test.generator.INumberGenerator;
import ru.inovus.test.generator.Parameters;
import ru.inovus.test.repositories.NumberRepository;

/**
 * Spring контроллер приложения
 */
@RestController
public class RESTController {

    /** Логгер */
    protected static final Logger LOGGER = LogManager.getLogger(RESTController.class);

    /** Генератор номеров */
    private INumberGenerator _numberGenerator;

    /** Репозиторий сущностей номеров */
    @Autowired
    private NumberRepository _repo;

    /** Предыдущий номер */
    private String _previousNumber;

    {
        Parameters params = Parameters.newBuilder()
                .withAlphabet(
                        Arrays.asList("А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"))
                .withBaseNumberPattern(
                        "(^[АЕТОРНУКХСВМ])(\\d{3}(?<!000))([АЕТОРНУКХСВМ]{2})\\s116\\sRUS$")
                .withDigitalNumberPattern("^(0{1})(\\d{2}(?<!0\\d))$|^(0{2})(\\d{1})$|^(\\d{3}$)")
                .withType(GeneratorType.BASE_RUS_CAR_NUMBER).build();
        try {
            _numberGenerator = new GeneratorFactory().createNumberGenerator(params);
        } catch (InvalidGeneratorConfigurationExcpetion e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Приветствие
     *
     * @return строка приветствия
     */
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Number Generator service.";
    }

    /**
     * Выдает произвольный номер, соответствующий формату A111AA 116 RUS
     *
     * @return произвольный номер
     * @throws IssuedNumberException в случае, если сгенерированный номер уже
     *             был выдан ранее
     */
    @RequestMapping(value = "/number/random", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public NumberEntity random() throws IssuedNumberException {
        String number = _numberGenerator.generateRandomNumber();
        NumberEntity numberEntity = new NumberEntity(number);
        if (!_repo.contains(numberEntity)) {
            _repo.saveNumber(numberEntity);
        } else {
            throw new IssuedNumberException("The number was issued earlier!");
        }
        return numberEntity;
    }

    /**
     * Последовательно, от меньшего к большему, выдает сгенерированный номер
     * начиная с A001AA 116 RUS
     *
     * @return номер, сгенерированный последовательно
     * @throws InvalidFormatNumberException в случае, если предыдущий номер имел
     *             невалидный формат
     */
    @RequestMapping(value = "/number/next", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public NumberEntity next() throws InvalidFormatNumberException {
        if (null == _previousNumber) {
            _previousNumber = "А001АА 116 RUS";
        }
        _previousNumber = _numberGenerator.generateNextNumber(_previousNumber);
        return new NumberEntity(_previousNumber);
    }

}
