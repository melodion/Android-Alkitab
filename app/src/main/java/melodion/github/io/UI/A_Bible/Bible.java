package melodion.github.io.UI.A_Bible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import melodion.github.io.Adapters.BibleBookAdapter;
import melodion.github.io.Adapters.BibleVerseAdapter;
import melodion.github.io.Database.TblBibleBook;
import melodion.github.io.Database.TblSetting;
import melodion.github.io.Models.ModBible;
import melodion.github.io.Models.Verse;
import melodion.github.io.R;

public class Bible extends AppCompatActivity implements BibleInterface.ViewInterface, BibleVerseAdapter.OnClick, BibleBookAdapter.OnClick, View.OnClickListener {
    @BindView(R.id.layout_info) LinearLayout layout_info;
    @BindView(R.id.shimmer_view_container) ShimmerFrameLayout shimmer_view_container;
    @BindView(R.id.recyclerview_bible_book) RecyclerView recyclerview_bible_book;
    @BindView(R.id.recyclerview_bible_verse) RecyclerView recyclerview_bible_verse;
    @BindView(R.id.layout_bible) LinearLayout layout_bible;
    @BindView(R.id.btn_setting) FrameLayout btn_setting;
    @BindView(R.id.sliding_layout) SlidingUpPanelLayout sliding_layout;
    @BindView(R.id.tv_close) TextView btn_close;
    @BindView(R.id.sp_version) Spinner sp_version;
    @BindView(R.id.sb_font_size) SeekBar sb_font_size;
    @BindView(R.id.tv_bible_verse_title) TextView tv_bible_verse_title;
    @BindView(R.id.tv_bible_verse_text) TextView tv_bible_verse_text;
    @BindView(R.id.tv_title_search) TextView tv_title_search;
    @BindView(R.id.btn_save_setting) Button btn_save_setting;

    private List<Verse> verseList;
    private BiblePresenter _presenter;
    private BibleBookAdapter bibleBookAdapter;
    private BibleVerseAdapter bibleVerseAdapter;
    private TblSetting tblSetting;
    private AlertDialog  dialogSearch;
    private String QUERY = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        sliding_layout.setTouchEnabled(false);

        //OnCLick
        btn_setting.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        btn_save_setting.setOnClickListener(this);


        recyclerview_bible_book.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerview_bible_book.setItemAnimator(new DefaultItemAnimator());

        recyclerview_bible_verse.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerview_bible_verse.setItemAnimator(new DefaultItemAnimator());
        _presenter = new BiblePresenter(this,  Bible.this);

        _presenter.getSetting();
        _presenter.getBook();
        //_presenter.getBible("tb","kej", "1");


        sb_font_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               tv_bible_verse_title.setTextSize(progress + 2);
               tv_bible_verse_text.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void showLoading() {
        layout_info.setVisibility(View.GONE);
        layout_bible.setVisibility(View.GONE);
        shimmer_view_container.setVisibility(View.VISIBLE);
        shimmer_view_container.startShimmer();
    }

    @Override
    public void hideLoading() {
        layout_info.setVisibility(View.GONE);
        layout_bible.setVisibility(View.VISIBLE);
        shimmer_view_container.setVisibility(View.GONE);
        shimmer_view_container.stopShimmer();
    }

    @Override
    public void responseSetting(TblSetting data) {
        tblSetting = new TblSetting();
        tblSetting = data;
        sb_font_size.setProgress(tblSetting.getFont_size());
        tv_bible_verse_title.setTextSize(tblSetting.getFont_size() + 2);
        tv_bible_verse_text.setTextSize(tblSetting.getFont_size());
        switch (tblSetting.getVersion()){
            case "TB":
                sp_version.setSelection(1);
                break;
            case "Jawa":
                sp_version.setSelection(2);
                break;
            default:
                sp_version.setSelection(0);
                break;
        }
    }

