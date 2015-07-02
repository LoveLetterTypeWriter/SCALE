package uci.scale;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DisplayMessageAdviceActivity extends ActionBarActivity {
    private GlobalActivity g;
    private String message; //NEW: Speaker stuff
    private boolean v_imp_setting; //NEW: Speaker stuff


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        g = (GlobalActivity)getApplication();

        // Get the message from the intent
        Intent intent = getIntent();
        //message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        message = "Walla walla"; //NEW:Test
        v_imp_setting = intent.getBooleanExtra(MyActivity.V_IMP_MESSAGE, false);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        // Set the text view as the a
        // ctivity layout
        ///setContentView(textView);
        setContentView(R.layout.activity_display_message_advice);

        //NEW:Speaker Stuff
        replayMessage(textView);
        if (v_imp_setting)
        {
            Button replayButton = (Button)findViewById(R.id.r_button);
            replayButton.setVisibility(View.VISIBLE);
        }
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
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //NEW: Speaker Stuff
    public void replayMessage(View view)
    {
        //NEW: If the visual impairement option is turned on, create the speaker activity
        //TODO possibly have the v_imp check occur in the speaker activity itself
        if (v_imp_setting)
        {
            if (g.speaker != null)
            {
                g.speaker.speak(message);
            }
            else
            {
                Log.d("ACTIVITY","Null speaker");
            }
        }
    }

}
