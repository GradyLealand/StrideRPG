package stride.com.striderpg.global;


import java.util.Locale;

import stride.com.striderpg.fit.FitnessUtil;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Global Class G used to create an accessible resource that can be reached
 * during runtime and provide easier methods to reach important game elements:
 *
 * Player activePlayer - Used to get and set numerous values for the current
 *                       user playing the game. This Player object is also
 *                       consistently pushed to the database.
 *
 * FitnessUtil fitnessUtil - Used to read the current users steps through the use
 *                           of Google's Fitness API.
 *
 * Integer lastStepCount - This Integer is null when the application is started and
 *                         for each steps read call afterwards, it is updated based
 *                         off of the lastStepCount to determine the difference
 *                         between a users last steps and current steps.
 */
public final class G {

    /**
     * Player object to store information about the Active Player
     * currently using application.
     */
    public static Player activePlayer;

    /**
     * FitnessUtil object to store information about the Fitness
     * api required to make successful calls to the current users
     * daily step count.
     */
    public static FitnessUtil fitnessUtil;

    /**
     * Globally available lastStepCount counter for calculating
     * the difference between steps. It is null on app startup but
     * set the the users current daily steps on each call to
     * updateSteps() in the Player class.
     */
    public static Integer lastStepCount = null;

    /**
     * Get the default Locale on instantiation for parsing and
     * formatting numbers based on a Users location and standards in
     * region.
     */
    public static Locale locale = Locale.getDefault();

    /**
     * Use an Integer to store Players current step count, once
     * this number reaches a certain threshold, it is reset back to
     * 0, this process continues as the Player has the game open.
     */
    public static Integer onlineActivitySteps = 0;
}
