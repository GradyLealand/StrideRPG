package stride.com.striderpg.rpg.generators;


import android.util.Log;

import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.Enums;
import stride.com.striderpg.rpg.models.Activity.Activity;
import stride.com.striderpg.rpg.models.Enemy.Enemy;
import stride.com.striderpg.rpg.models.Item.Item;
import stride.com.striderpg.rpg.utils.TimeParser;

public class OnlineGenerator {

    /**
     * OnlineGenerator Logging tag.
     */
    private static final String TAG = "OnlineGenerator";

    /**
     * Calculate and check if an online Activity is possible right now
     * with the Players current online steps count.
     * TODO: This method looks very similar to the calculateOfflineActivities method,
     * TODO: can these be consolidated/cleaned up to use similar code blocks
     */
    public static void calculateOnlineActivity() {
        if (G.onlineActivitySteps > Constants.ONLINE_ACTIVITY_STEP_THRESHOLD) {
            Log.d(TAG, "calculateOnlineActivity:begin");
            // Reset current sessions steps counter.
            G.onlineActivitySteps = 0;
            Activity activity = new Activity();

            // Pick a random activity type and begin building new Activity.
            Enums.ActivityType type = Enums.random(Enums.ActivityType.class);
            switch (type) {
                case LOOT:
                    // Create new base Item and Activity for Item.
                    Item item = ItemGenerator.generate(G.activePlayer);
                    activity = ActivityGenerator.generateLootActivity(item);

                    // Update Player generics for stats and quests.
                    G.activePlayer.getStats().updateItemsLooted();
                    G.activePlayer.getQuestLog().update(Enums.QuestType.LOOT_ITEMS, 1);

                    // Check if new Item is better than current equipped Item.
                    if (item.isBetter(G.activePlayer.getEquipment().getItem(item.getItemType()))) {
                        G.activePlayer.getEquipment().replaceItem(item.getItemType(), item);
                    }
                    break;

                case ENEMY:
                    // Generate a new random Enemy and have the activePlayer fight.
                    Enemy enemy = EnemyGenerator.generate(G.activePlayer);
                    boolean fightResult = G.activePlayer.fightEnemy(enemy);

                    // Based on fight result, generate corresponding Activity.
                    if (fightResult) {
                        activity = ActivityGenerator.generateEnemyDefeatedActivity(enemy);
                    } else {
                        activity = ActivityGenerator.generateDefeatedByEnemyActivity(enemy);
                    }
                    break;
            }

            // Set timestamp for this new Activity.
            activity.setTimestamp(TimeParser.makeTimestamp());
            Log.d(TAG, String.format(
                    G.locale,
                    "calculateOnlineActivity:success:activity=%s", activity)
            );

            // Add new Activity to Players ActivityLog.
            G.activePlayer.getActivityLog().addOnlineActivity(activity);
        }
    }

}
