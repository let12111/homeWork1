package home.work.common.currency;

import home.work.common.currency.model.CurrencyModel;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class CurrencyCSVDataLoader implements ICurrencyDataLoader {

    public List<CurrencyModel> LoadCurrency(String currencyName) throws IOException {
        var result = new ArrayList<CurrencyModel>();

        String file = currencyName + ".csv";
        String delimiter = ";";
        String line;
        List<List<String>> lines = new ArrayList();
        try (BufferedReader br =
                     new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(delimiter));
                lines.add(values);
            }
            lines.remove(0);
            lines.forEach(l -> result.add(
                    new CurrencyModel(
                            Integer.parseInt(l.get(0)),
                            dateFromatter(l.get(1)),
                            Float.parseFloat(l.get(2)),
                            l.get(3)
                    )));
        } catch (FileNotFoundException e) {
            String cwd = Path.of("").toAbsolutePath().toString();
            throw new FileNotFoundException("currency file not found in path " + cwd + "\\" + file);

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    Date dateFromatter(String strDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.ENGLISH);//9/22/2022
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }

}
