package stride.com.striderpg.global;


import android.app.Application;
import android.content.Context;

/**
 * Global Context class using a Singleton pattern to retrieve the Application
 * context.
 */
public class Stride extends Application {

    /**
     * Stride object instance.
     */
    private static Stride instance;

    /**
     * Retrieve this insance.
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
