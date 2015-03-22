package au.com.commbank.app.customview;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import au.com.commbank.app.Constants;
import au.com.commbank.app.R;
import au.com.commbank.app.Utils;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.pojo.Pending;
import au.com.commbank.app.pojo.Transaction;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by codebased on 21/03/15.
 */
public class TransactionRow extends LinearLayout {

    @InjectView(R.id.description)
     TextView mDescription;

    @InjectView(R.id.amount)
     TextView mAmount;

    private OnClickListener mClickListner = null;
    private Transaction mTransaction = null;
    private Atm mAtm = null;

    public TransactionRow(Context context) {
        super(context);
        init();
    }

    public TransactionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TransactionRow(Context context, Transaction transaction) {
        super(context);
        init(transaction, null);
    }

    public TransactionRow(Context context, Transaction transaction, Atm atm) {
        super(context);
        init(transaction, atm);
    }

    private void init() {
        init(null, null);
    }

    private void init(Transaction transaction, Atm atm) {

        mAtm = atm;
        mTransaction = transaction;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.transaction_row, this);

        ButterKnife.inject(this);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                if (mClickListner != null && mAtm != null) {
                    mClickListner.OnClick(mAtm);
                }
            }
        };

        mDescription.setOnClickListener(listener);
        mAmount.setOnClickListener(listener);

        if (transaction != null) {
            if (transaction instanceof Pending) {
                mDescription.setText(Html.fromHtml(String.format("<b>PENDING:</b> %s", transaction.getDescription())));
            } else {
                mDescription.setText(Html.fromHtml(transaction.getDescription()));
            }

            mAmount.setText(Utils.formatCurrency(transaction.getAmount()));
        } else {
            mDescription.setText(Constants.EMPTY_STRING);
            mAmount.setText(Constants.EMPTY_STRING);
        }
    }

    public void SetOnClickListner(OnClickListener listner) {
        mClickListner = listner;
    }

    public interface OnClickListener {
        void OnClick(Atm atm);
    }
}