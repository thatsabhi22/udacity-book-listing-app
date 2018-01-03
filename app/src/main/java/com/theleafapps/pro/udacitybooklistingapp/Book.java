package com.theleafapps.pro.udacitybooklistingapp;

/**
 * Created by aviator on 03/01/18.
 */

/**
 * An {@link Book} object contains information related to a single book.
 */
public class Book {

    /**
     * Title of the book
     */
    private String book_title;

    /**
     * Price listed for the book
     */
    private double list_price;

    /**
     * Actual retail price for the book
     */
    private double retail_price;

    /**
     * List of Authors for a book, authors can be more than one
     */
    private AuthorList authorList;

    /**
     * Url for thumbnail image of the book
     */
    private String thumbnailUrl;


    /**
     * Constructs a new {@link Book} object.
     *
     * @param book_title   title of the book
     * @param list_price   list price of the book
     * @param retail_price retail price od the book
     * @param authorList   List of authors of the book, authors of a book can be multiple
     * @param
     */
    public Book(String book_title, double list_price, double retail_price, AuthorList authorList, String thumbnailUrl) {
        this.book_title = book_title;
        this.list_price = list_price;
        this.retail_price = retail_price;
        this.authorList = authorList;
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * Returns the title of the book
     *
     * @return book_title
     */
    public String getBook_title() {
        return book_title;
    }

    /**
     * Returns the list_price of the book
     *
     * @return list_price
     */
    public double getList_price() {
        return list_price;
    }

    /**
     * Returns the retail_price of the book
     *
     * @return retail_price
     */
    public double getRetail_price() {
        return retail_price;
    }

    /**
     * Returns the list of authors in the AuthorList object of the book
     *
     * @return list_price
     */
    public AuthorList getAuthorList() {
        return authorList;
    }

    /**
     * Returns the thumbnail Image Url of the book
     *
     * @return thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
