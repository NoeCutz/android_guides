package practices.com.listview_pagination.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.listview_pagination.R;
import practices.com.listview_pagination.model.Jean;
import practices.com.listview_pagination.presentation.contract.MainContract;
import practices.com.listview_pagination.presentation.presenter.MainPresenter;
import practices.com.listview_pagination.presentation.view.adapter.JeansAdapter;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    //region Constants variables
    private static final int PAGE_SIZE = 8;
    //endregion

    //region UI variables
    @BindView(R.id.jeans_list_main)
    ListView jeansListView;

    @BindView(R.id.progress_main)
    ProgressBar progressBar;
    //endregion

    //region Member variables
    private MainContract.Presenter presenter;
    private boolean isLastPage = false;
    private boolean isLoading = false;
    private JeansAdapter jeansAdapter;
    private View footerListView;
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


        footerListView = getLayoutInflater().inflate(R.layout.layout_footer_progress, null);

        jeansAdapter = new JeansAdapter(this, new ArrayList<Jean>());
        jeansListView.setAdapter(jeansAdapter);
        jeansListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    int lastVisiblePositionListener = view.getLastVisiblePosition();
                    int lastPosition = jeansAdapter.getCount() - 1;
                    boolean isLastPosition = lastVisiblePositionListener == lastPosition;

                    if(!isLoading && !isLastPage &&
                            isLastPosition){
                        isLoading = true;
                        jeansListView.addFooterView(footerListView);
                        presenter.loadNextJeans(PAGE_SIZE);
                    }

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }
        });



        presenter = new MainPresenter(this);
        presenter.loadFirstJeans(PAGE_SIZE);

    }

    private void onItemSelected(int position){
        String message = String.format(
                Locale.getDefault(),
                getString(R.string.position),
                position
        );
        Toast.makeText(this, message,  Toast.LENGTH_SHORT).show();
    }
    //endregion


    //region Contract methods
    @Override
    public void showFirstJeans(List<Jean> jeans) {
        hideProgress();
        jeansAdapter.clear();
        if(jeans.size() >0){
            jeansAdapter.addAll(jeans);
            jeansListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onItemSelected(position);
                }
            });

            if(jeans.size() < PAGE_SIZE){
                isLastPage = true;
            }

        }else {
            isLastPage = true;
        }
    }

    @Override
    public void showNextJeans(List<Jean> jeans) {
        jeansListView.removeFooterView(footerListView);
        isLoading = false;
        isLastPage = false;

        if(jeans.size() >0){
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
