package abbottabad.comsats.avoider;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * This project Avoider is created by Kamran Ramzan on 07-Jan-17.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}