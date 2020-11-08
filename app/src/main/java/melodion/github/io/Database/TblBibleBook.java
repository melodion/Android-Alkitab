package melodion.github.io.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TblBibleBook {

    Integer book_id;
    String book_key;
    String book_name;
    String book_category;
    @Generated(hash = 754798818)
    public TblBibleBook(Integer book_id, String book_key, String book_name,
            String book_category) {
        this.book_id = book_id;
        this.book_key = book_key;
        this.book_name = book_name;
        this.book_category = book_category;
    }
    @Generated(hash = 1597023651)
    public TblBibleBook() {
    }
    public Integer getBook_id() {
        return this.book_id;
    }
    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
    public String getBook_key() {
        return this.book_key;
    }
    public void setBook_key(String book_key) {
        this.book_key = book_key;
    }
    public String getBook_name() {
        return this.book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public String getBook_category() {
        return this.book_category;
    }
    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }
    
}
