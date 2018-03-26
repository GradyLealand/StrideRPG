package stride.com.striderpg.fragments.Equipment;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
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
    private ArrayList<Item> items;

    /**
     * Constructor that sets the items ArrayList.
     * @param items Items ArrayList.
     */
    EquipmentAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Method called on each EquipmentViewHolder instantiated.
     * @param viewGroup ViewGroup that will contain the children
     *                  (Equipment elements) -> items.
     * @param i Index of current Item being inflated from items
     *          ArrayList.
     * @return New EquipmentViewHolder with items.get(i)'s properties set.
     */
    @Override
    public EquipmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_equipment_item, viewGroup, false);
        return new EquipmentViewHolder(v);
    }

    /**
     * Method called when the EquipmentViewHolder is being bound to its
     * property Item located in the items ArrayList.
     * @param equipmentViewHolder Current EquipmentViewHolder being bound.
     * @param i Index of current item being bound to EquipmentViewHolder.
     */
    @Override
    public void onBindViewHolder(EquipmentViewHolder equipmentViewHolder, int i) {
        equipmentViewHolder.itemImage.setBackgroundColor(items.get(i).getRarity().getColor());
        equipmentViewHolder.itemName.setText(items.get(i).getName());
        equipmentViewHolder.itemDescription.setText(items.get(i).getDescription());
        equipmentViewHolder.itemStatBoost.setText(items.get(i).getStatBoost());
    }

    @Override
    public int getItemCount() {
        return items.size();
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
         * @param itemView View this Item is inside of.
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
