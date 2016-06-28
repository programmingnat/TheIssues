package com.imaginat.theissues.customFragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imaginat.theissues.R;
import com.imaginat.theissues.managers.LegislatorManager;
import com.imaginat.theissues.models.Legislator;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by nat on 6/26/16.
 */
public class MainFragment_Leg_Adapter extends RecyclerView.Adapter<MainFragment_Leg_Adapter.LegislatorInfo> {

    LegislatorManager mLegislatorManager=null;


    public MainFragment_Leg_Adapter(){
        super();
        mLegislatorManager = LegislatorManager.getInstance();
    }


    @Override
    public LegislatorInfo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.legislator_summary_lineitem, parent, false);
        return new LegislatorInfo(view);
    }

    @Override
    public void onBindViewHolder(LegislatorInfo holder, int position) {
       Legislator legislator =  mLegislatorManager.getLegislatorAt(position);
        ImageLoader.getInstance().displayImage("https://theunitedstates.io/images/congress/225x275/" +
                legislator.getBioguide() + ".jpg", holder.mImageView);
        holder.mSummary_TextView.setText(legislator.getFirstName());

    }

    @Override
    public int getItemCount() {
        return mLegislatorManager.getSize();
    }

    class LegislatorInfo extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mSummary_TextView;
        public LegislatorInfo(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.yearbook_photo);
            mSummary_TextView = (TextView)itemView.findViewById(R.id.yearbook_summary);

        }
    }
}
