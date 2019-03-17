package bookazon.tdpii.fiuba.com.bookazon.services.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse {

    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("authors")
    public List<String> authors;

    @SerializedName("categories")
    public List<String> categories;

    @SerializedName("labels")
    public List<String> labels;

    @SerializedName("description")
    public String description;

    @SerializedName("imageLinks")
    public ImageLinksResponse imageLinks;

    @SerializedName("epub")
    public EpubResponse epub;

    @SerializedName("pdf")
    public PdfResponse pdf;

}
