package home.work.common.currency.calculator;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CurrencyCalculator {

    public List<Float> calculateAvg(CalcAvgParams params) throws Exception {
        if (params.currencyList.size() - 1 < params.interval) {
            throw new Exception("Too little data to calculate");
        }


        var resultList = new ArrayList<Float>();
        params.currencyList.sort(Comparator.comparing(o -> o.data));
        var intervallist = params.currencyList.subList(params.currencyList.size() - params.interval, params.currencyList.size());
        var calcList = new ArrayList<Float>(intervallist.stream().map(x -> x.curs).collect(Collectors.toList()));
        for (int i = 0; i < params.rateInterval; i++) {

            var avgVal = avg(calcList);
            resultList.add(avgVal);
            calcList.remove(0);
            calcList.add(avgVal);
        }

        return resultList;
    }

    float[] convertToArr(List<Float> list) {

        float[] floatArray = new float[list.size()];
        int i = 0;

        for (Float f : list) {
            floatArray[i++] = (f != null ? f : Float.NaN);
        }
        return floatArray;
    }

    public float avg(List<Float> items) {
        float result = 0;
        for (var item :
                items) {
            result += item;
        }


        result = result / items.size();
        return result;
    }
}
