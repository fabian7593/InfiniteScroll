package infinitescroll.frosquivel.com.infinitescroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.List;

import infinitescroll.frosquivel.com.infinitescroll.App.CountryAdapter;
import infinitescroll.frosquivel.com.infinitescroll.App.Utilities.Utils;
import infinitescroll.frosquivel.com.infinitescroll.Library.Adapter.InfiniteScrollAdapter;
import infinitescroll.frosquivel.com.infinitescroll.Library.InfiniteScrollCallRequest;
import infinitescroll.frosquivel.com.infinitescroll.Library.InfiniteScrollImpl;
import infinitescroll.frosquivel.com.infinitescroll.Library.InfiniteScrollInterface;
import infinitescroll.frosquivel.com.infinitescroll.Library.Model.InfiniteScrollObject;
import infinitescroll.frosquivel.com.infinitescroll.App.Utilities.Country;
import infinitescroll.frosquivel.com.infinitescroll.App.Utilities.ResponseModel;

public class MainActivity extends AppCompatActivity {

    private InfiniteScrollAdapter adapter;
    private List<Object> objectList;
    private final int numberOfRequest = 10;
    private ProgressBar progressBar;
    private ListView lvItems;
    private static ResponseModel responseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the relation of your layout and your progress bar
        View footer = getLayoutInflater().inflate(R.layout.progress_bar, null);
        progressBar = (ProgressBar) footer.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        lvItems = (ListView) findViewById(R.id.listView);
        lvItems.addFooterView(footer);

        InfiniteScrollObject infiniteScrollObject = new InfiniteScrollObject();
        infiniteScrollObject.setCurrentPage(-1);
        infiniteScrollObject.setMinimunNumberRowLoadingMore(3);

        lvItems.setOnScrollListener(new InfiniteScrollCallRequest(infiniteScrollObject) {
            @Override
            public boolean onLoadMoreData(int page, int totalItemsCount, ListView view) {
                return ((resquestAPI(page).size() != 0)?  true :  false);
            }
        });

        resquestAPI(-1);
    }

    public List<Country> resquestAPI(final int offset) {

        InfiniteScrollInterface interfaceInfinite = new InfiniteScrollImpl() {
            @Override
            public void onSuccess(Object responseModel) {
                MainActivity.responseModel = (ResponseModel) responseModel;


                if(adapter != null){
                    //if you need update the list view and your respective data
                    for(Country country : ((ResponseModel) responseModel).getResponse()){
                        objectList.add(country);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    //if not have adapter, init this and set adapter in listview
                    objectList = new ArrayList<Object>(((ResponseModel) responseModel).getResponse());
                    adapter = new CountryAdapter(MainActivity.this, objectList ,R.layout.activity_row_item_list);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lvItems.setAdapter(adapter);
                        }
                    });
                }
            }
        };

        Utils.callCountryAPI(
                "http://countryapi.gear.host/v1/Country/getCountries?pLimit=25&pPage="+
                        String.valueOf(offset),
                this, interfaceInfinite);

        return responseModel.getResponse();

    }
}
