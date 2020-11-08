package melodion.github.io.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import melodion.github.io.Models.Verse;
import melodion.github.io.R;

public class BibleVerseAdapter extends RecyclerView.Adapter<BibleVerseAdapter.VerseViewHolder> {


    private int font_size;
    private List<Verse> verses;
    private Context context;
    private int layout_item;
    private OnClick onCLick;
    public BibleVerseAdapter(List<Verse> verses, Context context, int layout_item, OnClick onCLick, int font_size) {
        this.verses = verses;
        this.context = context;
        this.layout_item = layout_item;
        this.onCLick = onCLick;
        this.font_size = font_size;
    }

    @NonNull
    @Override
    public BibleVerseAdapter.VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout_item, parent,false);

        return new VerseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BibleVerseAdapter.VerseViewHolder holder, int position) {
        if (verses.get(position).getType().equals("title")){
            if (verses.get(position).getContent().equals("Judul")){
                holder.layout_title.setVisibility(View.GONE);
            }else{
                holder.layout_title.setVisibility(View.VISIBLE);
            }

            holder.layout_content.setVisibility(View.GONE);
            holder.tv_bible_verse_title.setText(verses.get(position).getContent());
        }else{
            holder.layout_title.setVisibility(View.GONE);
            holder.layout_content.setVisibility(View.VISIBLE);
        }
        holder.tv_bible_verse_number.setText(verses.get(position).getVerse());
        holder.tv_bible_verse_text.setText(verses.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return verses.size();
    }

    public class VerseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bible_verse_number) TextView tv_bible_verse_number;
        @BindView(R.id.tv_bible_verse_text) TextView tv_bible_verse_text;
        @BindView(R.id.tv_bible_verse_title) TextView tv_bible_verse_title;
        @BindView(R.id.layout_title) LinearLayout layout_title;
        @BindView(R.id.layout_content) LinearLayout layout_content;



        public VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            tv_bible_verse_title.setTextSize(font_size + 2);
            tv_bible_verse_text.setTextSize(font_size);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCLick.OnVerseClick();
                }
            });
        }
    }

    public interface OnClick{
        void OnVerseClick();
    }
}
