package practices.com.listview_pagination.presentation.presenter;


import practices.com.listview_pagination.data.JeansRepository;
import practices.com.listview_pagination.presentation.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    //region Member variables
    private MainContract.View view;
    private JeansRepository jeansRepository;
    private int pageNumber = 0;
    //endregion

    //region Constructors
    public MainPresenter(MainContract.View view) {
        this.view = view;
        jeansRepository = new JeansRepository();
    }
    //endregion

    @Override
    public void loadFirstJeans(int pageSize) {
        pageNumber = 0;
        view.showProgress();
        view.showFirstJeans(jeansRepository.getJeans(pageNumber, pageSize));
    }

    @Override
    public void loadNextJeans(int pageSize) {
        pageNumber++;
        view.showNextJeans(jeansRepository.getJeans(pageNumber, pageSize));
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
