package com.yizhisha.maoyi.ui.home.jc;


import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yizhisha.maoyi.R;

import java.util.HashMap;

/**
 * Created by Nathen on 2017/6/9.
 */
public class DemoFragment extends Fragment {

    ListView listView;
    int index;

    public DemoFragment setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInastanceState) {
        listView = (ListView) inflater.inflate(R.layout.layout_list, container, false);
        listView.setAdapter(new VideoListAdapter(getActivity(), index));
        return listView;
    }

}
