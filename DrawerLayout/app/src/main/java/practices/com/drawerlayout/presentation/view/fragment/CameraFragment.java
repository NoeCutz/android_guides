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

public class CameraFragment extends Fragment {

    //region Constants
    public static final String TAG = "camera-fragment";
    //endregion

    //region UI variables
    @BindView(R.id.floating_button_camera)
    FloatingActionButton floatingActionButton;
    //endregion

    //region Factory methods
    public static CameraFragment newInstance(){
        CameraFragment cameraFragment = new CameraFragment() ;
        return cameraFragment;
    }
    //endregion


    //region Lifecycle methods
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_camera, container, false);
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
                Toast.makeText(getContext(),R.string.camera_message, Toast.LENGTH_LONG ).show();
            }
        });
    }
    //endregion
}
