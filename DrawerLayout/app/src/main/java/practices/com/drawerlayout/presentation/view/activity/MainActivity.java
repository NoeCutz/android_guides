package practices.com.drawerlayout.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.drawerlayout.R;
import practices.com.drawerlayout.presentation.view.fragment.CameraFragment;
import practices.com.drawerlayout.presentation.view.fragment.GalleryFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //region UI variables
    @BindView(R.id.drawer_main)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;

    @BindView(R.id.navigation_main)
    NavigationView navigationView;
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


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.camera_item_navigation);
        showCamera();

    }

    private void showCamera(){
        CameraFragment cameraFragment = CameraFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(
                R.id.container_frame_main,
                cameraFragment,
                CameraFragment.TAG
        );

        fragmentTransaction.commit();
    }

    private void showGallery(){
        GalleryFragment galleryFragment = GalleryFragment.newInstance();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(
                R.id.container_frame_main,
                galleryFragment,
                GalleryFragment.TAG
        );

        fragmentTransaction.commit();
    }
    //endregion

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.favorites_item_toolbar) {
            Toast.makeText(this, R.string.menu_toolbar_favorites, Toast.LENGTH_LONG).show();
            return true;
        }else if (id == R.id.settings_item_toolbar){
            Toast.makeText(this, R.string.menu_toolbar_settings, Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.camera_item_navigation) {
            showCamera();
        } else if (id == R.id.gallery_item_navigation) {
           showGallery();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
