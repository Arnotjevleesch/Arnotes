package com.arnotjevleesch.arnotes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.arnotjevleesch.arnotes.exception.UserException;
import com.arnotjevleesch.arnotes.matchStrategy.CompareMatchStrategy;
import com.arnotjevleesch.arnotes.matchStrategy.IMatchStrategy;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNote;
import com.arnotjevleesch.arnotes.soundStrategy.ISoundStrategy;
import com.arnotjevleesch.arnotes.soundStrategy.KMidiSoundStrategy;

import java.util.List;


public class MainActivity extends Activity {

    private static final boolean DEBUG = true;
    private ISoundStrategy soundStrategy = null;
    private List<SoundNote> soundNotes;
    private Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = createSpinner();

        (findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CanvasView canvasView = (CanvasView)findViewById(R.id.canvas);
                canvasView.clear();

                soundStrategy = new KMidiSoundStrategy(getApplicationContext());
                soundStrategy.begin();
                soundNotes = soundStrategy.getSoundNoteList(Integer.valueOf(spinner.getSelectedItem().toString()));
            }
        });

        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView canvasView = (CanvasView)findViewById(R.id.canvas);
                List<GraphicalNote> graphicalNotes = canvasView.getGraphicalNoteList();

                if(DEBUG){
                    Log.i("app","s:" + soundNotes + " g:" + graphicalNotes);
                }

                try {
                    IMatchStrategy matchStrategy = new CompareMatchStrategy(soundNotes, graphicalNotes);

                    if(matchStrategy.isMatching()) {
                        Toast.makeText(getApplicationContext(), "Match !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Match !", Toast.LENGTH_SHORT).show();
                    }

                } catch (UserException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public Spinner createSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.notes_number_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.notes_number_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        return spinner;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        soundStrategy.end();
    }
}
