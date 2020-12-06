package com.takhir.animetitlesjava;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.takhir.animetitlesjava.adapters.AnimeRecyclerAdapter;
import com.takhir.animetitlesjava.adapters.OnAnimeListener;
import com.takhir.animetitlesjava.models.kitsu.Anime;
import com.takhir.animetitlesjava.util.Testing;
import com.takhir.animetitlesjava.viewmodels.AnimeListViewModel;

import java.util.List;


public class AnimeListActivity extends BaseActivity implements OnAnimeListener {
    private static final String TAG = "AnimeListActivity";

    //ui
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;

    //vars
    private AnimeListViewModel mAnimeListViewModel;
    private AnimeRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_list);

        mRecyclerView = findViewById(R.id.anime_list);
        mSearchView = findViewById(R.id.search_view);

        mAnimeListViewModel = new ViewModelProvider(this).get(AnimeListViewModel.class);


        initRecyclerView();
        subscribeObservers();

        mAnimeListViewModel.searchAnimeListApi("cowboy bebop", 20, 1);
        mAdapter.displayLoading();
        initSearchView();
    }

    private void subscribeObservers() {
        mAnimeListViewModel.getAnimeList().observe(this, new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> animeList) {
                if (animeList != null) {
                    Testing.printAnimeList(animeList, "print animeList");
                    mAnimeListViewModel.setPerformingQuery(false);
                    mAdapter.setAnimeList(animeList);
                }
            }
        });
        mAnimeListViewModel.isQueryExhausted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Log.d(TAG, "onChanged: the query is exhausted");
                    mAdapter.setQueryExhausted();
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new AnimeRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!mRecyclerView.canScrollVertically(1)) {
                    mAnimeListViewModel.searchNextPage();
                }
            }
        });
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.displayLoading();
                mAnimeListViewModel.searchAnimeListApi(query, 20, 0);
                mSearchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                mAdapter.displayLoading();
//                mAnimeListViewModel.searchAnimeListApi(newText, 10, 0);
                return false;
            }
        });
    }

    @Override
    public void onAnimeClick(int position) {
        Intent intent = new Intent(this, AnimeActivity.class);
        Anime anime = mAdapter.getSelectedAnime(position);
        Log.d(TAG, "onAnimeClick: -----" + anime);
        intent.putExtra("anime", anime);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mAnimeListViewModel.onBackPressed()) {
            super.onBackPressed();
        }
//        mAdapter.setItemViewType();
//        mAdapter.notifyDataSetChanged();
//        Log.d(TAG, "onBackPressed: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
}
