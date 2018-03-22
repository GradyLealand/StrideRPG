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
 * FitnessUtil class that implements the Google Fitness API to read in
 * a users current step count.
 *
 * Fitness API Doc: https://github.com/googlesamples/android-fit
 */
public class FitnessUtil {

    /**
     * FitnessUtil Logging tag.
     */
    private static final String TAG = "FitnessUtil";

    /**
     * GoogleSignInAccount reference used to control and authenticate
     * any attempts to read a users fitness data.
     */
    private GoogleSignInAccount account;

    /**
     * FitnessUtil Constructor for setting the users GoogleSignInAccount.
     * @param account GoogleSignInAccount reference used to
     *                authenticate any calls to readData().
     */
    public FitnessUtil(GoogleSignInAccount account) {
        this.account = account;
        Log.d(TAG, "FitnessUtil:success:account=" + account.getId());
    }

    /**
     * Reads the users current steps from the Fitness API with this
     * utilities current GoogleSignInAccount reference.
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
                                    Integer total = dataSet.isEmpty() ? 0 : dataSet.getDataPoints()
                                                    .get(0)
                                                    .getValue(Field.FIELD_STEPS)
                                                    .asInt();
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
        } catch (Exception e) {
            Log.e(TAG, "readData:error", e);
        }
    }
}
