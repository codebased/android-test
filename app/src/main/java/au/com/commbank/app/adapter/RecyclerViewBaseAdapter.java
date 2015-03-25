package au.com.commbank.app.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class RecyclerViewBaseAdapter<TModel>
        extends RecyclerView.Adapter
        <RecyclerView.ViewHolder> {

    protected List<TModel> mItems;

    public RecyclerViewBaseAdapter(List<TModel> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException(
                    "modelData must not be null");
        }

        this.mItems = modelData;
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int idx);
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType);

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}