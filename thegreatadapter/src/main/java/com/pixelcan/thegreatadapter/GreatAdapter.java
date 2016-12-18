package com.pixelcan.thegreatadapter;

import android.content.Context;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pixelcan.thegreatadapter.items.SimpleViewItem;
import com.pixelcan.thegreatadapter.utils.JavaUtils;
import com.pixelcan.thegreatadapter.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Pacioianu on 11/19/16.
 */

public class GreatAdapter extends RecyclerView.Adapter<GreatViewHolder> {

    private static final String TAG = "The Great Adapter";

    private final Context mContext;
    private final Builder mBuilder;
    private final ArrayList<Class> mItemsClasses = new ArrayList<>();
    private final ArrayList<GreatItem> mData = new ArrayList<>();
    private final ArrayList<GreatItem> mHeaders = new ArrayList<>();
    private final ArrayList<GreatItem> mFooters = new ArrayList<>();

    private LoadListener loadListener;
    private boolean isLoading = false;
    private SimpleViewItem loadMoreFooter = null;
    private SimpleViewItem loadMoreFailedFooter = null;
    private int currentPage = 1;


    private GreatAdapter(Builder builder) {
        this.mContext = builder.context;
        this.mBuilder = builder;
        this.loadListener = builder.loadListener;
        this.setHasStableIds(true);
    }

    // ToDo: animations
    // todo emptyview

