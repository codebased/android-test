package au.com.commbank.app;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.content.Context;
import android.net.NetworkInfo;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.ConnectionResult;
import 	android.app.Dialog;


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
            instanceSdf = new SimpleDateFormat();
        }

        // applying a default pattern that can be further changed as when required.
        instanceSdf.applyPattern(MainApplication.getInstance().getResources().getString(R.string.dateformat));
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

    public static String displayDateFormat(Date effectiveDate) {
        SimpleDateFormat newFormat = new SimpleDateFormat(MainApplication.getInstance().getResources().getString(R.string.displayDateFormat));
        return newFormat.format(effectiveDate);
    }

    public static  boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static boolean isGooglePlayServicesAvailable(Activity activity) {
        final int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (status != ConnectionResult.SUCCESS) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, activity , 1);
            dialog.show();
            return false;
        } else {
            return true;
        }
    }
}