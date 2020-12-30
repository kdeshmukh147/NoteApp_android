package com.kamleshd.mynote;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.kamleshd.mynote.Database.dbHelper;
import com.kamleshd.mynote.adapter.RecyclerViewAdapter;
import com.kamleshd.mynote.model.Note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    dbHelper db=new dbHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        int given_id=intent.getIntExtra("Rid",0);
        if (given_id!=0) {
            Note note = new Note();
            note.setId(given_id);
            db.deleteNote(note);

        }
        ArrayList<Note> contactArrayList=new ArrayList<>();
        //recyclerview initialization
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Note> notes=db.getAllNote();

        for(Note contact:notes)
        {
             contactArrayList.add(contact);
        }
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(this,contactArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                Intent i= new Intent("");
//                startActivity(i);
                Intent intent=new Intent( MainActivity.this,TextNote.class);
                MainActivity.this.startActivity(intent);
               // setContentView(R.layout.activity_text_note);
            }
        });



    }




  }