package com.example.nurislam.recyclerviewclick;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nurislam on 01.10.2016.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private Context mContext;
    private List<Item> list;
    private ItemClickListenner mItemsClickListenner;

    public ItemAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private Item item;
        private View view;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        public void onBind(final Item item, final ItemClickListenner listenner) {
            this.item = item;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenner.onClick(item);
                }
            });
        }

        public TextView getName() {
            return name;
        }
    }
}
