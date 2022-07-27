package com.panicape.wellnesscoin;

import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class HelpMainActivity extends AppCompatActivity implements View.OnTouchListener {

    private ImageView infographicMain;

    private ImageButton nexthelpBtn;

    private static final String TAG = "HELP ACTIVITY";
    private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_main);

        infographicMain = (ImageView) findViewById(R.id.infographicLogin);
        infographicMain.setOnTouchListener(this);


        Toast.makeText(this,
                "User: "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        dumpEvent(event);
        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                matrix.set(view.getImageMatrix());
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted
                break;

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted
                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down
                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);

                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    // create the transformation in the matrix  of points
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                } else if (mode == ZOOM) {
                    // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);

                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        // display the transformation on screen
        view.setImageMatrix(matrix);

        // indicate event was handled
        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);

        return (float) Math.sqrt(x * x + y * y);
    }

    /*
     * --------------------------------------------------------------------------
     * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
     * Description: calculates the midpoint between the two fingers
     * ------------------------------------------------------------
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * Show an event in the LogCat view, for debugging
     */
    private void dumpEvent(MotionEvent event) {
        String names[] = {"DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
                "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?"};
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_").append(names[actionCode]);

        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")");
        }

        sb.append("[");
        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#").append(i);
            sb.append("(pid ").append(event.getPointerId(i));
            sb.append(")=").append((int) event.getX(i));
            sb.append(",").append((int) event.getY(i));

            if (i + 1 < event.getPointerCount())
                sb.append(";");
        }

        sb.append("]");
        Log.d("Touch Events ---------", sb.toString());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem helStatusItem = menu.findItem(R.id.action_help_status);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);

        MenuItem nextItem = menu.findItem(R.id.action_next);

        backItem.setVisible(true);
        webItem.setVisible(true);
        exitItem.setVisible(true);

        logoffItem.setVisible(false);
        helStatusItem.setVisible(false);
        nextItem.setVisible(false);
        loginItem.setVisible(false);
        mainItem.setVisible(false);
        infoItem.setVisible(false);
        configItem.setVisible(false);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
        }

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean response = false;

        Intent mainIntent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case R.id.action_back:
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    mainIntent.putExtra("frag","login");
                } else {
                    mainIntent.putExtra("frag","home");
                }

                finishAfterTransition();
                startActivity(mainIntent);

                response = true;
                break;
            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                finishAfterTransition();
                startActivity(webIntent);

                response = true;
                break;
            case R.id.action_exit:
                mainIntent.putExtra("frag","login");

                finishAfterTransition();
                startActivity(mainIntent);

                response = true;
                break;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        Intent mainIntent = new Intent(this, MainActivity.class);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mainIntent.putExtra("frag","login");
        } else {
            mainIntent.putExtra("frag","home");
        }
        finishAfterTransition();
        startActivity(mainIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}