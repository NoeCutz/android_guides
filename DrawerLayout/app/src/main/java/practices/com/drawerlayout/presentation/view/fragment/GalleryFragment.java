package practices.com.drawerlayout.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import practices.com.drawerlayout.R;

public class GalleryFragment extends Fragment {

    //region Constants
    public static final String TAG = "gallery-fragment";
    //endregion

    //region UI variables
    @BindView(R.id.floating_button_galllery)
    FloatingActionButton floatingActionButton;
    //endregion

    //region Factory methods
    public static GalleryFragment newInstance(){
        GalleryFragment galleryFragment = new GalleryFragment() ;
        return galleryFragment;
    }
    //endregion


    //region Lifecycle methods
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeElements();
    }
    //endregion

    //region Helper methods
    private void initializeElements(){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),R.string.gallery_message, Toast.LENGTH_LONG ).show();
            }
        });
    }
    //endregion
}
