package com.yizhisha.maoyi.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.bean.json.MyCommentListBean;
import com.yizhisha.maoyi.common.dialog.PicShowDialog;
import com.yizhisha.maoyi.ui.home.activity.ProductDetailActivity;
import com.yizhisha.maoyi.utils.DateUtil;
import com.yizhisha.maoyi.utils.GlideUtil;
import com.yizhisha.maoyi.widget.MultiImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lan on 2017/7/31.
 */

public class CommentYarnAdapter extends BaseMultiItemQuickAdapter<MyCommentListBean,BaseViewHolder> {
    private final String AVATARURL="http://www.taoshamall.com/data/attached/avatar/";

    public CommentYarnAdapter(List<MyCommentListBean> data) {
        super(data);
        addItemType(MyCommentListBean.TEXT_TYPE, R.layout.item_text_commnet);
        addItemType(MyCommentListBean.IMGS_TYPE, R.layout.item_img_commnet);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyCommentListBean item) {
        switch (helper.getItemViewType()){
            case MyCommentListBean.TEXT_TYPE:
                ImageView imageView=helper.getView(R.id.head_comment_iv);
                MyCommentListBean.MyComment comment=item.getComment();
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,AVATARURL+comment.getAvatar(),imageView,
                        R.drawable.icon_head_normal,R.drawable.icon_head_normal);
                if(comment.getMobile()!=null&&!comment.getMobile().equals("")){
                    helper.setText(R.id.name_comment_tv,comment.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }
                helper.setText(R.id.time_comment_tv, DateUtil.getDateToString1(comment.getComment_addtime()*1000));
                helper.setText(R.id.detail_comment_tv,comment.getComment_detail());
                if(comment.getComment_detail_add()!=null&&!comment.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_ll,true);
                    helper.setVisible(R.id.time_addcomment_ll,true);
                    helper.setText(R.id.detail_addcomment_tv,comment.getComment_detail_add());
                    helper.setText(R.id.time_addcomment_tv,DateUtil.getDateToString1(comment.getComment_addtime_add()*1000));
                }else{
                    helper.setVisible(R.id.addcomment_ll,false);
                    helper.setVisible(R.id.time_addcomment_ll,false);

                }
                if(comment.getComment_redetail()!=null&&!comment.getComment_redetail().equals("")){
                    helper.setVisible(R.id.business_reply_ll,true);
                    helper.setText(R.id.business_reply_content_tv,comment.getComment_redetail());
                    helper.setText(R.id.business_reply_time_tv, DateUtil.getDateToString1(comment.getComment_retime()*1000));
                }else{
                    helper.setVisible(R.id.business_reply_ll,false);
                }
                break;
            case MyCommentListBean.IMGS_TYPE:
                ImageView imageView1=helper.getView(R.id.head_comment_img_iv);
                MyCommentListBean.MyComment comment1=item.getComment();
                GlideUtil.getInstance().LoadContextCircleBitmap(mContext,AVATARURL+comment1.getAvatar(),imageView1,R.drawable.icon_head_normal,R.drawable.icon_head_normal);
                if(comment1.getMobile()!=null&&!comment1.getMobile().equals("")){
                    helper.setText(R.id.name_comment_img_tv,comment1.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                }
                helper.setText(R.id.time_comment_img_tv,DateUtil.getDateToString1(comment1.getComment_addtime()*1000));
                helper.setText(R.id.detail_comment_img_tv,comment1.getComment_detail());
                if(comment1.getComment_detail()==null||comment1.getComment_detail().length()==0){
                    helper.setVisible(R.id.detail_comment_img_tv,false);
                }else{
                    helper.setVisible(R.id.detail_comment_img_tv,true);
                    helper.setText(R.id.detail_comment_img_tv,comment1.getComment_detail());
                }
                if(comment1.getComment_detail_add()!=null&&!comment1.getComment_detail_add().equals("")){
                    helper.setVisible(R.id.addcomment_img_ll,true);
                    helper.setVisible(R.id.time_addcomment_img_ll,true);
                    helper.setText(R.id.detail_addcomment_img_tv,comment1.getComment_detail_add());
                    helper.setText(R.id.time_addcomment_img_tv,DateUtil.getDateToString1(comment1.getComment_addtime_add()*1000));
                }else{
                    helper.setVisible(R.id.addcomment_img_ll,false);
                    helper.setVisible(R.id.time_addcomment_img_ll,false);
                }
                if(comment1.getComment_redetail()!=null&&!comment1.getComment_redetail().equals("")){
                    helper.setVisible(R.id.business_reply_img_ll,true);
                    helper.setText(R.id.business_reply_content_img_tv,comment1.getComment_redetail());
                    helper.setText(R.id.business_reply_time_img_tv, DateUtil.getDateToString1(comment1.getComment_retime()*1000));
                }else{
                    helper.setVisible(R.id.business_reply_img_ll,false);
                }
                MultiImageView multiImageView = helper.getView(R.id.cilrcleimgMv_img);
                final List<String> photos =new ArrayList<>(9);
                if (comment1.getCommentPhotos() != null && comment1.getCommentPhotos().size() > 0) {
                   for(int i=0;i<comment1.getCommentPhotos().size();i++){
                       if(i<=8){
                           photos.add(comment1.getCommentPhotos().get(i));
                       }
                   }
                    multiImageView.setList(photos);
                    multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                            PicShowDialog dialog = new PicShowDialog(mContext, photos, position);
                            dialog.show();
                        }
                    });
                }
                break;
        }
    }
}
