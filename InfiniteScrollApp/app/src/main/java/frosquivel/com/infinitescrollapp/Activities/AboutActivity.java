package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import frosquivel.com.infinitescrollapp.Activities.Base.BaseDetailActivity;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 02/07/2017.
 */

public class AboutActivity extends BaseDetailActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = addLayout(R.layout.activity_about);

        final FrameLayout flHolder = (FrameLayout) activity.findViewById(R.id.frameLayout);

        AboutView view = AboutBuilder.with(activity)
                .setPhoto(R.drawable.profile)
                .setCover(R.mipmap.profile_cover)
                .setName("Your Full Name")
                .setSubTitle("Mobile Developer")
                .setBrief("I'm warmed of mobile technologies. Ideas maker, curious and nature lover.")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("fabian7593")
                .addEmailLink("fabian7593@gmail.com")
                .addSkypeLink("fabianre7593")
                .addYoutubeChannelLink("UCJnvvHb_vwMwbnZWplkHIfw")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        flHolder.addView(view);
    }

}
