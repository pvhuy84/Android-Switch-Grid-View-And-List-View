package com.example.hcd_fresher048.appdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HCD-Fresher048 on 1/11/2017.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<News> newsList;
    private List<News> newsListFiltered;
    private NewsRecyclerViewAdapterOnClick newsRecyclerViewAdapterOnClick;
    private NewsRecyclerViewAdapterItemDelete newsRecyclerViewAdapterItemDelete;
    private int viewKind;

    public NewsRecyclerViewAdapter(Context context, List<News> newsList, int viewKind) {
        this.context = context;
        this.newsList = newsList;
        this.newsListFiltered = new ArrayList<>(newsList);
        this.viewKind = viewKind;
    }

    public void setNewsRecyclerViewAdapterOnClick(NewsRecyclerViewAdapterOnClick newsRecyclerViewAdapterOnClick) {
        this.newsRecyclerViewAdapterOnClick = newsRecyclerViewAdapterOnClick;
    }

    public void setNewsRecyclerViewAdapterItemDelete(NewsRecyclerViewAdapterItemDelete newsRecyclerViewAdapterItemDelete) {
        this.newsRecyclerViewAdapterItemDelete = newsRecyclerViewAdapterItemDelete;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(ListNewsFragment.DISPLAY_BY_LIST_VIEW == viewKind) {
            v = LayoutInflater.from(context).inflate(R.layout.news_item_list_view, parent, false);
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.news_item_grid_view, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = newsListFiltered.get(position);
        holder.tvNewsContent.setText(news.getContent());
        if (viewKind == R.layout.fragment_list_news_list_view) {
            holder.tvNewsId.setText(String.valueOf(news.getId()));
        }
    }

    @Override
    public int getItemCount() {
        return newsListFiltered.size();
    }

    public void removeItem (int position) {
        News news = newsListFiltered.get(position);
        for (int i = 0; i < newsList.size(); i++) {
            if(newsList.get(i).getId() == news.getId()) {
                newsList.remove(i);
                if(newsRecyclerViewAdapterItemDelete != null) {
                    newsRecyclerViewAdapterItemDelete.delete(i);
                }
                break;
            }
        }
        newsListFiltered.remove(position);
        this.notifyItemRemoved(position);
        this.notifyItemRangeChanged(position, newsListFiltered.size());
    }

    private AlertDialog AskOption (final int position) {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.delete_dialog_title))
                .setMessage(context.getResources().getString(R.string.delete_dialog_message))
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton(context.getResources().getString(R.string.delete_dialog_delete), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        NewsRecyclerViewAdapter.this.removeItem(position);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(context.getResources().getString(R.string.delete_dialog_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewsId;
        TextView tvNewsContent;
        ImageButton btnDeleteNews;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNewsContent = (TextView) itemView.findViewById(R.id.tv_news_content);
            btnDeleteNews = (ImageButton) itemView.findViewById(R.id.btn_delete_news);
            btnDeleteNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog dialogDelete = AskOption(getLayoutPosition());
                    dialogDelete.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (newsRecyclerViewAdapterOnClick != null) {
                        newsRecyclerViewAdapterOnClick.onClick(getLayoutPosition());
                    }
                }
            });

            if (ListNewsFragment.DISPLAY_BY_LIST_VIEW == viewKind) {
                tvNewsId = (TextView) itemView.findViewById(R.id.tv_news_id);
            }
        }
    }

    interface NewsRecyclerViewAdapterOnClick {
        void onClick (int position);
    }

    interface NewsRecyclerViewAdapterItemDelete {
        void delete (int position);
    }
}
