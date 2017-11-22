package com.jiangkang.storage.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiangkang.storage.R;
import com.jiangkang.storage.greendao.GreenDaoHelper;
import com.jiangkang.storage.greendao.Note;
import com.jiangkang.storage.greendao.NoteAdapter;
import com.jiangkang.storage.greendao.NoteDao;

import java.util.ArrayList;
import java.util.List;


public class GreenDaoDemoActivity extends AppCompatActivity {


    private EditText mEtInput;

    private Button mBtnSubmit;

    private RecyclerView mRcNotes;

    private NoteAdapter mAdapter;

    private List<Note> noteList;

    private NoteDao mNoteDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao_demo);
        initViews();
        initData();
    }

    private void initData() {
        noteList = new ArrayList<>();
        mAdapter = new NoteAdapter(this,noteList);
        mNoteDao = new GreenDaoHelper(this).getNoteDao();
        initRecyclerView();
    }

    private void initViews() {
        mEtInput = (EditText) findViewById(R.id.et_input);
        mBtnSubmit = (Button) findViewById(R.id.btn_submit);
        mRcNotes = (RecyclerView) findViewById(R.id.rc_notes);
        mRcNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initRecyclerView() {

        mRcNotes.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick() {

            }
        });

    }


}
