package com.example.mealplanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GET = 2;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 3;

    private Toolbar toolbar;
    private Button cameraButton;
    private TextView add_meal_name;
    private ImageView add_picture;
    private Button saveButton;
    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_add);

        cameraButton = (Button) findViewById(R.id.camera_button);
        saveButton = (Button) findViewById(R.id.save_meal);
        add_meal_name = (TextView) findViewById(R.id.add_meal_name);
        add_picture = (ImageView) findViewById(R.id.add_meal_picture);

//        setSupportActionBar(toolbar);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal();
            }
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.my_navigation_items);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()) {
                    case R.id.nav_home:

                        Intent homeIntent = new Intent(AddActivity.this, MainActivity.class);
                        homeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(homeIntent);

                        break;


                    case R.id.nav_favourites:

                        Intent favouritesIntent = new Intent(AddActivity.this, FavouritesActivity.class);
                        favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(favouritesIntent);

                        break;

                }

                return false;
            }
        });

    }

    public void firebaseConfig() {
        storageReference = FirebaseStorage.getInstance().getReference("Added_Meals");
        databaseReference = FirebaseDatabase.getInstance().getReference("Added_Meals");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            add_picture.setImageBitmap(imageBitmap);

        }
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            try {

                Uri imageURL = data.getData();
                Bitmap thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURL);
                add_picture.setImageBitmap(thumbnail);


            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Exception: " + e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void cameraIntentCode() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void takePicture() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            cameraIntentCode();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cameraIntentCode();
                } else {
                    Toast.makeText(getApplicationContext(),"Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }

    }

    private void addMeal() {
        if(imageUri != null) {

            StorageReference fileReference = storageReference.child(String.valueOf(System.currentTimeMillis()));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddActivity.this, "Meal Added Successfully", Toast.LENGTH_LONG).show();

                            Add add = new Add(add_meal_name.getText().toString().trim(),
                                    taskSnapshot.getUploadSessionUri().toString());
                            String addedMealId = databaseReference.push().getKey();
                            databaseReference.child(addedMealId).setValue(add);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(AddActivity.this, "Take picture of meal!", Toast.LENGTH_SHORT).show();
        }
    }

}