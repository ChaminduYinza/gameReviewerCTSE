package com.mobile.gamereviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.gamereviewer.model.GameModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rushan on 3/28/2018.
 */

public class GameListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<GameModel> itemlist;

    public GameListAdapter(Context context, int layout, ArrayList<GameModel> itemlist) {
        this.context = context;
        this.layout = layout;
        this.itemlist = itemlist;
    }

    @Override
    public int getCount() {
        return itemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return itemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class  ViewHolder{

            ImageView item_img;
            TextView item_txt;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder =new ViewHolder();

        if(row == null){

            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(layout,null);


            holder.item_txt =(TextView)row.findViewById(R.id.textView_heading);
            holder.item_img =(ImageView) row.findViewById(R.id.imgGame);
            row.setTag(holder);
        }
        else {


            holder=(ViewHolder)row.getTag();
        }
        GameModel gameReview= itemlist.get(position);

        holder.item_txt.setText(gameReview.getGame_name());

//        byte[] gameImage=gameReview.getGame_img();
//
//        Bitmap bitmap= BitmapFactory.decodeByteArray(gameImage,0,gameImage.length);
//        Log.e("IMAGE REV",bitmap.toString());
//        holder.item_img.setImageBitmap(bitmap);

//        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.item_img);
        Glide.with(holder.item_img.getContext()).load(gameReview.getGame_img()).into(holder.item_img);
//        Picasso.with(context).load(gameReview.getGame_img().toString()).error(R.drawable.black_img).into(holder.item_img);

        return row;
    }
}
