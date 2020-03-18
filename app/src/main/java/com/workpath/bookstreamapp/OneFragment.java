package com.workpath.bookstreamapp;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hymanme.tagflowlayout.OnTagClickListener;
import com.github.hymanme.tagflowlayout.TagAdapter;
import com.github.hymanme.tagflowlayout.TagFlowLayout;
import com.github.hymanme.tagflowlayout.bean.TagBean;
import com.github.hymanme.tagflowlayout.tags.ColorfulStrokeTagView;
import com.workpath.bookstreamapp.activities.SearchActivity;
import com.workpath.bookstreamapp.adapters.HomeGoodsAdapter;
import com.workpath.bookstreamapp.moudles.goods.HomeGoodsMoudle;
import com.workpath.bookstreamapp.myapp.MyAdapter;
import com.workpath.bookstreamapp.myapp.MyFragment;
import com.workpath.bookstreamapp.utils.HiddenAnimUtils;
import com.workpath.bookstreamapp.utils.ScreenUtils;
import com.workpath.bookstreamapp.utils.SpacesItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by JayChen on 2020/2/3.
 */


public class OneFragment extends MyFragment{

    private Context context;
    /**搜索栏**/
    private LinearLayout search;

    /**横向滑动条**/
    LinearLayout hide_view;
    List<String> titleList; //内容
    private HorizontalScrollView mHorizontalScrollView;//水平分类条
    private LinearLayout mLinearLayout;//水平分类条包含的布局

