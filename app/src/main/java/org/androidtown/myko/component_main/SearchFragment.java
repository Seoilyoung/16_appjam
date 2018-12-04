package org.androidtown.myko.component_main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.androidtown.myko.R;
import org.androidtown.myko.component_server.ApplicationController;
import org.androidtown.myko.controller.ProfessorAdapter;
import org.androidtown.myko.model.Info_Professor;
import org.androidtown.myko.network.ServerInterface;

import java.util.InputMismatchException;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Administrator on 2015-06-30.
 */
public class SearchFragment extends Fragment {
        private ServerInterface api ;
        Button btn_search ;
        EditText edit_search ;
        public SearchFragment(){}
        private ListView list_name ;
        private ProfessorAdapter adapter;
        private InputMethodManager ipm ;
    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View Rootview = inflater.inflate(R.layout.fragment_search,container,false) ;
            ((MainActivity)getActivity()).ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ((MainActivity)getActivity()).c = 0 ;
            getActivity().invalidateOptionsMenu() ;
            ApplicationController application = ApplicationController.getInstance();
            application.buildServerInterface();
            api = application.getServerInterface();
            btn_search = (Button)Rootview.findViewById(R.id.btn_search);
            edit_search = (EditText)Rootview.findViewById(R.id.edit_search);
            btn_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ipm= (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    ipm.hideSoftInputFromWindow(edit_search.getWindowToken(), 0);
                    final String name = edit_search.getText().toString();
                    api.search_professor(name, new Callback<List<Info_Professor>>() {
                        @Override
                        public void success(List<Info_Professor> info_professors, Response response) {
                            adapter.setSource(info_professors);
                            edit_search.setText("");
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getActivity().getApplicationContext(), "fail to name", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new ShowFragment()).addToBackStack(null).commit();  //임시
                        }
                    });
                }
            });
            list_name = (ListView)Rootview.findViewById(R.id.list_name);
            adapter = new ProfessorAdapter() ;
            list_name.setAdapter(adapter);
            list_name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Info_Professor info_professor = (Info_Professor) adapter.getItem(i);
                    ((MainActivity)getActivity()).save_info = info_professor ;
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_Main, new ShowFragment()).addToBackStack(null).commit();
                }
            });
        return Rootview ;
    }

        @Override
        public void onAttach(Activity activity) { super.onAttach(activity); }

        @Override
        public void onDetach() { super.onDetach(); }

}
