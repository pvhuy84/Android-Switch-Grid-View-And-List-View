package com.example.hcd_fresher048.appdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListNewsFragmentDisplayInListView extends Fragment implements NewsRecyclerViewAdapter.NewsRecyclerViewAdapterOnClick, NewsRecyclerViewAdapter.NewsRecyclerViewAdapterItemDelete{
    private List<News> newsList;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private Context context;
    private RecyclerView rvListNews;

    public ListNewsFragmentDisplayInListView() {
        newsList = (ArrayList<News>) NewsData.getData();
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_news_view, container, false);

        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(context, newsList, ListNewsFragment.DISPLAY_BY_LIST_VIEW);
        newsRecyclerViewAdapter.setNewsRecyclerViewAdapterOnClick(this);
        newsRecyclerViewAdapter.setNewsRecyclerViewAdapterItemDelete(this);

        rvListNews = (RecyclerView) v.findViewById(R.id.rv_list_news);
        rvListNews.setLayoutManager(new LinearLayoutManager(context));
        rvListNews.setAdapter(newsRecyclerViewAdapter);

        return v;
    }

    @Override
    public void delete(int position) {
       newsList.remove(position);
    }

    @Override
    public void onClick(int position) {

    }


}
