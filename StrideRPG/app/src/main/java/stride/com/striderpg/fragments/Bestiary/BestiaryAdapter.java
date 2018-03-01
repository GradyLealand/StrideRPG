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
 * BestiaryAdapter that extends the Recycler View Adapter
 * of sub type EnemyViewHolder
 */
public class BestiaryAdapter extends RecyclerView.Adapter<BestiaryAdapter.EnemyViewHolder> {

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
    public EnemyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_bestiary_item, viewGroup, false);
        return new EnemyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EnemyViewHolder enemyViewHolder, int i) {
        enemyViewHolder.bestiaryName.setText(
                enemies.get(i).getName()
        );

        enemyViewHolder.bestiaryAmount.setText(
                String.format(G.locale, "%d",
                        G.activePlayer.getBestiary().getEnemies().get(
                                enemies.get(i).getType().name()
                        )
                )
        );

        enemyViewHolder.bestiaryImage.setImageResource(
                enemies.get(i).getIcon()
        );
    }

    @Override
    public int getItemCount() {
        return enemies.size();
    }


    static class EnemyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;

        TextView bestiaryName;

        TextView bestiaryAmount;

        ImageView bestiaryImage;

        public EnemyViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            bestiaryName = itemView.findViewById(R.id.bestiary_name);
            bestiaryAmount = itemView.findViewById(R.id.bestiary_count);
            bestiaryImage = itemView.findViewById(R.id.bestiary_image);
        }
    }

}
