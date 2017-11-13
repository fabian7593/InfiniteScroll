package github.frosquivel.infinitescrollapp.Classes;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;


import github.frosquivel.infinitescroll.Interface.InfiniteScrollInterface;
import github.frosquivel.infinitescrollapp.Models.ResponseModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Fabian on 02/06/2017.
 * The class of build the request of the country api
 */

public class RequestApi {
    //set url interface and activity
    public static void callCountryAPI(String url, final Activity activity, final InfiniteScrollInterface infiniteScrollInterface){

            //instance the request and ok http client
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            //do the request
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(final Call call, IOException e) {
                            //if the call is failure, call the method of interface onFailure
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

                            //if the code is different to 200 ok http, call the onFailure interface method
                            if(response.code() != 200){
                                infiniteScrollInterface.onFailure("Message: " + response.message() + "Code: " + response.code());
                            }else{
                                //set the tring of response into gson object, to ResponseModel
                                ResponseModel responseModel = gson.fromJson(res, ResponseModel.class);

                                //call the onSuccessInterface
                                infiniteScrollInterface.onSuccess(responseModel);
                            }
                        }
                    });
    }
}
