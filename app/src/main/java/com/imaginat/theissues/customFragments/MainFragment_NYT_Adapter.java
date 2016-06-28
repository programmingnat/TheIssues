package com.imaginat.theissues.customFragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imaginat.theissues.R;
import com.imaginat.theissues.managers.HeadlinesManager;
import com.imaginat.theissues.models.Headline;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by nat on 6/26/16.
 */
public class MainFragment_NYT_Adapter extends RecyclerView.Adapter<MainFragment_NYT_Adapter.NYT_HeadlineHolder> {

    public interface INYT_Adapter{
        public void onClick(View v,String data);
    }

    private INYT_Adapter mINYT_adapter=null;

    private static final String TAG = MainFragment_NYT_Adapter.class.getSimpleName();
    HeadlinesManager mHeadlinesManager = HeadlinesManager.getInstance();

    public MainFragment_NYT_Adapter(INYT_Adapter interfaceAdapter){
        super();
        mINYT_adapter = interfaceAdapter;
    }
    @Override
    public NYT_HeadlineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nyt_headlines_lineitem, parent, false);
        return new NYT_HeadlineHolder(v);
    }

    @Override
    public void onBindViewHolder(NYT_HeadlineHolder holder, int position) {
        Headline headline =mHeadlinesManager.getHeadline(position);
        if(headline.getHeadline()!=null) {
            Log.d(TAG, "inside onBindViewHolder with " + headline.getHeadline());
            holder.mTextView.setText(headline.getHeadline());
        }
        holder.mTextView.setHint(headline.getSnippet());
        if(headline.getImage()==null){
            holder.mImageView.setImageResource(R.drawable.noimage);
            String imageUri="drawable://"+R.drawable.noimage;
            ImageLoader.getInstance().displayImage(imageUri, holder.mImageView);
        }else {
            ImageLoader.getInstance().displayImage(headline.getImage(), holder.mImageView);
        }

        holder.mEntireView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mINYT_adapter.onClick(v,headline.getLink());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHeadlinesManager.getSize();
    }

    class NYT_HeadlineHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;
        public View mEntireView;
        public NYT_HeadlineHolder(View itemView) {
            super(itemView);
            mEntireView=itemView;
            mTextView = (TextView)itemView.findViewById(R.id.testTextView);
            mImageView =(ImageView)itemView.findViewById(R.id.articleImage);

        }
    }
}
