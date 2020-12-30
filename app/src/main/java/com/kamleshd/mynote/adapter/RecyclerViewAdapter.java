package com.kamleshd.mynote.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.kamleshd.mynote.MainActivity;
import com.kamleshd.mynote.R;
import com.kamleshd.mynote.TextNote;
import com.kamleshd.mynote.model.Note;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Note> noteList;

    public RecyclerViewAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note contact=noteList.get(position);

        holder.Text_Note.setText(contact.getText_Note());
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        Contact contact=contactList.get(position);
//
//        holder.contactName.setText(contact.getName());
//        holder.phone_number.setText(contact.getPhone_number());
//
//    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public  TextView Text_Note;
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            Text_Note=itemView.findViewById(R.id.textnote);
            imageView=itemView.findViewById(R.id.imageView2);

        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();

            imageView=itemView.findViewById(R.id.imageView2);
            imageView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    Log.d("sqlc", "onClick: image button");

                    Note note=noteList.get(position);
                    int id=note.getId();
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.putExtra("Rid",id);

                    context.startActivity(intent);
                }
            });

            Note note=noteList.get(position);
            String name= note.getText_Note();
            int id=note.getId();


            Intent intent=new Intent(context, TextNote.class);
            intent.putExtra("RText_name",name);
            intent.putExtra("Rid",id);

            context.startActivity(intent);

        }
    }
}
