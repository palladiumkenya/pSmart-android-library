package org.kenyahmis.psmartlibrary.Activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;

import com.acs.audiojack.AudioJackReader;
import com.acs.audiojack.DukptReceiver;

import org.kenyahmis.psmartlibrary.R;

public class AudioJackSettingsActivity extends Activity {

    private ProgressDialog mProgress;
    private AudioManager mAudioManager;
    private AudioJackReader mReader;
    private DukptReceiver mDukptReceiver = new DukptReceiver();
    private Context mContext = this;
    private int mSwipeCount;
    private final BroadcastReceiver mHeadsetPlugReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {

                boolean plugged = (intent.getIntExtra("state", 0) == 1);

                /* Mute the audio output if the reader is unplugged. */
                mReader.setMute(!plugged);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_jack_settings);
    }

    @Override
    protected void onDestroy() {

        /* Unregister the headset plug receiver. */
        unregisterReceiver(mHeadsetPlugReceiver);

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mReader.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mReader.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mProgress.dismiss();
        mReader.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProgress.dismiss();
        mReader.stop();
    }

}
