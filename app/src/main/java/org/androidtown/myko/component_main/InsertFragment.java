package org.androidtown.myko.component_main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.controller.ReviewAdapter;
import org.androidtown.myko.model.Info_Review;
import org.androidtown.myko.network.ServerInterface;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-07-08.
 */
public class InsertFragment extends Fragment {
    private ServerInterface api ;
    public InsertFragment(){}
    Button btn_insert ;
    EditText edit_content ;
    TextView txt_name ;
    private ReviewAdapter adapter;
    double depth=0, hardness=0, ability=0, reasonability=0,communication=0 ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_insert, container, false);
        ApplicationController application = ApplicationController.getInstance();
        application.buildServerInterface();
        api = application.getServerInterface();
        edit_content = (EditText)rootView.findViewById(R.id.edit_content);
        btn_insert = (Button) rootView.findViewById(R.id.btn_insert);
        txt_name = (TextView)rootView.findViewById(R.id.txt_name);
        txt_name.setText(((MainActivity)getActivity()).save_info.professorname);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this.getActivity(), R.array.score,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerListener1 spinnerListener1 = new SpinnerListener1();
        SpinnerListener2 spinnerListener2 = new SpinnerListener2();
        SpinnerListener3 spinnerListener3 = new SpinnerListener3();
        SpinnerListener4 spinnerListener4 = new SpinnerListener4();
        SpinnerListener5 spinnerListener5 = new SpinnerListener5();

            Spinner s_Sp3 = (Spinner) rootView.findViewById(R.id.spinner_insert3);
            s_Sp3.setAdapter(adapter);
            s_Sp3.setOnItemSelectedListener(spinnerListener1);

        Spinner s_Sp4 = (Spinner) rootView.findViewById(R.id.spinner_insert4);
        s_Sp4.setAdapter(adapter);
        s_Sp4.setOnItemSelectedListener(spinnerListener2);

        Spinner s_Sp5 = (Spinner) rootView.findViewById(R.id.spinner_insert5);
        s_Sp5.setAdapter(adapter);
        s_Sp5.setOnItemSelectedListener(spinnerListener3);

        Spinner s_Sp6 = (Spinner) rootView.findViewById(R.id.spinner_insert6);
        s_Sp6.setAdapter(adapter);
        s_Sp6.setOnItemSelectedListener(spinnerListener4);

        Spinner s_Sp7 = (Spinner) rootView.findViewById(R.id.spinner_insert7);
        s_Sp7.setAdapter(adapter);
        s_Sp7.setOnItemSelectedListener(spinnerListener5);



        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Info_Review info_review = new Info_Review() ;
                info_review.review = edit_content.getText().toString();
                info_review.depth = depth ;
                info_review.hardness = hardness ;
                info_review.ability = ability ;
                info_review.reasonability = reasonability ;
                info_review.communication = communication ;
                info_review.professorcode = ((MainActivity)getActivity()).save_info.professorcode ;
                api.newreview(info_review, new Callback<Info_Review>() {
                    @Override
                    public void success(Info_Review info_review, Response response) {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new ShowFragment()).addToBackStack(null).commit();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to send",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) { super.onAttach(activity); }

    @Override
    public void onDetach() { super.onDetach(); }

    public class SpinnerListener1 implements AdapterView.OnItemSelectedListener {
        public SpinnerListener1() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
                depth = (float)(position+1)/2 ;
        }public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    public class SpinnerListener2 implements AdapterView.OnItemSelectedListener {
        public SpinnerListener2() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
                hardness = (float)(position+1)/2 ;
        }public void onNothingSelected(AdapterView<?> arg0) {
            hardness = 0.5 ;
        }
    }
    public class SpinnerListener3 implements AdapterView.OnItemSelectedListener {
        public SpinnerListener3() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
                ability = (float)(position+1)/2 ;
        }public void onNothingSelected(AdapterView<?> arg0) {
            ability = 0.5 ;
        }
    }
    public class SpinnerListener4 implements AdapterView.OnItemSelectedListener {
        public SpinnerListener4() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
                reasonability = (float)(position+1)/2 ;
        }public void onNothingSelected(AdapterView<?> arg0) {
            reasonability = 0.5 ;
        }
    }
    public class SpinnerListener5 implements AdapterView.OnItemSelectedListener {
        public SpinnerListener5() {}
        public void onItemSelected(AdapterView<?> arg0, View arg1,final int position, long arg2) {
                communication = (float)(position+1)/2 ;
        }public void onNothingSelected(AdapterView<?> arg0) {
            communication = 0.5 ;
        }
    }
}