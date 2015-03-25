package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import au.com.commbank.app.MainApplication;
import au.com.commbank.app.R;
import au.com.commbank.app.Utils;
import au.com.commbank.app.customview.TransactionRow;
import au.com.commbank.app.pojo.AccountModel;
import au.com.commbank.app.pojo.Atm;
import au.com.commbank.app.pojo.Transaction;

public class RecyclerViewTransactionAdapter extends
        RecyclerViewBaseAdapter<Transaction> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private AccountModel mAccountModel;

    private OnItemListner<TransactionRow> mListner;

    public RecyclerViewTransactionAdapter(List<Transaction> modelData) {
        super(modelData);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.listitem, viewGroup, false);
            TransactionItemViewHolder transItem = new TransactionItemViewHolder(itemView);
            transItem.mListner = new OnItemListner<TransactionRow>() {
                @Override
                public void onClick(TransactionRow row) {
                    if (mListner != null ) {
                        mListner.onClick(row);
                    }
                }
            };

            return transItem;
        } else if (viewType == TYPE_HEADER) {
            View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.summary, viewGroup, false);
            return new AccountSummaryItemViewHolder(itemView);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if ( viewHolder instanceof TransactionItemViewHolder ) {
            TransactionItemViewHolder itemViewHolder  = (TransactionItemViewHolder) viewHolder;

            Transaction model = getItem(position);
            Transaction preItem = null;

            if (position != 1) {
                preItem = getItem(position - 1);
            }

            if (preItem == null) {
                itemViewHolder.getHeaderRow().setVisibility(View.VISIBLE);
                itemViewHolder.getHeaderRow().setValues(model);
            } else if (preItem != null && model.getEffectiveDate().getTime() != preItem.getEffectiveDate().getTime()) {
                itemViewHolder.getHeaderRow().setVisibility(View.VISIBLE);
                itemViewHolder.getHeaderRow().setValues(model);
            } else {
                itemViewHolder.getHeaderRow().setVisibility(View.GONE);
            }

            itemViewHolder.setModel(model);
            itemViewHolder.getRow().setValues(model, getAtmLocation(model.getAtmId()));

        } else if ( viewHolder instanceof AccountSummaryItemViewHolder){
            AccountSummaryItemViewHolder summaryItemViewHolder  = (AccountSummaryItemViewHolder) viewHolder;
            summaryItemViewHolder.getAccountSummary().setAccountHeader(mAccountModel.getAccount());
        }
    }

    private Atm getAtmLocation(String atmId) {

        if(Utils.isEmptyOrNull(atmId)) return null;

        for (Atm atm : mAccountModel.getAtms()) {
            if (atm != null && atm.getId().equalsIgnoreCase(atmId)) {
                return atm;
            }
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private Transaction getItem(int position) {
        return mItems.get(position-1);
    }

    public void setAccountModel(AccountModel accountModel) {
        mAccountModel = accountModel;
    }

    public void setListner(OnItemListner<TransactionRow> mListner) {
        this.mListner = mListner;
    }
}