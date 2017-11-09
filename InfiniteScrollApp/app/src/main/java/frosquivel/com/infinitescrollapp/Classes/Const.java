package frosquivel.com.infinitescrollapp.Classes;

/**
 * Created by Fabian on 03/06/2017.
 * A class with have all const that I need in all app
 */

public class Const {
    public static final String C_IS_FIRST_TIME = "C_IS_FIRST_TIME";

    //The url request of country api, for call the list of countries
    //If you like this go to github , fork and wive me a star
    //https://github.com/fabian7593/CountryAPI
    public static final String C_URL_REQUEST_COUNTRYAPI =
            "http://countryapi.gear.host/v1/Country/getCountries?" + "" +
            "pLimit=%s&pPage=%s&pName=%s"+
            "&pAlpha2Code=%s&pAlpha3Code=%s" +
            "&pAreaFrom=%s&pAreaTo=%s" +
            "&pRegion=%s&pSubRegion=%s";

    public static final String C_FONT_PATH = "font/NEOTERICBold.ttf";
    public static final String C_EMPTY_STRING = "";

    //shared preference
    public static final String C_SHARED_PREFERENCES = "SCROLL_INFINITE_PREFERENCES";
    public static final String C_MINIMUM_NUMBER_ROW_SHOW = "C_MINIMUM_NUMBER_ROW_SHOW";
    public static final String C_CURRENT_PAGE = "C_CURRENT_PAGE";
    public static final String C_MAX_LIMIT = "C_MAX_LIMIT";

    public static final String C_P_NAME = "C_P_NAME";
    public static final String C_P_REGION = "C_P_REGION";
    public static final String C_P_SUB_REGION = "C_P_SUB_REGION";
    public static final String C_P_ALPHA_CODE_2 = "C_P_ALPHA_CODE_2";
    public static final String C_P_ALPHA_CODE_3 = "C_P_ALPHA_CODE_3";
    public static final String C_P_NATIVE_LANGUAGE = "C_P_NATIVE_LANGUAGE";
    public static final String C_P_CURRENCY_CODE = "C_P_CURRENCY_CODE";
    public static final String C_P_AREA_FROM = "C_P_AREA_FROM";
    public static final String C_P_AREA_TO = "C_P_AREA_TO";

    public static final String C_VALUE_HASHMAP = "value";
    public static final String C_TITLE_HASHMAP = "title";
}
