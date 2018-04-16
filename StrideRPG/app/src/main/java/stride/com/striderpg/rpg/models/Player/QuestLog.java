package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Quest.Quest;

/**
 * QuestLog class to store the information about a Players Quests
 * and how far they've progressed on each.
 */
public class QuestLog {

    /**
     * PropertyChangeSupport to deal with raising events
     * when a Property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * HashMap to store a Players Quests and current progress.
     */
    private HashMap<String, Quest> quests = new HashMap<>();

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(QuestLog.class).
     * Also used to construct a new log of Quests for the Player
     * if one doesn't already exist.
     */
    public QuestLog() {
        for (Enums.QuestType questType : Enums.QuestType.values()) {
            quests.put(questType.name(), new Quest(
                    questType.getTitle(),
                    questType.getDescription(),
                    Enums.QuestLevel.ONE,
                    0,
                    0
            ));
        }
    }

    /**
     * Implementation of a QuestLog toString method to print out the
     * properties of the QuestLog.
     */
    @Override
    public String toString() {
        return "QuestLog{" +
                "quests=" + quests +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Update a Players specified quest with a new amount.
     */
    public void update(Enums.QuestType type, Integer amount) {
        // Grab the current Quest to deal with updating / changes directly.
        Quest quest = quests.get(type.name());

        // Update quest with new progress amount.
        quest.updateProgress(amount);

        // Check for completion of current Quest level.
        if (quest.completed()) {
            quest.levelUpQuest();
        }

        // Finally, put the updated quest back into the HashMap with
        // its new values.
        quests.put(type.name(), quest);

        // Fire PropertyChange event for the QuestLog being updated.
        changes.firePropertyChange(Constants.PROPERTY_QUEST_LOG_UPDATED, null, null);
    }

    public HashMap<String, Quest> getQuests() {
        return quests;
    }
}
