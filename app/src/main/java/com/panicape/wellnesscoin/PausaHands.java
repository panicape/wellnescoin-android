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
import android.view.KeyEvent;
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

    /**
     * Video Saving Request Code
     */
    static final int REQUEST_VIDEO_CAPTURE = 1;

    /**
     * StorageReference component
     */
    private StorageReference mStorageRef;

    /** Resource mDatabase */
    private DatabaseReference mDatabase;

    private FirebaseUser firebaseUser;

    private String username;

    /**
     * progress bar component
     */
    private ProgressBar p;

    /**
     *
     */
    private Button saveHandsBtn;

    /**
     * activityResultLauncher. Component to Launch an action of record video
     */
    ActivityResultLauncher<Intent> activityResultLauncher;

    /**
     * progress bar component
     */
    private Uri videoUri;


    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            Toast.makeText(this, "No se ha detectado usuario conectado", Toast.LENGTH_SHORT);
            startActivity(new Intent(this, MainActivity.class));
        } else {
            System.out.println("CURRENT="+firebaseUser.getEmail());
            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot element: snapshot.child("Users").getChildren()) {
                        if (element.child(element.getKey()).child("mail").exists() &&
                                element.child(element.getKey()).child("mail").equals(firebaseUser.getEmail())) {
                            System.out.println("USER FOUND: " + element.getKey());
                            username = element.getKey();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa_hands);

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

                                        Intent homeActivity = new Intent(PausaHands.this, MainActivity.class);
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
                                        Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        Log.e("ERROR", "PausaHands: savePausaBtn1 Button: " + e.getMessage());
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
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Log.i("ERROR", "Video no guardado. Uri=null");
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        p = findViewById(R.id.progressBar);
        p.setVisibility(View.GONE);
    }



    public boolean validarPermisos() {
        return ContextCompat.checkSelfPermission(PausaHands.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(PausaHands.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public void recordPausaVideo (View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        activityResultLauncher.launch(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea cancelar la pausa?");
            builder.setTitle("Cancelar");
            builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(PausaHands.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onKeyDown(keyCode, event);
    }

}