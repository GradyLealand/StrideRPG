package stride.com.striderpg.fragments.Bestiary;


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
import stride.com.striderpg.rpg.models.Enemy.Enemy;

/**
 * BestiaryAdapter used to instantiate new ViewHolders that contain
 * info about different enemies currently present in the game and
 * the amount defeated by the Active Player.
 */
public class BestiaryAdapter extends RecyclerView.Adapter<BestiaryAdapter.EnemyViewHolder> {

    /**
     * BestiaryAdapter Logging tag.
     */
    private static final String TAG = "BestiaryAdapter";

    /**
     * Enemy ArrayList used when inflating each Enemy as a new
     * EnemyViewHolder.
     */
    private ArrayList<EnemyDataHolder> enemies;

    /**
     * Constructor that sets the enemies ArrayList.
     */
    BestiaryAdapter(ArrayList<EnemyDataHolder> enemies) {
        this.enemies = enemies;
    }

    /**
     * Method called on each EnemyViewHolder instantiated.
     */
    @NonNull
    @Override
    public EnemyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_bestiary_item, viewGroup, false);
        return new EnemyViewHolder(v);
    }

    /**
     * Method called when the EnemyViewHolder is being bound to its
     * proper Enemy located in the enemies ArrayList.
     */
    @Override
    public void onBindViewHolder(@NonNull EnemyViewHolder enemyViewHolder, int i) {
        enemyViewHolder.bestiaryName.setText(enemies.get(i).getEnemy().getName());
        enemyViewHolder.bestiaryAmount.setText(
                String.format(G.locale, "%d", enemies.get(i).getAmount()));
        enemyViewHolder.bestiaryImage.setImageResource(enemies.get(i).getEnemy().getIcon());
    }

    /**
     * Method used to update the Bestiary Adapter by searching through the
     * ArrayList of EnemyDataHolders and when the correct one is found,
     * updated the Amount property by 1.
     */
    public void update(Enums.Enemies enemy) {
        // Loop through each EnemyDataHolder and check that current
        // enemy in iteration is equal to enemy being updated.
        for (EnemyDataHolder edh : enemies) {
            if (edh.getEnemy().getType() == enemy) {
                edh.updateAmount();
                break;
            }
        }

        // NotifyDataSetChanged will simply update the UI to reflect these changes.
        notifyDataSetChanged();
    }

    /**
     * Get count of enemies ArrayList.
     */
    @Override
    public int getItemCount() {
        return enemies.size();
    }

    /**
     * EnemyDataHolder Container used to store the Enemy and the Amount defeated
     * for the ActivePlayer.
     */
    static class EnemyDataHolder {

        /**
         * Enemy instance in container.
         */
        private Enemy enemy;

        /**
         * Amount of specific enemy defeated.
         */
        private Integer amount = 0;

        /**
         * EnemyDataHolder default Constructor.
         */
        EnemyDataHolder(Enemy enemy, Integer initialAmount) {
            this.enemy = enemy;
            this.amount = initialAmount;
        }

        /**
         * Update the Amount variable by 1.
         */
        void updateAmount() {
            this.amount += 1;
        }

        public Enemy getEnemy() {
            return enemy;
        }
        public void setEnemy(Enemy enemy) {
            this.enemy = enemy;
        }
        public Integer getAmount() {
            return amount;
        }
        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }

    /**
     * Static EnemyViewHolder class extending the ViewHolder class
     * and used to bind and inflate new EnemyViewsHolders with
     * required properties and elements.
     */
    static class EnemyViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView container that holds all EnemyView data.
         */
        CardView cv;

        /**
         * TextView to contain the Enemy name.
         */
        TextView bestiaryName;

        /**
         * TextView to contain the amount of this Enemy defeated.
         */
        TextView bestiaryAmount;

        /**
         * ImageView to hold this Enemies image resource.
         */
        ImageView bestiaryImage;

        /**
         * EnemyViewHolder constructor to set class properties to
         * elements contained in the itemView passed.
         */
        EnemyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            bestiaryName = itemView.findViewById(R.id.bestiary_name);
            bestiaryAmount = itemView.findViewById(R.id.bestiary_count);
            bestiaryImage = itemView.findViewById(R.id.bestiary_image);
        }
    }
}
