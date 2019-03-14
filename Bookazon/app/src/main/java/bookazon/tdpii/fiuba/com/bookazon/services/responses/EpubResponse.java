package bookazon.tdpii.fiuba.com.bookazon.services.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EpubResponse {


    @SerializedName("isAvailable")
    public Boolean isAvailable;

    @SerializedName("acsTokenLink")
    public String tokenLinkURL;


}
