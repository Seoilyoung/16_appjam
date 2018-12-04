package org.androidtown.myko.component_main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.controller.ReviewAdapter;
import org.androidtown.myko.model.Info_Review;
import org.androidtown.myko.network.ServerInterface;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-07-08.
 */
public class TotalFragment extends Fragment {
    private ServerInterface api ;
    TextView txt_total_count ;
    private ListView list_total ;
    private ReviewAdapter adapter ;
    String temp_id ;
    int list_value = 0 ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_total, container, false);
        ((MainActivity)getActivity()).c = 5 ;
        getActivity().invalidateOptionsMenu() ;
        txt_total_count = (TextView)view.findViewById(R.id.txt_total_count);
        ApplicationController application = ApplicationController.getInstance();
        application.buildServerInterface();
        api = application.getServerInterface();
        ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(
                this.getActivity(), R.array.arrange,android.R.layout.simple_spinner_item);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerListener spinnerListener = new SpinnerListener();
        Spinner spin_total = (Spinner)view.findViewById(R.id.spin_total);
        spin_total.setAdapter(arrayadapter);
        spin_total.setOnItemSelectedListener(spinnerListener);

        list_total = (ListView)view.findViewById(R.id.list_total) ;
        adapter = new ReviewAdapter() ;
        list_total.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public class SpinnerListener implements AdapterView.OnItemSelectedListener {
        public SpinnerListener() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
            list_value = position ;
            if(position==1) {
                api.getreview_totalpoint(((MainActivity)getActivity()).save_info.professorcode, new Callback<List<Info_Review>>() {
                    @Override
                    public void success(List<Info_Review> info_reviews, Response response) {
                        adapter.setSource(info_reviews);

                        String str_total_count = String.format("%d", info_reviews.size());
                        txt_total_count.setText(str_total_count);
                    }

                    @Override
                    public void failure(RetrofitError retrofitError) {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to load riview lists",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else {
                Info_Review info_review = new Info_Review();
                info_review.professorcode = temp_id ;
                api.getreview_time(info_review, new Callback<List<Info_Review>>() {
                    @Override
                    public void success(List<Info_Review> info_reviews, Response response) {
                        adapter.setSource(info_reviews);
                        String str_total_count = String.format("%d", info_reviews.size());
                        txt_total_count.setText(str_total_count);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to load riview lists",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }

        }public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        temp_id = ((MainActivity)getActivity()).save_info.professorcode ;
        Info_Review info_review = new Info_Review();
        info_review.professorcode = temp_id ;
        api.getreview_time(info_review, new Callback<List<Info_Review>>() {
            @Override
            public void success(List<Info_Review> info_reviews, Response response) {
                adapter.setSource(info_reviews);
                String str_total_count = String.format("%d", info_reviews.size());
                txt_total_count.setText(str_total_count);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to load riview lists",
                        Toast.LENGTH_SHORT).show();

            }
        });
/*        api.getreview_time(, new Callback<List<Info_Review>>() {
            @Override
            public void success(List<Info_Review> info_reviews, Response response) {
                adapter.setSource(info_reviews);

                String str_total_count = String.format("%d", info_reviews.size());
                txt_total_count.setText(str_total_count);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getActivity().getApplicationContext(), "Failed to load riview lists",
                        Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}