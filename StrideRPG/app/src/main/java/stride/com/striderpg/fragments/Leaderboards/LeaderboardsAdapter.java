package stride.com.striderpg.fragments.Leaderboards;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * LeaderboardsAdapter that extends the Recycler View Adapter of
 * sub type PlayerViewHolder.
 */
public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.PlayerViewHolder> {


    /**
     * LeaderboardsAdapter Logging tag.
     */
    private static final String TAG = "LeaderboardsAdapter";

    /**
     * ArrayList of type Player.
     */
    private ArrayList<Player> players;

    /**
     * Constructor method for the LeaderboardsAdapter, sets the players
     * ArrayList to the specified ArrayList.
     * @param players ArrayList of type Player.
     */
    LeaderboardsAdapter(ArrayList<Player> players) {
        this.players = players;
        Log.d(TAG, "LeaderboardsAdapter:success:ArrayList<Player>=" + players);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_leaderboards_item, viewGroup, false);
        return new PlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder playerViewHolder, int i) {
        // Set players player[i] playerUsername TextView to Player username.
        playerViewHolder.playerUsername.setText(players.get(i).getUsername());

        // Set players player[i] playerLevel TextView to Player level.
        playerViewHolder.playerLevel.setText(String.format(
                G.locale, "%d", players.get(i).getLevel())
        );

        // Set players player[i] playerImage ImageView to Player image.
        // TODO: Use an actual Player image. PLACEHOLDER.
        playerViewHolder.playerImage.setImageResource(R.mipmap.ic_launcher);

        // Log successful player bind.
        Log.d(TAG, String.format(
                G.locale,
                "onBindViewHolder:success:player successfully bound: %s",
                players.get(i).toString())
        );
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Static sub-class for holding the PlayerViewHolder that contains
     * all relevant information about the Player object being inflated
     * and displayed.
     */
    static class PlayerViewHolder extends RecyclerView.ViewHolder {
        /**
         * Main CardView to hold this Players information.
         */
        CardView cv;

        /**
         * Player username TextView.
         */
        TextView playerUsername;

        /**
         * Player level TextView.
         */
        TextView playerLevel;

        /**
         * Player image ImageView.
         */
        ImageView playerImage;

        /**
         * PlayerViewHolder constructor method to set the ids of the
         * views inside of the holder.
         * @param itemView This Players itemView.
         */
        PlayerViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            playerUsername = itemView.findViewById(R.id.player_username);
            playerLevel = itemView.findViewById(R.id.player_level);
            playerImage = itemView.findViewById(R.id.player_profile_picture);
        }
    }
}
