package stride.com.striderpg.fragments.Generator;


import stride.com.striderpg.fragments.Bestiary.BestiaryFragment;
import stride.com.striderpg.fragments.Dashboard.DashboardFragment;
import stride.com.striderpg.fragments.InventoryFragment;
import stride.com.striderpg.fragments.Leaderboards.LeaderboardsFragment;
import stride.com.striderpg.fragments.Quests.QuestsFragment;

/**
 * Generator class for instancing the different Fragments present in
 * the application. Each fragment in game is generated on
 * FragmentGenerator instantiation, from then on, Fragments can be
 * hidden or shown based on NavigationActivity choices made by the user.
 */
public class FragmentGenerator {

    /**
     * Dashboard Fragment, similar to a Home page in game. Displays
     * a log of recent activity in the game. As well as giving the
     * Player a brief look into their stats at the top of the Fragment
     * with a Profile Bar at the top of the screen.
     */
    public final DashboardFragment dashboardFragment = new DashboardFragment();

    /**
     * Bestiary Fragment for looking at the different enemies in the
     * game and tracking how many of each, if any, have been defeated
     * by the Player.
     */
    public final BestiaryFragment bestiaryFragment = new BestiaryFragment();

    /**
     * Equipment Fragment, allow a Player to look at their current
     * equipment and inventory. They may also choose to un-equip,
     * trash or equip a new item in this Fragment.
     */
    public final InventoryFragment inventoryFragment = new InventoryFragment();

    /**
     * Quests Fragment for showing a Player what quests they have
     * completed and their current progress to different quests
     * earned by playing the game.
     */
    public final QuestsFragment questsFragment = new QuestsFragment();

    /**
     * Leaderboards Fragment for displaying a list of Players in the
     * game and their ranks. Can be sorted by different values
     * (level, total steps, experience, enemies defeated).
     */
    public final LeaderboardsFragment leaderboardsFragment = new LeaderboardsFragment();
}
