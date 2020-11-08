package melodion.github.io.UI.A_Bible;

import java.util.List;

import melodion.github.io.Database.TblBibleBook;
import melodion.github.io.Database.TblSetting;
import melodion.github.io.Models.ModBible;

public interface BibleInterface {

    interface PresenterInterface{
        void getSetting();
        void getBible(String version,String query);
        void getBook();
        void saveSetting(Long id, TblSetting setting);
    }

    interface ViewInterface{
        void showLoading();
        void hideLoading();
        void responseSetting(TblSetting tblSetting);
        void responseBible(ModBible bible);
        void responseBook(List<TblBibleBook> books);
        void responseSucces(String message);
        void responseError(String error);
    }
}
