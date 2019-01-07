package practices.com.recycler.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.recycler.R;
import practices.com.recycler.domain.model.Jean;

public class JeansAdapter extends RecyclerView.Adapter<JeansAdapter.JeanViewHolder> {

    private List<Jean> jeans;

    public JeansAdapter(List<Jean> jeans) {
        this.jeans = jeans;
    }

    @NonNull
    @Override
    public JeanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(viewGroup.getContext()).
              inflate(R.layout.item_jean, viewGroup, false);

      return new JeanViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return jeans.size();
    }

    @Override
    public void onBindViewHolder(@NonNull JeanViewHolder jeanViewHolder, int i) {
        Jean jean =  jeans.get(i);
        jeanViewHolder.nameTextView.setText(jean.getName());
        jeanViewHolder.sizeTextView.setText(jean.getSize());
        String price = Double.toString(jean.getPrice());
        jeanViewHolder.priceTextView.setText(price);
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
