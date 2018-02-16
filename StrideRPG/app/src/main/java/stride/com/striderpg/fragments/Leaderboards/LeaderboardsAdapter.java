package stride.com.striderpg.fragments.Leaderboards;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * LeaderboardsAdapter that extends the Recycler View Adapter of type Player.
 * Used to inflate the CardView for each Player.
 */
public class LeaderboardsAdapter extends RecyclerView.Adapter<LeaderboardsAdapter.PlayerViewHolder> {

    /**
     * ArrayList of type Player.
     */
    private ArrayList<Player> players;

    /**
     * Constructor method for the LeaderboardsAdapter, sets the players
     * ArrayList to the specified ArrayList.
     * @param players
     */
    LeaderboardsAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_leaderboards_item, viewGroup, false);
        return new PlayerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder playerViewHolder, int i) {
        playerViewHolder.personName.setText(players.get(i).getUsername());
        playerViewHolder.personAge.setText(players.get(i).getLevel().toString());
        playerViewHolder.personPhoto.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Static sub-class for holding the PlayerViewHolder that contains all
     * relevant information about the Player object being inflated and displayed.
     */
    public static class PlayerViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        /**
         * PlayerViewHolder constructor method to set the ids of the views
         * inside of the holder.
         * @param itemView This Players itemView.
         */
        PlayerViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            personName = itemView.findViewById(R.id.player_username);
            personAge = itemView.findViewById(R.id.player_level);
            personPhoto = itemView.findViewById(R.id.player_profile_picture);
        }
    }
}
