package edu.umbc.cs.acmstudentchapter.hiteavotingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ratingsManager";

	// Contacts table name
	private static final String TABLE_RATINGS = "ratings";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_DATE = "date";
	private static final String KEY_RATINGS = "ratings";
	private static final String KEY_NUMBER_OF_VOTES = "numberofvotes";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_RATINGS_TABLE = "CREATE TABLE " + TABLE_RATINGS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_DATE + " TEXT,"
				+ KEY_RATINGS + " REAL,"
				+ KEY_NUMBER_OF_VOTES + " REAL" + ")";
		db.execSQL(CREATE_RATINGS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATINGS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new uer raing
	public void addUserRaintg(UserRating rating) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_DATE, rating.getDate()); // current date
		values.put(KEY_RATINGS, rating.getRating()); // Rating
		values.put(KEY_NUMBER_OF_VOTES, 1.0); // First vote

		// Inserting Row
		db.insert(TABLE_RATINGS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single user rating
	public UserRating getUserRating(String date) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_RATINGS, new String[] { KEY_ID, KEY_DATE, KEY_RATINGS, KEY_NUMBER_OF_VOTES }, KEY_DATE + "=?",
				new String[] { String.valueOf(date) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			UserRating aUserRating = new UserRating(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getDouble(2), cursor.getDouble(3));
			// return rating
			return aUserRating;
		}
		return null;
	}
	
	// Updating single user rating
	public int updateUserRating(UserRating aUserRating) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, aUserRating.getDatabaseReference());
		values.put(KEY_DATE, aUserRating.getDate());
		values.put(KEY_RATINGS, aUserRating.getRating());
		values.put(KEY_NUMBER_OF_VOTES, aUserRating.getVotes());

		// updating row
		return db.update(TABLE_RATINGS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(aUserRating.getDatabaseReference()) });
	}

//	// Getting All Contacts - NOT NEEDED NOW
//	public List<Contact> getAllContacts() {
//		List<Contact> contactList = new ArrayList<Contact>();
//		// Select All Query
//		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//
//		// looping through all rows and adding to list
//		if (cursor.moveToFirst()) {
//			do {
//				Contact contact = new Contact();
//				contact.setID(Integer.parseInt(cursor.getString(0)));
//				contact.setName(cursor.getString(1));
//				contact.setPhoneNumber(cursor.getString(2));
//				// Adding contact to list
//				contactList.add(contact);
//			} while (cursor.moveToNext());
//		}
//
//		// return contact list
//		return contactList;
//	}

//	// Deleting single contact - NOT REQUIRED NOW
//	public void deleteContact(Contact contact) {
//		SQLiteDatabase db = this.getWritableDatabase();
//		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//				new String[] { String.valueOf(contact.getID()) });
//		db.close();
//	}


//	// Getting contacts Count - NOT REQUIRED NOW
//	public int getContactsCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//
//		// return count
//		return cursor.getCount();
//	}
}