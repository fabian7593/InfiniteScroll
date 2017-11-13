package github.frosquivel.infinitescrollapp.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Slider;
import com.travijuu.numberpicker.library.NumberPicker;

import java.util.Arrays;
import java.util.List;

import github.frosquivel.infinitescrollapp.Activities.Base.BaseDetailActivity;
import github.frosquivel.infinitescrollapp.Classes.Const;
import github.frosquivel.infinitescrollapp.Classes.Utils;
import github.frosquivel.infinitescrollapp.R;

/**
 * Created by Fabian on 09/07/2017.
 * The activity of filter preferences, if the user click back button save all changes
 * For show the new responses with the new filters for web service
 */
public class SharedPreferenceActivity extends BaseDetailActivity {
    private Activity activity;
    private EditText txtRegion;
    private EditText txtSubRegion;
    private EditText txtAlphaCode2;
    private EditText txtAlphaCode3;
    private EditText txtName;

    private NumberPicker numberMinimumRowNumber;
    private Slider sliderMaxLimitRequest;
    private Slider sliderMinArea;
    private Slider sliderMaxArea;
    private NumberPicker numberPickerCurrentPage;
    private List<String> subRegions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = addLayout(R.layout.activity_shared_preference);
        setUIComponents();
        getValuesSharedPreference();
        setListeners();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void setUIComponents(){

        txtRegion = (EditText) activity.findViewById(R.id.txtRegion);
        txtSubRegion = (EditText) activity.findViewById(R.id.txtSubRegion);
        txtAlphaCode2 = (EditText) activity.findViewById(R.id.txtAlphaCode2);
        txtAlphaCode3 = (EditText) activity.findViewById(R.id.txtAlphaCode3);
        txtName = (EditText) activity.findViewById(R.id.txtName);

        numberMinimumRowNumber = (NumberPicker) activity.findViewById(R.id.numberMinimumRowNumber);
        numberPickerCurrentPage = (NumberPicker) activity.findViewById(R.id.numberPickerCurrentPage);

        sliderMaxLimitRequest = (Slider) activity.findViewById(R.id.sliderMaxLimitRequest);
        sliderMinArea = (Slider) activity.findViewById(R.id.sliderMinArea);
        sliderMaxArea = (Slider) activity.findViewById(R.id.sliderMaxArea);

        numberMinimumRowNumber.setMax(15);
        numberPickerCurrentPage.setMax(30);
        numberMinimumRowNumber.setMin(1);
        numberPickerCurrentPage.setMin(1);
    }

    private void getValuesSharedPreference(){
        String region = Utils.getSharedPreference(activity, Const.C_P_REGION);
        txtRegion.setText(region);

        String subRegion = Utils.getSharedPreference(activity, Const.C_P_SUB_REGION);
        txtSubRegion.setText(subRegion);

        subRegionByRegion(region);

        String alphaCode2 = Utils.getSharedPreference(activity, Const.C_P_ALPHA_CODE_2);
        txtAlphaCode2.setText(alphaCode2);

        String name = Utils.getSharedPreference(activity, Const.C_P_NAME);
        txtName.setText(name);

        String alphaCode3 = Utils.getSharedPreference(activity, Const.C_P_ALPHA_CODE_3);
        txtAlphaCode3.setText(alphaCode3);

        String maxLimitNumber = Utils.getSharedPreference(activity, Const.C_MAX_LIMIT);
        sliderMaxLimitRequest.setValue(Float.parseFloat(maxLimitNumber),true);

        String currentPage = Utils.getSharedPreference(activity, Const.C_CURRENT_PAGE);
        numberPickerCurrentPage.setValue(Integer.parseInt(currentPage));

        String areaRangeFrom = Utils.getSharedPreference(activity, Const.C_P_AREA_FROM);
        String areaRangeTo = Utils.getSharedPreference(activity, Const.C_P_AREA_TO);
        sliderMinArea.setValue(Float.parseFloat(areaRangeFrom),true);
        sliderMaxArea.setValue(Float.parseFloat(areaRangeTo),true);

        String minimumRowNumber = Utils.getSharedPreference(activity, Const.C_MINIMUM_NUMBER_ROW_SHOW);
        numberMinimumRowNumber.setValue(Integer.parseInt(minimumRowNumber));

    }

