package id.putraprima.mygoldtracker.screen.portolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import id.putraprima.mygoldtracker.R;

public class PorfolioFragment extends Fragment {
    public static final String ARG_PAGE ="ARG_PAGE";
    private int mPage;

    public static PorfolioFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PorfolioFragment fragment = new PorfolioFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_porfolio,container,false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #"+mPage);
        return view;
    }
}