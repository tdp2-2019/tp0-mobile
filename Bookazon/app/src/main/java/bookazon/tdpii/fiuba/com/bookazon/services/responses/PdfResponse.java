package bookazon.tdpii.fiuba.com.bookazon.services.responses;

import com.google.gson.annotations.SerializedName;

public class PdfResponse {

    @SerializedName("isAvailable")
    public Boolean isAvailable;

    @SerializedName("acsTokenLink")
    public String tokenLinkURL;
    

}
