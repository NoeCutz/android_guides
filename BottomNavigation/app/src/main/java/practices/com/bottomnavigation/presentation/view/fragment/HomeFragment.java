package practices.com.bottomnavigation.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import practices.com.bottomnavigation.R;

public class HomeFragment extends Fragment {

    //region Constants
    public static final String TAG = "home-fragment";
    //endregion

    //region Factory methods
    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
    //endregion

    //region Lifecycle methods
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    //endregion
}
