package stride.com.striderpg.rpg.models.Quest;


import stride.com.striderpg.rpg.Enums;

/**
 * A Quest class to represent a single Quest in the game and the
 * information about it.
 */
public class Quest {

    /**
     * Quest name.
     */
    private String name;

    /**
     * Quest description.
     */
    private String description;

    /**
     * Quest QuestLevel enumeration.
     */
    private Enums.QuestLevel questLevel;

    /**
     * Quest progress.
     */
    private Integer progress;

    /**
     * Quest progress to completion.
     */
    private Integer progressGoal;

    /**
     * Default Constructor required for calls to
     * dataSnapshot.getValue(Quest.class).
     */
    public Quest() { }

    /**
     * Constructor to set the Quest's name, description, questLevel, progress
     * and progressGoal properties.
     */
    public Quest(String name, String description, Enums.QuestLevel questLevel, Integer progress, Integer progressGoal) {
        this.name = name;
        this.description = description;
        this.questLevel = questLevel;
        this.progress = progress;
        this.progressGoal = progressGoal;
    }

    /**
     * Implementation of a Quests toString method to print out the
     * properties of a Quest object.
     */
    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questLevel=" + questLevel +
                ", progress=" + progress +
                ", progressGoal=" + progressGoal +
                '}';
    }

    /**
     * Update the quests progress.
     */
    public void updateProgress(Integer amount) {
        this.progress += amount;
    }

    /**
     * Get a boolean representing whether or not the current Quest's
     * progress is greater than or equal to the current Quest progress goal.
     */
    public boolean completed() {
        return progress >= progressGoal;
    }

    /**
     * Level this quest up to the QuestLevel next to it in it's Enumeration.
     */
    public void levelUpQuest() {
        // Use QuestLevel Enum next() method to return the next QuestLevel.
        Enums.QuestLevel newQuestLevel = this.getQuestLevel().next();

        // Set new information for Players quest.
        this.setQuestLevel(newQuestLevel);
        this.setProgress(0);
        this.setProgressGoal(newQuestLevel.getGoal());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Enums.QuestLevel getQuestLevel() {
        return questLevel;
    }
    public void setQuestLevel(Enums.QuestLevel questLevel) {
        this.questLevel = questLevel;
    }
    public Integer getProgress() {
        return progress;
    }
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
    public Integer getProgressGoal() {
        return progressGoal;
    }
    public void setProgressGoal(Integer progressGoal) {
        this.progressGoal = progressGoal;
    }
}
