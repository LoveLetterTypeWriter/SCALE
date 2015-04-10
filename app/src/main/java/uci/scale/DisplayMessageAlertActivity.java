package uci.scale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class DisplayMessageAlertActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        boolean v_imp_setting = intent.getBooleanExtra(MyActivity.V_IMP_MESSAGE, false);

        // Create the text view
       // TextView textView = new TextView(this);
       // TextView textView = (TextView) findViewById(R.id.button1);
       // textView.setTextSize(40);
       // textView.setText("@string/zero");
        // Set the text view as the a
        // ctivity layout
        ///setContentView(textView);




        setContentView(R.layout.activity_display_message_alert);



        /*button R.layout.MyActivity.button1;
        TextView textView = (TextView) findViewById(R.id.button1);
        textView.setTextSize(40);
        textView.setText("@string/zero");*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar) {
            //Call the options menu here
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

       return super.onOptionsItemSelected(item);
    }


}
