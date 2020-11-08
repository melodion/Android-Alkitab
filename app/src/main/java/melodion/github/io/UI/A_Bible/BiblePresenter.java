package melodion.github.io.UI.A_Bible;

import android.app.Activity;
import android.util.Log;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import melodion.github.io.Database.App;
import melodion.github.io.Database.TblBibleBook;
import melodion.github.io.Database.TblSetting;
import melodion.github.io.Database.TblSettingDao;
import melodion.github.io.Models.ModBible;
import melodion.github.io.Utils.HttpClient.RequestAPI;
import melodion.github.io.Utils.HttpClient.Services;

public class BiblePresenter implements BibleInterface.PresenterInterface {

    BibleInterface.ViewInterface _view;
    Activity _ctx;
    public BiblePresenter(BibleInterface.ViewInterface _view, Activity _ctx) {
        this._view = _view;
        this._ctx  = _ctx;
    }

    @Override
    public void getSetting() {
        TblSetting tblSetting = ((App)_ctx.getApplication()).getDaoSession().getTblSettingDao().queryBuilder().limit(1).unique();
        _view.responseSetting(tblSetting);
    }

    @Override
    public void getBible(String version, String query) {
        _view.showLoading();
        RequestAPI.getAPI().create(Services.class).getBible(version, query).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<ModBible>() {

            @Override
            public void onNext(ModBible bible) {
                _view.responseBible(bible);
            }

            @Override
            public void onError(Throwable e) {
                _view.hideLoading();
                _view.responseError("Error " + e);
            }

            @Override
            public void onComplete() {
                _view.hideLoading();
            }
        });
    }

    @Override
    public void getBook() {
        List<TblBibleBook> books = ((App)_ctx.getApplication()).getDaoSession().getTblBibleBookDao().queryBuilder().list();
        _view.responseBook(books);
    }

    @Override
    public void saveSetting(Long id,TblSetting setting) {
        try {
            TblSetting tbl = ((App)_ctx.getApplication()).getDaoSession().getTblSettingDao().load(id);
            tbl .setVersion(setting.getVersion());
            tbl .setFont_size(setting.getFont_size());
            ((App)_ctx.getApplication()).getDaoSession().getTblSettingDao().update(tbl);
            _view.responseSucces("Berhasil Disimpan !");
        }catch (Exception e){}
    }
}