    @Override
    public long getItemId(int position) {
        return getItem(position).getItemId();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    @Override
    public GreatViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        for (Class itemClass:mItemsClasses) {
            try {
                GreatItem item = (GreatItem) itemClass.newInstance();
                if (item.getItemType() == type) {
                    return item.getItemViewHolder(parent, parent.getContext());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(GreatViewHolder viewHolder, int position) {
        GreatItem item = getItem(position);
        viewHolder.bindType(item);
    }

    @Override
    public int getItemCount() {
        return mData.size() + mFooters.size() + mHeaders.size();
    }

    // GET

    private GreatItem getItem(int position) {
        ArrayList<GreatItem> allItems = new ArrayList<>();
        allItems.addAll(mHeaders);
        allItems.addAll(mData);
        allItems.addAll(mFooters);
        return allItems.get(position);
    }

    public GreatItem get(int position) {
        return mData.get(position);
    }

    public GreatItem getFooter(int position) {
        return mFooters.get(position);
    }

    public GreatItem getHeader(int position) {
        return mHeaders.get(position);
    }

    // ADD

    private void addItemClass(Class itemClass) {
        if(!mItemsClasses.contains(itemClass)) {
            if (JavaUtils.hasNoArgConstructor(itemClass)) {
                Log.d(TAG, "addItemClass: " + itemClass.getName());
                this.mItemsClasses.add(itemClass);
            } else {
                throw new IllegalArgumentException("Having a no-arg constructor is mandatory!");
            }
        }
    }

    public <T extends GreatItem> void add(T item) {
        add(mData.size(), item);
    }

    public <T extends GreatItem> void add(int position, T item) {
        addItemClass(item.getClass());
        this.mData.add(position, item);
        this.notifyDataSetChanged();
    }

    private <T extends GreatItem> void addAll(List<T> items) {
        addAll(mData.size(), items);
    }

    public <T extends GreatItem> void addAll(int position, List<T> items) {
        for (T item: items) {
            addItemClass(item.getClass());
        }
        this.mData.addAll(position, items);
        this.notifyDataSetChanged();
    }

    public <T extends GreatItem> void addHeader(T header) {
        addHeader(0, header);
    }

    public <T extends GreatItem> void addHeader(int position, T header) {
        addItemClass(header.getClass());
        this.mHeaders.add(position, header);
        this.notifyDataSetChanged();
    }

    public <T extends GreatItem> void addFooter(T footer) {
        addFooter(mFooters.size(), footer);
    }

    public <T extends GreatItem> void addFooter(int position, T footer) {
        addItemClass(footer.getClass());
        this.mFooters.add(position, footer);
        this.notifyDataSetChanged();
    }

    // REMOVE

    private void remove(int position) {
        this.mData.remove(position);
        this.notifyDataSetChanged();
    }

    private <T extends GreatItem> void remove(T data) {
        this.mData.remove(data);
        this.notifyDataSetChanged();
    }

    public void removeHeader(int position) {
        this.mHeaders.remove(position);
        this.notifyDataSetChanged();
    }

    public <T extends GreatItem> void removeHeader(T header) {
        this.mHeaders.remove(header);
        this.notifyDataSetChanged();
    }

    public void removeFooter(int position) {
        this.mFooters.remove(position);
        this.notifyDataSetChanged();
    }

    public <T extends GreatItem> void removeFooter(T footer) {
        this.mFooters.remove(footer);
        this.notifyDataSetChanged();
    }

    // UPDATE

    public <T extends GreatItem> void update(List<T> data){
        this.mData.clear();
        this.addAll(data);
    }

    // CLEAR

    public void clearAll() {
        this.mData.clear();
        this.mHeaders.clear();
        this.mFooters.clear();
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.mData.clear();
        this.notifyDataSetChanged();
    }

    public void clearHeaders() {
        this.mHeaders.clear();
        this.notifyDataSetChanged();
    }

    public void clearFooters() {
        this.mFooters.clear();
        this.notifyDataSetChanged();
    }

    // COUNT

    public int count() {
        return mData.size();
    }

    public int countHeaders() {
        return mHeaders.size();
    }

    public int countFooters() {
        return mFooters.size();
    }

    // LOAD MORE

    public interface LoadListener{
        void onLoad(int page);
    }

    public void setLoadListener(LoadListener listener) {
        loadListener = listener;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void startLoading() {
        stopLoading();
        isLoading = true;
        loadListener.onLoad(currentPage);

        if (loadMoreFooter == null) {
            loadMoreFooter = new SimpleViewItem(mBuilder.loadMoreView);
        }
        addFooter(loadMoreFooter);
    }

    public void loadComplete() {
        stopLoading();
        currentPage++;
    }

    public void loadingFailed() {
        stopLoading();
        isLoading = true;
        currentPage--;

        if (loadMoreFailedFooter == null) {
            View view = mBuilder.loadFailedView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startLoading();
                }
            });
            loadMoreFailedFooter = new SimpleViewItem(view);
        }
        addFooter(loadMoreFailedFooter);
    }

    private void stopLoading() {
        isLoading = false;
        removeFooter(loadMoreFooter);
        removeFooter(loadMoreFailedFooter);
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        // set items decorator
        GreatItemDecorator decorator = new GreatItemDecorator(mBuilder.dividerSizePx);
        recyclerView.addItemDecoration(decorator);

        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        // set on load more listener
        if (mBuilder.enableLoadMore) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                int prevCount = -1;

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    int viewedItems = 0;
                    if (layoutManager instanceof LinearLayoutManager) {
                        viewedItems = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        int[] lastItemsPos = ((StaggeredGridLayoutManager) layoutManager)
                                .findLastVisibleItemPositions(null);
                        for (int itemPos : lastItemsPos) {
                            viewedItems += itemPos;
                        }
                    }
                    if (!isLoading && prevCount != count() &&
                            viewedItems >= (getItemCount() - 1 - mBuilder.loadItemsThreshold)) {
                        prevCount = count();
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                startLoading();
                            }
                        });
                    }
                }
            });
        }

        // set items span size
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) layoutManager;
            // set span sizes
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemCount() > 0 && position < getItemCount()) {
                        int itemSpanSize = getItem(position).getSpanSize();
                        if (itemSpanSize < 0) {return gridManager.getSpanCount();}
                        return itemSpanSize;
                    } else {
                        return 0;
                    }
                }
            });
        }
    }

    public static class Builder {

        private final Context context;

        private boolean enableLoadMore;
        private LoadListener loadListener;
        private int dividerSizePx;
        private int loadItemsThreshold;
        private View loadMoreView;
        private View loadFailedView;

        public Builder (Context context) {
            this.context = context;

            this.loadListener = null;
            this.enableLoadMore = false;
            this.dividerSizePx = 0;
            this.loadItemsThreshold = 0;
            this.loadMoreView = LayoutInflater.from(context).inflate(R.layout.view_load_more, null);
            this.loadFailedView = LayoutInflater.from(context).inflate(R.layout.view_load_failed, null);
        }

        public Builder enableLoadMore(boolean enableLoadMore) {
            this.enableLoadMore = enableLoadMore;
            return this;
        }

        public Builder loadListener(LoadListener listener) {
            this.loadListener = listener;
            return this;
        }

        public Builder dividerSizeDp(float dividerSizeDp) {
            this.dividerSizePx = (int) ViewUtils.pxFromDp(context, dividerSizeDp);
            return this;
        }

        public Builder dividerSizePx(int dividerSizePx) {
            this.dividerSizePx = dividerSizePx;
            return this;
        }

        public Builder loadItemsThreshold(int threshold) {
            this.loadItemsThreshold = threshold;
            return this;
        }

        public Builder loadMoreView(View loadMoreView) {
            this.loadMoreView = loadMoreView;
            return this;
        }

        public Builder loadFailedView(View loadFailedView) {
            this.loadFailedView = loadFailedView;
            return this;
        }

        public GreatAdapter build() {
            return new GreatAdapter(this);
        }

    }

}
