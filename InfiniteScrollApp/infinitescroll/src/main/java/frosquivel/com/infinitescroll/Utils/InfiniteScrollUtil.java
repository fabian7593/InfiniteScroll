package frosquivel.com.infinitescroll.Utils;

import android.widget.Adapter;

import java.util.HashMap;

/**
 * Created by Fabian on 02/06/2017.
 */

public class InfiniteScrollUtil {
    public static final String C_ADAPTER_RETURN = "adapterObject";
    public static final String C_RESPONSE_SIZE_RETURN = "responseSizeObject";

    public static Object returnObject(Adapter adapter, int responseIntSize){
        HashMap<String, Object> mapStructure = new HashMap<String, Object>();
        mapStructure.put(C_ADAPTER_RETURN, adapter);
        mapStructure.put(C_RESPONSE_SIZE_RETURN, responseIntSize);
        return mapStructure;
    }
}
