package abbottabad.comsats.avoider;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This project Avoider is created by Kamran Ramzan on 07-Jan-17.
 */

 class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {

    private final String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";
    private SharedPreferences sharedPreferences;
    private Context context;
    private List<AppInfo> appInfoList = new ArrayList<>();

    AppInfoAdapter(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_row, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final AppInfo appInfo = appInfoList.get(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.appName.setText(appInfo.getName());
        holder.appPackage.setText(appInfo.getPackageName());
        if (sharedPreferences.getBoolean(holder.appPackage.getText().toString() + "_IS_ON", false)){
            appInfo.setChecked(true);
        } else appInfo.setChecked(false);
        holder.appLock.setOnCheckedChangeListener(null);
        holder.appLock.setChecked(appInfo.isChecked());
        holder.appLock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                appInfo.setChecked(b);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (b) {
                    Toast.makeText(context, holder.appName.getText().toString() + " Locked", Toast.LENGTH_SHORT).show();
                    editor.putBoolean(holder.appPackage.getText().toString() + "_IS_ON", true);
                    editor.apply();
                } else {
                    Toast.makeText(context, holder.appName.getText().toString() + " Unlocked", Toast.LENGTH_SHORT).show();
                    editor.putBoolean(holder.appPackage.getText().toString() + "_IS_ON", false);
                    editor.apply();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return appInfoList.size();
    }

    void addItem(AppInfo appInfo, int position) {
        appInfoList.add(position, appInfo);
        notifyItemInserted(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView appIcon;
        private TextView appName;
        private TextView appPackage;
        private Switch appLock;
        ViewHolder(View itemView) {
            super(itemView);
            setIsRecyclable(false);
            appIcon = (ImageView) itemView.findViewById(R.id.appIcon);
            appName = (TextView) itemView.findViewById(R.id.appName);
            appPackage = (TextView) itemView.findViewById(R.id.appPackage);
            appLock = (Switch) itemView.findViewById(R.id.btn_lock);

        }
    }
}
