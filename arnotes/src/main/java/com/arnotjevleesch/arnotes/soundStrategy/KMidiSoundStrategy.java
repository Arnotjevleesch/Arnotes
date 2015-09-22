package com.arnotjevleesch.arnotes.soundStrategy;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Environment;
import com.arnotjevleesch.arnotes.pojo.SoundNote;
import jp.kshoji.javax.sound.midi.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class KMidiSoundStrategy implements ISoundStrategy {

    UsbMidiSystem usbMidiSystem = null;
    private static final int VELOCITY = 64;
    private Context context;
    int sm = -1;
    List<SoundNote> notes = new ArrayList<SoundNote>();

    public KMidiSoundStrategy(Context context){
        this.context = context;
    }

	@Override
	public List<SoundNote> getSoundNoteList(int numberOfSound) {

		try {

			Sequence myseq = new Sequence(Sequence.PPQ,1);
			Track t = myseq.createTrack();


            for(int i=0;i<numberOfSound;i++) {
                Random r = new Random();
                int randomHigh = r.nextInt(90 - 50) + 50;

                SoundNote sn = addNoteToTrack(t, randomHigh, i);

                // pas de doublons
                if(!notes.contains(sn)) {
                    notes.add(sn);
                }
            }

			MidiSystem.write(myseq, 0, new File(Environment.getExternalStorageDirectory() + "/myseq.mid"));

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}


        SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
/*
                    new SoundPool.Builder()
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
			public void onLoadComplete(SoundPool soundPool, int sampleId,
									   int status) {
                AudioManager mgr = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
                float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
                float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                float volume = streamVolumeCurrent / streamVolumeMax;

                soundPool.play(sm, volume, volume, 1, 0, 1f);

			}
		});

		sm = soundPool.load(Environment.getExternalStorageDirectory()+"/myseq.mid", 1);



		//soundPool.release();

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



		return notes;
	}

    private SoundNote addNoteToTrack(Track t, int high, int noteNumber) throws InvalidMidiDataException {
        ShortMessage a = new ShortMessage();
        a.setMessage(ShortMessage.NOTE_ON, 1, high, VELOCITY);
        MidiEvent noteOn = new MidiEvent(a,noteNumber*8);
        t.add(noteOn);

        ShortMessage b = new ShortMessage();
        b.setMessage(ShortMessage.NOTE_OFF, 1, high, VELOCITY);
        MidiEvent noteOff = new MidiEvent(b,noteNumber*8 + 8);
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
