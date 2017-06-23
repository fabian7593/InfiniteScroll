package frosquivel.com.infinitescrollapp.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ViewGroup;

import com.irozon.sneaker.Sneaker;

import frosquivel.com.infinitescrollapp.Activities.WebViewActivity;
import frosquivel.com.infinitescrollapp.R;


/**
 * Created by Fabian on 10/06/2017.
 */

public class Utils {

    //initial the shared preference
    public static void setInitialSharedPreference(Context context, boolean isDefaultValues){

        if(getSharedPreference(context, Const.C_MINIMUM_NUMBER_ROW_SHOW).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_MINIMUM_NUMBER_ROW_SHOW, context.getString(R.string.shared_preference_minimun_number_row_loading));

        if(getSharedPreference(context, Const.C_CURRENT_PAGE).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_CURRENT_PAGE, context.getString(R.string.shared_preference_current_page));

        if(getSharedPreference(context, Const.C_MAX_LIMIT).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_MAX_LIMIT, context.getString(R.string.shared_preference_max_limit));

        if(getSharedPreference(context, Const.C_P_NAME).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_NAME, "");

        if(getSharedPreference(context, Const.C_P_REGION).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_REGION, "");

        if(getSharedPreference(context, Const.C_P_SUB_REGION).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_SUB_REGION, "");

        if(getSharedPreference(context, Const.C_P_ALPHA_CODE_2).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_ALPHA_CODE_2, "");

        if(getSharedPreference(context, Const.C_P_ALPHA_CODE_3).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_ALPHA_CODE_3, "");

        if(getSharedPreference(context, Const.C_P_NATIVE_LANGUAGE).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_NATIVE_LANGUAGE, "");

        if(getSharedPreference(context, Const.C_P_CURRENCY_CODE).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_CURRENCY_CODE, "");

        if(getSharedPreference(context, Const.C_P_AREA_FROM).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_AREA_FROM, "");

        if(getSharedPreference(context, Const.C_P_AREA_TO).equals("") || isDefaultValues)
            setSharedPreference(context, Const.C_P_AREA_TO, "");

    }


    //#Shared preference methods
    public static void setSharedPreference(Context context, String preferenceName, String preferenceValue){
        SharedPreferences.Editor editor = context.getSharedPreferences(Const.C_SHARED_PREFERENCES, context.MODE_PRIVATE).edit();
        editor.putString(preferenceName, preferenceValue);
        editor.commit();
    }

    public static String getSharedPreference(Context context, String preferenceName){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences(Const.C_SHARED_PREFERENCES, context.MODE_PRIVATE);
        return pref.getString(preferenceName, "");
    }

    /**
     * open a link into webView
     * @param url
     * @param activity
     */
    public static void goToWebView(String url, Activity activity){
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("link",url);
        activity.startActivity(intent);
    }

    public static void sendMeAnEmail(Activity activity){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"fabian7593@gmail.com"});
       // email.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.email_subject));
        email.putExtra(Intent.EXTRA_TEXT, "");
        email.setType("message/rfc822");
        //activity.startActivity(Intent.createChooser(email, activity.getString(R.string.email_choose)));
    }

    public static void sharedApp(Activity activity) {
        Intent shareIntent = new Intent();
        String textEmail = activity.getString(R.string.email_text);
        textEmail = textEmail.replace("XXXX1", activity.getString(R.string.link_git));
        textEmail = textEmail.replace("XXXX2", activity.getString(R.string.link_play_store));
        textEmail = textEmail.replace("XXXX3", activity.getString(R.string.link_play_store_payment));

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textEmail);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.email_subject));
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(shareIntent, activity.getString(R.string.email_title)));
    }



    //validate if network is available
    public static boolean isNetworkAvailable(Activity context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        boolean toReturn = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        showSneakerDialog(context, context.getString(R.string.error_not_have_networking));

        return toReturn;
    }



    public static void showSneakerDialog(Activity activity, String errorMessage){
        Sneaker.with(activity)
                .setTitle(activity.getString(R.string.error_title))
                .setMessage(errorMessage)
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .sneakError();
    }
}
