package com.codekun.androidfirstcode.ch2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * Created by kun on 2015/11/16.
 */
public class Book implements Parcelable {

    private String name;
    private Person author;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String toString(){
        return "Book->[name:"+name+", author: "+author.toString()+"]";
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //写入与读取顺序需要一致
        dest.writeString(name);
        dest.writeSerializable(author);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            Book book  = new Book();
            //写入与读取顺序需要一致
            book.name = source.readString();
            book.author = (Person)source.readSerializable();
            return book;
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}
