package com.theleafapps.pro.udacitybooklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = BookListActivity.class.getName();

    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LIST_LOADER_ID = 1;
    /**
     * URL for a USGS JSON Response
     */
    private static final String GOOGLE_BOOK_LIST_API_URL = "https://www.googleapis.com/books/v1/volumes";

    /**
     * Variable checking if the internet is connected or not
     */
    boolean isConnected;

    /**
     * Adapter providing data to the book listview
     */
    BookListAdapter bookListAdapter;

    /**
     * String variable to store book search query entered by the user
     */
    String searchQuery;

    /**
     * EditText view on the book list screen where user enters search keyword for books
     */
    TextView search_book_edit_text;

    /**
     * Button on the book list screen where user enters search keyword for books
     * and press this button
     */
    Button search_book_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);


        //Get the system service Connectivity Service
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Get the active information of the network
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        //Check for the current network state
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        //Get the LoaderManager object
        LoaderManager loaderManager = getLoaderManager();

        //Initialize the loaderManager Object with its unique ID
        loaderManager.initLoader(BOOK_LIST_LOADER_ID, null, this);

        final List<Book> books = new ArrayList<>();

        //Find reference to the book query edittext where user can enter search keyword for books
        search_book_edit_text = (TextView) findViewById(R.id.search_book_edit_text);

        //Find reference to the book query button for searching book based on search query
        search_book_button = findViewById(R.id.search_book_button);

        search_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchQuery)) {
                    Toast.makeText(BookListActivity.this,
                            "Please ", Toast.LENGTH_SHORT).show();
                    search_book_edit_text.setError("Please enter something");
                } else {
                    searchQuery = search_book_edit_text.getText().toString();
                }
            }
        });

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.book_list_listview);

        // Create a new {@link ArrayAdapter} of earthquakes
        bookListAdapter = new BookListAdapter(this, books);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(bookListAdapter);


    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(GOOGLE_BOOK_LIST_API_URL);

        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("q", searchQuery);
        uriBuilder.appendQueryParameter("maxResults", "5");

        // Create a new loader for the given URL
        return new BookListLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
