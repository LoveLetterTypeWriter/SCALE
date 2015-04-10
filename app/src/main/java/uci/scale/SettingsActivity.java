package uci.scale;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
/**
 * Created by 1089C on 3/21/2015.
 */
public class SettingsActivity extends ActionBarActivity {
    //Settings constants go here
    public static final String KEY_V_IMP_MODE = "v_impaired_mode";
    public static final String KEY_A_IMP_MODE = "a_impaired_mode";
    public static final String E_CALL_NUMBER = "e_call_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
