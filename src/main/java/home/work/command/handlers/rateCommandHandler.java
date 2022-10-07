package home.work.command.handlers;

import home.work.command.utils.paramValidator.RateParamValidator;
import home.work.common.currency.calculator.CalcAvgParams;
import home.work.common.currency.calculator.CurrencyCalculator;
import home.work.common.currency.dataLoader.ICurrencyDataLoader;

import home.work.common.currency.model.CurrencyModel;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class rateCommandHandler implements ICommandHadler {
    enum CommandMode {
        WEEK,
        TOMORROW
    }

    CurrencyCalculator calculator;
    ICurrencyDataLoader currencyDataLoader;
    RateParamValidator rateParamValidator;

    public rateCommandHandler(CurrencyCalculator calculator, ICurrencyDataLoader currencyDataLoader, RateParamValidator rateParamValidator) {
        this.calculator = calculator;
        this.currencyDataLoader = currencyDataLoader;
        this.rateParamValidator = rateParamValidator;
    }

    Integer TOMMOROW = 1;
    Integer WEEK = 7;

    private String calcTomorrow(String currency) throws Exception {

        var calcAvgParams = new CalcAvgParams();
        calcAvgParams.rateInterval = TOMMOROW;
        calcAvgParams.interval = WEEK;
        calcAvgParams.currencyList = currencyDataLoader.LoadCurrency(currency);
        var result = calculator.calculateAvg(calcAvgParams);
        var lastDay = getLastDate(calcAvgParams.currencyList);
        return resultFormatter(result, lastDay);
    }


    private Date getLastDate(List<CurrencyModel> currList) {
        return currList.stream().map(x -> x.data).
                sorted(Comparator.reverseOrder()).findFirst().get();
    }

    private String calcWeek(String currency) throws Exception {

        var calcAvgParams = new CalcAvgParams();
        calcAvgParams.rateInterval = WEEK;
        calcAvgParams.interval = WEEK;
        var currModel = currencyDataLoader.LoadCurrency(currency);
        calcAvgParams.currencyList = currencyDataLoader.LoadCurrency(currency);
        var lastDay = getLastDate(calcAvgParams.currencyList);
        var result = calculator.calculateAvg(calcAvgParams);
        return resultFormatter(result, lastDay);
    }

    Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    private String resultFormatter(List<Float> items, Date lastDay) {
        var day = lastDay;
        var result = "";
        for (Float item : items) {
            day = addDays(day, 1);
            result += getDayOfWeek(day) + " " + item + System.lineSeparator();
        }
        return result;
    }

    private String getDayOfWeek(Date dt1) {
        DateFormat format2 = new SimpleDateFormat("YYYY.MM.dd");
        String finalDay = format2.format(dt1);
        return finalDay;
    }


    CommandMode getMode(List<String> params) throws Exception {
        var hasWeek = params.stream().anyMatch(x -> x.toLowerCase().equals("week"));
        var hasTomorrow = params.stream().anyMatch(x -> x.toLowerCase().equals("tomorrow"));
        if (hasWeek && !hasTomorrow) {
            return CommandMode.WEEK;
        } else if (!hasWeek && hasTomorrow) {
            return CommandMode.TOMORROW;
        } else {
            throw new Exception("Отсутствует параметр интервала для прогноза!");
        }
    }

    String getCurrency(List<String> params) {
        return params.get(0);

    }

    @Override
    public String executeCommand(List<String> params) throws Exception {
        rateParamValidator.Validate(params);
        var result = "";
        var commandCurrency = getCurrency(params);
        var commandMode = getMode(params);


        if (commandMode == CommandMode.TOMORROW) {
            result = calcTomorrow(commandCurrency);
        } else if (commandMode == CommandMode.WEEK) {
            result = calcWeek(commandCurrency);
        } else {
            throw new Exception("Неизвестная команда");
        }
        return result;
    }
}