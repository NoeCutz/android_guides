package practices.com.listview.presentation.contract;


import java.util.List;

import practices.com.listview.model.Jean;

public interface MainContract {
    interface View{
        void showJeans(List<Jean> jeans);
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void loadJeans();
        void onDestroy();
    }
}
