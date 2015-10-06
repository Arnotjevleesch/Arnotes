package com.arnotjevleesch.arnotes;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.arnotjevleesch.arnotes.exception.TechnicalException;
import com.arnotjevleesch.arnotes.exception.UserException;
import com.arnotjevleesch.arnotes.matchStrategy.CompareMatchStrategy;
import com.arnotjevleesch.arnotes.matchStrategy.IMatchStrategy;
import com.arnotjevleesch.arnotes.pojo.GraphicalNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;
import com.arnotjevleesch.arnotes.soundStrategy.ISoundStrategy;
import com.arnotjevleesch.arnotes.soundStrategy.KMidiSoundStrategy;

import java.util.List;


public class MainActivity extends Activity {

    private static final boolean DEBUG = true;
    private ISoundStrategy soundStrategy = null;
    private SoundNoteSet soundNotes = null;
    private List<GraphicalNote> graphicalNotes = null;
    private Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = createSpinner();
        createPlayButton();
        createValidateButton();
    }

    public ImageButton createPlayButton(){
        ImageButton imageButton = (ImageButton)(findViewById(R.id.imageButton));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CanvasView canvasView = (CanvasView) findViewById(R.id.canvas);
                canvasView.clear();

                try {
                    soundStrategy = new KMidiSoundStrategy(getApplicationContext());
                    soundStrategy.begin();
                    soundNotes = soundStrategy.getSoundNoteListAndPlay(Integer.valueOf(spinner.getSelectedItem().toString()));
                } catch (TechnicalException e) {
                    Toast.makeText(getApplicationContext(), R.string.technical_problem_message, Toast.LENGTH_SHORT).show();
                    Log.i("app", e.getMessage());
                }
            }
        });

        return imageButton;
    }

    public Button createValidateButton(){
        Button button = (Button)(findViewById(R.id.button));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CanvasView canvasView = (CanvasView) findViewById(R.id.canvas);
                graphicalNotes = canvasView.getGraphicalNoteList();

                if (DEBUG) {
                    Log.i("app", "s:" + soundNotes + " g:" + graphicalNotes);
                }

                try {
                    controlSoundAndGraphical();
                } catch (UserException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                IMatchStrategy matchStrategy = new CompareMatchStrategy(soundNotes, graphicalNotes);

                if (matchStrategy.isMatching()) {
                    Toast.makeText(getApplicationContext(), R.string.match_message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.not_match_message, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return button;
    }

    public void controlSoundAndGraphical() throws UserException{
        if(soundNotes.size() != graphicalNotes.size()) {
            throw new UserException(getApplicationContext().getString(R.string.notes_number_message));
        }
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
