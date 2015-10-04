package com.arnotjevleesch.arnotes.soundStrategy;

import com.arnotjevleesch.arnotes.pojo.SoundNoteSet;


public class JFugueSoundStrategy implements ISoundStrategy {

	@Override
	public SoundNoteSet getSoundNoteListAndPlay(int numberOfSound) {
		return null;
	}

    @Override
    public boolean begin() {
        return false;
    }

    @Override
    public boolean end() {
        return false;
    }

    public void jFugueShit(){
        /*
        Player player = new Player();

        try {
            String etat=Environment.getExternalStorageState();
            //Toast.makeText(this, etat, Toast.LENGTH_LONG).show();
            if (Environment.MEDIA_MOUNTED.equals(etat)){
                Log.i("app", "Le stockage est accessible en lecture écriture.");
            }
            else {
                if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(etat)){
                    Log.i("app", "Le stockage est accessible uniquement en écriture.");
                }
                else Log.i("app", "Le stockage n'est pas accessible.");
            }

            Log.i("app",Environment.getExternalStorageDirectory().getAbsolutePath());

            //Création du fichier
            //if (!file.exists()){
            //  file.createNewFile();
            //}

            Pattern p = new Pattern();
            p.add("C D E F G A B");

            player.saveMidi(p, new File(Environment.getExternalStorageDirectory()+"/HelloWorldAndroid.mid"));

            //MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, android.net.Uri.parse(file.toURI().toString()));

            //if(!mediaPlayer.isPlaying()){
            //  mediaPlayer.start();
            //Toast.makeText(MainActivity.this,
            //      "mediaPlayer.start()",
            //    Toast.LENGTH_LONG).show();
            //}

        } catch (IOException e) {
            e.printStackTrace();
        }
        */
	}
}
