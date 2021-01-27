package com.example.talk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talk.Fragment.ChatFragment;
import com.example.talk.Fragment.ContactsFragment;
import com.example.talk.Fragment.MeFragment;
import com.example.talk.Fragment.MyAdapter;
import com.example.talk.utils.StatusTextColor;
import com.jaeger.library.StatusBarUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/*ButterKnife黄油刀框架*/
/*Usage in Activity*/
/*ButterKnife.bind(this)必须在初始化绑定布局文件(setContentView())之后,否则会报错*/
/*Usage in Fragment*/
/*和Activity相比在fragment destory时要将实例化的ButterKnife对象解绑，binder.unbind()*/
public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments=new ArrayList<Fragment>();
    Drawable ic_contact_clo,ic_contact_un,ic_message_clo,ic_message_un,ic_me_un,ic_me_clo;
    @BindView(R.id.tB_main)
    Toolbar mTb_main;
    @BindView(R.id.vP_main)
    ViewPager mVp_main;
    @BindView(R.id.ll_bot)
    LinearLayout mLl_bot;
    @BindView(R.id.tv_chat)
    TextView mTv_chat;
    @BindView(R.id.tv_contacts)
    TextView mTv_contacts;
    @BindView(R.id.tv_me)
    TextView mTv_me;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this);
        setContentView(R.layout.activity_main);
        StatusTextColor.Drak(this);
        ButterKnife.bind(MainActivity.this);
        getDraw();
        initData();
        setListener();

    }

    private void setListener() {
        /*Toolbar绑定Menu*/
        mTb_main.inflateMenu(R.menu.toolbar_main);

        /*Menu item点击事件*/
        mTb_main.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        Toasty.info(MainActivity.this,"Search").show();
                        break;
                    case R.id.carmera:
                         break;
                    case R.id.plus:
                        break;
                }
                return true;
            }
        });

        /*底部三TExtView的点击事件*/
        mTv_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTb_main.setTitle("CHat");
                mVp_main.setCurrentItem(1);
            }
        });

        mTv_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp_main.setCurrentItem(2);
            }
        });

        mTv_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVp_main.setCurrentItem(0);
            }
        });

        mVp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mTb_main.setTitle("Chat");
                        restDraw();
                        setDraw(mTv_chat,ic_message_clo);
                        resetCol();
                        setSelectCol(mTv_chat);
                        break;
                    case 1:
                        mTb_main.setTitle("Contacts");
                        restDraw();
                        setDraw(mTv_contacts,ic_contact_clo);
                        resetCol();
                        setSelectCol(mTv_contacts);
                        break;
                    case 2:
                        mTb_main.setTitle("Me");
                        restDraw();
                        setDraw(mTv_me,ic_me_clo);
                        resetCol();
                        setSelectCol(mTv_me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setDraw(TextView textView,Drawable drawable){
        textView.setCompoundDrawables(null,drawable,null,null);
    }
    private void restDraw(){
        mTv_chat.setCompoundDrawables(null,ic_message_un,null,null);
        mTv_contacts.setCompoundDrawables(null,ic_contact_un,null,null);
        mTv_me.setCompoundDrawables(null,ic_me_un,null,null);

    }
    private void getDraw(){
         ic_contact_clo= getResources().getDrawable(R.drawable.ic_contact_clo);
         ic_contact_un= getResources().getDrawable(R.drawable.ic_contacts_un);
         ic_message_clo= getResources().getDrawable(R.drawable.ic_message_clo);
         ic_message_un= getResources().getDrawable(R.drawable.ic_message_un);
         ic_me_clo= getResources().getDrawable(R.drawable.ic_me_clo);
         ic_me_un= getResources().getDrawable(R.drawable.ic_me_un);
         ic_contact_clo.setBounds(0, 0, ic_contact_clo.getMinimumWidth(),ic_contact_clo.getMinimumHeight());
         ic_contact_un.setBounds(0, 0, ic_contact_un.getMinimumWidth(),ic_contact_un.getMinimumHeight());
         ic_message_clo.setBounds(0, 0, ic_message_clo.getMinimumWidth(),ic_message_clo.getMinimumHeight());
         ic_message_un.setBounds(0, 0, ic_message_un.getMinimumWidth(),ic_message_un.getMinimumHeight());
         ic_me_clo.setBounds(0, 0,  ic_me_clo.getMinimumWidth(), ic_me_clo.getMinimumHeight());
         ic_me_un.setBounds(0, 0, ic_me_un.getMinimumWidth(),ic_me_un.getMinimumHeight());
    }
    private void initData(){
        mFragments.add(new ChatFragment());
        mFragments.add(new ContactsFragment());
        mFragments.add(new MeFragment());
        mVp_main.setAdapter(new MyAdapter(getSupportFragmentManager(),mFragments));
    }
    /*onCreateOptionsMenu只会执行一次，他只会在MENU显示之前，去做一次！！！之后就不再去执行
          onPrepareOptionsMenu 每次在display menu之前，都会去执行
          重写onPrepareOptionsMenu(Menu menu)实现对Toolbar的动态加载
          ！！！每次调用onPrepareOptionsMenu()的时候需要首先调用menu.clear()清除一下之前的menu数据，如果不清除的话，之前的menu数据不会被释放，造成内存泄漏！！！
        */

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

         /*switch (mVp_main.getCurrentItem()) {
            case 0:
                menu.findItem(R.id.carmera).setVisible(false);
                menu.findItem(R.id.search).setVisible(false);
                menu.findItem(R.id.plus).setVisible(false);
                Toasty.info(getApplicationContext(),"da").show();
                break;
            case 1:
                menu.findItem(R.id.carmera).setVisible(false);
                menu.findItem(R.id.search).setVisible(true);
                menu.findItem(R.id.plus).setVisible(true);
                Toasty.info(getApplicationContext(),"d").show();
                break;
            case 2:
                menu.findItem(R.id.carmera).setVisible(true);
                menu.findItem(R.id.search).setVisible(false);
                menu.findItem(R.id.plus).setVisible(false);
                Toasty.info(getApplicationContext(),"a").show();
                break;
             default:Toasty.warning(getApplicationContext(),"没调用").show();
        }*/
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                Toasty.info(getApplication(),"onOptionsItemSelected").show();
                break;
            case R.id.carmera:
                Toasty.info(getApplication(),"onOptionsItemSelected").show();
                break;
            case R.id.plus:
                Toasty.info(getApplication(),"onOptionsItemSelected").show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private  void setSelectCol(TextView text){
        text.setTextColor(getResources().getColor(R.color.textCol_Clo));
    }
    public  void resetCol(){
        mTv_chat.setTextColor(getResources().getColor(R.color.textCol_un));
        mTv_contacts.setTextColor(getResources().getColor(R.color.textCol_un));
        mTv_me.setTextColor(getResources().getColor(R.color.textCol_un));
    }
}
