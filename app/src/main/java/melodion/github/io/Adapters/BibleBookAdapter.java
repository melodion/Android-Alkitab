package melodion.github.io.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import melodion.github.io.Database.TblBibleBook;
import melodion.github.io.Models.Verse;
import melodion.github.io.R;

public class BibleBookAdapter extends RecyclerView.Adapter<BibleBookAdapter.VerseViewHolder> {


    private List<TblBibleBook> books;
    private Context context;
    private int layout_item;
    private OnClick onCLick;
    public BibleBookAdapter(List<TblBibleBook> books, Context context, int layout_item, OnClick onCLick) {
        this.books = books;
        this.context = context;
        this.layout_item = layout_item;
        this.onCLick = onCLick;
    }

    @NonNull
    @Override
    public BibleBookAdapter.VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(layout_item, parent,false);

        return new VerseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BibleBookAdapter.VerseViewHolder holder, int position) {
        holder.tv_bible_book.setText(books.get(position).getBook_key());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class VerseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bible_book) TextView tv_bible_book;



        public VerseViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCLick.OnBookClick(books.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnClick{
        void OnBookClick(TblBibleBook book);
    }
}
