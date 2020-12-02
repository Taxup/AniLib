package com.takhir.animetitlesjava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.takhir.animetitlesjava.R;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;
import com.takhir.animetitlesjava.models.shikimori.ShikimoriAnime;
import com.takhir.animetitlesjava.util.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AnimeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int ANIME_TYPE = 1;
    public static final int LOADING_TYPE = 2;

        private List<KitsuAnime> mAnimeList;
    private final OnAnimeListener mOnAnimeListener;

    public AnimeRecyclerAdapter(OnAnimeListener mOnAnimeListener) {
        this.mOnAnimeListener = mOnAnimeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ANIME_TYPE: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_anime_list_item, parent, false);
                return new AnimeViewHolder(view, mOnAnimeListener);
            }
            case LOADING_TYPE:
            default: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);

        if (itemViewType == ANIME_TYPE) {
            String imageURL = mAnimeList.get(position).getAttributes().getPosterImage().getMedium();

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageURL)
                    .into(((AnimeViewHolder) holder).image);

            ((AnimeViewHolder) holder).title.setText(mAnimeList.get(position).getAttributes().getCanonicalTitle());
            ((AnimeViewHolder) holder).description.setText(mAnimeList.get(position).getAttributes().getDescription());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mAnimeList.get(position).getId() == -1) {
            return LOADING_TYPE;
        } else if (position == mAnimeList.size() - 1 && position != 0) {
            return LOADING_TYPE;
        } else {
            return ANIME_TYPE;
        }
    }

//    public void setItemViewType(){
//        mAnimeList.get(mAnimeList.size()-1).setId(1);
//    }

    public void displayLoading() {
        if (!isLoading()) {
            KitsuAnime anime = new KitsuAnime();
            anime.setId(-1);
            List<KitsuAnime> list = new ArrayList<>();
            list.add(anime);
            mAnimeList = list;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (mAnimeList != null) {
            if (mAnimeList.get(mAnimeList.size() - 1).getId() == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if (mAnimeList != null) {
            return mAnimeList.size();
        }
        return 0;
    }

    public void setAnimeList(List<KitsuAnime> animeList) {
        mAnimeList = animeList;
        notifyDataSetChanged();
    }

}
