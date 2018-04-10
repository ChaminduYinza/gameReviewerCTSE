package com.mobile.gamereviewer;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.mobile.gamereviewer.model.GameModel;

import java.util.ArrayList;


public class GameListActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<GameModel>list;
    GameListAdapter adapter=null;
    DatabaseHelper db;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        goBack();



        String genre =getIntent().getStringExtra("genre");
//        Toast.makeText(GameListActivity.this,"Recived"+genre,Toast.LENGTH_SHORT).show();

        gridView =(GridView) findViewById(R.id.review_list);
        list = new ArrayList<>();
        adapter=new GameListAdapter(this,R.layout.custom_item_review,list);
        gridView.setAdapter(adapter);


        showDetailReview();


        db=new DatabaseHelper(this);
        Cursor cursor= db.getGameByGenre(genre);


        list.clear();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String genredata = cursor.getString(cursor.getColumnIndex("genre"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("img"));

            list.add(new GameModel(name,image,genredata));



        }
        for(int i =0 ; i<list.size();i++) {
            Log.e("Game Data", list.get(i).getGame_genre());
        }
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void showDetailReview(){

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("game_name",list.get(position).getGame_name());
                startActivity(intent);


            }
        });


    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
