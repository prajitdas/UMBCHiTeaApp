package edu.umbc.cs.acmstudentchapter.hiteavotingapp;

import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.UserRating;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class MainActivity extends Activity {

	private RatingBar foodPresentationRatingBar;
	private RatingBar foodQualityRatingBar;
	private RatingBar valueForMoneyRatingBar;

	private float foodPresentationRating;
	private float foodQualityRating;
	private float valueForMoneyRating;
	
	private Button submitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnFoodPresentationRatingBar();
		addListenerOnValueForMoneyRatingBar();
		addListenerOnFoodQualityRatingBar();
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		submitButton = (Button) findViewById(R.id.submit_button);

		//if click on me, then display the current rating value.
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				UserRating newUserRating = new UserRating(foodPresentationRating, foodQualityRating, valueForMoneyRating);
				Toast.makeText(MainActivity.this,
						newUserRating.toString(),
						Toast.LENGTH_LONG).show();
			}
		});
	}

	private void addListenerOnFoodQualityRatingBar() {
		foodQualityRatingBar = (RatingBar) findViewById(R.id.food_quality_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		foodQualityRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setFoodQualityRating(rating);
			}
		});
	}

	private void addListenerOnValueForMoneyRatingBar() {
		valueForMoneyRatingBar = (RatingBar) findViewById(R.id.value_for_money_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		valueForMoneyRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setValueForMoneyRating(rating);
			}
		});
	}

	private void addListenerOnFoodPresentationRatingBar() {
		foodPresentationRatingBar = (RatingBar) findViewById(R.id.food_presentation_ratingbar);

		//if rating is changed,
		//store the current rating value in the result (float) automatically
		foodPresentationRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				setFoodPresentationRating(rating);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public float getFoodPresentationRating() {
		return foodPresentationRating;
	}

	public void setFoodPresentationRating(float rating) {
		this.foodPresentationRating = rating;
	}

	public float getFoodQualityRating() {
		return foodQualityRating;
	}

	public void setFoodQualityRating(float foodQualityRating) {
		this.foodQualityRating = foodQualityRating;
	}

	public float getValueForMoneyRating() {
		return valueForMoneyRating;
	}

	public void setValueForMoneyRating(float valueForMoneyRating) {
		this.valueForMoneyRating = valueForMoneyRating;
	}

}