package com.example.administrator.endrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MyAdapter myAdapter;
    int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.endLess_recycleview);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPositions = linearLayoutManager.findLastVisibleItemPosition();
                if (myAdapter.getItemCount() - lastVisibleItemPositions < 5) {
                    List<DataModel> data = loadData();
                    myAdapter.addData(data);
                }
            }
        });
        myAdapter = new MyAdapter();
        mRecyclerView.setAdapter(myAdapter);
        List<DataModel> data = loadData();
        myAdapter.addData(data);
    }

    private List<DataModel> loadData() {
        List<DataModel> dataModels = new ArrayList<>();
        int[] ids = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l};
        for (int i = 0; i < ids.length; i++) {
            DataModel dataModel = new DataModel(ids[i], "Item - " + count++);
            dataModels.add(dataModel);
        }
        return dataModels;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<DataModel> mData = new ArrayList<>();
        LayoutInflater mLayoutInflater;

        public void addData(List<DataModel> dataModels) {
            mData.addAll(dataModels);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mLayoutInflater == null) {
                mLayoutInflater = LayoutInflater.from(parent.getContext());
            }
            View view = mLayoutInflater.inflate(R.layout.adapter_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            DataModel dataModel = mData.get(position);
            holder.bind(dataModel);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private ImageView mImage;
            private TextView mText;

            public ViewHolder(View itemView) {
                super(itemView);
                mImage = (ImageView) itemView.findViewById(R.id.img);
                mText = (TextView) itemView.findViewById(R.id.text);
            }

            public void bind(DataModel dataModel) {
                mImage.setImageResource(dataModel.image);
                mText.setText(dataModel.text);
            }
        }
    }

    public class DataModel {
        int image;
        String text;

        public DataModel(int image, String text) {
            this.image = image;
            this.text = text;
        }
    }
}
