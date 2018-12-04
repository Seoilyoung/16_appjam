package org.androidtown.myko.controller;

import android.content.Context;
import android.media.Image;
import android.media.Rating;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_main.TotalFragment;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.model.Info_Review;
import org.androidtown.myko.network.ServerInterface;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-07-08.
 */
public class ReviewAdapter extends BaseAdapter {
    private ServerInterface api ;
    private List<Info_Review> reviews = new ArrayList<>();
    long temp_id ;
    int review_count ;
    public void append(Info_Review review) {
        this.reviews.add(0, review);
        this.notifyDataSetChanged();
    }
    public void setSource(List<Info_Review> reviews) {
        this.reviews = reviews;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (reviews != null)
            review_count = reviews.size();
        else
            review_count = 0;
        return review_count ;
    }
    @Override
    public Object getItem(int position) {
        return (reviews != null && (0 <= position && position < reviews.size()))
                ? reviews.get(position) : null;
    }
    @Override
    public long getItemId(int position) { return 0; }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ApplicationController application = ApplicationController.getInstance();
        application.buildServerInterface();
        api = application.getServerInterface();

        view = View.inflate(parent.getContext(), R.layout.item_review, null);
        Info_Review info_review = reviews.get(position);

        ImageButton btn_good = (ImageButton)view.findViewById(R.id.btn_review_good);
        TextView txt_good = (TextView)view.findViewById(R.id.txt_review_good) ;
        ImageButton btn_bad = (ImageButton)view.findViewById(R.id.btn_review_bad);
        TextView txt_bad = (TextView)view.findViewById(R.id.txt_review_bad) ;
        RatingBar rating_1 = (RatingBar)view.findViewById(R.id.rate_review_1) ;
        RatingBar rating_2 = (RatingBar)view.findViewById(R.id.rate_review_2) ;
        RatingBar rating_3 = (RatingBar)view.findViewById(R.id.rate_review_3) ;
        RatingBar rating_4 = (RatingBar)view.findViewById(R.id.rate_review_4) ;
        RatingBar rating_5 = (RatingBar)view.findViewById(R.id.rate_review_5) ;
        TextView txt_content = (TextView)view.findViewById(R.id.txt_review_content);
        TextView txt_delete = (TextView)view.findViewById(R.id.txt_review_delete) ;

        temp_id = info_review.id;
        btn_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info_Review info_review1 = new Info_Review();
                info_review1.id = temp_id;
                api.good(info_review1, new Callback<List<Info_Review>>() {
                    @Override
                    public void success(List<Info_Review> info_reviews, Response response) {
                    }
                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
            }
        });
        String str_good = String.format("%.0f",info_review.good);
        txt_good.setText(str_good);
        btn_bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info_Review info_review2 = new Info_Review();
                info_review2.id = temp_id;
                api.bad(info_review2, new Callback<List<Info_Review>>() {
                    @Override
                    public void success(List<Info_Review> info_reviews, Response response) {
                    }

                    @Override
                    public void failure(RetrofitError error) {
                    }
                });
            }
        });
        String str_bad = String.format("%.0f",info_review.bad);
        txt_bad.setText(str_bad);
        rating_1.setRating((float) info_review.depth);
        rating_2.setRating((float) info_review.hardness);
        rating_3.setRating((float) info_review.ability);
        rating_4.setRating((float) info_review.reasonability);
        rating_5.setRating((float) info_review.communication);
        txt_content.setText(info_review.review);
        txt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info_Review info_review3 = new Info_Review();
                info_review3.id = temp_id;
                api.delete(info_review3, new Callback<List<Info_Review>>() {
                    @Override
                    public void success(List<Info_Review> info_reviews, Response response) {
                    }
                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }
        });

        return view;
    }
}