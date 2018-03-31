package com.yizhisha.maoyi.ui.home.jc;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.utils.GlideUtil;


import java.util.HashMap;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class DemoFragment1 extends Fragment {
    int index;//0是视屏,1是图片
    String path;

    public DemoFragment1 setIndex(String path,int index) {
        this.index = index;
        this.path=path;
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
        ImageView img=(ImageView)view.findViewById(R.id.img);
        if(index==1){
            jcVideoPlayer.setVisibility(View.GONE);
            Glide.with(getActivity()).load(path).into(img);
        }else{
            img.setVisibility(View.GONE);
            jcVideoPlayer.setUp(path, JCVideoPlayer.SCREEN_LAYOUT_LIST,  "");
            try {

                jcVideoPlayer.thumbImageView.setImageBitmap( DemoFragment1.retriveVideoFrameFromVideo(path));
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }


//        listView = (ListView) inflater.inflate(R.layout.layout_list, container, false);
//        listView.setAdapter(new VideoListAdapter(getActivity(), index));
        return view;
    }
    public static Bitmap retriveVideoFrameFromVideo(String videoPath)
            throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable(
                    "Exception in retriveVideoFrameFromVideo(String videoPath)"
                            + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }
}
