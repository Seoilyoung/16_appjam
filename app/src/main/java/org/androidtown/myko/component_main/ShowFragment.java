package org.androidtown.myko.component_main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.model.Info_Review;
import org.androidtown.myko.model.Info_User;
import org.androidtown.myko.model.test;
import org.androidtown.myko.network.ServerInterface;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-07-08.
 */
public class ShowFragment extends Fragment {

    private ServerInterface api;
    TextView txt_name;
    TextView txt_show_schoolname ;
    TextView txt_totalshow;
    TextView txt_total ;
    ImageView img_show_mark ;
    RatingBar rating_1;
    RatingBar rating_2;
    RatingBar rating_3;
    RatingBar rating_4;
    RatingBar rating_5;
    RatingBar rating_6;

    public ShowFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Rootview = inflater.inflate(R.layout.fragment_show, container, false);
        ApplicationController application = ApplicationController.getInstance();
        application.buildServerInterface();
        ((MainActivity) getActivity()).ab.setHomeAsUpIndicator(R.drawable.ic_back);
        ((MainActivity) getActivity()).c = 3;
        getActivity().invalidateOptionsMenu();
        api = application.getServerInterface();
        txt_name = (TextView) Rootview.findViewById(R.id.txt_name);
        txt_show_schoolname = (TextView)Rootview.findViewById(R.id.txt_show_schoolname);
        txt_name.setText(((MainActivity) getActivity()).save_info.professorname);
        txt_total = (TextView)Rootview.findViewById(R.id.txt_total);
        img_show_mark = (ImageView)Rootview.findViewById(R.id.img_show_mark);
        rating_1 = (RatingBar) Rootview.findViewById(R.id.ratingBar1);
        rating_2 = (RatingBar) Rootview.findViewById(R.id.ratingBar2);
        rating_3 = (RatingBar) Rootview.findViewById(R.id.ratingBar3);
        rating_4 = (RatingBar) Rootview.findViewById(R.id.ratingBar4);
        rating_5 = (RatingBar) Rootview.findViewById(R.id.ratingBar5);
        rating_6 = (RatingBar) Rootview.findViewById(R.id.ratingBar6);

        rating_1.setRating((float) 2.5);
        rating_2.setRating((float) 3);
        rating_3.setRating((float) 3);
        rating_4.setRating((float) 4);
        rating_5.setRating((float) 2);
        rating_6.setRating((float) 3.5);
        txt_total.setText("3.5");
        txt_totalshow = (TextView) Rootview.findViewById(R.id.txt_totalshow);
        switch(((MainActivity) getActivity()).save_info.professorschoolcode) {
            case "1" :
                img_show_mark.setImageResource(R.drawable.logo_seoulwomen);
                txt_show_schoolname.setText("서울여대");
                break;
            case "2" :
                img_show_mark.setImageResource(R.drawable.logo_soongsil);
                txt_show_schoolname.setText("숭실대");
                break;
            case "3" :
                img_show_mark.setImageResource(R.drawable.logo_sogang);
                txt_show_schoolname.setText("서강대");
                break;
            case "4" :
                img_show_mark.setImageResource(R.drawable.logo_kookmin);
                txt_show_schoolname.setText("국민대");
                break;
            case "5" :
                img_show_mark.setImageResource(R.drawable.logo_hongik);
                txt_show_schoolname.setText("홍익대");
                break ;
            case "6" :
                img_show_mark.setImageResource(R.drawable.logo_kwangwoon);
                txt_show_schoolname.setText("강원대");
                break;
            case "7" :
                img_show_mark.setImageResource(R.drawable.logo_sejong);
                txt_show_schoolname.setText("세종대");
                break;
            case "8" :
                img_show_mark.setImageResource(R.drawable.logo_yonsei);
                txt_show_schoolname.setText("연세대");
                break;
            case "9" :
                img_show_mark.setImageResource(R.drawable.logo_korea);
                txt_show_schoolname.setText("고려대");
                break;
            default:
                break ;


        }

                txt_totalshow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new TotalFragment()).addToBackStack(null).commit();
                    }
                });

        return Rootview;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
