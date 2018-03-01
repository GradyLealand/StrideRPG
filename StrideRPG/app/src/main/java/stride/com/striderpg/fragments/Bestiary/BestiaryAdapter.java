package stride.com.striderpg.fragments.Bestiary;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.rpg.models.Encounter.Enemy;

/**
 * BestiaryAdapter that extends the Recycler View Adapter
 * of sub type EnemyViewHolder
 */
public class BestiaryAdapter extends RecyclerView.Adapter<BestiaryAdapter.EnemyBossViewHolder> {

    /**
     * BestiaryAdapter Logging tag.
     */
    private static final String TAG = "BestiaryAdapter";

    /**
     * ArrayList of type Monster.
     */
    private ArrayList<Enemy> enemies;

    BestiaryAdapter(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public EnemyBossViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(EnemyBossViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    static class EnemyBossViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView bestiaryName;

        TextView bestiaryAmount;

        ImageView bestiaryImage;

        public EnemyBossViewHolder(View itemView) {
            super(itemView);

        }
    }

}
