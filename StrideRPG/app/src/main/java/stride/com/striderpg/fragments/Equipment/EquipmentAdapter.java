package stride.com.striderpg.fragments.Equipment;


import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * Equipment Adapter used to instantiate ViewHolders that contain information
 * about the different items that the
 */
public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    /**
     * EquipmentAdapter Logging tag.
     */
    private static final String TAG = "EquipmentAdapter";

    /**
     * Item ArrayList used when inflating each Item as a new
     * EquipmentViewHolder.
     */
    private ArrayList<ItemDataHolder> items;

    /**
     * Constructor that sets the items ArrayList.
     */
    EquipmentAdapter(ArrayList<ItemDataHolder> items) {
        this.items = items;
    }

    /**
     * Method called on each EquipmentViewHolder instantiated.
     */
    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_equipment_item, viewGroup, false);
        return new EquipmentViewHolder(v);
    }

    /**
     * Method called when the EquipmentViewHolder is being bound to its
     * property Item located in the items ArrayList.
     */
    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder equipmentViewHolder, int i) {
        equipmentViewHolder.itemImage.setBackgroundColor(Color.parseColor(items.get(i).item.getRarity().getColor()));
        equipmentViewHolder.itemName.setText(items.get(i).item.getName());
        equipmentViewHolder.itemDescription.setText(items.get(i).item.getDescription());

        String skillBoost = null;
        switch (items.get(i).item.getType()) {
            case WEAPON: skillBoost = "Strength"; break;
            case BOOTS: skillBoost = "Speed"; break;
            case HELMET: skillBoost = "Vitality"; break;
        }

        equipmentViewHolder.itemStatBoost.setText(String.format(G.locale, "+%d %s",
                items.get(i).item.getStatBoost(),
                skillBoost)
        );
    }

    public void update(Item newItem) {
        // Loop through each ItemDataHolder and check that current
        // item type in iteration is equal to the items that's been replaced.
        for (ItemDataHolder idh : items) {
            if (idh.type == newItem.getType()) {
                idh.item = newItem;
                break;
            }
        }

        // NotifyDataSetChanged will simply update the UI to reflect this change.
        notifyDataSetChanged();
    }

    /**
     * Get count of items ArrayList.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemDataHolder {
        private Enums.ItemType type;
        private Item item;

        ItemDataHolder(Enums.ItemType type, Item item) {
            this.type = type;
            this.item = item;
        }
    }

    static class EquipmentViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView container that holds all EquipmentView data.
         */
        CardView cv;

        /**
         * ImageView to contain the Item Image icon.
         */
        ImageView itemImage;

        /**
         * TextView to contain the Item name.
         */
        TextView itemName;

        /**
         * TextView to contain the Item description.
         */
        TextView itemDescription;

        /**
         * TextView to contain the Item stat boost.
         */
        TextView itemStatBoost;

        /**
         * EquipmentViewHolder constructor to set class properties
         * to elements contained in the itemView passed.
         */
        public EquipmentViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_desc);
            itemStatBoost = itemView.findViewById(R.id.item_statboost);
        }
    }
}
