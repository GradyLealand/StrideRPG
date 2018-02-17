package stride.com.striderpg.fit;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import stride.com.striderpg.global.G;
import stride.com.striderpg.global.Stride;

/**
 * FitnessUtil class to deal with the reading of an Accounts daily step count.
 */
public class FitnessUtil {

    /**
     * FitnessUtil Logging tag.
     */
    private static final String TAG = "FitnessUtil";

    /**
     * GoogleSignInAccount for the Fitness api calls.
     */
    private GoogleSignInAccount account;

    /**
     * FitnessUtil custom constructor for setting the Context and GoogleSignAccount.
     * @param account GoogleSignInAccount.
     */
    public FitnessUtil(GoogleSignInAccount account) {
        this.account = account;
        Log.d(TAG, "FitnessUtil:success:account=" + account.getId());
    }

    /**
     * Attempt to read the current accounts daily step count.
     * The step count resets at midnight local time depending on the
     * phone being used.
     */
    public void readData() {
        try {
            Fitness.getHistoryClient(Stride.getContext(), account)
                    .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)

                    // Add a new OnSuccessListener to deal with updating the current
                    // Players steps when the application reads new data from the Fitness api.
                    .addOnSuccessListener(new OnSuccessListener<DataSet>() {
                                @Override
                                public void onSuccess(DataSet dataSet) {
                                    Integer total =
                                            dataSet.isEmpty()
                                                    ? 0
                                                    : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                    Log.d(TAG, "readData:onSuccess:value=" + total);
                                    G.activePlayer.updateSteps(total);
                                }
                            })

                    // Add a new OnFailureListener to dea, with any unexpected failures while
                    // attempting to read data from the Fitness api.
                    .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e(TAG, "readData:onFailure:error", e);
                                }
                            });

        // Catch exception case for issues with the Fitness object
        // when attempting to getHistoryClient.
        } catch (Exception e) {
            Log.e(TAG, "readData:error", e);
        }
    }
}
