package com.mobile.gamereviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobile.gamereviewer.model.GameModel;
import com.mobile.gamereviewer.model.GameReview;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Yinza on 3/31/2018.
 */

public class GameReviewAdapter extends BaseAdapter {

    Context context;
    private  int layout;
    private ArrayList<GameReview> commentlist;

    public GameReviewAdapter(Context context, int layout, ArrayList<GameReview> commentlist) {
        this.context = context;
        this.layout = layout;
        this.commentlist = commentlist;
    }

    @Override
    public int getCount() {
        return commentlist.size();
    }

    @Override
    public Object getItem(int position) {
        return commentlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private  class  ViewHolder{

        TextView comment;
        TextView user;
        RatingBar ratingBar;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        View row = view;
        GameReviewAdapter.ViewHolder holder =new GameReviewAdapter.ViewHolder();

        if(row == null){

            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(layout,null);


            holder.comment =(TextView)row.findViewById(R.id.comment);
            holder.user =(TextView)row.findViewById(R.id.added_user);
            holder.ratingBar=(RatingBar)row.findViewById(R.id.MyRating);
            row.setTag(holder);
        }
        else {


            holder=(GameReviewAdapter.ViewHolder)row.getTag();
        }
        GameReview gameReview= commentlist.get(position);

        holder.comment.setText(gameReview.getComments());
        holder.user.setText(gameReview.getAdded_user());
        holder.ratingBar.setRating(gameReview.getRate());

        return row;
    }
}
