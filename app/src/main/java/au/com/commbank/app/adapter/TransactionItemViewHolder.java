package au.com.commbank.app.adapter;

/**
 * Created by codebased on 24/03/15.
 */

import android.view.View;

import au.com.commbank.app.R;
import au.com.commbank.app.customview.TransactionHeaderRow;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.pojo.Transaction;
import butterknife.ButterKnife;
import butterknife.InjectView;

public final class TransactionItemViewHolder extends BaseListItemViewHolder<Transaction>{

    @InjectView(R.id.transactionItem)
    TransactionRow row;

    @InjectView(R.id.transactionHeaderItem)
    TransactionHeaderRow headerRow;

    OnListItemListener<TransactionRow> mListener;

    public TransactionItemViewHolder(View itemView) {
        super(itemView);

        ButterKnife.inject(this, itemView);

        row.SetOnClickListner(new TransactionRow.OnClickListener() {
            @Override
            public void OnClick(Atm atm) {
                if (mListener != null)
                    mListener.onClick(row);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (mListener != null)
            mListener.onClick(row);
    }

    public TransactionRow getRow() {
        return row;
    }

    public TransactionHeaderRow getHeaderRow() {
        return headerRow;
    }

    public void setListener(OnListItemListener<TransactionRow> listner){
        this.mListener = listner;
    }
}