package edu.umbc.cs.acmstudentchapter.hiteavotingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.DatabaseHandler;
import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.UserRating;

public class MainActivity extends Activity {

	private RatingBar foodPresentationRatingBar;
	private RatingBar foodQualityRatingBar;
	private RatingBar valueForMoneyRatingBar;

	private double foodPresentationRating = 1.0;
	private double foodQualityRating = 1.0;
	private double valueForMoneyRating = 1.0;
	
	private UserRating currentUserRating;
	
	private Button submitButton;
	private Button resultsButton;
	
	private static DatabaseHandler db;
	
	public static DatabaseHandler getDb() {
		return db;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		db = new DatabaseHandler(this);
    	currentUserRating = new UserRating();
		
		foodQualityRatingBar = (RatingBar) findViewById(R.id.food_quality_ratingbar);
		valueForMoneyRatingBar = (RatingBar) findViewById(R.id.value_for_money_ratingbar);
		foodPresentationRatingBar = (RatingBar) findViewById(R.id.food_presentation_ratingbar);

		submitButton = (Button) findViewById(R.id.submit_button);
    	submitButton.setEnabled(true);

    	resultsButton = (Button) findViewById(R.id.results_button);
    	// use this part of the code to see results 
    	resultsButton.setVisibility(View.GONE);

    	addListenerOnFoodPresentationRatingBar();
		addListenerOnValueForMoneyRatingBar();
		addListenerOnFoodQualityRatingBar();
		addListenerOnButton();
	}

	private void storeInDatabase(UserRating aUserRating) {
        Log.v("Msg", Environment.getDataDirectory().toString());
        
        try {
        	UserRating tempUserRating = db.getUserRating(aUserRating.getDate());
//        	Log.v("Msg", "I was here"+tempUserRating.toString());
        	tempUserRating.setRating(tempUserRating.getRating()+aUserRating.getRating());
        	tempUserRating.setVotes(tempUserRating.getVotes()+1.0);
        	db.updateUserRating(tempUserRating);
//            Log.v("Msg", Environment.getDataDirectory().toString());
//    		Toast.makeText(MainActivity.this, tempUserRating.toString(), Toast.LENGTH_LONG).show();
//        	Log.v("Msg", "I was here"+tempUserRating.toString());
        }
        catch (Exception someExcpetion) {
        	db.addUserRating(aUserRating);
//        	Log.v("Msg", "I was here for the first time"+aUserRating.toString());
        }
	}

	private void addListenerOnButton() {
		//if click on me, then display the current rating value.
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setCurrentUserRating(new UserRating(foodPresentationRating, foodQualityRating, valueForMoneyRating));
				
				Toast aToast = Toast.makeText(MainActivity.this,"Your rating is being submitted!\nThank you!",Toast.LENGTH_LONG);
				aToast.setGravity(Gravity.CENTER, 0, 0);
				aToast.setDuration(5000);// Now you can set Toast Duration in Milliseconds here,its 1-sec Now
				aToast.show();
				
				//This is the point where the data has been collected and needs to be stored
				storeInDatabase(getCurrentUserRating());
				submitButton.setEnabled(false);
				submitButton.setText(R.string.wait);
		        new Handler().postDelayed(new Runnable() {
		            @Override
		            public void run() {
		            	submitButton.setEnabled(true);
						submitButton.setText(R.string.submit);
		            }}, 7000);
		        foodPresentationRatingBar.setRating(1.0f);
		    	foodQualityRatingBar.setRating(1.0f);
		    	valueForMoneyRatingBar.setRating(1.0f);
			}
		});
		resultsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),
						ResultsActivity.class);
				startActivity(myIntent);			}
		});
	}

	private void addListenerOnFoodQualityRatingBar() {
		//if rating is changed,
		//store the current rating value in the result (float) automatically
		foodQualityRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setFoodQualityRating((double) rating);
			}
		});
	}

	private void addListenerOnValueForMoneyRatingBar() {
		//if rating is changed,
		//store the current rating value in the result (float) automatically
		valueForMoneyRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setValueForMoneyRating((double) rating);
			}
		});
	}

	private void addListenerOnFoodPresentationRatingBar() {
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