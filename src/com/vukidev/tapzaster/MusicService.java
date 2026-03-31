package com.vukidev.tapzaster;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.content.res.AssetFileDescriptor;
import java.util.Random;

public class MusicService extends Service {
    private MediaPlayer mp;
    private String[] playlist = {"music/music1.mp3", "music/music2.mp3"};
    private Random random = new Random();

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String action = intent.getAction();

		if (action != null) {
			if (action.equals("PAUSE")) {
				if (mp != null && mp.isPlaying()) mp.pause();
			} else if (action.equals("RESUME")) {
				if (mp != null && !mp.isPlaying()) mp.start();
			}
		} else {
			// First time starting the app
			if (mp == null) playNext();
		}
		return START_STICKY;
	}
    private void playNext() {
        try {
            if (mp != null) { mp.release(); }
            mp = new MediaPlayer();
            
            AssetFileDescriptor afd = getAssets().openFd(playlist[random.nextInt(playlist.length)]);
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer p) { playNext(); }
            });

            mp.prepare();
            mp.start();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public void onDestroy() {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        super.onDestroy();
    }
}