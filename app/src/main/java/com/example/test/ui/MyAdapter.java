package com.example.test.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.test.R;
import com.example.test.model.ModelVotes;

import java.util.ArrayList;
import java.util.List;

class MyAdapter extends Adapter<MyAdapter.ModelVotesViewHolder> {

    public final List<ModelVotes> votesList;

    MyAdapter() {
        this.votesList = new ArrayList<>();
    }

    public void addVotes(List<ModelVotes> votes) {
        final int index = this.votesList.size();
        this.votesList.addAll(votes);
        notifyItemRangeInserted(index, votes.size());
    }

    class ModelVotesViewHolder extends RecyclerView.ViewHolder {

        private ModelVotes model;
        private TextView title;
        private TextView votes;

        public ModelVotesViewHolder(@NonNull View parent) {
            super(parent);
            title = parent.findViewById(R.id.language_view_holder);
            votes = parent.findViewById(R.id.votes_view_holder);
        }

        void bind(final ModelVotes model) {
            if (this.model == null || !this.model.equals(model)) {
                this.model = model;
                title.setText(model.getLanguage());
                votes.setText(String.valueOf(model.getVotes()));
            }
        }
    }

    @NonNull
    @Override
    public ModelVotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ModelVotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ModelVotesViewHolder holder, int position) {
        holder.bind(votesList.get(position));
    }


    @Override
    public int getItemCount() {
        return votesList.size();
    }
}
