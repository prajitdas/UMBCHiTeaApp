package edu.umbc.cs.acmstudentchapter.hiteavotingapp;

import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.DatabaseHandler;
import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.UserRating;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	private RatingBar foodPresentationRatingBar;
	private RatingBar foodQualityRatingBar;
	private RatingBar valueForMoneyRatingBar;

	private double foodPresentationRating;
	private double foodQualityRating;
	private double valueForMoneyRating;
	
	private UserRating currentUserRating;
	
	private Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		currentUserRating = new UserRating();
		addListenerOnFoodPresentationRatingBar();
		addListenerOnValueForMoneyRatingBar();
		addListenerOnFoodQualityRatingBar();
		addListenerOnButton();
		storeInDatabase(getCurrentUserRating());
	}

	private void storeInDatabase(UserRating aUserRating) {
        DatabaseHandler db = new DatabaseHandler(this);
        
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        try {
        	UserRating tempUserRating = db.getUserRating(aUserRating.getDate());
        	tempUserRating.setRating(tempUserRating.getRating()+aUserRating.getRating());
        	tempUserRating.setVotes(tempUserRating.getVotes()+1.0);
        	db.updateUserRating(tempUserRating);
    		Toast.makeText(MainActivity.this, aUserRating.toString(), Toast.LENGTH_LONG).show();
        }
        catch (Exception someExcpetion) {
        	db.addUserRaintg(aUserRating);
        }
	}

	private void addListenerOnButton() {
		submitButton = (Button) findViewById(R.id.submit_button);
		
		//if click on me, then display the current rating value.
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setCurrentUserRating(new UserRating(foodPresentationRating, foodQualityRating, valueForMoneyRating));
				//This is the point where the data has been collected and needs to be stored
//				Toast.makeText(MainActivity.this,
//						newUserRating.toString(),
//						Toast.LENGTH_LONG).show();
			}
		});
	}

	private void addListenerOnFoodQualityRatingBar() {
		foodQualityRatingBar = (RatingBar) findViewById(R.id.food_quality_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		foodQualityRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setFoodQualityRating((double) rating);
			}
		});
	}

	private void addListenerOnValueForMoneyRatingBar() {
		valueForMoneyRatingBar = (RatingBar) findViewById(R.id.value_for_money_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		valueForMoneyRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setValueForMoneyRating((double) rating);
			}
		});
	}

	private void addListenerOnFoodPresentationRatingBar() {
		foodPresentationRatingBar = (RatingBar) findViewById(R.id.food_presentation_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		foodPresentationRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setFoodPresentationRating((double) rating);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public double getFoodPresentationRating() {
		return foodPresentationRating;
	}

	public void setFoodPresentationRating(double rating) {
		this.foodPresentationRating = rating;
	}

	public double getFoodQualityRating() {
		return foodQualityRating;
	}

	public void setFoodQualityRating(double foodQualityRating) {
		this.foodQualityRating = foodQualityRating;
	}

	public double getValueForMoneyRating() {
		return valueForMoneyRating;
	}

	public void setValueForMoneyRating(double valueForMoneyRating) {
		this.valueForMoneyRating = valueForMoneyRating;
	}

	public UserRating getCurrentUserRating() {
		return currentUserRating;
	}

	public void setCurrentUserRating(UserRating currentUserRating) {
		this.currentUserRating = currentUserRating;
	}

}