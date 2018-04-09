package stride.com.striderpg.fragments.Quests;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Quest.Quest;

/**
 * Quests Adapter used to instantiate ViewHolders that contain information
 * about the different quests in the game and the Players current progress in each.
 */
public class QuestsAdapter extends RecyclerView.Adapter<QuestsAdapter.QuestViewHolder> {

    /**
     * QuestsAdapter Logging tag.
     */
    private static final String TAG = "QuestsAdapter";

    /**
     * Quest ArrayList used when inflating each Quest as a new
     * QuestViewHolder.
     */
    private ArrayList<Quest> quests;

    /**
     * Constructor that sets the quests ArrayList.
     * @param quests Quest ArrayList.
     */
    QuestsAdapter(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    /**
     * Method called on each QuestViewHolder instantiated.
     * @param viewGroup ViewGroup that will contain the children
     *                  (Quest elements) -> quests.
     * @param i Index of current quest being inflated from quests
     *          ArrayList.
     * @return New QuestViewHolder with quests.get(i)'s properties set.
     */
    @NonNull
    @Override
    public QuestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.fragment_quests_item, viewGroup, false);
        return new QuestViewHolder(v);
    }

    /**
     * Method called when the QuestViewHolder is being bound to its
     * property Quest located in the quests ArrayList.
     */
    @Override
    public void onBindViewHolder(@NonNull QuestViewHolder questViewHolder, int i) {
        questViewHolder.questTitle.setText(quests.get(i).getName());
        questViewHolder.questTier.setText(parseTier(i));
        questViewHolder.questDescription.setText(quests.get(i).getDescription());
        questViewHolder.questProgress.setMax(quests.get(i).getProgressGoal());
        questViewHolder.questProgress.setProgress(quests.get(i).getProgress());
        questViewHolder.questProgressText.setText(parseProgress(i));
    }

    /**
     * Parse out a quests current tier number from it's Enumeration.
     * @param i Integer representing Quest Tier.
     * @return Readable string to represent Quest Tier.
     */
    private String parseTier(int i) {
        return String.format(
                G.locale,
                "Tier: %s",
                quests.get(i).getQuestLevel().getNumeral()
        );
    }

    /**
     * Parse out a Quests progress in the format (Progress/Goal).
     */
    private String parseProgress(int i) {
        String currentProgress = quests.get(i).getProgress().toString();
        String progressGoal = quests.get(i).getProgressGoal().toString();

        return currentProgress + "/" + progressGoal;
    }

    /**
     * Returns the quests ArrayList size.
     */
    @Override
    public int getItemCount() {
        return quests.size();
    }

    /**
     * Static QuestViewHolder class extending the ViewHolder class
     * and used to bind and inflate new QuestViewHolders with
     * required properties and elements.
     */
    static class QuestViewHolder extends RecyclerView.ViewHolder {

        /**
         * CardView container that holds all QuestView data.
         */
        CardView cv;

        /**
         * ImageView to contain the Quest Image icon.
         */
        ImageView questImage;

        /**
         * TextView to contain the Quest title.
         */
        TextView questTitle;

        /**
         * TextView to contain the Quest tier.
         */
        TextView questTier;

        /**
         * TextView to contain the Quest description.
         */
        TextView questDescription;

        /**
         * ProgressBar to contain the Quest current progress.
         */
        ProgressBar questProgress;

        /**
         * TextView to contain the Quest current progress text.
         */
        TextView questProgressText;

        /**
         * QuestViewHolder constructor to set class properties
         * to elements contained in the itemView passed.
         */
        public QuestViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            questImage = itemView.findViewById(R.id.questImage);
            questTitle = itemView.findViewById(R.id.questTitle);
            questTier = itemView.findViewById(R.id.questTier);
            questDescription =  itemView.findViewById(R.id.questDescription);
            questProgress =  itemView.findViewById(R.id.questProgress);
            questProgressText =  itemView.findViewById(R.id.questProgressText);
        }
    }
}
