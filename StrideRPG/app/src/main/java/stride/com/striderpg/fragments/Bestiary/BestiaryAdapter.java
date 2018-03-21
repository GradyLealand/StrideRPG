package stride.com.striderpg.fragments.Bestiary;


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
    private ArrayList<Enemy> enemies;

    /**
     * Constructor that sets the enemies ArrayList.
     * @param enemies Enemy ArrayList.
     */
    BestiaryAdapter(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * Method called on each EnemyViewHolder instantiated.
     * @param viewGroup ViewGroup that will contain the children
     *                  (Bestiary elements) -> enemies.
     * @param i Index of current enemy being inflated from enemies
     *          ArrayList.
     * @return New EnemyViewHolder with enemies.get(i)'s properties set.
     */
    @Override
    public EnemyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_bestiary_item, viewGroup, false);
        return new EnemyViewHolder(v);
    }

    /**
     * Method called when the EnemyViewHolder is being bound to its
     * proper Enemy located in the enemies ArrayList.
     * @param enemyViewHolder Current EnemyViewHolder being bound.
     * @param i Index of current enemy being bound to EnemyViewHolder.
     */
    @Override
    public void onBindViewHolder(EnemyViewHolder enemyViewHolder, int i) {
        enemyViewHolder.bestiaryName.setText(enemies.get(i).getName());
        enemyViewHolder.bestiaryAmount.setText(String.format(
                G.locale,
                "%d",
                G.activePlayer.getBestiary().getEnemies().get(
                        enemies.get(i).getType().name()
                    )
                )
        );
        enemyViewHolder.bestiaryImage.setImageResource(
                enemies.get(i).getIcon()
        );
    }

    /**
     * Returns the enemies ArrayList size.
     * @return enemies ArrayList size.
     */
    @Override
    public int getItemCount() {
        return enemies.size();
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
         * @param itemView View this Enemy is inside of.
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
