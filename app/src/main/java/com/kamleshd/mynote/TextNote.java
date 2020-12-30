package com.kamleshd.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kamleshd.mynote.Database.dbHelper;
import com.kamleshd.mynote.model.Note;

import java.util.List;

public class TextNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_note);

        dbHelper db=new dbHelper(this);

        Intent intent=getIntent();
        String name=intent.getStringExtra("RText_name");
        int given_id=intent.getIntExtra("Rid",0);
        EditText editText=findViewById(R.id.editTextTextMultiLine);
        editText.setText(name);


        Button button=findViewById(R.id.buttonDone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sqlc", "onClick: ");
                if (given_id==0 )
                {
                    //TextView editText=(TextView) findViewById(R.id.editTextTextMultiLine);
                    Note note=new Note();
                    note.setText_Note((editText.getText().toString()));
                    db.addNote(note);



                }
                else {
                    Note note=new Note();
                    note.setId(given_id);
                    note.setText_Note((editText.getText().toString()));
                    db.updateNote(note);

                }
                Intent intent=new Intent( TextNote.this,MainActivity.class);
                TextNote.this.startActivity(intent);

//                Intent i=new Intent( "MainActivity.class");
//                startActivity(i);
            }

        });


    }

    public void donebuttonclick(View view) {

        TextView editText=(TextView) findViewById(R.id.editTextTextMultiLine);

        dbHelper db=new dbHelper(this);
        Note note=new Note();
        note.setText_Note(editText.toString());
        db.addNote(note);


        List<Note> notes=db.getAllNote();

        for(Note contact:notes)
        {
            Log.d("sql", "select id ="+contact.getId()+"\n name ="+contact.getText_Note());
            // contactArrayList.add(contact);
        }
        Intent i=new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}