package practices.com.listview_pagination.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.listview_pagination.R;
import practices.com.listview_pagination.model.Jean;

public class JeansAdapter extends ArrayAdapter<Jean> {

    //region Constructors
    public JeansAdapter(@NonNull Context context) {
        super(context, R.layout.item_jean);
    }

    public JeansAdapter(@NonNull Context context, List<Jean> jeans) {
        super(context, R.layout.item_jean, jeans);
    }
    //endregion


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JeanViewHolder viewHolder;
        if(convertView != null){
            viewHolder = (JeanViewHolder) convertView.getTag();
        }else{
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_jean,
                    parent,
                    false
            );

            viewHolder = new JeanViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Jean jean = (Jean) getItem(position);
        viewHolder.nameTextView.setText(jean.getName());
        viewHolder.sizeTextView.setText(jean.getSize());
        String price = String.format(
                Locale.getDefault(),
                parent.getContext().getString(R.string.price_jean),
                jean.getPrice()
        );

        viewHolder.priceTextView.setText(price);

        return convertView;
    }


    //region Inner class
    static class JeanViewHolder{

        //region UI variables
        @BindView(R.id.name_text_jean)
        TextView nameTextView;

        @BindView(R.id.size_text_jean)
        TextView sizeTextView;

        @BindView(R.id.price_text_jean)
        TextView priceTextView;
        //endregion

        //region Constructors
        public JeanViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
        //endregion
    }


}
