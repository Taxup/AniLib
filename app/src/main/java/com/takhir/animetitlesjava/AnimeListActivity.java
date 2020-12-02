package com.takhir.animetitlesjava;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.takhir.animetitlesjava.adapters.AnimeRecyclerAdapter;
import com.takhir.animetitlesjava.adapters.OnAnimeListener;
import com.takhir.animetitlesjava.models.kitsu.KitsuAnime;
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

        mAnimeListViewModel.searchAnimeListApi("cowboy bebop", 10, 1);
        mAdapter.displayLoading();
        initSearchView();
    }

    private void subscribeObservers() {
        mAnimeListViewModel.getAnimeList().observe(this, new Observer<List<KitsuAnime>>() {
            @Override
            public void onChanged(List<KitsuAnime> animeList) {
                if (animeList != null) {
                    Testing.printAnimeList(animeList, "print animeList");
                    mAnimeListViewModel.setPerformingQuery(false);
                    mAdapter.setAnimeList(animeList);
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
                if (!mRecyclerView.canScrollVertically(0)) {
//                    mAnimeListViewModel.searchNextPage();
                }
            }
        });
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.displayLoading();
                mAnimeListViewModel.searchAnimeListApi(query, 10, 1);
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
