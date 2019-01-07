package practices.com.recyclerview_pagination.presentation.presenter;

import practices.com.recyclerview_pagination.data.JeansRepository;
import practices.com.recyclerview_pagination.presentation.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    //region Member variables
    private MainContract.View view;
    private JeansRepository jeansRepository;
    private int pageNumber = 0;
    //endregion

    public MainPresenter(MainContract.View view) {
        this.view = view;
        jeansRepository = new JeansRepository();
    }

    @Override
    public void loadFirstJeans(int pageSize) {
        pageNumber = 0;
        view.showProgress();
        view.showFirstJeans(jeansRepository.getJeans(pageNumber, pageSize));
    }

    @Override
    public void loadNextJeans(int pageSize) {
        pageNumber++;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        view.showNextJeans(jeansRepository.getJeans(pageNumber, pageSize));

    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
