package org.androidtown.myko.controller;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import org.androidtown.myko.R;
import org.androidtown.myko.model.Info_Professor;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-07-09.
 */
public class ProfessorAdapter extends BaseAdapter {

    private List<Info_Professor> professors = new ArrayList<>();
    public void setSource(List<Info_Professor> professors) {
        this.professors = professors;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() { return professors != null ? professors.size() : 0; }
    @Override
    public Object getItem(int position) {
        return (professors != null && (0 <= position && position < professors.size()))
                ? professors.get(position) : null;
    }
    @Override
    public long getItemId(int position) { return 0; }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = View.inflate(parent.getContext(), R.layout.item_professor, null);
        Info_Professor info_professor = professors.get(position);
        TextView txt_professor = (TextView)view.findViewById(R.id.txt_professor);
        txt_professor.setText(info_professor.professorname);
        return view;
    }
}
