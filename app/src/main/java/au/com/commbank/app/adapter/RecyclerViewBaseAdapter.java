package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class RecyclerViewBaseAdapter<TModel>
        extends RecyclerView.Adapter
        <RecyclerView.ViewHolder> {

    protected List<TModel> mItems;

    public RecyclerViewBaseAdapter(List<TModel> modelData) {
        if (modelData == null || !(modelData instanceof List)) {
            throw new IllegalArgumentException("modelData must not be null and should be of type List<>");
        }

        this.mItems = modelData;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int idx);

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);
}