package infinitescroll.frosquivel.com.infinitescroll.Library;

import android.mtp.MtpObjectInfo;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Fabian on 31/05/2017.
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
