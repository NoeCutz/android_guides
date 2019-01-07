package practices.com.recyclerview_pagination.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region PresentationConstants
    protected static final int ITEM = 1;
    protected static final int FOOTER = 2;
    //endregion

    //region Description
    protected boolean isFooterAdded = false;
    protected List<T> items;
    //endregion


    //region Constructors
    public BaseAdapter(){
        items =  new ArrayList<>();
    }
    //endregion


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder =  null;

        switch (viewType){
            case ITEM :
                viewHolder = createItemViewHolder(viewGroup);
                break;
            case FOOTER:
                viewHolder = createFooterViewHolder(viewGroup);
                break;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)){
            case ITEM :
                bindItemViewHolder(viewHolder, position);
                break;
            case FOOTER:
                bindFooterViewHolder(viewHolder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //region Abstract methods
    protected abstract RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent);
    protected abstract RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent);
    protected abstract void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position);
    protected abstract void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder);
    public abstract void addFooter();
    //endregion

    public void add(T item){
        items.add(item);
        notifyItemInserted(items.size() -1);
    }

    public void remove(T item){
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void remove(int position){
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void restore(T item, int position){
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(List<T> items){
        for(T item: items){
            add(item);
        }
    }

    public void clear(){
        isFooterAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public T getItem(int position){
        return items.get(position);
    }

    public void removeFooter(){
        isFooterAdded =  false;
        int position = items.size() -1;
        T item =  items.get(position);

        if(item != null){
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isLastPosition(int position){
        return (position == items.size() -1);
    }
}
