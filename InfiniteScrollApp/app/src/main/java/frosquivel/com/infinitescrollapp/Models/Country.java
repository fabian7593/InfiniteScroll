package frosquivel.com.infinitescrollapp.Models;

import java.io.Serializable;

/**
 * Created by Fabian on 02/06/2017.
 * The model of country like response of web service,
 * has all attributes
 */

public class Country implements Serializable{

    private String Name;
    private String Alpha2Code;
    private String Alpha3Code;
    private String NativeName;
    private String Region;
    private String SubRegion;
    private String Latitude;
    private String Longitude;
    private long Area;
    private int NumericCode;
    private String NativeLanguage;
    private String CurrencyCode;
    private String CurrencyName;
    private String CurrencySymbol;
    private String Flag;
    private String FlagPng;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlpha2Code() {
        return Alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        Alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return Alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        Alpha3Code = alpha3Code;
    }

    public String getNativeName() {
        return NativeName;
    }

    public void setNativeName(String nativeName) {
        NativeName = nativeName;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getSubRegion() {
        return SubRegion;
    }

    public void setSubRegion(String subRegion) {
        SubRegion = subRegion;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public long getArea() {
        return Area;
    }

    public void setArea(long area) {
        Area = area;
    }

    public int getNumericCode() {
        return NumericCode;
    }

    public void setNumericCode(int numericCode) {
        NumericCode = numericCode;
    }

    public String getNativeLanguage() {
        return NativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        NativeLanguage = nativeLanguage;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return CurrencyName;
    }

    public void setCurrencyName(String currencyName) {
        CurrencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return CurrencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        CurrencySymbol = currencySymbol;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String flag) {
        Flag = flag;
    }

    public String getFlagPng() {
        return FlagPng;
    }

    public void setFlagPng(String flagPng) {
        FlagPng = flagPng;
    }

}
