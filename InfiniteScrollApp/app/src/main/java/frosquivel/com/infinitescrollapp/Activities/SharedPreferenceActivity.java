package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.webkit.WebView;

import com.appyvet.rangebar.RangeBar;
import com.github.glomadrian.loadingballs.BallView;
import com.rey.material.widget.EditText;
import com.rey.material.widget.Slider;
import com.travijuu.numberpicker.library.NumberPicker;

import frosquivel.com.infinitescrollapp.Activities.Base.BaseDetailActivity;
import frosquivel.com.infinitescrollapp.Classes.Const;
import frosquivel.com.infinitescrollapp.Classes.Utils;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 09/07/2017.
 */

public class SharedPreferenceActivity extends BaseDetailActivity {

    private Activity activity;
    private CardView cardViewRequest;
    private CardView cardViewRegionFilter;
    private CardView cardViewIndividualFilter;
    private CardView cardViewSize;
    private EditText txtRegion;
    private EditText txtSubRegion;
    private EditText txtAlphaCode2;
    private EditText txtAlphaCode3;
    private EditText txtCurrencyName;
    private Slider sliderMinimumRowNumber;
    private Slider sliderMaxLimitRequest;
    private NumberPicker numberPickerCurrentPage;
    private RangeBar rangebarSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = addLayout(R.layout.activity_shared_preference);

     /*   cardViewRequest = (CardView) activity.findViewById(R.id.cardViewRequest);
        cardViewRegionFilter = (CardView) activity.findViewById(R.id.cardViewRegionFilter);
        cardViewIndividualFilter = (CardView) activity.findViewById(R.id.cardViewIndividualFilter);
        cardViewSize = (CardView) activity.findViewById(R.id.cardViewSize);

        txtRegion = (EditText) activity.findViewById(R.id.txtRegion);
        txtSubRegion = (EditText) activity.findViewById(R.id.txtSubRegion);
        txtAlphaCode2 = (EditText) activity.findViewById(R.id.txtAlphaCode2);
        txtAlphaCode3 = (EditText) activity.findViewById(R.id.txtAlphaCode3);
        txtCurrencyName = (EditText) activity.findViewById(R.id.txtCurrencyName);

        sliderMinimumRowNumber = (Slider) activity.findViewById(R.id.sliderMinimumRowNumber);
        sliderMaxLimitRequest = (Slider) activity.findViewById(R.id.sliderMaxLimitRequest);
        numberPickerCurrentPage = (NumberPicker) activity.findViewById(R.id.numberPickerCurrentPage);
        rangebarSize = (RangeBar) activity.findViewById(R.id.rangebarSize);*/

       /* String region = Utils.getSharedPreference(activity, Const.C_P_REGION);
        txtRegion.setText(region);

        String subRegion = Utils.getSharedPreference(activity, Const.C_P_SUB_REGION);
        txtSubRegion.setText(subRegion);

        String alphaCode2 = Utils.getSharedPreference(activity, Const.C_P_ALPHA_CODE_2);
        txtAlphaCode2.setText(alphaCode2);

        String alphaCode3 = Utils.getSharedPreference(activity, Const.C_P_ALPHA_CODE_3);
        txtAlphaCode3.setText(alphaCode3);

        String currencyName = Utils.getSharedPreference(activity, Const.C_P_CURRENCY_CODE);
        txtCurrencyName.setText(currencyName);

        String minimumRowNumber = Utils.getSharedPreference(activity, Const.C_MINIMUM_NUMBER_ROW_SHOW);
        sliderMinimumRowNumber.setValue(Float.parseFloat(minimumRowNumber),true);

        String maxLimitNumber = Utils.getSharedPreference(activity, Const.C_MAX_LIMIT);
        sliderMaxLimitRequest.setValue(Float.parseFloat(maxLimitNumber),true);

        String currentPage = Utils.getSharedPreference(activity, Const.C_CURRENT_PAGE);
        numberPickerCurrentPage.setValue(Integer.parseInt(currentPage));

        String areaRangeFrom = Utils.getSharedPreference(activity, Const.C_P_AREA_FROM);
        String areaRangeTo = Utils.getSharedPreference(activity, Const.C_P_AREA_TO);
        rangebarSize.setRangePinsByValue(Float.parseFloat(areaRangeFrom), Float.parseFloat(areaRangeTo));*/

    }
}
