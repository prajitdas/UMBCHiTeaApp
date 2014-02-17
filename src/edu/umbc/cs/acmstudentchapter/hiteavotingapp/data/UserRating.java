/**
 * 
 */
package edu.umbc.cs.acmstudentchapter.hiteavotingapp.data;

/**
 * @author Prajit
 *
 */
public class UserRating {
	
	private float foodPresentationRating;
	private float foodQualityRating;
	private long timestampId;
	private float valueForMoneyRating;

	/**
	 * 
	 */
	public UserRating(float aFoodPresentationRating, float aFoodQualityRatingBar, float aValueForMoneyRating) {
		setTimestampId(System.currentTimeMillis());
		setFoodPresentationRating(aFoodPresentationRating);
		setFoodQualityRating(aFoodQualityRatingBar);
		setValueForMoneyRating(aValueForMoneyRating);
	}

	private float getFoodPresentationRating() {
		return foodPresentationRating;
	}

	private float getFoodQualityRating() {
		return foodQualityRating;
	}

	private long getTimestampId() {
		return timestampId;
	}

	private float getValueForMoneyRating() {
		return valueForMoneyRating;
	}

	private void setFoodPresentationRating(float foodPresentationRating) {
		this.foodPresentationRating = foodPresentationRating;
	}

	private void setFoodQualityRating(float foodQualityRating) {
		this.foodQualityRating = foodQualityRating;
	}

	private void setTimestampId(long timestampId) {
		this.timestampId = timestampId;
	}

	private void setValueForMoneyRating(float valueForMoneyRating) {
		this.valueForMoneyRating = valueForMoneyRating;
	}
	
	@Override
	public String toString() {
		return 	Long.toString(getTimestampId())
				+","
				+Float.toString(getFoodPresentationRating())
				+","
				+Float.toString(getFoodQualityRating())
				+","
				+Float.toString(getValueForMoneyRating());
	}
}