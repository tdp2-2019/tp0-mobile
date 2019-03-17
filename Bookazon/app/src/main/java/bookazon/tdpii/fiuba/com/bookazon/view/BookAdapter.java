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
import android.widget.AdapterView;
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

    private Context context;

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

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
        this.context = context;
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
        Picasso.get().load(book.coverLink).into(imageView);


        TextView authorView = (TextView) convertView.findViewById(R.id.author);
        authorView.setText(book.getAuthors());

        ImageView category1View = (ImageView) convertView.findViewById(R.id.category1);
        category1View.setImageResource(getDrawableCategoryOrDefault(!book.categories.isEmpty() ? book.categories.get(0) : null));

        ImageView category2View = (ImageView) convertView.findViewById(R.id.category2);
        if(book.categories.size() > 1){
            category2View.setImageResource(getDrawableCategoryOrDefault(book.categories.get(1)));
        }



        TextView labelsView = (TextView) convertView.findViewById(R.id.labels);
        labelsView.setText(book.getLabels());

        TextView descriptionView = (TextView) convertView.findViewById(R.id.description);

        descriptionView.setText(book.description);

        final ImageView downloadImageViewEPUB = (ImageView) convertView.findViewById(R.id.downloadEPUB);
        if(!book.epub){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            downloadImageViewEPUB.setColorFilter(filter);
        } else {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(10);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            downloadImageViewEPUB.setColorFilter(filter);
            if(!book.downloadingLinkEPUB.equals("LINK_HOLDER")) {
                downloadImageViewEPUB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!book.downloadingLinkEPUB.equals("LINK_HOLDER")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.downloadingLinkEPUB));
                            downloadImageViewEPUB.getContext().startActivity(browserIntent);
                        }
                    }
                });
            }
        }


        final ImageView downloadImageViewPDF = (ImageView) convertView.findViewById(R.id.downloadPDF);
        if(!book.pdf){
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            downloadImageViewPDF.setColorFilter(filter);
        } else {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(10);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            downloadImageViewPDF.setColorFilter(filter);
            if(!book.downloadingLinkPDF.equals("LINK_HOLDER")) {
                downloadImageViewPDF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!book.downloadingLinkPDF.equals("LINK_HOLDER")) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.downloadingLinkPDF));
                            downloadImageViewPDF.getContext().startActivity(browserIntent);
                        }
                    }
                });

            }
        }





        // Lookup view for data population

        // Return the completed view to render on screen
        return convertView;
    }

}

