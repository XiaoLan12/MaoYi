package com.yizhisha.maoyi.ui.type;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.yizhisha.maoyi.R;
import com.yizhisha.maoyi.adapter.TypeContentAdapter;
import com.yizhisha.maoyi.adapter.TypeTabAdapter;
import com.yizhisha.maoyi.base.BaseFragment;
import com.yizhisha.maoyi.bean.TypeContentBean;
import com.yizhisha.maoyi.bean.json.SortedBean;
import com.yizhisha.maoyi.bean.json.SortedListBean;
import com.yizhisha.maoyi.bean.json.StudioBean;
import com.yizhisha.maoyi.ui.type.contract.TypeContract;
import com.yizhisha.maoyi.ui.type.presenter.TypePresenter;
import com.yizhisha.maoyi.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 小蓝 on 2018/3/20.
 */

public class TypeFragment extends BaseFragment<TypePresenter> implements TypeContract.View{
    @Bind(R.id.tab_el)
    ExpandableListView mTabEl;
    @Bind(R.id.content_rl)
    ListView mContentRl;

    private TypeTabAdapter mAdapter;
    private List<String> group;
    private List<List<String>> child;

    List<TypeContentBean> contentListData;
    private TypeContentAdapter mTypeContentAdapter;

    public static int POSITION=0;
    private int CURRENTID=0;

    private int[] jacketImg=new int[]{R.drawable.zhenzhishan2,R.drawable.zhenzhikaishan,R.drawable.chenshan,
            R.drawable.txue,R.drawable.beixin,R.drawable.maoniwaitao,R.drawable.mianyi,R.drawable.yurongfu};

    private int[] dressImg=new int[]{R.drawable.lianyiqun,R.drawable.banshenqun,R.drawable.beixinqun};

    private int[] trousersImg=new int[]{R.drawable.dadiku,R.drawable.niuzaiku,R.drawable.xiuxianku};

