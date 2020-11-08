package melodion.github.io.Database;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        daoSession = new DaoMaster(new DbOpenHelper(this, "warta.db").getWritableDb()).newSession();
        Long book = daoSession.getTblBibleBookDao().queryBuilder().count();
        if (book == 0){
            //Perjanjian Lama
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(1,"Kej","Kejadian","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(2,"Kel","Keluaran","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(3,"Ima","Imamat","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(4,"Bil","Bilangan","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(5,"Ula","Ulangan","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(6,"Yos","Yosua","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(7,"Hak","Hakim-Hakim","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(8,"Rut","Rut","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(9,"1Sa","1 Samuel","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(10,"2Sa","2 Samuel","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(11,"1Ra","1 Raja-Raja","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(12,"2Ra","2 Raja-Raja","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(13,"1Ta","1 Tawarikh","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(14,"2Ta","2 Tawarikh","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(15,"Ezr","Ezra","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(16,"Neh","Nehemia","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(17,"Est","Ester","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(18,"Ayb","Ayub","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(19,"Mzm","Mazmur","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(20,"Ams","Amsal","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(21,"Pkh","Pengkhotbah","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(22,"Kid","Kidung Agung","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(23,"Yes","Yesaya","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(24,"Yer","Yeremia","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(25,"Rat","Ratapan","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(26,"Yeh","Yehezkiel","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(27,"Dan","Daniel","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(28,"Hos","Hosea","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(29,"Yoe","Yoel","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(30,"Amo","Amos","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(31,"Oba","Obaja","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(32,"Yun","Yunus","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(33,"Mik","Mikha","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(34,"Nah","Nahum","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(35,"Hab","Habkuk","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(36,"Zef","Zefanya","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(37,"Hag","Hagai","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(38,"Zak","Zakharia","PL"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(39,"Mal","Maleakhi","PL"));

            //Perjanjian Baru
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(40,"Mat","Matius","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(41,"Mrk","Markus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(42,"Luk","Lukas","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(43,"Yoh","Yohanes","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(44,"Kis","Kisah Para Rasul","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(45,"Rom","Roma","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(46,"1Ko","1 Korintus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(47,"2Ko","2 Korintus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(48,"Gal","Galatia","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(49,"Efe","Efesus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(50,"Flp","Filipi","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(51,"Kol","Kolose","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(52,"1Te","1 Tesalonika","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(53,"2Te","2 Tesalonika","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(54,"1Ti","1 Timotius","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(55,"2Ti","2 Timotius","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(56,"Tit","Titus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(57,"Flm","Filemon","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(58,"Ibr","Ibrani","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(59,"Yak","Yakobus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(60,"1Pt","1 Petrus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(61,"2Pt","2 Petrus","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(62,"1Yo","1 Yohanes","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(63,"2Yo","2 Yohanes","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(64,"3Yo","3 Yohanes","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(65,"Yud","Yudas ","PB"));
            daoSession.getTblBibleBookDao().insert(new TblBibleBook(66,"Why","Wahyu ","PB"));
        }
        Long setting = daoSession.getTblSettingDao().queryBuilder().count();
        if (setting == 0){
            TblSetting set = new TblSetting();
            set.setVersion("TB");
            set.setFont_size(14);
            daoSession.getTblSettingDao().insert(set);
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}