    @Override
    public void responseBible(ModBible bible) {
        if (bible.getErrCode().equals("0")){
            this.verseList = bible.getResponse();
            bibleVerseAdapter = new BibleVerseAdapter(verseList, this, R.layout.item_bible_verse, this, tblSetting.getFont_size());
            recyclerview_bible_verse.setAdapter(bibleVerseAdapter);

        }
    }

    @Override
    public void responseBook(List<TblBibleBook> books) {
        bibleBookAdapter = new BibleBookAdapter(books, this, R.layout.item_bible_book, this);
        recyclerview_bible_book.setAdapter(bibleBookAdapter);
    }

    @Override
    public void responseSucces(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sliding_layout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED){
                    sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                    if (QUERY != "" || !QUERY.equals("")) {
                        searchBible(tblSetting.getVersion(), QUERY);
                    }
                }
            }
        }, 800);

    }

    @Override
    public void responseError(String error) {

    }

    @Override
    public void OnVerseClick() {

    }


    @Override
    public void OnBookClick(TblBibleBook book) {
        View view = LayoutInflater.from(Bible.this).inflate(R.layout.dialog_search_bible, null);

        LinearLayout btn_search = view.findViewById(R.id.btn_search);
        TextView tv_search = view.findViewById(R.id.tv_search);
        TextView tv_close = view.findViewById(R.id.tv_close);
        TextView tv_title_book = view.findViewById(R.id.tv_title_book);
        EditText tv_search_chapter = view.findViewById(R.id.tv_search_chapter);
        EditText tv_search_verse_1 = view.findViewById(R.id.tv_search_verse_1);
        EditText tv_search_verse_2 = view.findViewById(R.id.tv_search_verse_2);

        //http://localhost:3000/jawa/yoh+1:1-16
        tv_title_book.setText(book.getBook_name());
         btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tv_search_chapter.getText())){
                    Toast.makeText(Bible.this, "Masukan Pasal", Toast.LENGTH_SHORT).show();
                }else{
                    QUERY = book.getBook_key() + "+" + tv_search_chapter.getText().toString() + ":" + tv_search_verse_1.getText().toString() + "-"+  tv_search_verse_2.getText().toString();
                    searchBible(tblSetting.getVersion(), QUERY);
                    tv_title_search.setText(book.getBook_name() + " " + tv_search_chapter.getText().toString() + ": " + tv_search_verse_1.getText().toString() + " - " + tv_search_verse_2.getText().toString());
                }
          }
        });
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tv_search_chapter.getText())){
                    Toast.makeText(Bible.this, "Masukan Pasal", Toast.LENGTH_SHORT).show();
                }else {
                    QUERY = book.getBook_key() + "+" + tv_search_chapter.getText().toString() + ":" + tv_search_verse_1.getText().toString() + "-" + tv_search_verse_2.getText().toString();
                    searchBible(tblSetting.getVersion(), QUERY);
                    tv_title_search.setText(book.getBook_name() + " " + tv_search_chapter.getText().toString() + ": " + tv_search_verse_1.getText().toString() + " - " + tv_search_verse_2.getText().toString());
                }
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSearch.dismiss();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(Bible.this);
        builder.setView(view);
        builder.setCancelable(true);
        dialogSearch = builder.create();
        dialogSearch.show();
    }


        private void searchBible(String v, String q){
            _presenter.getBible(v,q);
            tv_title_search.setVisibility(View.VISIBLE);
            if (dialogSearch != null){
                dialogSearch.dismiss();
            }
        }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_setting:
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                break;

            case R.id.tv_close:
                sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;

            case R.id.btn_save_setting:
                if (sp_version.getSelectedItem().toString() =="Pilih"){
                    Toast.makeText(this, "Pilih Versi", Toast.LENGTH_SHORT).show();
                }else{
                    TblSetting tbl = new TblSetting();
                    tbl.setId(tblSetting.getId());
                    tbl.setVersion(sp_version.getSelectedItem().toString());
                    tbl.setFont_size(sb_font_size.getProgress());
                    _presenter.saveSetting(tblSetting.getId(), tbl);
                }
                break;
        }
    }
}