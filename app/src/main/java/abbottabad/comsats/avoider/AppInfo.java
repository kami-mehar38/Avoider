package abbottabad.comsats.avoider;

import android.graphics.drawable.Drawable;

/**
 * This project Avoider is created by Kamran Ramzan on 07-Jan-17.
 */

class AppInfo {

    private String name;
    private Drawable appIcon;
    private String packageName;
    private boolean isChecked;

    boolean isChecked() {
        return isChecked;
    }

    void setChecked(boolean checked) {
        isChecked = checked;
    }

    String getPackageName() {
        return packageName;
    }

    void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Drawable getAppIcon() {
        return appIcon;
    }

    void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }
}
