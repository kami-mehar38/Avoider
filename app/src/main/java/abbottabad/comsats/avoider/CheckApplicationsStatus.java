package abbottabad.comsats.avoider;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.rvalerio.fgchecker.AppChecker;

public class CheckApplicationsStatus extends Service {

    private String currentApp = null;
    private SharedPreferences sharedPreferences;

    public CheckApplicationsStatus() {
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, int startId) {
        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";

        sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        AppChecker appChecker = new AppChecker();
        appChecker.other(new AppChecker.Listener() {
            @Override
            public void onForeground(String process) {
                if (sharedPreferences.getBoolean(process + "_IS_ON", false)) {
                    if (currentApp == null || !currentApp.equals(process)) {
                        currentApp = process;
                        Intent intent = new Intent(CheckApplicationsStatus.this, LockScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                } else if (!process.equalsIgnoreCase("abbottabad.comsats.avoider")) {
                    currentApp = process;
                }
            }
        }).timeout(100).start(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
