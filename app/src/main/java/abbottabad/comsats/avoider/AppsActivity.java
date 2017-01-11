package abbottabad.comsats.avoider;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class AppsActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    public static AppInfoAdapter appInfoAdapter;
    private final int RESULT_SETTINGS = 10;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 18;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);

        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";
        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestUsageStatsPermission();
            checkDrawPermission();
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        appInfoAdapter = new AppInfoAdapter(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(appInfoAdapter);

        new BackgroundTasks(this).getAllApps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                startActivityForResult(new Intent(AppsActivity.this, SettingsActivity.class), RESULT_SETTINGS);
                break;
            }
            case R.id.action_about_us: {
                startActivity(new Intent(AppsActivity.this, AboutUs.class));
                break;
            }
        }
        return true;
    }


    void requestUsageStatsPermission() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                && !hasUsageStatsPermission(this)) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("USAGE_STATS_PERMISSION_GRANTED", true);
            editor.apply();
            if (sharedPreferences.getBoolean("OVERLAY_PERMISSION_GRANTED", false))
            startService(new Intent(this, CheckApplicationsStatus.class));
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    boolean hasUsageStatsPermission(Context context) {
        AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow("android:get_usage_stats",
                android.os.Process.myUid(), context.getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_SETTINGS) {
            SharedPreferences sharedPrefs = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("PASSWORD", sharedPrefs.getString("Password", "1234"));
            editor.putString("PASSWORDAPP", sharedPrefs.getString("PasswordApp", "1234"));
            editor.putBoolean("LOCK_MY_APP", sharedPrefs.getBoolean("LockMyApp", false));
            editor.putBoolean("VIBRATE_IF_WRONG", sharedPrefs.getBoolean("VibrateIfWrong", false));
            editor.apply();
        } else if (requestCode == REQUEST_CODE_ASK_PERMISSIONS) {
            if (!Settings.canDrawOverlays(AppsActivity.this)) {
                checkDrawPermission();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("OVERLAY_PERMISSION_GRANTED", true);
                editor.apply();
                if (sharedPreferences.getBoolean("USAGE_STATS_PERMISSION_GRANTED", false))
                startService(new Intent(this, CheckApplicationsStatus.class));
            }
        }
    }

    void checkDrawPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, REQUEST_CODE_ASK_PERMISSIONS);
            }
        }
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        requestUsageStatsPermission();
        checkDrawPermission();
    }*/
}