    private void setListeners(){

        txtRegion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    InputMethodManager inputmethodmgr= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmethodmgr.hideSoftInputFromWindow(txtRegion.getWindowToken(), 0);

                    int choise = -1;
                    final List<String> continents = Arrays.asList(getResources().getStringArray(R.array.continents));
                    for(int x=0; x < continents.size(); x++)  {
                        if(continents.get(x).equals(Utils.getSharedPreference(activity, Const.C_P_REGION))){
                            choise = x;
                        }
                    }

                    new MaterialDialog.Builder(activity)
                            .title(R.string.shared_preference_list_region_label)
                            .items(R.array.continents)
                            .itemsCallbackSingleChoice(choise, new MaterialDialog.ListCallbackSingleChoice() {
                                @Override
                                public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    if(which > -1) {
                                        if(!continents.get(which).equals(Utils.getSharedPreference(activity, Const.C_P_REGION))){
                                            txtRegion.setText(continents.get(which));
                                            Utils.setSharedPreference(activity, Const.C_P_REGION, continents.get(which));
                                            subRegionByRegion(continents.get(which));
                                            txtSubRegion.setText("All");
                                            Utils.setSharedPreference(activity, Const.C_P_SUB_REGION, "All");
                                        }
                                    }
                                    return true;
                                }
                            })
                            .positiveText(R.string.general_accept)
                            .show();
                }
            }
        });


        txtSubRegion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    InputMethodManager inputmethodmgr= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmethodmgr.hideSoftInputFromWindow(txtSubRegion.getWindowToken(), 0);

                    if(subRegions != null){
                        int choise = -1;

                        for(int x=0; x < subRegions.size(); x++)  {
                            if(subRegions.get(x).equals(Utils.getSharedPreference(activity, Const.C_P_SUB_REGION))){
                                choise = x;
                            }
                        }

                        new MaterialDialog.Builder(activity)
                                .title(R.string.shared_preference_regions_filter_title)
                                .items(subRegions)
                                .itemsCallbackSingleChoice(choise, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                        if(which > -1) {
                                            txtSubRegion.setText(subRegions.get(which));
                                            Utils.setSharedPreference(activity, Const.C_P_SUB_REGION, subRegions.get(which));
                                        }

                                        return true;
                                    }
                                })
                                .positiveText(R.string.general_accept)
                                .show();
                    }else{
                        txtSubRegion.setText("All");
                    }
                }
            }
        });
    }

    private void subRegionByRegion(String continent){

        switch (continent) {
            case "All":
                subRegions = null;
                txtSubRegion.setText("All");
                break;

            case "Africa":
                subRegions = Arrays.asList(getResources().getStringArray(R.array.africa_list));
                break;

            case "Americas":
                subRegions = Arrays.asList(getResources().getStringArray(R.array.americas_list));
                break;

            case "Asia":
                subRegions = Arrays.asList(getResources().getStringArray(R.array.asia_list));
                break;

            case "Europe":
                subRegions = Arrays.asList(getResources().getStringArray(R.array.europe_list));
                break;

            case "Oceania":
                subRegions = Arrays.asList(getResources().getStringArray(R.array.oceania_list));
                break;

            case "Polar":
                subRegions = null;
                break;

            default:
                break;
        }
    }

    private void saveData(){
        Utils.setSharedPreference(activity, Const.C_P_ALPHA_CODE_2, txtAlphaCode2.getText().toString());
        Utils.setSharedPreference(activity, Const.C_P_ALPHA_CODE_3, txtAlphaCode3.getText().toString());
        Utils.setSharedPreference(activity, Const.C_P_NAME, txtName.getText().toString());
        Utils.setSharedPreference(activity, Const.C_MINIMUM_NUMBER_ROW_SHOW, String.valueOf(numberMinimumRowNumber.getValue()));
        Utils.setSharedPreference(activity, Const.C_CURRENT_PAGE, String.valueOf(numberPickerCurrentPage.getValue()));
        Utils.setSharedPreference(activity, Const.C_MAX_LIMIT, String.valueOf(sliderMaxLimitRequest.getValue()));
        Utils.setSharedPreference(activity, Const.C_P_AREA_FROM, String.valueOf(sliderMinArea.getValue()));
        Utils.setSharedPreference(activity, Const.C_P_AREA_TO, String.valueOf(sliderMaxArea.getValue()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }
}

