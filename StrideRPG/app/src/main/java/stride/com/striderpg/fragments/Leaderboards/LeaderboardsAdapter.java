package stride.com.striderpg.fragments.Leaderboards;


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
import stride.com.striderpg.rpg.generators.LevelGenerator;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Leaderboards Adapter used to instantiate new ViewHolders that
 * contain info about different players in the game sorted to display
 * players in a ranked format.
 */
public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.PlayerViewHolder> {

    /**
     * LeaderboardsAdapter Logging tag.
     */
    private static final String TAG = "LeaderboardsAdapter";

    /**
     * Player ArrayList used when inflating each Player as a new
     * PlayerViewHolder.
     */
    private ArrayList<Player> players;

    /**
     * Constructor that sets the players ArrayList.
     * @param players Player ArrayList.
     */
    LeaderboardsAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Method called on each PlayerViewHolder instantiated.
     */
    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_leaderboards_item, viewGroup, false);
        return new PlayerViewHolder(v);
    }

    /**
     * Method called when the PlayerViewHolder is being bound to its
     * property Player located in the players ArrayList.
     * @param playerViewHolder Current PlayerViewHolder being bound.
     * @param i Index of current player being bound to PlayerViewHolder.
     */
    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i) {
        playerViewHolder.playerRank.setText(parsePlayerRank(i));
        playerViewHolder.playerUsername.setText(players.get(i).getUsername());
        playerViewHolder.playerLevel.setText(String.format(
                G.locale, "Level %d", players.get(i).getLevel())
        );
        playerViewHolder.playerExp.setText(
                LevelGenerator.getReadableExpString(
                        players.get(i).getExperience(),
                        players.get(i).getLevel()
                )
        );
        playerViewHolder.playerImage.setImageResource(R.mipmap.ic_launcher);
    }

    /**
     * Parse out a Players rank based on their position in the sorted players
     * ArrayList, a Player in the top 10 will have their rank (0#).
     * Otherwise their rank is just their index + 1.
     */
    private String parsePlayerRank(int i) {
        if (i < 10) {
            return "0" + (i + 1);
        } else {
            return String.valueOf(i + 1);
        }
    }

    /**
     * Returns the players ArrayList size.
     */
    @Override
    public int getItemCount() {
        return players.size();
    }

    /**
     * Static PlayerViewHolder class extending the ViewHolder class
     * and used to bind and inflate new PlayerViewHolders with
     * required properties and elements.
     */
    static class PlayerViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView container that holds all PlayerView data.
         */
        CardView cv;

        /**
         * TextView to contain the Player rank.
         */
        TextView playerRank;

        /**
         * TextView to contain the Player username.
         */
        TextView playerUsername;

        /**
         * TextView to contain the Player level.
         */
        TextView playerLevel;

        /**
         * TextView to contain the Player experience.
         */
        TextView playerExp;

        /**
         * TextView to contain the Player image resource.
         */
        ImageView playerImage;

        /**
         * PlayerViewHolder constructor to set class properties
         * to elements contained in the itemView passed.
         */
        PlayerViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            playerRank = itemView.findViewById(R.id.player_rank);
            playerUsername = itemView.findViewById(R.id.player_username);
            playerLevel = itemView.findViewById(R.id.player_level);
            playerExp = itemView.findViewById(R.id.player_exp);
            playerImage = itemView.findViewById(R.id.player_profile_picture);
        }
    }
}
