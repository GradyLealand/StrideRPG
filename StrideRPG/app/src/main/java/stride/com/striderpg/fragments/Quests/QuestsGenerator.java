package stride.com.striderpg.fragments.Quests;

import java.util.ArrayList;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.models.Quest.Quest;

/**
 * QuestsGenerator used to retrieve the current Players QuestLog
 * to generate a new ArrayList of each Quest.
 */
public class QuestsGenerator {

    /**
     * QuestsGenerator Logging tag.
     */
    private static final String TAG = "QuestsGenerator";

    /**
     * Quest ArrayList to hold each Quest that will be inside of the
     * Quests.
     */
    private ArrayList<Quest> quests = new ArrayList<>();

    /**
     * Constructor that calls the buildQuests() method to
     * generate the Quests information/data.
     */
    QuestsGenerator() {
        buildQuests();
    }

    /**
     * Build out the quests ArrayList by adding the active players
     * current quest log values to the quests ArrayList.
     */
    private void buildQuests() {
        quests.addAll(G.activePlayer.getQuestLog().getQuests().values());
    }

    /**
     * Gets the quests ArrayList.
     * @return ArrayList quests.
     */
    public ArrayList<Quest> getQuests() {
        return quests;
    }

}
