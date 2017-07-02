package frosquivel.com.infinitescrollapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Fabian on 12/06/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/NEOTERICBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        setContentView(R.layout.activity_splash_screen);

        Utils.setInitialSharedPreference(this, true);

        Thread timer= new Thread()
        {
            public void run()
            {
                try
                {
                    //Display for 3 seconds
                    sleep(1500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
        timer.start();


       /* Typeface typeface = Typeface.createFromAsset(getAssets(), "font/NEOTERICBold.ttf");

        RotatingTextWrapper rotatingTextWrapper = (RotatingTextWrapper) findViewById(R.id.custom_switcher);
        rotatingTextWrapper.setSize(35);
        rotatingTextWrapper.setTypeface(typeface);

        Rotatable rotatable = new Rotatable(Color.parseColor("#673AB7"),2000 ,
                getString(R.string.splash_child_human), getString(R.string.splash_child_brothers));
        rotatable.setSize(35);

        rotatable.setAnimationDuration(500);
        rotatable.setInterpolator(new BounceInterpolator());
        rotatable.setTypeface(typeface);

        rotatingTextWrapper.setContent(getString(R.string.splash_father_we_are),rotatable);*/

    }
}
