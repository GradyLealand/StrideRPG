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

    public static FitnessUtil fitnessUtil;
}
