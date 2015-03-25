package au.com.commbank.app.adapter;

import au.com.commbank.app.customview.TransactionRow;

/**
 * Created by codebased on 25/03/15.
 */
public interface OnItemListner<TModel> {
    public void onClick(TModel row);
}