package practices.com.recyclerview_pagination.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.recyclerview_pagination.R;
import practices.com.recyclerview_pagination.model.Jean;
import practices.com.recyclerview_pagination.presentation.view.listener.OnItemClickListener;

public class JeansAdapter extends BaseAdapter<Jean> {

    //region Member variables
    private Context context;
    private OnItemClickListener listener;
    //endregion

    //region Constructors
    public JeansAdapter(Context context, OnItemClickListener listener) {
        super();
        this.context =  context;
        this.listener = listener;
    }
    //endregion

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_jean,parent, false);

        return new JeanViewHolder(view);
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
       View view =  LayoutInflater.from(parent.getContext()).
               inflate(R.layout.layout_footer_progress,parent, false);

       return new FooterViewHolder(view);
    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        JeanViewHolder jeanViewHolder = (JeanViewHolder) viewHolder;
        Jean jean =  items.get(position);
        jeanViewHolder.nameTextView.setText(jean.getName());
        jeanViewHolder.sizeTextView.setText(jean.getSize());
        String price = String.format(
                Locale.getDefault(),
                context.getString(R.string.price_jean),
                jean.getPrice()
        );

        jeanViewHolder.priceTextView.setText(price);

        jeanViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItem(position);
            }
        });
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        items.add(new Jean());
    }

    //region Inner class
    static class JeanViewHolder extends RecyclerView.ViewHolder{

        //region UI variables
        @BindView(R.id.name_text_jean)
        TextView nameTextView;

        @BindView(R.id.size_text_jean)
        TextView sizeTextView;

        @BindView(R.id.price_text_jean)
        TextView priceTextView;
        //endregion

        //region Constructors
        public JeanViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        //endregion
    }


}
