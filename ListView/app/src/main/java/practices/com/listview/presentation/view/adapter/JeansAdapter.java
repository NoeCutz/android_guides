package practices.com.listview.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.listview.R;
import practices.com.listview.model.Jean;

public class JeansAdapter extends BaseAdapter {

    private List<Jean> jeans;


    public JeansAdapter(List<Jean> jeans) {
        this.jeans = jeans;
    }

    @Override
    public int getCount() {
        return jeans.size();
    }

    @Override
    public Object getItem(int position) {
        return jeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
            convertView.setTag(convertView);
        }

        Jean jean = (Jean) getItem(position);
        viewHolder.nameTextView.setText(jean.getName());
        viewHolder.sizeTextView.setText(jean.getSize());
        viewHolder.priceTextView.setText(Double.toString(jean.getPrice()));


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
