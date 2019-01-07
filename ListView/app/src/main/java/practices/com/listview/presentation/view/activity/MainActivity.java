package practices.com.listview.presentation.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import practices.com.listview.R;
import practices.com.listview.model.Jean;
import practices.com.listview.presentation.contract.MainContract;
import practices.com.listview.presentation.presenter.MainPresenter;
import practices.com.listview.presentation.view.adapter.JeansAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View {


    //region UI variables
    @BindView(R.id.jeans_list_main)
    ListView jeansListView;

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
        presenter = new MainPresenter(this);
        presenter.loadJeans();

    }

    @OnItemSelected(R.id.jeans_list_main)
    void onItemSelected(int position){
        String message = "position: " + position;
        Toast.makeText(this, message,  Toast.LENGTH_SHORT).show();
    }
    //endregion


    //region Contract methods
    @Override
    public void showJeans(List<Jean> jeans) {
        hideProgress();
        JeansAdapter jeansAdapter =  new JeansAdapter(jeans);
        jeansListView.setAdapter(jeansAdapter);
    }

    @Override
    public void showProgress() {
        jeansListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        jeansListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
    //endregion
}
