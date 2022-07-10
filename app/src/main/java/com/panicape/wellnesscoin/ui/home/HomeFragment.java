package com.panicape.wellnesscoin.ui.home;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private Button loginBtn;
    private ImageView infographics;
    private ScrollView scrollView;

    private float mScale = 1f;
    private ScaleGestureDetector scaleGestureDetector;
    GestureDetector gestureDetector;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        infographics = binding.infographicLogin;
        scrollView = binding.homeScrollView;

        scaleGestureDetector = new ScaleGestureDetector(binding.getRoot().getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                // firstly we will get the scale factor by which we want to zoom
                float scale = 1 - detector.getScaleFactor();
                float prevScale = mScale;
                mScale += scale;

                // we can maximise our focus to 10f only
                if (mScale > 10f)
                    mScale = 10f;

                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f / prevScale, 1f / mScale,
                        1f / prevScale, 1f / mScale,
                        detector.getFocusX(), detector.getFocusY());

                // duration of animation will be 0.It will not change by self after that
                scaleAnimation.setDuration(0);

                scaleAnimation.setFillAfter(true);

                // we are setting it as animation
                scrollView.startAnimation(scaleAnimation);
                return true;
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return false;
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        });



        return root;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        // special types of touch screen events such as pinch ,
        // double tap, scrolls , long presses and flinch,
        // onTouch event is called if found any of these
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return gestureDetector.onTouchEvent(event);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}