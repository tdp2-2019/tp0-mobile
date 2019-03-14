package bookazon.tdpii.fiuba.com.bookazon.view;


import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bookazon.tdpii.fiuba.com.bookazon.R;
import bookazon.tdpii.fiuba.com.bookazon.model.Book;


public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Book book = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_view, parent, false);
        }

        TextView titleView = (TextView) convertView.findViewById(R.id.bookTitle);
        titleView.setText(book.title);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.bookImage);

        Picasso.get().load(book.coverLink).into(imageView);

        // Lookup view for data population

        // Return the completed view to render on screen
        return convertView;
    }



}
