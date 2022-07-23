package com.panicape.wellnesscoin;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class PausaHands extends AppCompatActivity {

    /**
     * Saving folder root
     */
    private final String rootFolder = "bienestarAPP/";

    /**
     * Current Photo path
     */
    private String currentPhotoPath;

    /**
     * Current video path
     */
    private String currentVideoPath;

    /**
     * Default Video Path
     */
    private final String videoFolderPath = rootFolder + "videos";

    /**
     * Video Path
     */
    private String videoPath;

    /**
     * Video View Component
     */
    private VideoView videoView;

    /** Video Saving Request Code */
    static final int REQUEST_VIDEO_CAPTURE = 1;

    /** StorageReference component */
    private StorageReference mStorageRef;

    /** Resource mDatabase */
    private DatabaseReference mDatabase;

    private FirebaseUser firebaseUser;

    private String username;

    private Button saveHandsBtn;

    /** progress bar component */
    private ProgressBar p;

    /**
     * activityResultLauncher. Component to Launch an action of record video
     */
    ActivityResultLauncher<Intent> activityResultLauncher;

    private Uri videoUri;

    private boolean isVideoSaved;


    // Methods

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser == null) {
            Toast.makeText(this, "No se ha detectado usuario conectado", Toast.LENGTH_SHORT);
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Log.i("PausaHands: ", "CURRENT="+firebaseUser.getEmail());

            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    snapshot.child("Users").getChildren().forEach(element -> {
                        if (element.child(element.getKey()).child("mail").exists() &&
                                element.child(element.getKey()).child("mail").equals(firebaseUser.getEmail())) {
                            Log.i("PausaHands: ", "USER FOUND: " + element.getKey());
                            username = element.getKey();
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("PausaHands: ", "Pausa Cancelada. Error="+error.getMessage());
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa_hands);

        isVideoSaved = false;
        if (!validarPermisos()) {
            ActivityCompat.requestPermissions(PausaHands.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 100);
        }

        p = findViewById(R.id.progressBar);
        p.setVisibility(View.GONE);

        videoView = findViewById(R.id.videoViewNew);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent intent1 = result.getData();
                if (result.getResultCode() == Activity.RESULT_OK) {
                    videoView.start();
                    videoUri = intent1.getData();
                    videoView.setVideoURI(videoUri);
                }
            }
        });

        saveHandsBtn = findViewById(R.id.saveHandsPausaBtn);
        saveHandsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoUri != null) {
                    Log.i("SAVE VIDEO", "Video Uri to save: " + videoUri);

                    p.setVisibility(View.VISIBLE);
                    mStorageRef = FirebaseStorage.getInstance().getReference(videoFolderPath + System.currentTimeMillis());
                    mStorageRef.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        /**
                         * Method onSuccess
                         *
                         * @param taskSnapshot
                         */
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            String timestamp = "" + System.currentTimeMillis();

                            while (!uriTask.isSuccessful()) {
                                // URl del video subido
                                Task<Uri> downloadedUri = taskSnapshot.getStorage().getDownloadUrl();

                                // Now can add video details to DB
                                HashMap<String, Object> hashMap = new HashMap<String, Object>();
                                hashMap.put("id", "Panicape" + timestamp);
                                hashMap.put("title", "pausa_" + timestamp);
                                hashMap.put("timestamp", timestamp);
                                hashMap.put("video_url", downloadedUri);

                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                                reference.child("Videos").child("Panicape").setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    /**
                                     * Method onSuccess
                                     * @param aVoid
                                     */
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // video upload to db
                                        p.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(), "Pausa Guardada", Toast.LENGTH_SHORT).show();
                                        Log.i("VIDEO", "Video uploaded");

                                        isVideoSaved = true;

                                        Intent homeActivity = new Intent(PausaHands.this,
                                                PausasMainActivity.class);
                                        p.setVisibility(View.GONE);
                                        startActivity(homeActivity);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    /**
                                     *
                                     * @param e
                                     */
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        p.setVisibility(View.GONE);
                                        Toast.makeText(getApplicationContext(),
                                                "Error: " + e.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                        Log.e("ERROR",
                                                "PausaHands: savePausaBtn1 Button: "
                                                        + e.getMessage());
                                        isVideoSaved=false;
                                    }
                                });
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        /**
                         * Method onFailure
                         *
                         * @param e Exception
                         */
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            p.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            isVideoSaved=true;
                        }
                    });
                } else {
                    Log.i("ERROR", "Video no guardado. Uri=null");
                    Toast.makeText(getApplicationContext(), "Error. Video no guardado",
                            Toast.LENGTH_SHORT).show();
                    isVideoSaved=true;
                }
            }
        });

        p = findViewById(R.id.progressBar);
        p.setVisibility(View.GONE);
    }

    public boolean validarPermisos() {
        return ContextCompat.checkSelfPermission(PausaHands.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this,
                        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public void recordPausaVideo (View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        activityResultLauncher.launch(intent);
    }

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem infoItem =  menu.findItem(R.id.action_info);
        MenuItem settingsItem =  menu.findItem(R.id.action_settings);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem nextItem = menu.findItem(R.id.action_next);

        backItem.setVisible(true);
        webItem.setVisible(true);
        logoffItem.setVisible(true);
        profileItem.setVisible(true);
        exitItem.setVisible(true);

        mainItem.setVisible(false);
        nextItem.setVisible(false);
        infoItem.setVisible(false);
        loginItem.setVisible(false);
        settingsItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.action_back:
                Intent backIntent = new Intent(this, PausasMainActivity.class);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Cancelar Pausa?");
                builder.setMessage("¿Desea cancelar la pausa?");
                builder.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAfterTransition();
                                startActivity(backIntent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                finishAfterTransition();
                startActivity(webIntent);
                return true;

            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent mainIntent = new Intent(this, MainActivity.class);
                    mainIntent.putExtra("frag", "profile");
                    finishAfterTransition();
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(this,
                            "No se ha encontrado usuario conectado",
                            Toast.LENGTH_SHORT).show();
                }

                return true;

            case R.id.action_logoff:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;

            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, PausasMainActivity.class);

        if (videoUri != null) {
            if(isVideoSaved) {
                finishAfterTransition();
                startActivity(backIntent);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Cancelar Pausa?");
                builder.setMessage("¿Desea cancelar la pausa?");
                builder.setPositiveButton("Si",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAfterTransition();
                                startActivity(backIntent);
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else {
            finishAfterTransition();
            startActivity(backIntent);
        }
    }
}