package practices.com.bottomnavigation.presentation.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.bottomnavigation.R;
import practices.com.bottomnavigation.presentation.view.fragment.FavoritesFragment;
import practices.com.bottomnavigation.presentation.view.fragment.HomeFragment;
import practices.com.bottomnavigation.presentation.view.fragment.NotificationsFragment;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    //region UI variables
    @BindView(R.id.bottom_navigation_main)
    BottomNavigationView bottomNavigationView;
    //endregion


    //region Lifecycle methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeElements();
    }
    //endregion


    //region Helper methods
    private void initializeElements(){
        ButterKnife.bind(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home_item_navigation);
    }

    private void showHome(){
        HomeFragment homeFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frame_main, homeFragment, HomeFragment.TAG);
        transaction.commit();
    }

    private void showFavorites(){
        FavoritesFragment favoritesFragment = FavoritesFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_frame_main, favoritesFragment, FavoritesFragment.TAG);
        transaction.commit();
    }

    private void showNotifications(){
        NotificationsFragment notificationsFragment = NotificationsFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(
                R.id.container_frame_main,
                notificationsFragment,
                NotificationsFragment.TAG
        );
        transaction.commit();
    }
    //endregion


    //region Listener methods
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home_item_navigation:
                showHome();
                return true;
            case R.id.favorites_item_navigation:
                showFavorites();
                return true;
            case R.id.notifications_item_navigation:
                showNotifications();
                return true;
        }
        return false;
    }
    //endregion
}
