package bookazon.tdpii.fiuba.com.bookazon.services;

import java.util.ArrayList;

import bookazon.tdpii.fiuba.com.bookazon.services.responses.BookResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoreAPI {


    @GET("/v1/books")
    Call<ArrayList<BookResponse>> getBooks(@Query("search") String search);

    @GET("/v1/books/{id}")
    Call<BookResponse> getBookById(@Path("id") String id);

}