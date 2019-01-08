package practices.com.listview_pagination.presentation.contract;


import java.util.List;

import practices.com.listview_pagination.model.Jean;

public interface MainContract {
    interface View{
        void showFirstJeans(List<Jean> jeans);
        void showNextJeans(List<Jean> jeans);
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void loadFirstJeans(int pageSize);
        void loadNextJeans(int pageSize);
        void onDestroy();
    }
}
