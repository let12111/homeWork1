package home.work.common.currency.model;

import java.util.Date;

    public class CurrencyModel {
        public CurrencyModel(){}
        public CurrencyModel(int nominal, Date data, float curs, String cdx )
        {
            this.nominal=nominal;
            this.data=data;
            this.curs=curs;
            this.cdx=cdx;

        }


    public Integer nominal;
    public Date data;
    public  Float curs;
    public  String cdx;

}