    private List<SortedListBean.SortedsBean> sortedsBeanList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView() {
        group = new ArrayList<String>();
        child = new ArrayList<List<String>>();
        contentListData=new ArrayList<>();
        initTabData();

        mAdapter=new TypeTabAdapter(mContext,group,child);
        mTabEl.setAdapter(mAdapter);
        mTabEl.expandGroup(0);
        mPresenter.getSortedList();
        mPresenter.getStudio();
       mContentRl.setOnScrollListener(new AbsListView.OnScrollListener() {
           @Override
           public void onScrollStateChanged(AbsListView absListView, int i) {

           }
           @Override
           public void onScroll(AbsListView absListView, int firstVisibleItem,
                                int visibleItemCount, int totalItemCount) {
               if(firstVisibleItem!=CURRENTID){
                   CURRENTID=firstVisibleItem;
                   if(CURRENTID!=POSITION) {
                       POSITION = CURRENTID;
                   }
                   mAdapter.notifyDataSetChanged();
               }
           }
       });

        mTabEl.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(groupPosition==1){
                    POSITION=(child.get(0).size());
                }else{
                    POSITION=childPosition;
                }
                mAdapter.notifyDataSetChanged();
                mTypeContentAdapter.notifyDataSetChanged();
                mContentRl.setSelection(POSITION);
                mContentRl.smoothScrollToPositionFromTop(POSITION, 0, 100);
                return true;
            }
        });


    }
    private void initTabData() {
        group.add("类别");
        group.add("工作室");

        List<String> childlist1 = new ArrayList<String>();
        childlist1.add("上装");
        childlist1.add("裙装");
        childlist1.add("裤装");

        List<String> childlist2 = new ArrayList<String>();
        childlist2.add("工作室名");

        child.add(childlist1);
        child.add(childlist2);


    }

    @Override
    public void getStudioSuccess(List<StudioBean.StudioListBean> model) {
        ArrayList<StudioBean.StudioListBean> dataList=new ArrayList<>();
        dataList.addAll(model);
        TypeContentBean typeContentBean3=new TypeContentBean();
        typeContentBean3.setTitle("工作室名");
        typeContentBean3.setType(3);
        List<TypeContentBean.ContentBena> contentBenaList3=new ArrayList<>();

        for(int i=0;i<dataList.size();i++){
            TypeContentBean.ContentBena contentBena=new TypeContentBean().new ContentBena();
            contentBena.setTitle(dataList.get(i).getWorkshop());
            contentBena.setImgsrc(dataList.get(i).getCover());
            contentBena.setType(3);

            contentBena.setCid(dataList.get(i).getId());
            contentBenaList3.add(contentBena);
        }
        typeContentBean3.setContentBenaList(contentBenaList3);
        contentListData.add(typeContentBean3);
        mTypeContentAdapter=new TypeContentAdapter(mContext,contentListData);
        mContentRl.setAdapter(mTypeContentAdapter);
    }

    @Override
    public void getSortedListSuccess(List<SortedListBean.SortedsBean> model) {
        sortedsBeanList.addAll(model);
        int lenght=sortedsBeanList.size();
        for(int i=0;i<lenght;i++){
            if(sortedsBeanList.get(i).getName().equals("上装")){
                TypeContentBean typeContentBean=new TypeContentBean();
                typeContentBean.setTitle(sortedsBeanList.get(i).getName());
                typeContentBean.setId(sortedsBeanList.get(i).getId());
                typeContentBean.setType(0);

                List<TypeContentBean.ContentBena> contentBenaList=new ArrayList<>();
                List<SortedBean> sortedBeans=new ArrayList<>();
                sortedBeans.addAll(sortedsBeanList.get(i).getCat());
                for(int j=0;j<sortedBeans.size();j++){
                    TypeContentBean.ContentBena contentBena=new TypeContentBean().new ContentBena();
                    contentBena.setTitle(sortedBeans.get(j).getCat_name());
                    contentBena.setUrl(jacketImg[j]);
                    contentBena.setType(0);
                    contentBena.setCid(sortedBeans.get(j).getCat_id());
                    contentBenaList.add(contentBena);
                }
                typeContentBean.setContentBenaList(contentBenaList);
                contentListData.add(typeContentBean);
            }else if(sortedsBeanList.get(i).getName().equals("裙装")){
                TypeContentBean typeContentBean=new TypeContentBean();
                typeContentBean.setTitle(sortedsBeanList.get(i).getName());
                typeContentBean.setId(sortedsBeanList.get(i).getId());
                typeContentBean.setType(0);

                List<TypeContentBean.ContentBena> contentBenaList=new ArrayList<>();
                List<SortedBean> sortedBeans=new ArrayList<>();
                sortedBeans.addAll(sortedsBeanList.get(i).getCat());
                for(int j=0;j<sortedBeans.size();j++){
                    TypeContentBean.ContentBena contentBena=new TypeContentBean().new ContentBena();
                    contentBena.setTitle(sortedBeans.get(j).getCat_name());
                    contentBena.setUrl(dressImg[j]);
                    contentBena.setType(0);
                    contentBena.setCid(sortedBeans.get(j).getCat_id());
                    contentBenaList.add(contentBena);
                }
                typeContentBean.setContentBenaList(contentBenaList);
                contentListData.add(typeContentBean);
            }else if(sortedsBeanList.get(i).getName().equals("裤装")){
                TypeContentBean typeContentBean=new TypeContentBean();
                typeContentBean.setTitle(sortedsBeanList.get(i).getName());
                typeContentBean.setId(sortedsBeanList.get(i).getId());
                typeContentBean.setType(0);

                List<TypeContentBean.ContentBena> contentBenaList=new ArrayList<>();
                List<SortedBean> sortedBeans=new ArrayList<>();
                sortedBeans.addAll(sortedsBeanList.get(i).getCat());
                for(int j=0;j<sortedBeans.size();j++){
                    TypeContentBean.ContentBena contentBena=new TypeContentBean().new ContentBena();
                    contentBena.setTitle(sortedBeans.get(j).getCat_name());
                    contentBena.setUrl(trousersImg[j]);
                    contentBena.setType(0);
                    contentBena.setCid(sortedBeans.get(j).getCat_id());
                    contentBenaList.add(contentBena);
                }
                typeContentBean.setContentBenaList(contentBenaList);
                contentListData.add(typeContentBean);
            }

        }
    }

    @Override
    public void loadFail(String msg) {
        ToastUtil.showShortToast(msg);
    }
}
