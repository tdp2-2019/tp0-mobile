package bookazon.tdpii.fiuba.com.bookazon.services.books;

import android.util.Log;

import java.util.ArrayList;

import bookazon.tdpii.fiuba.com.bookazon.services.ApiClient;
import bookazon.tdpii.fiuba.com.bookazon.services.CoreAPI;
import bookazon.tdpii.fiuba.com.bookazon.services.responses.BookResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookService {

    private CoreAPI coreAPI;

    public BookService() {
        coreAPI = ApiClient.getInstance().getCoreAPI();
    }

    public Call<ArrayList<BookResponse>> getBooks(String searchQuery) {
        return coreAPI.getBooks(searchQuery);
    }

    public Call<BookResponse> getBookById(String id){
        return coreAPI.getBookById(id);
    }

    public void getBooks(String searchQuery, final BookClient delegate){
        coreAPI.getBooks(searchQuery).enqueue(new Callback<ArrayList<BookResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<BookResponse>> call, Response<ArrayList<BookResponse>> response) {
                if (response.code() > 199 && response.code() < 300) {
                    if(response.body() != null) {
                        Log.i("BOOKSERVICE", response.body().toString());
                        delegate.onResponseSuccess(response.body());
                    }else {
                        Log.i("BOOKSERVICE", "NO RESPONSE");
                        delegate.onResponseError();
                    }
                } else {
                    if(response.body() != null) {
                        Log.e("BOOKSERVICE", response.body().toString());
                    }else {
                        Log.e("BOOKSERVICE", "NO RESPONSE");
                    }
                    delegate.onResponseError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BookResponse>> call, Throwable t) {
                delegate.onResponseError();
                Log.e("BOOKSERVICE", t.getMessage());
            }
        });

    }

}
