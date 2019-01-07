package practices.com.recyclerview_pagination.presentation.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.recyclerview_pagination.R;

public class FooterViewHolder extends RecyclerView.ViewHolder{

        //region UI variables
        @BindView(R.id.progress_footer)
        ProgressBar progressBar;
        //endregion

        //region Constructors
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        //endregion
    }