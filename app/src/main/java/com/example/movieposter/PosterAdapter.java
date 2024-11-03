package com.example.movieposter;

import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * PosterAdapter is a RecyclerView.Adapter that binds Poster data to each item view in the RecyclerView,
 * allowing the user to interact with each poster item (such as selecting it to add to a watchlist).
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));

    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster> posterList;
    /**
     * Constructor for PosterAdapter.
     *
     * @param posterList      List of Poster objects to bind to the RecyclerView.
     * @param posterListener  Listener for handling poster selection actions.
     */
    public PosterAdapter(List<Poster> posterList, PosterListener posterListener) {
        this.posterList = posterList;
        this.posterListener = posterListener;
    }


    private PosterListener posterListener;

    /**
     * Returns a list of selected Poster objects.
     *
     * @return List of selected posters.
     */
    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }


    class PosterViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layoutPosters;
        View viewBackground;

        RoundedImageView imagePoster;
        TextView textName, textCreateBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Constructor for PosterViewHolder.
         *
         * @param itemView The item view of the RecyclerView item.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreateBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds the Poster object to the item view, displaying its data and handling selection state.
         *
         * @param poster The Poster object containing data for the item view.
         */
        void bindPoster(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreateBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            if(poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else{
                viewBackground.setBackgroundResource(R.drawable.poster_backgroung);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_backgroung);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            posterListener.onPosterAction(false);
                        }
                    } else{
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected=true;
                        posterListener.onPosterAction(true);
                    }

                }
            });



        }




    }
}
