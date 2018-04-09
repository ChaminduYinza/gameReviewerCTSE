package com.mobile.gamereviewer;


import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.mobile.gamereviewer.model.GameModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    GridView gridView;
    private ActionBarDrawerToggle drawerToggle;
    ArrayList<GameModel> gamelist;
    GameListAdapter adapter=null;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar_new);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        setupDrawerContent(nvDrawer);

        gridView =(GridView) findViewById(R.id.game_list);
        gamelist = new ArrayList<>();
        adapter=new GameListAdapter(this,R.layout.main_menu_item,gamelist);
        gridView.setAdapter(adapter);

        db=new DatabaseHelper(this);
        Cursor cursor= db.getAllGame();


        gamelist.clear();

        while (cursor.moveToNext()){


            int id = cursor.getInt(0);
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String genredata = cursor.getString(cursor.getColumnIndex("genre"));
            byte[] image = cursor.getBlob(cursor.getColumnIndex("img"));



            gamelist.add(new GameModel(name,image,genredata));



        }
        for(int i =0 ; i<gamelist.size();i++) {
            Log.e("Game Data", gamelist.get(i).getGame_genre());
        }
        adapter.notifyDataSetChanged();
        showDetailReview();

    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {



        switch(menuItem.getItemId()) {


            case R.id.nav_action:

                Intent intent=new Intent(this,GameListActivity.class);
                intent.putExtra("genre",menuItem.getTitle());
                startActivity(intent);


                break;
            case R.id.nav_race:

                Intent intent2=new Intent(this,GameListActivity.class);
                intent2.putExtra("genre",menuItem.getTitle());
                startActivity(intent2);

                break;
            case R.id.nav_adeventure:

                Intent intent3=new Intent(this,GameListActivity.class);
                intent3.putExtra("genre",menuItem.getTitle());
                startActivity(intent3);


                break;
            case R.id.nav_sports:

                Intent intent4=new Intent(this,GameListActivity.class);
                intent4.putExtra("genre",menuItem.getTitle());
                startActivity(intent4);

                break;
            case R.id.nav_moba:

                Intent intent5=new Intent(this,GameListActivity.class);
                intent5.putExtra("genre",menuItem.getTitle());
                startActivity(intent5);
                break;
            case R.id.nav_rpg:
                Intent intent6=new Intent(this,GameListActivity.class);
                intent6.putExtra("genre",menuItem.getTitle());
                startActivity(intent6);

                break;
            case R.id.nav_add_review:
                Intent intent7=new Intent(this,ValidateGameNameActivity.class);
                startActivity(intent7);

                break;
            default:

        }



        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
//        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
//                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }


//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }


        return super.onOptionsItemSelected(item);


    }
    private void showDetailReview(){

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("game_name",gamelist.get(position).getGame_name());
                startActivity(intent);


            }
        });


    }
    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

}
