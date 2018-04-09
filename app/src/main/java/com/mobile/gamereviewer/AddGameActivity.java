package com.mobile.gamereviewer;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddGameActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText gameName, reviewDesc;
    Spinner spinnerGenre;
    ImageView gameImage;
    RatingBar gameRating;
    Button btnAddGame;
    Button btnUpload;
    TextView userName;
    ImageView btnBack;
    private Toolbar toolbar;


    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        btnBack = (ImageView) toolbar.findViewById(R.id.btnGoBack);
        btnBack.setVisibility(View.VISIBLE);
        db = new DatabaseHelper(this);
        userName = (TextView) findViewById(R.id.userTxt);
        gameName = (EditText) findViewById(R.id.game_name);
        userName.setText(getIntent().getStringExtra("user_name"));
        gameName.setText(getIntent().getStringExtra("game_name"));
        reviewDesc = (EditText) findViewById(R.id.game_review);
        gameRating = (RatingBar) findViewById(R.id.game_rate);
        spinnerGenre = (Spinner) findViewById(R.id.game_genre);
        btnAddGame = (Button) findViewById(R.id.btn_submit);
        btnUpload = (Button) findViewById(R.id.btn_choose);
        gameImage = (ImageView) findViewById(R.id.upload_image);


        //Action listener on upload button click event
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(AddGameActivity.this,  new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY
                );
            }
        });

        //Toolbar Navigation -> Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onSupportNavigateUp();
            }
        });


        addGameReview();

    }

    // Navigate back to home page
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    //Read external storrage image gallery
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_GALLERY) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {

                Toast.makeText(AddGameActivity.this, "You don't have permissions to access file location ", Toast.LENGTH_LONG).show();
            }
            return;
        }
    }
    //Display selected image in ui
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {

                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                gameImage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {

                e.printStackTrace();
            }
        }

    }
    // add gameName review data to sqlite game_review and gameName tables
    public void addGameReview() {


        btnAddGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (reviewDesc.getText().toString().matches("")) {

                        Toast.makeText(getApplicationContext(), "Review fields Required!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (db.insertGame(gameName.getText().toString(), spinnerGenre.getSelectedItem().toString(), imageViewToByte(gameImage))) {

                        db.insertGameReview(gameName.getText().toString(), (int) (Math.round(gameRating.getRating())), userName.getText().toString(), reviewDesc.getText().toString());

                        Toast.makeText(AddGameActivity.this, "Game review Successfully Saved", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(AddGameActivity.this, "Game review Can not be saved, Try again", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }
    // convert select image into byte array
    protected byte[] imageViewToByte(ImageView image) {


        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;


    }

}
