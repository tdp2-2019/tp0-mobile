package bookazon.tdpii.fiuba.com.bookazon.services.responses;
import com.google.gson.annotations.SerializedName;

public class ImageLinksResponse {

    @SerializedName("smallThumbnail")
    public String smallThumbnail;

    @SerializedName("thumbnail")
    public String thumbnail;
}
