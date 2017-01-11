package abbottabad.comsats.avoider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This project Avoider is created by Kamran Ramzan on 11-Jan-17.
 */

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, CheckApplicationsStatus.class);
        context.startService(myIntent);
    }
}
