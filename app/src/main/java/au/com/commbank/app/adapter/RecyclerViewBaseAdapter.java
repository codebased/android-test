package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;

import java.util.List;

public abstract class RecyclerViewBaseAdapter<TViewHolder extends BaseListItemViewHolder<TModel>, TModel> extends RecyclerView.Adapter
        <TViewHolder> {

    protected List<TModel> mItems;

    public RecyclerViewBaseAdapter(List<TModel> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }

        this.mItems = modelData;
    }

    public abstract void onBindViewHolder(TViewHolder viewHolder, int idx);
    public abstract TViewHolder  onCreateViewHolder(ViewGroup viewGroup, int i);

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}