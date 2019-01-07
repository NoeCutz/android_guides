package practices.com.recycler.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import practices.com.recycler.R;
import practices.com.recycler.domain.model.Jean;
import practices.com.recycler.presentation.contract.MainContract;
import practices.com.recycler.presentation.presenter.MainPresenter;
import practices.com.recycler.presentation.view.adapter.JeansAdapter;
import practices.com.recycler.presentation.view.listener.OnItemClickListener;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        OnItemClickListener {

    //region UI variables
    @BindView(R.id.jeans_recycler_main)
    RecyclerView jeansRecyclerView;

    @BindView(R.id.progress_main)
    ProgressBar progressBar;
    //endregion

    //region Member variables
    private MainContract.Presenter presenter;
    //endregion

    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeElements();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //endregion


    //region Helper methods
    private void initializeElements(){
        ButterKnife.bind(this);
        jeansRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jeansRecyclerView.setLayoutManager(layoutManager);

        presenter = new MainPresenter(this);
        presenter.loadJeans();

    }
    //endregion


    //region Contract methods
    @Override
    public void showJeans(List<Jean> jeans) {
        hideProgress();
        JeansAdapter jeansAdapter =  new JeansAdapter(this, jeans, this);
        jeansRecyclerView.setAdapter(jeansAdapter);
    }

    @Override
    public void showProgress() {
        jeansRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        jeansRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    //endregion

    //region Listener methods
    @Override
    public void onClickItem(int position) {
        String message = String.format(
                Locale.getDefault(),
                getString(R.string.position),
                position
        );
        Toast.makeText(this, message,  Toast.LENGTH_SHORT).show();
    }
    //endregion
}
