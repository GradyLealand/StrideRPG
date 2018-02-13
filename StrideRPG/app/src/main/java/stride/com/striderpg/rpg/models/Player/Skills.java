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
    public Skills() {
        vitality = 5;
        strength = 5;
        speed = 5;
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

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Get a Skills vitality property.
     * @return Skill vitality property.
     */
    public Integer getVitality() {
        return vitality;
    }
    /**
     * Set a Skills vitality property.
     * @param vitality New Skill vitality.
     */
    public void setVitality(Integer vitality) {
        if (!Objects.equals(this.vitality, vitality))
            changes.firePropertyChange(DBKeys.SKILLS_VITALITY, this.vitality, vitality);
        this.vitality = vitality;
    }

    /**
     * Get a Skills strength property.
     * @return Skill strength property.
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * Set a Skills strength property.
     * @param strength New Skill strength.
     */
    public void setStrength(Integer strength) {
        if (!Objects.equals(this.strength, strength))
            changes.firePropertyChange(DBKeys.SKILLS_STRENGTH, this.strength, strength);
        this.strength = strength;
    }

    /**
     * Get a Skills speed property.
     * @return Skill speed property.
     */
    public Integer getSpeed() {
        return speed;
    }

    /**
     * Set a Skills speed property.
     * @param speed New Skill speed.
     */
    public void setSpeed(Integer speed) {
        if (!Objects.equals(this.speed, speed))
            changes.firePropertyChange(DBKeys.SKILLS_SPEED, this.speed, speed);
        this.speed = speed;
    }
}
