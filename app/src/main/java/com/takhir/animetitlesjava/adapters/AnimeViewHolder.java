package com.takhir.animetitlesjava.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.takhir.animetitlesjava.R;
import org.jetbrains.annotations.NotNull;

public class AnimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    AppCompatImageView image;
    TextView title, description;
    OnAnimeListener onAnimeListener;

    public AnimeViewHolder(@NotNull View itemView, OnAnimeListener onAnimeListener) {
        super(itemView);

        this.onAnimeListener = onAnimeListener;

        image = itemView.findViewById(R.id.anime_image);
        title = itemView.findViewById(R.id.anime_title);
        description = itemView.findViewById(R.id.anime_description);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onAnimeListener.onAnimeClick(getAdapterPosition());
    }
}
