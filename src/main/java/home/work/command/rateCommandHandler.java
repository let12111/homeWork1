package home.work.command;

import home.work.common.currency.CalcAvgParams;
import home.work.common.currency.CurrencyCalculator;
import home.work.common.currency.ICurrencyDataLoader;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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

    public rateCommandHandler(CurrencyCalculator calculator, ICurrencyDataLoader currencyDataLoader) {
        this.calculator = calculator;
        this.currencyDataLoader = currencyDataLoader;
    }

    Integer TOMMOROW = 1;
    Integer WEEK = 7;

    private String calcTomorrow(String currency) throws Exception {

        var calcAvgParams = new CalcAvgParams();
        calcAvgParams.rateInterval = TOMMOROW;
        calcAvgParams.interval = WEEK;
        calcAvgParams.currencyList = currencyDataLoader.LoadCurrency(currency);
        var result = calculator.calculateAvg(calcAvgParams);
        var lastDay = new Date();
        return resultFormatter(result, lastDay);
    }

    private String calcWeek(String currency) throws Exception {

        var calcAvgParams = new CalcAvgParams();
        calcAvgParams.rateInterval = WEEK;
        calcAvgParams.interval = WEEK;
        var currModel = currencyDataLoader.LoadCurrency(currency);
        calcAvgParams.currencyList = currencyDataLoader.LoadCurrency(currency);
        var lastDay = new Date();
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
        DateFormat format2 = new SimpleDateFormat("EEEE");
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
            throw new Exception("rate interval is missing!");
        }
    }

    String getCurrency(List<String> params) {
        return params.get(0);

    }

    @Override
    public String executeCommand(List<String> params) throws Exception {
        var result = "";
        var commandMode = getMode(params);
        var commandCurrency = getCurrency(params);

        if (commandMode == CommandMode.TOMORROW) {
            result = calcTomorrow(commandCurrency);
        } else if (commandMode == CommandMode.WEEK) {
            result = calcWeek(commandCurrency);
        } else {
            throw new Exception("unexpected command mode");
        }
        return result;
    }
}