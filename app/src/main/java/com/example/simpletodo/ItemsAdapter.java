package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener{
        void ontItemLongClicker(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickLister, OnClickListener clickListener ) {
        this.items = items;
        this.longClickListener = longClickLister;
        this.clickListener = clickListener;


    }

    @NonNull
    @Override
    // responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflater to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // wrap it inside a ViewHolder and return it

        return new ViewHolder(todoView);
    }

    @Override
    //responsible for taking data at a particular position and putting it into a ViewHolder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);

        //Bind the item into the  specified ViewHolder

        holder.bind(item);
    }

    @Override
    //# of items available in the data
    public int getItemCount() {

        return items.size();
    }

    // Container to provide easy access to views that represent each row in the list

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //Update the view inside of the VieHolder with this data.
        public void bind(String item){

            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());

                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notify the listener which position was long pressed.
                    longClickListener.ontItemLongClicker(getAdapterPosition());
                    return true;
                }
            });

        }
    }
}
