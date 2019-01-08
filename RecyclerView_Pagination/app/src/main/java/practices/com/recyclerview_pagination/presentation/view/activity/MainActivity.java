package practices.com.recyclerview_pagination.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.recyclerview_pagination.R;
import practices.com.recyclerview_pagination.model.Jean;
import practices.com.recyclerview_pagination.presentation.contract.MainContract;
import practices.com.recyclerview_pagination.presentation.presenter.MainPresenter;
import practices.com.recyclerview_pagination.presentation.view.adapter.JeansAdapter;
import practices.com.recyclerview_pagination.presentation.view.listener.OnItemClickListener;


public class MainActivity extends AppCompatActivity implements MainContract.View,
        OnItemClickListener{


    //region Constants
    private static final int PAGE_SIZE = 8;
    //endregion

    //region UI variables
    @BindView(R.id.jeans_recycler_main)
    RecyclerView jeansRecyclerView;

    @BindView(R.id.progress_main)
    ProgressBar progressBar;
    //endregion

    //region Member variables
    private MainContract.Presenter presenter;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private JeansAdapter jeansAdapter;
    private LinearLayoutManager jeansLayoutManager;
    //endregion

    //region Listeners
    private RecyclerView.OnScrollListener scrollListener =
            new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    int lastVisiblePositionListener = jeansLayoutManager.findLastVisibleItemPosition();
                    int lastPosition = jeansAdapter.getItemCount() -1;
                    boolean isLastPosition = lastVisiblePositionListener == lastPosition;

                    if(!isLoading && !isLastPage && isLastPosition){
                        jeansAdapter.addFooter();
                        isLoading =  true;
                        presenter.loadNextJeans(PAGE_SIZE);
                    }
                }
            };
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
        jeansLayoutManager = new LinearLayoutManager(this);
        jeansLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jeansRecyclerView.setLayoutManager(jeansLayoutManager);
        jeansAdapter = new JeansAdapter(this, this);
        jeansRecyclerView.setAdapter(jeansAdapter);
        jeansRecyclerView.addOnScrollListener(scrollListener);

        presenter = new MainPresenter(this);
        presenter.loadFirstJeans(PAGE_SIZE);

    }
    //endregion


    //region Contract methods
    @Override
    public void showFirstJeans(List<Jean> jeans) {
        hideProgress();
        jeansAdapter.clear();
        if(jeans.size() > 0){
            jeansAdapter.addAll(jeans);

            if(jeans.size() < PAGE_SIZE){
                isLastPage = true;
            }

        }else{
            isLastPage = true;
        }
    }

    @Override
    public void showNextJeans(List<Jean> jeans) {
        jeansAdapter.removeFooter();
        isLastPage = false;
        isLoading = false;
        if(jeans.size() > 0){
            jeansAdapter.addAll(jeans);

            if(jeans.size() < PAGE_SIZE){
                isLastPage = true;
            }

        }else{
            isLastPage = true;
        }
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
