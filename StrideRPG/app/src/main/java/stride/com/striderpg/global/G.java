package stride.com.striderpg.global;


import stride.com.striderpg.fit.FitnessUtil;
import stride.com.striderpg.rpg.models.Player.Player;

/**
 * Simple public class to hold and store any objects that should be accessible anywhere from the
 * application during runtime procedures.
 */
public final class G {

    /**
     * Player object to store information about the Active Player currently using application.
     */
    public static Player activePlayer;

    /**
     * FitnessUtil object to store information about the Fitness api required to
     * make successful calls to the current users daily step count.
     */
    public static FitnessUtil fitnessUtil;

    /**
     * Globally available lastStepCount counter for calculating
     * the difference between steps. It is null on app startup but
     * set the the users current daily steps on each call to updateSteps()
     * in the Player class.
     */
    public static Integer lastStepCount = null;
}
