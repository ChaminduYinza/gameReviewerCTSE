package com.mobile.gamereviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ValidateGameNameActivity extends AppCompatActivity {

    EditText gameName;
    EditText addedUser;
    Button btnNext;
    ImageView btnBack;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_name);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        btnBack = (ImageView) findViewById(R.id.btnGoBack);
        btnBack.setVisibility(View.VISIBLE);
        db = new DatabaseHelper(this);
        gameName = (EditText) findViewById(R.id.game_name);
        addedUser = (EditText) findViewById(R.id.user_name);
        btnNext = (Button) findViewById(R.id.btn_next);


        //Toolbar Navigation -> Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSupportNavigateUp();
            }
        });

        //Validate game name and do re-direction depends on the uniqueness of the game name
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (gameName.getText().toString().matches("") && addedUser.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "All fields Required!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (db.validateGameName(gameName.getText().toString())) {
                    Intent intent = new Intent(ValidateGameNameActivity.this, AddGameActivity.class);
                    intent.putExtra("game_name", gameName.getText().toString());
                    intent.putExtra("user_name", addedUser.getText().toString());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ValidateGameNameActivity.this, DetailActivity.class);
                    intent.putExtra("game_name", gameName.getText().toString());
                    Toast.makeText(getApplicationContext(), "The game is already in the added game list, Please add review", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
