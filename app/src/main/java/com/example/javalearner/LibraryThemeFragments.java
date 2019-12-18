package com.example.javalearner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.content.Intent;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class LibraryThemeFragments extends Fragment {
    public CardView card1, card2, card3, card4, card5, card6;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate( R.layout.lib_theme_choose, container, false );
        // card ini
        card1 = v.findViewById( R.id.Tab1 );
        card2 = v.findViewById( R.id.tab2 );
        card3 = v.findViewById( R.id.tab3 );
        card4 = v.findViewById( R.id.tab4 );
        card5 = v.findViewById( R.id.tab5 );
        card6 = v.findViewById( R.id.tab6 );
        txt1 = v.findViewById( R.id.text1 );
        txt2 = v.findViewById( R.id.text2 );
        txt3 = v.findViewById( R.id.text3 );
        txt4 = v.findViewById( R.id.text4 );
        txt5 = v.findViewById( R.id.text5 );
        txt6 = v.findViewById( R.id.text6 );

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 1 );
                view.getContext().startActivity(intent);}
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 2 );
                view.getContext().startActivity(intent);}
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 3 );
                view.getContext().startActivity(intent);}
        });


        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 4 );
                view.getContext().startActivity(intent);}
        });


        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 5 );
                view.getContext().startActivity(intent);}
        });

        card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LibraryThemeInfoActivity.class);
                intent.putExtra( "Lib_Theme" , 6 );
                view.getContext().startActivity(intent);}
        });

        // ini Animations

        Animation animeRightToleft = AnimationUtils.loadAnimation( getActivity(), R.anim.anime_right_to_left );
        Animation animeLeftToRight = AnimationUtils.loadAnimation( getActivity(), R.anim.anime_left_to_right );


        // setup Animation :
        card1.setAnimation( animeLeftToRight );
        card2.setAnimation( animeRightToleft );
        card3.setAnimation( animeLeftToRight );
        card4.setAnimation( animeRightToleft );
        card5.setAnimation( animeLeftToRight );
        card6.setAnimation( animeRightToleft );

        // Inflate the layout for this fragment
        return v;
    }
}