package practices.com.recycler.presentation.presenter;

import practices.com.recycler.data.JeansRepository;
import practices.com.recycler.presentation.contract.MainContract;

public class MainPresenter implements MainContract.Presenter {

    //region Member variables
    private MainContract.View view;
    private JeansRepository jeansRepository;
    //endregion

    public MainPresenter(MainContract.View view) {
        this.view = view;
        jeansRepository = new JeansRepository();
    }


    @Override
    public void loadJeans() {
        view.showProgress();
        view.showJeans(jeansRepository.getJeans());
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
