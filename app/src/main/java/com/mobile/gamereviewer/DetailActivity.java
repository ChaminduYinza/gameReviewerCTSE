package com.mobile.gamereviewer;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mobile.gamereviewer.model.GameReview;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    Button addComment;
    TextView gameName;
    ImageView mainImg;
    DatabaseHelper db;
    ArrayList<GameReview> gameReviewList;
    GameReviewAdapter adapter = null;
    ListView listView;
    TextView gName;
    private Toolbar toolbar;
    EditText dialogName;
    EditText dialogComment;
    RatingBar gameRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = new DatabaseHelper(this);
        addComment = (Button) findViewById(R.id.btnAddReview);

        gameName = (TextView) findViewById(R.id.txtGameTitle);
        mainImg = (ImageView) findViewById(R.id.imgGame);


        gName = (TextView) findViewById(R.id.txtGameTitle);


        listView = (ListView) findViewById(R.id.comment_list);


        gameReviewList = new ArrayList<>();
        adapter = new GameReviewAdapter(this, R.layout.item_comment, gameReviewList);
        listView.setAdapter(adapter);

        showAddReview();

        db = new DatabaseHelper(this);
        Cursor gameResultSet = db.getGameByName(getIntent().getStringExtra("game_name"));

        gameResultSet.moveToFirst();

        String name = gameResultSet.getString(gameResultSet.getColumnIndex("name"));
        byte[] resultImg= gameResultSet.getBlob(gameResultSet.getColumnIndex("img"));
        gameResultSet.getColumnIndex("genre");

        gameName.setText(name);
//        Picasso.with(getApplicationContext()).load("http://i.imgur.com/DvpvklR.png").into(mainImg);

        Glide.with(this).load(resultImg).into(mainImg);
//        Picasso.with(getApplicationContext()).load(resultImg).error(R.drawable.black_img).into(mainImg);


        loadGameReviewList(getIntent().getStringExtra("game_name"));


    }
    // this function navigate back to home
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //load select gamereviews by gameName name
    public void loadGameReviewList(String name) {
        Cursor reviewResultSet = db.getGameReviewByName(name);


        gameReviewList.clear();

        while (reviewResultSet.moveToNext()) {

            int rate = reviewResultSet.getInt(reviewResultSet.getColumnIndex("rate"));
            String review = reviewResultSet.getString(reviewResultSet.getColumnIndex("review"));
            String user = reviewResultSet.getString(reviewResultSet.getColumnIndex("added_user"));

            gameReviewList.add(new GameReview(rate, review, user));


        }
    }
    // show add review dialog box by click addcomment button
    public void showAddReview() {

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // custom dialog
                final Dialog dialog = new Dialog(DetailActivity.this);
                dialog.setContentView(R.layout.review_dialog_box);
                dialogName = (EditText) dialog.findViewById(R.id.dialog_name);
                dialogComment = (EditText) dialog.findViewById(R.id.dialog_comment);

                gameRating = (RatingBar) dialog.findViewById(R.id.dialog_rate);
                dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setGravity(Gravity.BOTTOM);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_btn);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if (db.insertGameReview(gName.getText().toString(), (int)(Math.round(gameRating.getRating())), dialogName.getText().toString(), dialogComment.getText().toString())) {


                            Toast.makeText(DetailActivity.this, "Game review Successfully Saved", Toast.LENGTH_LONG).show();


                        } else {

                            Toast.makeText(DetailActivity.this, "Error occurred while saving", Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                        loadGameReviewList(gName.getText().toString());

                    }
                });

                dialog.show();


            }
        });
    }


}
