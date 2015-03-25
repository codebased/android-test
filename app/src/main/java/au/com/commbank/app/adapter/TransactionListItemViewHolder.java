package au.com.commbank.app.adapter;

/**
 * Created by codebased on 24/03/15.
 */

import android.view.View;

import au.com.commbank.app.R;
import au.com.commbank.app.customview.TransactionHeaderRow;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.pojo.Transaction;
import butterknife.ButterKnife;
import butterknife.InjectView;

public final class TransactionListItemViewHolder extends BaseListItemViewHolder<Transaction> {

    @InjectView(R.id.transactionItem)
    TransactionRow row;

    @InjectView(R.id.transactionHeaderItem)
    TransactionHeaderRow headerRow;

    public TransactionListItemViewHolder(View itemView) {
        super(itemView);

        ButterKnife.inject(this, itemView);
    }

    @Override
    public void onClick(View v) {

    }

    public TransactionRow getRow() {
        return row;
    }

    public TransactionHeaderRow getHeaderRow() {
        return headerRow;
    }

}