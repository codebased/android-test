package au.com.commbank.app.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import au.com.commbank.app.Utils;

public class MomentTextView extends TextView {
    public MomentTextView(Context context) {
        super(context);
    }

    public MomentTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MomentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (Utils.isEmptyOrNull(text)) {
            super.setText(text, type);
        } else {

            try {

                // @todo get the format from customcontrol property such as pattern.
                Date transactionDate = Utils.instanceDateFormat().parse(text.toString());
                setText(transactionDate, type);

            } catch (ParseException ex) {
                // well just don't do anything for now.
                super.setText(text, type);
            }
        }
    }

    public void setText(Date date, BufferType type) {
        Date currentDate = new Date();
        // umm.. I don't know if DateUtils functions... can do the magic here.
        long difference = currentDate.getTime() - date.getTime();
        long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

        super.setText(String.format("%d days ago", days), type);
    }
}