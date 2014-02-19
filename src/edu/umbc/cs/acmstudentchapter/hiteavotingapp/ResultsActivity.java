package edu.umbc.cs.acmstudentchapter.hiteavotingapp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.DatabaseHandler;
import edu.umbc.cs.acmstudentchapter.hiteavotingapp.data.UserRating;

public class ResultsActivity extends Activity {

	private TextView resultsTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		resultsTextView = (TextView) findViewById(R.id.results_text_view);
		DatabaseHandler db = MainActivity.getDb();
		ArrayList<UserRating> userRatingList = db.getAllContacts();
		
		resultsTextView.setText(userRatingList.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}

}