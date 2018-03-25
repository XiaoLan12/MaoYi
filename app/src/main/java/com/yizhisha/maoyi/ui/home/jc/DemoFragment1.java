package com.yizhisha.maoyi.ui.home.jc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.gangedrecyclerview.BaseFragment;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class DemoFragment1 extends Fragment {
    int index;

    public DemoFragment1 setIndex(int index) {
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
        View view=inflater.inflate(R.layout.item_videoview1,container,false);
        JCVideoPlayerStandard jcVideoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.videoplayer);
        jcVideoPlayer.setUp("http://pic.ibaotu.com/00/56/32/78G888piCyDu.mp4", JCVideoPlayer.SCREEN_LAYOUT_LIST,
                "");
//        listView = (ListView) inflater.inflate(R.layout.layout_list, container, false);
//        listView.setAdapter(new VideoListAdapter(getActivity(), index));
        return view;
    }
}
