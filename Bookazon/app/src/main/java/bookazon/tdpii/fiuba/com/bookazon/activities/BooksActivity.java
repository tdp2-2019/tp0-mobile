package bookazon.tdpii.fiuba.com.bookazon.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import bookazon.tdpii.fiuba.com.bookazon.R;
import bookazon.tdpii.fiuba.com.bookazon.model.Book;
import bookazon.tdpii.fiuba.com.bookazon.services.books.BookClient;
import bookazon.tdpii.fiuba.com.bookazon.services.books.BookService;
import bookazon.tdpii.fiuba.com.bookazon.services.responses.BookResponse;
import bookazon.tdpii.fiuba.com.bookazon.view.BookAdapter;

public class BooksActivity extends AppCompatActivity implements BookClient {

    public static final String SEARCH_QUERY_KEY = "search_key";

    private ArrayList<Book> bookArray;
    private BookService bookService;
    private String searchQuery;

    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_ADDED_CONTACT = 400;

    public BooksActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        bookService = new BookService(getApplicationContext());

        setupInitials();


    }

    private void setupInitials() {
        Intent intent = getIntent();
        searchQuery = intent.getStringExtra(SEARCH_QUERY_KEY);
        bookArray = new ArrayList<Book>();
        bookService.getBooks(searchQuery, this);

    }


    private void displayBooks() {
        final ListView bookList = (ListView) findViewById(R.id.list_of_books);
        BookAdapter bookAdapter = new BookAdapter(BooksActivity.this, bookArray);
        bookList.setAdapter(bookAdapter);
        bookList.setSelection(this.bookArray.size());


        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                final Book book = bookArray.get(position);

                if (book.pdf && book.downloadingLinkPDF != null) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.downloadingLinkPDF));
                    startActivity(browserIntent);
                } else if (book.epub && book.downloadingLinkEPUB != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.downloadingLinkEPUB));
                    startActivity(browserIntent);
                }
            }
        });

        if (!this.bookArray.isEmpty()) {
            bookList.setSelection(0);
        }
    }

    private void displayEmptyResults() {
        final ImageView emptyResultView = (ImageView) findViewById(R.id.empty_results);

        emptyResultView.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE_ADDED_CONTACT) {
                ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
                loadingView.setVisibility(View.VISIBLE);
                bookService.getBooks(searchQuery, this);
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponseSuccess(Object responseBody) {
        bookArray.clear();
        ArrayList<BookResponse> bookResponseArrayList = (ArrayList<BookResponse>) responseBody;

        for (BookResponse bookResponse : bookResponseArrayList) {

            Book book = new Book();
            book.authors = bookResponse.authors != null ? bookResponse.authors : Arrays.asList("No authors");
            book.categories = bookResponse.categories != null ? bookResponse.categories : Arrays.asList("");
            book.coverLink = bookResponse.imageLinks != null ?
                    bookResponse.imageLinks.thumbnail != null ?
                            bookResponse.imageLinks.thumbnail :
                            bookResponse.imageLinks.smallThumbnail :
                    null;
            book.description = bookResponse.description != null ? bookResponse.description : "No description";
            book.id = bookResponse.id;
            book.downloadingLinkEPUB = bookResponse.epub.tokenLinkURL;
            book.downloadingLinkPDF = bookResponse.pdf.tokenLinkURL;
            book.labels = bookResponse.labels;
            book.title = bookResponse.title != null ? bookResponse.title : "No Title";
            book.epub = bookResponse.epub.isAvailable;
            book.pdf = bookResponse.pdf.isAvailable;

            bookArray.add(book);
        }

        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);

        if (bookArray.isEmpty()) {
            displayEmptyResults();
        } else {
            displayBooks();
        }
    }

    public void onResponseError() {
        Toast.makeText(this, "Se produjo un error de conexión, intente luego",
                Toast.LENGTH_LONG).show();
        ProgressBar loadingView = (ProgressBar) findViewById(R.id.loading);
        loadingView.setVisibility(View.INVISIBLE);
        finish();

    }
}
