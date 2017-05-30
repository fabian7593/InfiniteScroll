package infinitescroll.frosquivel.com.infinitescroll.App.Utilities;

import java.util.List;

/**
 * Created by Fabian on 26/05/2017.
 */

public class ResponseModel {

    private int isSucessfull;
    private String userMessage;
    private String technicalMessage;
    private int totalCount;
    private List<Country> response;

    public int getIsSucessfull() {
        return isSucessfull;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<Country> getResponse() {
        return response;
    }
}
