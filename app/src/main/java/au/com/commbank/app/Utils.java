package au.com.commbank.app;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by codebased on 22/03/15.
 */
public class Utils {

    static SimpleDateFormat instanceSdf = null;

    public static String formatCurrency(Double value) {

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(value);
    }

    public static boolean isEmptyOrNull(CharSequence value) {
        return value == null || Utils.isEmptyOrNull(value.toString());
    }

    public static boolean isEmptyOrNull(String value) {
        return (value == null || value.isEmpty());
    }

    public static SimpleDateFormat instanceDateFormat() {

        if (instanceSdf == null) {
            instanceSdf = new SimpleDateFormat(MainApplication.getInstance().getResources().getString(R.string.dateformat));
        }

        return instanceSdf;
    }

    public static int CompareDates(String date1, String date2) throws ParseException {

        try {
            Date sourceDate = Utils.instanceDateFormat().parse(date1);
            Date targetDate = Utils.instanceDateFormat().parse(date2);
            return sourceDate.compareTo(targetDate);

        } catch (ParseException ex) {
            throw ex;
        }
    }
}