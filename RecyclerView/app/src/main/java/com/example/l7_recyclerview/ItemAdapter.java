package com.example.l7_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// Model class for the data to be displayed in each item
class Item {
    int imageResId;  // Image resource ID
    String title;    // Title text
    String subtitle; // Subtitle text

    Item(int imageResId, String title, String subtitle) {
        this.imageResId = imageResId;
        this.title = title;
        this.subtitle = subtitle;
    }
}


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Item> itemList;


    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        // Get the current item
        Item item = itemList.get(position);

        // Bind data to the views
        holder.imageView.setImageResource(item.imageResId);
        holder.titleTextView.setText(item.title);
        holder.subtitleTextView.setText(item.subtitle);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    // ViewHolder class that holds references to the views for each item
    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView subtitleTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            titleTextView = itemView.findViewById(R.id.item_title);
            subtitleTextView = itemView.findViewById(R.id.item_subtitle);
        }
    }
}
