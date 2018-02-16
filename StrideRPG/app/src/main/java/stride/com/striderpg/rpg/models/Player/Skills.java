package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

import stride.com.striderpg.database.DBKeys;

/**
 * A Skills class to represent a Players skills or attributes in game.
 */
public class Skills {

    /**
     * PropertyChangedSupport object to deal with raising events when a Property on this object/bean
     * is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /**
     * Simple readonly tag to represent the Vitality skill as an abbreviation.
     */
    public static final String VIT_TAG = "VIT";

    /**
     * Simple readonly tag to represent the Strength skill as an abbreviation.
     */
    public static final String STR_TAG = "STR";

    /**
     * Simple readonly tag to represent the Speed skill as an abbreviation.
     */
    public static final String SPD_TAG = "SPD";

    /**
     * Players current vitality skill.
     */
    private Integer vitality;

    /**
     * Players current strength skill.
     */
    private Integer strength;

    /**
     * Players current speed skill.
     */
    private Integer speed;

    /**
     * Default constructor required for calls to
     * DataSnapshot.getValue(Skills.class).
     * Will also set by default all skills to 5.
     */
    public Skills() { }

    /**
     * Custom constructor function to set each skill as they are passed.
     * @param vitality Skill vitality property.
     * @param strength Skill strength property.
     * @param speed Skill speed property.
     */
    Skills(Integer vitality, Integer strength, Integer speed) {
        this.vitality = vitality;
        this.strength = strength;
        this.speed = speed;
    }

    /**
     * Implementation of a Skills toString() method to print out properties of a Skills object.
     * @return Properties of the Skills object.
     */
    @Override
    public String toString() {
        return "Skills{" +
                "vitality=" + vitality +
                ", strength=" + strength +
                ", speed=" + speed +
                '}';
    }

    /**
     * Attach a new PropertyChangeListener to this classes PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public Integer getVitality() {
        return vitality;
    }
    public void setVitality(Integer vitality) {
        if (!Objects.equals(this.vitality, vitality))
            changes.firePropertyChange(DBKeys.SKILLS_VITALITY, this.vitality, vitality);
        this.vitality = vitality;
    }

    public Integer getStrength() {
        return strength;
    }
    public void setStrength(Integer strength) {
        if (!Objects.equals(this.strength, strength))
            changes.firePropertyChange(DBKeys.SKILLS_STRENGTH, this.strength, strength);
        this.strength = strength;
    }

    public Integer getSpeed() {
        return speed;
    }
    public void setSpeed(Integer speed) {
        if (!Objects.equals(this.speed, speed))
            changes.firePropertyChange(DBKeys.SKILLS_SPEED, this.speed, speed);
        this.speed = speed;
    }
}
