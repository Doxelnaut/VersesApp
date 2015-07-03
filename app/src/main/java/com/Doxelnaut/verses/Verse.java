package com.Doxelnaut.verses;

/**
 * Created by Corey on 7/3/2015.
 */
public class Verse {



    int _RowId;
    String _Text;
    String _Version;
    String _Book;
    int _Chapter;
    String _Verse;
    int _FavRank;
    int _Favorited;
    int _Rank;

    Verse(int i){
        _RowId = i;
    }

    public int get_RowId() {
        return _RowId;
    }

    public void set_RowId(int _RowId) {
        this._RowId = _RowId;
    }

    public String get_Text() {
        return _Text;
    }

    public void set_Text(String _Text) {
        this._Text = _Text;
    }

    public String get_Version() {
        return _Version;
    }

    public void set_Version(String _Version) {
        this._Version = _Version;
    }

    public String get_Book() {
        return _Book;
    }

    public void set_Book(String _Book) {
        this._Book = _Book;
    }

    public int get_Chapter() {
        return _Chapter;
    }

    public void set_Chapter(int _Chapter) {
        this._Chapter = _Chapter;
    }

    public String get_Verse() {
        return _Verse;
    }

    public void set_Verse(String _Verse) {
        this._Verse = _Verse;
    }

    public int get_FavRank() {
        return _FavRank;
    }

    public void set_FavRank(int _FavRank) {
        this._FavRank = _FavRank;
    }

    public int get_Favorited() {
        return _Favorited;
    }

    public void set_Favorited(int _Favorited) {
        this._Favorited = _Favorited;
    }

    public int get_Rank() {
        return _Rank;
    }

    public void set_Rank(int _Rank) {
        this._Rank = _Rank;
    }
}
