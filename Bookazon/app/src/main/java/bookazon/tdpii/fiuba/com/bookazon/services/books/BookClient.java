package bookazon.tdpii.fiuba.com.bookazon.services.books;

import android.content.Context;

public interface BookClient<T> {

    public abstract void onResponseSuccess(T responseBody);

    public abstract void onResponseError();

    public abstract Context getApplicationContext();
}
