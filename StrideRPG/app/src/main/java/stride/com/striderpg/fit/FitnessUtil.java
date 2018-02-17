package stride.com.striderpg.fit;


import android.content.Context;
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
    }

    /**
     * Attempt to read the current accounts daily step count.
     * The step count resets at midnight local time depending on the
     * phone being used.
     */
    public void readData() {
        Fitness.getHistoryClient(Stride.getContext(), account)
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(
                        new OnSuccessListener<DataSet>() {
                            @Override
                            public void onSuccess(DataSet dataSet) {
                                Integer total =
                                        dataSet.isEmpty()
                                                ? 0
                                                : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                                Log.d(TAG, "readData:successful : value=" + total);
                                G.activePlayer.updateSteps(total);
                            }}
                        )
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "readData:error", e);
                            }}
                        );
    }
}