    /**分类显示**/
    private int height;
    private TagFlowLayout mTagFlowLayout;
    private List<TagBean> tagBeans;//侧滑栏数据集合
    //自定义Adapter：MyTagAdapter，其中TagBean为泛型类，即每一个tag的实体类
    //在getView()里面自定义tag标签的样式
    //默认提供了两个实例tag：DefaultTagView，ColorfulTagView
    //DefaultTagView：默认tag
    //ColorfulTagView：彩色的tag
    //当然还可以自己自定义
    class MyTagAdapter extends TagAdapter<TagBean> {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //定制tag的样式，包括背景颜色，点击时背景颜色，背景形状等
            //DefaultTagView (默认实心tag)
            //ColorfulTagView (彩色背景实心tag)
            //StrokeTagView	（空心带边框的tag）
            //ColorfulStrokeTagView （空心彩色边框tag）
            //自定义tag，继承以上tag
            ColorfulStrokeTagView textView = new ColorfulStrokeTagView(getContext());
            textView.setText(((TagBean) getItem(position)).getName());
            return textView;
        }
    }


    /**货物显示**/
    private RecyclerView mRecyclerView;
    List<HomeGoodsMoudle> dataList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.one_fragment_layout;
    }


    @Override
    protected void initArgs(Bundle arguments) {
        super.initArgs(arguments);
        context = getContext();

        /**横向滑动条的内容**/
        titleList = new ArrayList<>();
        titleList.add("推荐"); titleList.add("类1");
        titleList.add("类2");titleList.add("类3");
        titleList.add("类4");titleList.add("更多分类");

        /**更多分类的内容**/
        tagBeans=new ArrayList<>();
        tagBeans.add(new TagBean(0,"哲学"));tagBeans.add(new TagBean(1,"经济学"));
        tagBeans.add(new TagBean(2,"法学"));tagBeans.add(new TagBean(3,"教育学"));
        tagBeans.add(new TagBean(4,"文学"));tagBeans.add(new TagBean(5,"历史学"));
        tagBeans.add(new TagBean(6,"农学"));tagBeans.add(new TagBean(7,"医学"));
        tagBeans.add(new TagBean(8,"军事学"));tagBeans.add(new TagBean(9,"管理学"));
        tagBeans.add(new TagBean(10,"艺术学"));tagBeans.add(new TagBean(11,"理学"));
        tagBeans.add(new TagBean(12,"考研数学"));tagBeans.add(new TagBean(13,"考研英语"));
        tagBeans.add(new TagBean(14,"考研政治"));tagBeans.add(new TagBean(15,"计算机"));
        tagBeans.add(new TagBean(16,"化学"));tagBeans.add(new TagBean(17,"教师教育学"));
        tagBeans.add(new TagBean(18,"英语口译"));tagBeans.add(new TagBean(19,"机械自动化"));
        tagBeans.add(new TagBean(20,"法语"));tagBeans.add(new TagBean(21,"生物"));

        /**商品显示内容**/
        dataList = new ArrayList<>();
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book1));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book2));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book3));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book4));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book5));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book6));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book1));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book2));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book3));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book4));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book5));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book6));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book1));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book2));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book3));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book4));
        dataList.add(new HomeGoodsMoudle(context,R.mipmap.book5));dataList.add(new HomeGoodsMoudle(context,R.mipmap.book6));


    }

    protected void initWidget(View view) throws IOException {
        /**
         * 设置状态栏为透明色
         */
        transparencyBar();

        /**搜索栏**/
        search = (LinearLayout) view.findViewById(R.id.toolbar_layout).findViewById(R.id.search);

        /**横滑条**/
        mHorizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.horizontal_view);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.layout_horizontal_view);

        /**更多分类**/
        mTagFlowLayout = (TagFlowLayout) view.findViewById(R.id.tag_flow_layout);
        hide_view = (LinearLayout) view.findViewById(R.id.hidden_comprhsv);


        /**商品展示**/
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_home);


    }

    protected void initData() throws IOException {

        /**搜索**/
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        /**渲染横滑条**/
        addView(mLinearLayout,titleList);

        /**渲染recycleview**/
        //渲染RecycleView
        StaggeredGridLayoutManager mStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //设置间距
        mRecyclerView.setPadding(1,1,1,1);
        SpacesItemDecoration decoration=new SpacesItemDecoration(1);
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        //适配器
        HomeGoodsAdapter goodsAdapter = new HomeGoodsAdapter(context, dataList, new MyAdapter.AdapterListener() {
            @Override
            public void onItemClick(MyAdapter.MyViewHolder holder, Object data) {
                if(!(hide_view.getVisibility()==View.GONE)){
                    showHidden();
                }
            }

            @Override
            public void onItemLongClick(MyAdapter.MyViewHolder holder, Object data) {

            }

            @Override
            public Boolean setAddActionContinue() {
                return false;
            }

            @Override
            public void updataUI(Object object) {

            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setAdapter(goodsAdapter);

        /**更多分类**/
        mTagFlowLayout.setTitleTextSize(12);
        //设置监听(单击和长按事件)
        mTagFlowLayout.setTagListener(new OnTagClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), "click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "long click==" + ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

        });
        MyTagAdapter tagAdapter=new MyTagAdapter();
        mTagFlowLayout.setTagAdapter(tagAdapter);
        tagAdapter.addAllTags(tagBeans);
        hide_view.setVisibility(View.GONE);


    }

    @Override
    public void RefreshData() {


    }


    /**
     * 直播分类横向滚动条
     * @param mLinearLayout 包含选项的父布局
     * @param mTitle 项目的显示数据
     */
    public void addView(LinearLayout mLinearLayout, List<String> mTitle){

        LinearLayout.LayoutParams params,params1,params2;
        final List<TextView> viewList=new ArrayList<>();
        params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params2=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.leftMargin=40; params1.leftMargin=10;
        params2.leftMargin=40; params2.rightMargin=10;
        final int count=mTitle.size();
        for(int i=0;i<count;i++){
            TextView tv=new TextView(getContext());
            tv.setTextSize(18);
            Resources resources=getContext().getResources();
            final ColorStateList color0=resources.getColorStateList(R.color.black_alpha_128,null);
            final ColorStateList color1=resources.getColorStateList(R.color.black,null);
            /**
             * 默认第一项为选中状态
             */
            if(i==0){tv.setTextColor(color1);
            }else{tv.setTextColor(color0);}
            tv.setGravity(Gravity.CENTER_VERTICAL);
            viewList.add(tv);
            tv.setText(mTitle.get(i));
            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView temp= (TextView) v;
                    for(int j=0;j<count;j++){
                        if(j==finalI){
                            //TODO 设置分类条项目选中后的渲染效果
                            viewList.get(j).setTextColor(color1);
                        }else{
                            //TODO 设置分类条项目未选中的渲染效果
                            viewList.get(j).setTextColor(color0);
                        }
                    }
                    onClickSet(v, finalI);
                }
            });
            if(i==0){
                mLinearLayout.addView(tv,params1);
            }else if(i==count-1){
                mLinearLayout.addView(tv,params2);
            }else{
                mLinearLayout.addView(tv,params);
            }

        }

    }

    /**
     * 分类条子项点击事件
     * @param v 选中的view对象
     * @param positon 该项目在集合中的位置
     */
    void onClickSet(View v,int positon){
        //TODO 分类条点击事件
        //Toast.makeText(getContext(),"点击了"+titleList.get(positon),Toast.LENGTH_SHORT).show();
        switch (positon){
            case 0:
                break;
            case 5:
                showHidden();
                break;
            default:
                if(!(hide_view.getVisibility()==View.GONE)){
                    showHidden();
                }
                break;
        }
    }

    /**
     * 布局隐藏或者出现
     */
    public void showHidden(){
        height = (int) ((2.0/5)*ScreenUtils.getScreenHeight(context));
        HiddenAnimUtils.newInstance(getContext(),hide_view,height,30).toggle();
    }



}
