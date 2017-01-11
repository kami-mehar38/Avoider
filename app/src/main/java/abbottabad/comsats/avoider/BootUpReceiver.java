package abbottabad.comsats.avoider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * This project Avoider is created by Kamran Ramzan on 11-Jan-17.
 */

public class BootUpReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.getBoolean("OVERLAY_PERMISSION_GRANTED", false)) {
            if (sharedPreferences.getBoolean("USAGE_STATS_PERMISSION_GRANTED", false)) {
                context.startService(new Intent(context, CheckApplicationsStatus.class));
            }
        }
    }
}
