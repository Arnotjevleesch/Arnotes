package com.arnotjevleesch.arnotes.soundStrategy;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import com.arnotjevleesch.arnotes.exception.TechnicalException;
import com.arnotjevleesch.arnotes.pojo.SoundNote;
import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;
import jp.kshoji.javax.sound.midi.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;


public class KMidiSoundStrategy implements ISoundStrategy {

    private static final int VELOCITY = 64;
    private static final String FILENAME = "myseq.mid";
    private static final int START_MIDI_NOTE = 50;
    private static final int END_MIDI_NOTE = 90;
    private static final int SOUND_POOL_MAX_STREAMS = 4;
    private static final int TICK_INTERVAL = 8;

    private UsbMidiSystem usbMidiSystem = null;
    private Context context = null;
    private int sm = -1;
    private SoundNoteSet notes = new SoundNoteSet();

    public KMidiSoundStrategy(Context context){
        this.context = context;
    }

	@Override
	public SoundNoteSet getSoundNoteListAndPlay(int numberOfSound) throws TechnicalException {
        try {
            Sequence myseq = new Sequence(Sequence.PPQ, 1);
            Track t = myseq.createTrack();

            for (int i = 0; i < numberOfSound; i++) {
                Random r = new Random();
                int randomHigh = r.nextInt(END_MIDI_NOTE - START_MIDI_NOTE) + START_MIDI_NOTE;
                SoundNote sn = addNoteToTrack(t, randomHigh, i);
                notes.add(sn);
            }

            MidiSystem.write(myseq, 0, new File(Environment.getExternalStorageDirectory() + FILENAME));

            SoundPool soundPool = new SoundPool(SOUND_POOL_MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    /*
            SoundPool soundPool = new SoundPool.Builder()
                                .setMaxStreams(2)
                                .setAudioAttributes(
                                        new AudioAttributes.Builder()
                                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                                                .build())
                                .build();
    */

            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
                    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                    float volume = streamVolumeCurrent / streamVolumeMax;

                    soundPool.play(sm, volume, volume, 1, 0, 1f);

                }
            });

            sm = soundPool.load(Environment.getExternalStorageDirectory() + "/myseq.mid", 1);

            /*
            MediaPlayer mPlayer = new MediaPlayer();

            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            try {
                mPlayer.setDataSource(getApplicationContext(), android.net.Uri.parse(f.toURI().toString()));
            } catch (IllegalArgumentException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            } catch (SecurityException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            } catch (IllegalStateException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                mPlayer.prepare();
            } catch (IllegalStateException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
            }

            mPlayer.start();
            */
        } catch (InvalidMidiDataException e) {
            throw new TechnicalException(e.getMessage());
        } catch (IOException e) {
            throw new TechnicalException(e.getMessage());
        }

		return notes;
	}

    private SoundNote addNoteToTrack(Track t, int high, int noteNumber) throws InvalidMidiDataException {
        ShortMessage a = new ShortMessage();
        a.setMessage(ShortMessage.NOTE_ON, 1, high, VELOCITY);
        MidiEvent noteOn = new MidiEvent(a,noteNumber*TICK_INTERVAL);
        t.add(noteOn);

        ShortMessage b = new ShortMessage();
        b.setMessage(ShortMessage.NOTE_OFF, 1, high, VELOCITY);
        MidiEvent noteOff = new MidiEvent(b,(noteNumber+1)*TICK_INTERVAL);
        t.add(noteOff);

        return new SoundNote(BigDecimal.valueOf(high));
    }

    @Override
    public boolean begin() {
        usbMidiSystem = new UsbMidiSystem(context);
        usbMidiSystem.initialize();
        return true;
    }

    @Override
    public boolean end() {
        if (usbMidiSystem != null) {
            usbMidiSystem.terminate();
        }
        return true;
    }

}
