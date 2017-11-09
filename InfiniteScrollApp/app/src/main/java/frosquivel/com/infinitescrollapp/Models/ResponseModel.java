package frosquivel.com.infinitescrollapp.Models;

import java.util.List;

/**
 * Created by Fabian on 02/06/2017.
 * The response model of rest service,
 * have the necessary attributes and a list of countries, like the response of rest api service
 */

public class ResponseModel {
    private int IsSucess;
    private String UserMessage;
    private String TechnicalMessage;
    private int TotalCount;
    private List<Country> Response;

    public int getIsSucessfull() {
        return IsSucess;
    }

    public String getUserMessage() {
        return UserMessage;
    }

    public String getTechnicalMessage() {
        return TechnicalMessage;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public List<Country> getResponse() {
        return Response;
    }
}
