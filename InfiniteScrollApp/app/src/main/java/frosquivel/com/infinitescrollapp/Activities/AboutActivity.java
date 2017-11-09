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
 * A class for show all information about
 */

public class AboutActivity extends BaseDetailActivity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = addLayout(R.layout.activity_about);

         FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.frameLayout);

        AboutView view = AboutBuilder.with(activity)
                .setPhoto(R.drawable.profile)
                .setCover(R.drawable.bg_galaxy)
                .setName(getString(R.string.about_name))
                .setSubTitle(getString(R.string.about_sub_title))
                .setBrief(getString(R.string.about_brief))
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(getString(R.string.about_gitlink))
                .addEmailLink(getString(R.string.about_email))
                .addYoutubeChannelLink(getString(R.string.about_youtube))
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        frameLayout.addView(view);
    }

}
