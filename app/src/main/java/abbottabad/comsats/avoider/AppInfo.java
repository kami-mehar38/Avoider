package abbottabad.comsats.avoider;

import android.graphics.drawable.Drawable;

/**
 * This project Avoider is created by Kamran Ramzan on 07-Jan-17.
 */

class AppInfo {

    private String name;
    private Drawable appIcon;
    private String packageName;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
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
