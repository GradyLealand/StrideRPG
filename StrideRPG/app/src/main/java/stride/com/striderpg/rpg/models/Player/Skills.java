package stride.com.striderpg.rpg.models.Player;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;
import java.util.Random;

import stride.com.striderpg.database.DBKeys;
import stride.com.striderpg.rpg.Constants;

/**
 * A Skills class to represent a Players skills or attributes in game.
 */
public class Skills {

    /**
     * PropertyChangedSupport object to deal with raising events
     * when a Property on this object/bean is changed.
     */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

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
     */
    public Skills() { }

    /**
     * Custom constructor function to set each skill as they are
     * passed.
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
     * Implementation of a Skills toString() method to print out
     * properties of a Skills object.
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
     * Attach a new PropertyChangeListener to this classes
     * PropertyChangeSupport object.
     * @param listener Listener implementation.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Method to Increment a Players stats by a random amount.
     */
    public void levelUpSkills() {

        // Create Random instance for choosing amounts to increment.
        Random r = new Random();

        // Increment skills.
        this.setVitality(
                this.getVitality() + r.nextInt(
                        Constants.LEVEL_UP_VIT_STR_MAX_AMOUNT + 2
                ));
        this.setStrength(
                this.getVitality() + r.nextInt(
                        Constants.LEVEL_UP_VIT_STR_MAX_AMOUNT + 2
                ));
        this.setSpeed(this.getSpeed() + 3);
    }

    public Integer getVitality() {
        return vitality;
    }
    public void setVitality(Integer vitality) {
        if (!Objects.equals(this.vitality, vitality))
            changes.firePropertyChange(Constants.PROPERTY_VITALITY, this.vitality, vitality);
        this.vitality = vitality;
    }
    public Integer getStrength() {
        return strength;
    }
    public void setStrength(Integer strength) {
        if (!Objects.equals(this.strength, strength))
            changes.firePropertyChange(Constants.PROPERTY_STRENGTH, this.strength, strength);
        this.strength = strength;
    }
    public Integer getSpeed() {
        return speed;
    }
    public void setSpeed(Integer speed) {
        if (!Objects.equals(this.speed, speed))
            changes.firePropertyChange(Constants.PROPERTY_SPEED, this.speed, speed);
        this.speed = speed;
    }
}
