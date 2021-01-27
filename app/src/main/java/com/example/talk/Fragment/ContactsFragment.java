package com.example.talk.Fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.talk.Provider.ContactsProvider;
import com.example.talk.R;
import com.example.talk.dbHelper.ContactOpenHelper;
import com.example.talk.service.IMService;
import com.example.talk.utils.ThreadUtils;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;

import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_contacts, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        initListener();
        super.onActivityCreated(savedInstanceState);
    }

    private void init() {
        /*registerForContextObserver;*/
    }

    private void initView(View view) {

    }

    private void initListener() {

    }
    /*
    * Roster roster=conn.getRoster();
    * 获取所有的联系人
    * roster.getEntries()方法来获取所有RosterEntry对象，返回类型为Collection<RosterEntry>
    * Collection<RosterEntry> rosterentrys = roster.getEntries();
    * 获得分组信息，需要调用roster.getGroups(), 返回结果是一个Collection<RosterGroup>。可以在通过rosterGroup.getEntries()获取每个分组的成员
    * 对于每个成员的状态信息，如是否在线，签名等，可通过roster.gerPresence(RosterEntry)获取
    * */
    private void initData() {
//        获取花名册对象
        Roster roster=IMService.conn.getRoster();
//        获取所有的联系人
        Collection<RosterEntry> entries = roster.getEntries();


       for (RosterEntry entry: entries){
            System.out.println(entry.toString());
            System.out.println(entry.getUser());
            System.out.println(entry.getName());
            System.out.println(entry.getGroups());
            System.out.println(entry.getStatus());
            System.out.println(entry.getType());
       }
//       使用线程同步花名册
        ThreadUtils.runThread(new Runnable() {
            @Override
            public void run() {
                for (RosterEntry entry:entries){
                    Context context= getContext();
                    String account=entry.getUser();
                    ContentValues values=new ContentValues();
                    values.put(ContactOpenHelper.ContactTable.ACCOUNT,entry.getUser());
                    values.put(ContactOpenHelper.ContactTable.NICKNAME,entry.getName());
                    values.put(ContactOpenHelper.ContactTable.AVATAR,"0");
                   // values.put(ContactOpenHelper.ContactTable.PINYIN,entry.get);
                }
            }
        });
    }

  /*  @Override
    public void onDestroy() {
        // 按照常理,我们Fragment销毁了.那么我们就不应该去继续去监听
        // 但是,实际,我们是需要一直监听对应roster的改变
        // 所以,我们把联系人的监听和同步操作放到Service去
        unRegisterContentObserver();
        super.onDestroy();
        super.onDestroy();
    }*/

   /* private void setOrUpdateAdapter() {
        // 判断adapter是否存在
        if (mAdapter != null) {
            // 刷新adapter就行了
            mAdapter.getCursor().requery();
            return;
        }
        ThreadUtils.runThread(new Runnable() {
            @Override
            public void run() {
                // 对应查询记录
                final Cursor c =
                        getActivity().getContentResolver().query(ContactsProvider.URI_CONTACT, null, null, null, null);

                // 假如没有数据的时候
                if (c.getCount() <= 0) {
                    return;
                }
                // 设置adapter,然后显示数据
                ThreadUtils.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        *//**
                         BaseAdapter    -->getView
                         |-CursorAdapter
                         *//*
                        mAdapter = new CursorAdapter(getActivity(), c) {
                            // 如果convertView==null,返回一个具体的根视图
                            @Override
                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                                View view = View.inflate(context, R.layout.item_contact, null);
                                return view;
                            }

                            // 设置数据显示数据
                            @Override
                            public void bindView(View view, Context context, Cursor cursor) {
                                ImageView ivHead = (ImageView) view.findViewById(R.id.head);
                                TextView tvAccount = (TextView) view.findViewById(R.id.account);
                                TextView tvNickName = (TextView) view.findViewById(R.id.nickname);

                                String acccount =
                                        cursor.getString(c.getColumnIndex(ContactOpenHelper.ContactTable.ACCOUNT));

                                String nickName =
                                        cursor.getString(c.getColumnIndex(ContactOpenHelper.ContactTable.NICKNAME));

                                tvAccount.setText(acccount);
                                tvNickName.setText(nickName);
                            }
                        };

                        mListView.setAdapter(mAdapter);
                    }
                });
            }
        });
    }
    *//*=============== 监听数据库记录的改变 ===============*//*

    MyContentObserver mMyContentObserver	= new MyContentObserver(new Handler());

    *//**注册监听*//*
    public void registerContentObserver() {
        // content://xxxx/contact
        // content://xxxx/contact/i
        getActivity().getContentResolver().registerContentObserver(ContactsProvider.URI_CONTACT, true,
                mMyContentObserver);
    }

    public void unRegisterContentObserver() {
        getActivity().getContentResolver().unregisterContentObserver(mMyContentObserver);
    }

    *//**反注册监听*//*

    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }

        *//**
         * 如果数据库数据改变会在这个方法收到通知
         *//*
        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            // 更新adapter或者刷新adapter
            setOrUpdateAdapter();
        }
    }*/
}