package com.geekchtech.android1lesson2povtor;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private onRecyclerViewClickListener listener;

    public interface onRecyclerViewClickListener{
        void onItemClick(Students students,int position);
    }

    public void onRecyclerViewClickListener(onRecyclerViewClickListener listener){
        this.listener = listener;
    }



    private ArrayList<Students> list = new ArrayList<>();

    public MainAdapter() {
    }



    public void addStudent(Students student) {
        this.list.add(student);
        notifyDataSetChanged();
    }

    public void changeStudent(Students student, int pos) {
        list.remove(pos);
        this.list.add(pos, student);
        notifyItemChanged(pos);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new MyViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname;


        public MyViewHolder(View view, onRecyclerViewClickListener listener) {
            super(view);
            name = view.findViewById(R.id.name);
            surname = view.findViewById(R.id.surname);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                       listener.onItemClick(list.get(getAdapterPosition()),getAdapterPosition());
                       Log.e("tag", "click");
                   }
                }
            });

        }

        public void onBind(Students students) {
            name.setText(students.getName());
            surname.setText(students.getSurname());
        }
    }

}
