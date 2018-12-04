package org.androidtown.myko.component_side;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidtown.myko.R;

/**
 * Created by Administrator on 2015-06-30.
 */
public class AboutFragment extends Fragment {

        public AboutFragment(){}

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about,container,false);
    }

        @Override
        public void onAttach(Activity activity) { super.onAttach(activity); }

        @Override
        public void onDetach() { super.onDetach(); }
}
