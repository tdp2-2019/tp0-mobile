package bookazon.tdpii.fiuba.com.bookazon.view;


import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bookazon.tdpii.fiuba.com.bookazon.R;
import bookazon.tdpii.fiuba.com.bookazon.model.Book;


public class BookAdapter extends ArrayAdapter<Book> {

    private static Map<String, Integer> imgSrcForCategory;
    int[] books;

    static {

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
    }

    private final Context context;

    private static Integer getDrawableCategoryOrDefault(String category) {
        Integer drawable = imgSrcForCategory.get(category);
        return drawable == null ? R.drawable.default_category : drawable;

    }

    public BookAdapter(Context context, ArrayList<Book> bookArray) {
        super(context, 0, bookArray);
        this.context = context;

        books = new int[bookArray.size()];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Book book = getItem(position);
        
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_view, parent, false);
        }


        TextView titleView = (TextView) convertView.findViewById(R.id.title);
        titleView.setText(book.title);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.thumbnail);
        if(book.coverLink == null){
         imageView.setImageResource(R.drawable.bookflat);
        } else {
            Picasso.get().load(book.coverLink).into(imageView);
        }

        TextView authorView = (TextView) convertView.findViewById(R.id.author);
        authorView.setText(book.getAuthors());

        ImageView category1View = (ImageView) convertView.findViewById(R.id.category1);
        category1View.setImageResource(getDrawableCategoryOrDefault(!book.categories.isEmpty() ? book.categories.get(0) : null));

        ImageView category2View = (ImageView) convertView.findViewById(R.id.category2);
        if (book.categories.size() > 1) {
            category2View.setImageResource(getDrawableCategoryOrDefault(book.categories.get(1)));
        }


        TextView labelsView = (TextView) convertView.findViewById(R.id.labels);
        labelsView.setText(book.getLabels());

        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);
        descriptionView.setText(book.description);

        final ImageView downloadPdfImageView = (ImageView) convertView.findViewById(R.id.downloadPDF);
        downloadPdfImageView.setImageResource(R.drawable.pdf_download);
        if (!book.pdf || book.downloadingLinkPDF == null) {
            downloadPdfImageView.setImageResource(R.drawable.not_pdf_download);
        }


        ImageView downloadEpubImageView = (ImageView) convertView.findViewById(R.id.downloadEPUB);
        downloadEpubImageView.setImageResource(R.drawable.epub_download);
        if (!book.epub || book.downloadingLinkEPUB == null) {
            downloadEpubImageView.setImageResource(R.drawable.not_epub_download);
        }


        return convertView;
    }


}

