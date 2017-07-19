package frosquivel.com.infinitescrollapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import frosquivel.com.infinitescrollapp.Classes.Const;
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

        String isFirstTime = "";
        boolean isFirstTimeBool = true;
        try{
            isFirstTime = Utils.getSharedPreference(this, Const.C_IS_FIRST_TIME);
            isFirstTimeBool = Boolean.parseBoolean(isFirstTime);
        }catch(Exception ex){
            Utils.setSharedPreference(this, Const.C_IS_FIRST_TIME, "false");
        }

        if(isFirstTimeBool){
            Utils.setInitialSharedPreference(this, true);
        }
        
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
    }
}
