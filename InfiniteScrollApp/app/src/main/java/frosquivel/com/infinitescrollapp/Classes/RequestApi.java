package frosquivel.com.infinitescrollapp.Classes;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import frosquivel.com.infinitescroll.Interface.InfiniteScrollInterface;
import frosquivel.com.infinitescrollapp.Models.ResponseModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Fabian on 02/06/2017.
 */

public class RequestApi {
    public static void callCountryAPI(String url, final Activity activity, final InfiniteScrollInterface infiniteScrollInterface){

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(final Call call, IOException e) {

                            final String stackTrace = e.toString();
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    infiniteScrollInterface.onFailure(stackTrace);
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            String res = response.body().string();
                            Gson gson = new GsonBuilder().disableHtmlEscaping().create();

                            if(response.code() != 200){
                                infiniteScrollInterface.onFailure("Message: " + response.message() + "Code: " + response.code());
                            }else{
                                ResponseModel responseModel = gson.fromJson(res, ResponseModel.class);
                                infiniteScrollInterface.onSuccess(responseModel);
                            }
                        }
                    });
    }
}
