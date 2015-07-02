package uci.scale;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
    public final static String preferenceBrokerHost = "@string/preferenceBrokerHost"; //test
    public final static String preferenceBrokerUser = "@string/preferenceBrokerUser"; //test

    public GlobalActivity g;
    private final int CHECK_CODE = 0x1;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        g = (GlobalActivity)getApplication();
        setContentView(R.layout.activity_my);

      //  SharedPreferences settings = getSharedPreferences(MQTTService.APP_ID, 0);
      //  SharedPreferences.Editor editor = settings.edit();
      //  editor.putString("broker", preferenceBrokerHost);
      //  editor.putString("topic",  preferenceBrokerUser);
      //  editor.commit();

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

        //Speaker activity creation/check for tts engine
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, CHECK_CODE);
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
        Intent intent = new Intent(this, MQTTActivity.class);
       /* TextView editText = (TextView) findViewById(R.id.button1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        //Pass settings
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean v_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_V_IMP_MODE, false);
        boolean a_imp_mode = sharedPref.getBoolean(SettingsActivity.KEY_A_IMP_MODE, false);
        String e_call_number = sharedPref.getString(SettingsActivity.E_CALL_NUMBER, "");
        intent.putExtra(V_IMP_MESSAGE, v_imp_mode);
        intent.putExtra(A_IMP_MESSAGE, a_imp_mode);
        intent.putExtra(E_CALL_MESSAGE, e_call_number);*/
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

    //NEW: Speaker Stuff
    //TODO possibly have a global speaker object, although it may be better to do it in each individual activity to save resources
    //since the tts can be shut off after leaving the message activity
    //TODO have the check occur in the sharedPreferencesChanged listener in MainActivity

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CHECK_CODE){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                Log.d("ACTIVITY", "Create speaker");
                if (g == null)
                {
                    Log.d("ACTIVITY","WTF");
                }
                else
                {
                    g.speaker = new SpeakerActivity(this);
                    g.speaker.speak("Testing one two three");
                }

            }else {
                Log.d("ACTIVITY","Req install");
                Intent install = new Intent();
                install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(install);
            }
        }
    }



}

