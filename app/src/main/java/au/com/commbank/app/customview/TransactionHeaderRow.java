package au.com.commbank.app.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.commbank.app.Constants;
import au.com.commbank.app.R;
import au.com.commbank.app.Utils;
import au.com.commbank.app.pojo.Transaction;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class TransactionHeaderRow extends LinearLayout {


    @InjectView(R.id.descriptionDate)
    TextView descriptionDate;

    @InjectView(R.id.momentDate)
    MomentTextView momentDate;

    public TransactionHeaderRow(Context context) {
        super(context);
        init(null);
    }

    public TransactionHeaderRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(null);
    }

    public TransactionHeaderRow(Context context, Transaction transaction) {
        super(context);
        init(transaction);
    }

    private void init(Transaction transaction) {


        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.transaction_header_row, this);

        ButterKnife.inject(this);

        if  (transaction != null ) {
            descriptionDate.setText(Utils.displayDateFormat(transaction.getEffectiveDate()));
            momentDate.setText(transaction.getEffectiveDate(), TextView.BufferType.NORMAL);
        }
        else{
            descriptionDate.setText(Constants.EMPTY_STRING);
            momentDate.setText(Constants.EMPTY_STRING);
        }
    }

    public void setValues(Transaction transaction){
        init(transaction);
    }
}
