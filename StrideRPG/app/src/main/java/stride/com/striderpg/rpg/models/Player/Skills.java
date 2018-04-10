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
     * Players current skill points.
     */
    private Integer skillPoints;

    /**
     * Constructor function to initialize a new Skill objects values.
     */
    Skills() {
        this.vitality = Constants.PLAYER_DEFAULT_VITALITY;
        this.strength = Constants.PLAYER_DEFAULT_STRENGTH;
        this.speed = Constants.PLAYER_DEFAULT_SPEED;
        this.skillPoints = 0;
    }

    /**
     * Implementation of a Skills toString() method to print out
     * properties of a Skills object.
     */
    @Override
    public String toString() {
        return "Skills{" +
                "vitality=" + vitality +
                ", strength=" + strength +
                ", speed=" + speed +
                ", skillPoints=" + skillPoints +
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
     * Increment the Players skill points property by the Constant defined.
     */
    public void gainSkillPoints() {
        this.skillPoints += Constants.SKILL_POINTS_ON_LEVEL;
        changes.firePropertyChange(Constants.PROPERTY_SKILL_POINTS, null, this.getSkillPoints());
    }

    /**
     * Level up a Players vitality property by one.
     */
    public void levelUpVitality() {
        this.vitality += 1;
        changes.firePropertyChange(Constants.PROPERTY_SKILL_VITALITY, null, this.getVitality());
    }

    /**
     * Level up a Players strength property by one.
     */
    public void levelUpStrength() {
        this.strength += 1;
        changes.firePropertyChange(Constants.PROPERTY_SKILL_STRENGTH, null, this.getStrength());
    }

    /**
     * Level up a Players speed property by one.
     */
    public void levelUpSpeed() {
        this.speed += 1;
        changes.firePropertyChange(Constants.PROPERTY_SKILL_SPEED, null, this.getSpeed());
    }

    public Integer getVitality() {
        return vitality;
    }
    public Integer getStrength() {
        return strength;
    }
    public Integer getSpeed() {
        return speed;
    }
    public Integer getSkillPoints() {
        return skillPoints;
    }
}
