package frosquivel.com.infinitescrollapp.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 28/06/2017.
 */
public class PaperOnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_main_layout);

        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnboarding(), getApplicationContext());

        engine.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int oldElementIndex, int newElementIndex) {

            }
        });

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {

            }
        });

    }

    // Just example data for Onboarding
    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage(getString(R.string.on_boarding_android_title),
                getString(R.string.on_boarding_android_value),
                ContextCompat.getColor(this, R.color.onboarding_01), R.drawable.ic_onboarding_android, R.drawable.ic_onboarding_android);
        PaperOnboardingPage scr2 = new PaperOnboardingPage(getString(R.string.on_boarding_ios_title),
                getString(R.string.on_boarding_ios_value),
                ContextCompat.getColor(this, R.color.onboarding_02), R.drawable.ic_onboarding_ios_fix, R.drawable.ic_onboarding_ios_fix);
        PaperOnboardingPage scr3 = new PaperOnboardingPage(getString(R.string.on_boarding_web_title),
                getString(R.string.on_boarding_web_value),
                ContextCompat.getColor(this, R.color.onboarding_03), R.drawable.ic_onboarding_web, R.drawable.ic_onboarding_web);
        PaperOnboardingPage scr4 = new PaperOnboardingPage(getString(R.string.on_boarding_cloud_title),
                getString(R.string.on_boarding_cloud_value),
                ContextCompat.getColor(this, R.color.onboarding_04), R.drawable.ic_onboarding_cloud, R.drawable.ic_onboarding_cloud);
        PaperOnboardingPage scr5 = new PaperOnboardingPage(getString(R.string.on_boarding_analytics_title),
                getString(R.string.on_boarding_analytics_value),
                ContextCompat.getColor(this, R.color.onboarding_05), R.drawable.ic_onboarding_graphics, R.drawable.ic_onboarding_graphics);
        PaperOnboardingPage scr6 = new PaperOnboardingPage(getString(R.string.on_boarding_qa_title),
                getString(R.string.on_boarding_qa_value),
                ContextCompat.getColor(this, R.color.onboarding_06), R.drawable.ic_onboarding_qa, R.drawable.ic_onboarding_qa);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        elements.add(scr4);
        elements.add(scr5);
        elements.add(scr6);

        return elements;
    }
}
