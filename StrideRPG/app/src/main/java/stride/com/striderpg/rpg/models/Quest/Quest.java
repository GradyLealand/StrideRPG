package stride.com.striderpg.rpg.models.Quest;


import stride.com.striderpg.rpg.Enums;

/**
 * A Quest class to represent a single Quest in the game and the information about it.
 */
public class Quest {

    /**
     * Generic Quest name.
     */
    private String name;

    /**
     * Description of the quest.
     */
    private String description;

    /**
     * Enumeration representation of the current Quest level.
     */
    private Enums.QuestLevel questLevel;

    /**
     * Current quest progress.
     */
    private Integer progress;

    /**
     * Goal for current Quest.
     */
    private Integer progressGoal;

    /**
     * Default Constructor required for calls to
     * dataSnapshot.getValue(Quest.class).
     */
    public Quest() { }

    /**
     * Custom Constructor to generate a new Quest with specified parameters.
     * @param name Quest name.
     * @param description Quest description.
     * @param questLevel Enum QuestLevel.
     * @param progress Current Quest progress.
     * @param progressGoal Quest goal progress.
     */
    public Quest(String name, String description, Enums.QuestLevel questLevel, Integer progress, Integer progressGoal) {
        this.name = name;
        this.description = description;
        this.questLevel = questLevel;
        this.progress = progress;
        this.progressGoal = progressGoal;
    }

    /**
     * Implementation of a Quests toString method to print out the properties of a Quest object.
     * @return Properties of the Quest object.
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

    public void updateProgress(Integer amount) {
        this.progress += amount;
    }

    public boolean completed() {
        return progress >= progressGoal;
    }

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
