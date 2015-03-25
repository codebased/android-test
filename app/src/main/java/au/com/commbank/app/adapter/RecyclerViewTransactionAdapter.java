package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import au.com.commbank.app.R;
import au.com.commbank.app.pojo.Transaction;

/**
 * Created by codebased on 25/03/15.
 */
public class RecyclerViewTransactionAdapter extends RecyclerViewBaseAdapter<TransactionListItemViewHolder, Transaction> {


    public RecyclerViewTransactionAdapter(List<Transaction> modelData) {
        super(modelData);
    }

    @Override
    public void onBindViewHolder(TransactionListItemViewHolder viewHolder, int idx) {
        Transaction model = mItems.get(idx);

        Transaction preItem = null;
        if (idx != 0) {
            preItem = mItems.get(idx - 1);
        }

        if (preItem == null ||
                model.getEffectiveDate().getTime() != preItem.getEffectiveDate().getTime()) {
            viewHolder.getHeaderRow().setValues(model);
            viewHolder.getHeaderRow().setVisibility(View.VISIBLE);
        } else {
            viewHolder.getHeaderRow().setVisibility(View.GONE);
        }

        viewHolder.setModel(model);

        viewHolder.getRow().setValues(model, null);
    }


    @Override
    public TransactionListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.test, viewGroup, false);

        return new TransactionListItemViewHolder(itemView);
    }
}
