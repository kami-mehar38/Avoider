package abbottabad.comsats.avoider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

/**
 * This project Avoider is created by Kamran Ramzan on 09-Jan-17.
 */

class BackgroundTasks {

    private Context context;

    BackgroundTasks(Context context) {
        this.context = context;
    }

    void getAllApps() {
        new GetAllApps().execute();
    }

    private class GetAllApps extends AsyncTask<Void, Void, List<AppInfo>> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
        }

        @Override
        protected List<AppInfo> doInBackground(Void... voids) {
            final PackageManager pm = context.getPackageManager();
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> apps = pm.queryIntentActivities(intent, PackageManager.GET_META_DATA);

            List<AppInfo> appInfoList = new ArrayList<>();
            for (ResolveInfo resolveInfo : apps) {
                if (!resolveInfo.activityInfo.packageName.equals("abbottabad.comsats.avoider")) {
                    Drawable packageIcon = pm.getApplicationIcon(resolveInfo.activityInfo.applicationInfo);
                    String packageLabel = String.valueOf(pm.getApplicationLabel(resolveInfo.activityInfo.applicationInfo));
                    AppInfo appInfo = new AppInfo();
                    appInfo.setAppIcon(packageIcon);
                    appInfo.setName(packageLabel);
                    appInfo.setPackageName(resolveInfo.activityInfo.packageName);
                    appInfoList.add(appInfo);
                }
            }
            return appInfoList;
        }

        @Override
        protected void onPostExecute(List<AppInfo> appInfoList) {
            progressDialog.cancel();
            if (appInfoList.size() > 0 && MainActivity.appInfoAdapter != null) {
                for(AppInfo appInfo : appInfoList) {
                    MainActivity.appInfoAdapter.addItem(appInfo, 0);
                }
            }
        }
    }
}
