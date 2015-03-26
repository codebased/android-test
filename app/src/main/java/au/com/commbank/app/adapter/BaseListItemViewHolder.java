package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseListItemViewHolder<TModel>
        extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TModel mModel;

    public BaseListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setModel(TModel model) {
        this.mModel = model;
    }

    public abstract void onClick(View v);
}