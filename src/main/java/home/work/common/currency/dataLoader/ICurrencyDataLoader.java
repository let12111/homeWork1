package home.work.common.currency.dataLoader;

import home.work.common.currency.model.CurrencyModel;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface ICurrencyDataLoader {

    public List<CurrencyModel> LoadCurrency(String  currencyName  ) throws IOException;
}
