package stride.com.striderpg.rpg.models.Player;


import java.util.HashMap;

import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Quest.Quest;

/**
 * QuestLog class to store the information about a Players Quests and how far they've
 * progressed on each.
 */
public class QuestLog {

    /**
     * HashMap to store a Players Quests and current progress.
     */
    private HashMap<String, Quest> quests = new HashMap<>();

    /**
     * Default constructor required for calls to DataSnapshot.getValue(QuestLog.class).
     * Also used to construct a new log of Quests for the Player if one doesn't already exist.
     */
    public QuestLog() {
        for (Enums.QuestType questType : Enums.QuestType.values()) {
            quests.put(questType.name(), new Quest(
                    questType.getTitle(),
                    questType.getDescription(),
                    Enums.QuestLevel.ONE,
                    0,
                    Enums.QuestLevel.ONE.getGoal()
            ));
        }
    }

    /**
     * Implementation of a QuestLog toString method to print out the properties of the QuestLog.
     * @return Properties of the QuestLog object.
     */
    @Override
    public String toString() {
        return "QuestLog{" +
                "quests=" + quests +
                '}';
    }

    /**
     * Update a Players specified quest with a new amount.
     * @param type Quest being updated.
     * @param amount Amount being added to Quest progress.
     */
    public void update(Enums.QuestType type, Integer amount) {
        // Grab the current Quest to deal with updating/changes directly.
        Quest quest = quests.get(type.name());

        // Update quest with new progress amount.
        quest.updateProgress(amount);

        // Check for completion of current Quest level.
        if (quest.completed()) {
            quest.levelUpQuest();
        }

        // Finally, put the updated quest back into the HashMap with its new values.
        quests.put(type.name(), quest);
    }

    public HashMap<String, Quest> getQuests() {
        return quests;
    }
}
