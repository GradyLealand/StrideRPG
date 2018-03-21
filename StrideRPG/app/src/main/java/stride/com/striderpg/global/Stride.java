package stride.com.striderpg.global;


import android.app.Application;
import android.content.Context;

/**
 * Simple Stride Contextual class for retrieving itself, this is necessary
 * for constructing the FitnessUtil class, it required a Context when calling
 * the readSteps() method. This class provides that Context.
 */
public class Stride extends Application {

    /**
     * Stride object instance.
     */
    private static Stride instance;

    /**
     * Retrieve this instance.
     * @return Stride object.
     */
    public static Stride getInstance() {
        return instance;
    }

    /**
     * Retrieve the application context from the instance.
     * @return Context for this instance.
     */
    public static Context getContext() {
        return instance.getApplicationContext();
    }

    /**
     * Set the instance to this object when created.
     */
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
