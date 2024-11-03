package com.example.movieposter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * MainActivity is the entry point of the application, displaying a list of movie posters
 * and allowing users to select movies to add to their watchlist.
 */
public class MainActivity extends AppCompatActivity implements PosterListener {
    private Button buttonAddToWatchList;

    /**
     * Initializes the activity, sets up the Edge-to-Edge layout, and populates the RecyclerView
     * with movie poster data. It also sets up the button to display selected movies in a Toast message.
     *
     * @param savedInstanceState The saved instance state for restoring the activity state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView posterRecyclerView = findViewById(R.id.posterRecyclerView);
        buttonAddToWatchList = findViewById(R.id.buttonAddToWatchlist);

        //prepare data

        List<Poster> posterList = new ArrayList<>();




        final PosterAdapter posterAdapter = new PosterAdapter(posterList,this);
        posterRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPosters();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++){
                    if (i == 0){
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }

                }
                Toast.makeText(MainActivity.this, posterNames.toString(),Toast.LENGTH_SHORT).show();
            }
        });


        Poster thor = new Poster();
        thor.image = R.drawable.download1;
        thor.name = "THOR The Dark World";
        thor.createdBy = "Disney";
        thor.rating = 5f;
        thor.story = "A movie about Thor";
        posterList.add(thor);

        Poster frightNight = new Poster();
        frightNight.image = R.drawable.download2;
        frightNight.name = "Fright Night";
        frightNight.createdBy = "Tom Holland";
        frightNight.rating = 4.2f;
        frightNight.story = "There are some very good reasons to be afraid of the dark";
        posterList.add(frightNight);

        Poster hobbit = new Poster();
        hobbit.image = R.drawable.download3;
        hobbit.name = "Hobbit";
        hobbit.createdBy = "J.R.R Tolkien";
        hobbit.rating = 2.4f;
        hobbit.story = "A movie about a Hobbit";
        posterList.add(hobbit);

        Poster harryPotter = new Poster();
        harryPotter.image = R.drawable.download4;
        harryPotter.name = "Harry Potter";
        harryPotter.createdBy = "RON";
        harryPotter.rating = 4.2f;
        harryPotter.story = "A movie about a sorcerer";
        posterList.add(harryPotter);

        Poster miami = new Poster();
        miami.image = R.drawable.download5;
        miami.name = "Miami";
        miami.createdBy = "Dwane the Rock";
        miami.rating = 4.2f;
        miami.story = "A movie about Miami";
        posterList.add(miami);

        Poster arrival = new Poster();
        arrival.image = R.drawable.download6;
        arrival.name = "Arrival";
        arrival.createdBy = "Amy Adams";
        arrival.rating = 4.2f;
        arrival.story = "A movie about aliens";
        posterList.add(arrival);

        Poster sweetPepper = new Poster();
        sweetPepper.image = R.drawable.download7;
        sweetPepper.name = "My Sweet Pepper Land";
        sweetPepper.createdBy = "Korkmaz Arslan";
        sweetPepper.rating = 4.2f;
        sweetPepper.story = "A movie about farming";
        posterList.add(sweetPepper);

        Poster coverVersion = new Poster();
        coverVersion.image = R.drawable.download8;
        coverVersion.name = "Cover Version";
        coverVersion.createdBy = "Mark Jonson";
        coverVersion.rating = 1.7f;
        coverVersion.story = "A movie about singing";
        posterList.add(coverVersion);

        Poster walkingEnemy = new Poster();
        walkingEnemy.image = R.drawable.download9;
        walkingEnemy.name = "Walking with the Enemy";
        walkingEnemy.createdBy = "Mark Bryan";
        walkingEnemy.rating = 5f;
        walkingEnemy.story = "A movie about deception";
        posterList.add(walkingEnemy);

        Poster winniePooh = new Poster();
        winniePooh.image = R.drawable.download10;
        winniePooh.name = "Winnie the Pooh Blood and Honey";
        winniePooh.createdBy = "Jack Black";
        winniePooh.rating = 1.1f;
        winniePooh.story = "A movie about a bear that likes honey";
        posterList.add(winniePooh);


    }

    /**
     * Displays or hides the "Add to Watchlist" button based on whether any posters are selected.
     *
     * @param isSelected Indicates if at least one poster is selected.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if(isSelected){
            buttonAddToWatchList.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchList.setVisibility(View.GONE);
        }
    }
}