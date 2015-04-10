package uci.scale;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Button;
import android.net.Uri;
import android.view.View.OnClickListener;



public class MyActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "@string/action_open";
    public final static String V_IMP_MESSAGE = "@string/v_impaired_setting"; //test
    public final static String A_IMP_MESSAGE = "@string/a_impaired_setting"; //test
    public final static String E_CALL_MESSAGE = "@string/e_call_number"; //test
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
        /////
        button = (Button) findViewById(R.id.buttonCall);

        // add button listener
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String callingnumber = "tel:" + e_call_number;
                callIntent.setData(Uri.parse(callingnumber));
                startActivity(callIntent);

            }

        });
        /////

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Call the options menu here
            Intent intent = new Intent(this, SettingsActivity.class);
            //Pass settings
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            boolean v_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_V_IMP_MODE, false);
            boolean a_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_A_IMP_MODE, false);
            String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
            intent.putExtra(V_IMP_MESSAGE, v_imp_mode);
            intent.putExtra(A_IMP_MESSAGE, a_imp_mode);
            intent.putExtra(E_CALL_MESSAGE, e_call_number);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendAlertMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageAlertActivity.class);
        TextView editText = (TextView) findViewById(R.id.button1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //Pass settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean v_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_V_IMP_MODE, false);
        boolean a_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_A_IMP_MODE, false);
        String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
        intent.putExtra(V_IMP_MESSAGE, v_imp_mode);
        intent.putExtra(A_IMP_MESSAGE, a_imp_mode);
        intent.putExtra(E_CALL_MESSAGE, e_call_number);
        startActivity(intent);
    }
    public void sendWarningMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageWarningActivity.class);
        TextView editText = (TextView) findViewById(R.id.button2);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //Pass settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean v_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_V_IMP_MODE, false);
        boolean a_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_A_IMP_MODE, false);
        String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
        intent.putExtra(V_IMP_MESSAGE, v_imp_mode);
        intent.putExtra(A_IMP_MESSAGE, a_imp_mode);
        intent.putExtra(E_CALL_MESSAGE, e_call_number);
        startActivity(intent);
    }

    public void sendAdviceMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageAdviceActivity.class);
        TextView editText = (TextView) findViewById(R.id.button3);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //Pass settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean v_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_V_IMP_MODE, false);
        boolean a_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_A_IMP_MODE, false);
        String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
        intent.putExtra(V_IMP_MESSAGE, v_imp_mode);
        intent.putExtra(A_IMP_MESSAGE, a_imp_mode);
        intent.putExtra(E_CALL_MESSAGE, e_call_number);
        startActivity(intent);
    }


    public void calling(View view) {
        // show a dialog calling
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Calling!");
        //TODO have setmessage use the e_call_number
        alert.setMessage("+11(571)400-0000");

    }
    public void printStarter(View view) {
        TextView v = (TextView) findViewById(R.id.button1);
        int score = Integer.parseInt(v.getText().toString());
        v.setText(""+score++);
    }




}

