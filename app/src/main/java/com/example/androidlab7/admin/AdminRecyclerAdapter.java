package com.example.androidlab7.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidlab7.myinterf.OnClick;
import com.example.androidlab7.data.Item;
import com.example.androidlab7.data.ItemData;
import com.example.androidlab7.R;

public class AdminRecyclerAdapter extends RecyclerView.Adapter<AdminRecyclerAdapter.ViewHolder> {
    private OnClick click;
    public AdminRecyclerAdapter(OnClick click) {
        this.click = click;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_admin, parent,  false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Item item = ItemData.getInstance().getItems().get(position);
        holder.name.setText(item.getName());
        holder.price.setText(Integer.toString(item.getPrice()));
        holder.count.setText(Integer.toString(item.getCount()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemData.getInstance().getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView price;
        TextView count;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_recycler_admin);
            price = itemView.findViewById(R.id.price_text_recycler_admin);
            count = itemView.findViewById(R.id.count_text_recycler_admin);
            layout = itemView.findViewById(R.id.recycler_item_layout_admin);
        }
    }
}
