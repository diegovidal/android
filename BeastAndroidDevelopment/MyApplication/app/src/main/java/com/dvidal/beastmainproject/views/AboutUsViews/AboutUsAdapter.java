package com.dvidal.beastmainproject.views.AboutUsViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.activities.BaseActivity;
import com.dvidal.beastmainproject.enties.EventCard;

import java.util.ArrayList;

/**
 * Created by diegovidal on 15/05/17.
 */

public class AboutUsAdapter extends RecyclerView.Adapter {

    private final int VIEW_TYPE_MAIN_HEADER = 1;
    private final int VIEW_TYPE_SERVICE_LIST = 2;
    private final int VIEW_TYPE_BROTHERHOOD_LIST = 3;
    private final int VIEW_TYPE_SOCIAL_LIST = 4;
    private final int VIEW_TYPE_LIST_HEADER = 5;

    private LayoutInflater mInflater;
    private BaseActivity mBaseActivity;

    private ArrayList<EventCard> mCommunityServiceEventCards;
    private ArrayList<EventCard> mBrotherhoodEventCards;
    private ArrayList<EventCard> mSocialEventCards;

    private AboutUsListener mListener;

    public AboutUsAdapter(BaseActivity baseActivity, AboutUsListener listener) {
        this.mBaseActivity = baseActivity;
        this.mListener = listener;

        this.mInflater = baseActivity.getLayoutInflater();
        this.mCommunityServiceEventCards = new ArrayList<>();
        this.mBrotherhoodEventCards = new ArrayList<>();
        this.mSocialEventCards = new ArrayList<>();
    }

    public ArrayList<EventCard> getmCommunityServiceEventCards() {
        return mCommunityServiceEventCards;
    }

    public ArrayList<EventCard> getmBrotherhoodEventCards() {
        return mBrotherhoodEventCards;
    }

    public ArrayList<EventCard> getmSocialEventCards() {
        return mSocialEventCards;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return VIEW_TYPE_MAIN_HEADER;

        position --;

        if (mCommunityServiceEventCards.size() > 0){
            if (position == 0) return VIEW_TYPE_LIST_HEADER;

            position--;

            if (position < mCommunityServiceEventCards.size())
                return VIEW_TYPE_SERVICE_LIST;

            position -= mCommunityServiceEventCards.size();
        }

        if (mBrotherhoodEventCards.size() > 0){
            if (position == 0) return VIEW_TYPE_LIST_HEADER;

            position--;

            if (position < mBrotherhoodEventCards.size())
                return VIEW_TYPE_BROTHERHOOD_LIST;

            position -= mBrotherhoodEventCards.size();
        }

        if (mSocialEventCards.size() > 0){
            if (position == 0) return VIEW_TYPE_LIST_HEADER;

            position--;

            if (position < mSocialEventCards.size())
                return VIEW_TYPE_SOCIAL_LIST;

            position -= mSocialEventCards.size();
        }

        position -= mSocialEventCards.size();

        throw new IllegalArgumentException("We are being asked for a viewType at position "+ position +
        " although " + "we are at the end of the adapter support viewholders. Please check your adapter");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View eventCardView = mInflater.inflate(R.layout.list_event_card, parent, false);
        View listHeader = mInflater.inflate(R.layout.simple_header, parent, false);

        if (viewType == VIEW_TYPE_MAIN_HEADER){

            return new AboutUsMainHeaderViewHolder(mInflater, parent);
        }

        else if (viewType == VIEW_TYPE_SERVICE_LIST){

            final CommunityServiceViewHolder communityServiceViewHolder = new CommunityServiceViewHolder(eventCardView);
            communityServiceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EventCard eventCard = (EventCard) communityServiceViewHolder.itemView.getTag();
                    mListener.onEventCardClicked(eventCard);
                }
            });

            return communityServiceViewHolder;
        }

        else if (viewType == VIEW_TYPE_BROTHERHOOD_LIST){

            final BrotherhoodViewHolder brotherhoodViewHolder = new BrotherhoodViewHolder(eventCardView);
            brotherhoodViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EventCard eventCard = (EventCard) brotherhoodViewHolder.itemView.getTag();
                    mListener.onEventCardClicked(eventCard);
                }
            });

            return brotherhoodViewHolder;
        }

        else if (viewType == VIEW_TYPE_SOCIAL_LIST){

            final SocialViewHolder socialViewHolder = new SocialViewHolder(eventCardView);
            socialViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    EventCard eventCard = (EventCard) socialViewHolder.itemView.getTag();
                    mListener.onEventCardClicked(eventCard);
                }
            });

            return socialViewHolder;
        }

        else if (viewType == VIEW_TYPE_LIST_HEADER){

            return new AboutUsListHeaderViewHolder(listHeader);
        }

        throw new IllegalArgumentException(viewType + " is not supported in this adapter");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof  AboutUsMainHeaderViewHolder){

            AboutUsMainHeaderViewHolder aboutUsMainHeaderViewHolder = (AboutUsMainHeaderViewHolder) holder;
        }

        if (holder instanceof  CommunityServiceViewHolder){

            position--;
            if (mCommunityServiceEventCards.size() > 0) position--;

            EventCard eventCard = getmCommunityServiceEventCards().get(position);
            ((CommunityServiceViewHolder) holder).populate(mBaseActivity, eventCard);
        }

        if (holder instanceof BrotherhoodViewHolder){

            position--;
            if (mBrotherhoodEventCards.size() > 0){

                position--;
                position -= mCommunityServiceEventCards.size();
            }

            if (mBrotherhoodEventCards.size() > 0) position--;

            EventCard eventCard = getmBrotherhoodEventCards().get(position);
            ((BrotherhoodViewHolder) holder).populate(mBaseActivity, eventCard);
        }

        if (holder instanceof  SocialViewHolder){

            position--;
            if (mCommunityServiceEventCards.size() > 0){

                position--;
                position -= mCommunityServiceEventCards.size();
            }

            if (mBrotherhoodEventCards.size() > 0){

                position--;
                position -= mBrotherhoodEventCards.size();
            }

            if (mSocialEventCards.size() > 0) position--;

            EventCard eventCard = getmSocialEventCards().get(position);
            ((SocialViewHolder) holder).populate(mBaseActivity, eventCard);
        }

        if (holder instanceof  AboutUsListHeaderViewHolder){

            AboutUsListHeaderViewHolder aboutUsListHeaderViewHolder = (AboutUsListHeaderViewHolder) holder;
            int servicePosition = 1;
            int brotherhoodPosition = servicePosition + mCommunityServiceEventCards.size() + 1;
            int socialPosition = brotherhoodPosition + mBrotherhoodEventCards.size() + 1;

            if (position == servicePosition){

                aboutUsListHeaderViewHolder.populate("Community Service Events");
            }

            if (position == brotherhoodPosition){

                aboutUsListHeaderViewHolder.populate("Brother Events");
            }

            if (position == socialPosition){

                aboutUsListHeaderViewHolder.populate("Social Events");
            }
        }
    }

    @Override
    public int getItemCount() {

        int count = 1;

        if (mCommunityServiceEventCards.size() > 0){
            count += 1 + mCommunityServiceEventCards.size();
        }

        if (mBrotherhoodEventCards.size() > 0){
            count += 1 + mBrotherhoodEventCards.size();
        }

        if (mSocialEventCards.size() > 0){
            count += 1 + mSocialEventCards.size();
        }

        return count;
    }

    public interface AboutUsListener{

        void onEventCardClicked(EventCard eventCard);
    }
}
