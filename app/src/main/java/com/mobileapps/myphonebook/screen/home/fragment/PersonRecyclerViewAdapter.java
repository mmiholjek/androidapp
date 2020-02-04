package com.mobileapps.myphonebook.screen.home.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.model.Person;

import java.util.List;


public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {

    public interface RecyclerViewEventListener{
        void onItemClick(View view, int position, Person person);
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView nameTextView;
        TextView surnameTextView;
        ImageView personImageView;

        PersonViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.nameTextView = itemView.findViewById(R.id.nameTextView);
            this.surnameTextView = itemView.findViewById(R.id.surnameTextView);
            this.personImageView = itemView.findViewById(R.id.personImageView);
        }
    }

    private List<Person> people;
    private RecyclerViewEventListener listener;

    PersonRecyclerViewAdapter(List<Person> people, RecyclerViewEventListener listener){
        this.people = people;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rv_item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, final int position) {
        final Person person = people.get(position);
        holder.itemView.setOnClickListener(view -> listener.onItemClick(holder.itemView, position, person));
        holder.nameTextView.setText(person.getName());
        holder.surnameTextView.setText(person.getSurname());
        Glide.with(holder.itemView.getContext())
                .load(person.getImageUrl())
                .placeholder(R.drawable.logo)
                .into(holder.personImageView);
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
