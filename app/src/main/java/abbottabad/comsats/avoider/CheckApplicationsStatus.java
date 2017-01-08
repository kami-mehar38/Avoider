package abbottabad.comsats.avoider;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import com.rvalerio.fgchecker.AppChecker;

public class CheckApplicationsStatus extends Service {

    public static boolean lockNow = true;

    public CheckApplicationsStatus() {
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, int startId) {
        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";
        final SharedPreferences sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        AppChecker appChecker = new AppChecker();
        /*appChecker.other(new AppChecker.Listener() {
            @Override
            public void onForeground(String process) {
                Log.i("TAG", "onForeground: " + process);
                if (sharedPreferences.getBoolean(process + "_IS_ON", false)) {
                    if (lockNow) {
                        lockNow = false;
                        Intent intent = new Intent(CheckApplicationsStatus.this, LoackScreen.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("APP_PACKAGE", process);
                        startActivity(intent);
                    }
                }
            }
        }).timeout(1000).start(this);*/

        appChecker.when("abbottabad.comsats.campusapp", new AppChecker.Listener() {
            @Override
            public void onForeground(String process) {
                Intent intent = new Intent(CheckApplicationsStatus.this, LoackScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("APP_PACKAGE", process);
                startActivity(intent);
            }
        }).start(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}