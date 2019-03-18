package bookazon.tdpii.fiuba.com.bookazon.activities;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookazon.tdpii.fiuba.com.bookazon.R;
import bookazon.tdpii.fiuba.com.bookazon.model.Book;
import bookazon.tdpii.fiuba.com.bookazon.services.books.BookService;

class BookActivity extends AppCompatActivity {

    public final static String BOOK_KEY = "book_key";

    private Book book;

    public BookActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra(BOOK_KEY);

        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(book.title);

        ImageView imageView = (ImageView) findViewById(R.id.thumbnail);
        Picasso.get().load(book.coverLink).into(imageView);

        TextView authorView = (TextView) findViewById(R.id.author);
        authorView.setText(book.getAuthors());

        ImageView category1View = (ImageView) findViewById(R.id.category1);
        category1View.setImageResource(getDrawableCategoryOrDefault(!book.categories.isEmpty() ? book.categories.get(0) : null));

        ImageView category2View = (ImageView) findViewById(R.id.category2);
        if(book.categories.size() > 1){
            category2View.setImageResource(getDrawableCategoryOrDefault(book.categories.get(1)));
        }

        TextView labelsView = (TextView) findViewById(R.id.labels);
        labelsView.setText(book.getLabels());

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(book.description);

        ImageView downloadImageView = (ImageView) findViewById(R.id.download);
        if(!book.pdf && !book.epub){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            downloadImageView.setColorFilter(filter);
        }
    }

    private static Map<String, Integer > imgSrcForCategory;
    static{

        imgSrcForCategory = new HashMap<>();
        imgSrcForCategory.put("Religion", R.drawable.cat_religion);
        imgSrcForCategory.put("Education", R.drawable.cat_education);
        imgSrcForCategory.put("Computers", R.drawable.cat_computer);
        imgSrcForCategory.put("Fiction", R.drawable.cat_fiction);
        imgSrcForCategory.put("Family & Relationships", R.drawable.cat_family);
        imgSrcForCategory.put("Crime", R.drawable.cat_crime);
        imgSrcForCategory.put("Music", R.drawable.cat_music);
        imgSrcForCategory.put("Juvenile Fiction", R.drawable.cat_adventure);
        imgSrcForCategory.put("Biography & Autobiography", R.drawable.cat_bio);
        imgSrcForCategory.put("Comedy", R.drawable.cat_comedy);
        imgSrcForCategory.put("Horror", R.drawable.cat_horror);
        imgSrcForCategory.put("Love", R.drawable.cat_love);

        imgSrcForCategory.put("Magic", R.drawable.cat_magic);
        imgSrcForCategory.put("War", R.drawable.cat_war);
        imgSrcForCategory.put("Wester", R.drawable.cat_western);
    };

    private static Integer getDrawableCategoryOrDefault(String category){
        Integer drawable = imgSrcForCategory.get(category);
        return drawable == null ? R.drawable.default_category : drawable;

    }
}