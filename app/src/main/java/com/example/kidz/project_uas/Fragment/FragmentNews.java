package com.example.kidz.project_uas.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kidz.project_uas.R;

public class FragmentNews extends Fragment implements View.OnClickListener {
    View view;
    ImageView imgNews1, imgNews2, imgNews3;

    public FragmentNews(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);

        imgNews1 = (ImageView) view.findViewById(R.id.imgNews1);
        imgNews1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://pb.garena.co.id/evo/berita/event/transfer-akun-kamu-mulai-1-desember"));
                startActivity(intent);
            }
        });

        imgNews2 = (ImageView) view.findViewById(R.id.imgNews2);
        imgNews2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.sea.playblackdesert.com/Intro/Event/Archer_Update"));
                startActivity(intent);
            }
        });

        imgNews3 = (ImageView) view.findViewById(R.id.imgNews3);
        imgNews3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://vikendi.pubg.com/"));
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
    }
}
