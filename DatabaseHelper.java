package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tripbuddy.db";
    private static final int DATABASE_VERSION = 1;


    private static final String TABLE_MEMORIES = "memories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MOOD = "mood";
    private static final String COLUMN_MUSIC_TRACK = "music_track";


    private static final String TABLE_TRIPS = "trips";
    private static final String TABLE_TRIP_ACTIVITIES = "trip_activities";
    private static final String TABLE_TRIP_EXPENSES = "trip_expenses";

    private static final String COLUMN_TRIP_ID = "trip_id";
    private static final String COLUMN_DESTINATION = "destination";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_SUBTOTAL = "subtotal";
    private static final String COLUMN_DISCOUNT = "discount";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_DATE_CREATED = "date_created";

    private static final String COLUMN_ACTIVITY_NAME = "activity_name";
    private static final String COLUMN_ACTIVITY_COST = "activity_cost";
    private static final String COLUMN_IS_SELECTED = "is_selected";

    private static final String COLUMN_EXPENSE_DESCRIPTION = "expense_description";
    private static final String COLUMN_EXPENSE_COST = "expense_cost";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEMORIES_TABLE = "CREATE TABLE " + TABLE_MEMORIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_IMAGE_PATH + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_DATE + " INTEGER,"
                + COLUMN_MOOD + " TEXT,"
                + COLUMN_MUSIC_TRACK + " TEXT"
                + ")";
        db.execSQL(CREATE_MEMORIES_TABLE);

        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESTINATION + " TEXT,"
                + COLUMN_START_DATE + " TEXT,"
                + COLUMN_END_DATE + " TEXT,"
                + COLUMN_NOTES + " TEXT,"
                + COLUMN_SUBTOTAL + " REAL,"
                + COLUMN_DISCOUNT + " REAL,"
                + COLUMN_TOTAL + " REAL,"
                + COLUMN_DATE_CREATED + " INTEGER"
                + ")";
        db.execSQL(CREATE_TRIPS_TABLE);

        String CREATE_TRIP_ACTIVITIES_TABLE = "CREATE TABLE " + TABLE_TRIP_ACTIVITIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TRIP_ID + " INTEGER,"
                + COLUMN_ACTIVITY_NAME + " TEXT,"
                + COLUMN_ACTIVITY_COST + " REAL,"
                + COLUMN_IS_SELECTED + " INTEGER,"
                + "FOREIGN KEY(" + COLUMN_TRIP_ID + ") REFERENCES " + TABLE_TRIPS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(CREATE_TRIP_ACTIVITIES_TABLE);

        String CREATE_TRIP_EXPENSES_TABLE = "CREATE TABLE " + TABLE_TRIP_EXPENSES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TRIP_ID + " INTEGER,"
                + COLUMN_EXPENSE_DESCRIPTION + " TEXT,"
                + COLUMN_EXPENSE_COST + " REAL,"
                + "FOREIGN KEY(" + COLUMN_TRIP_ID + ") REFERENCES " + TABLE_TRIPS + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(CREATE_TRIP_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP_EXPENSES);
        onCreate(db);
    }


    public long addMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, memory.getTitle());
        values.put(COLUMN_DESCRIPTION, memory.getDescription());
        values.put(COLUMN_IMAGE_PATH, memory.getImagePath());
        values.put(COLUMN_LOCATION, memory.getLocation());
        values.put(COLUMN_DATE, memory.getDate().getTime());
        values.put(COLUMN_MOOD, memory.getMood());
        values.put(COLUMN_MUSIC_TRACK, memory.getMusicTrack());

        long id = db.insert(TABLE_MEMORIES, null, values);
        db.close();
        return id;
    }

    public List<Memory> getAllMemories() {
        List<Memory> memoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MEMORIES + " ORDER BY " + COLUMN_DATE + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                Memory memory = new Memory();
                memory.setId(cursor.getInt(0));
                memory.setTitle(cursor.getString(1));
                memory.setDescription(cursor.getString(2));
                memory.setImagePath(cursor.getString(3));
                memory.setLocation(cursor.getString(4));
                memory.setDate(new java.util.Date(cursor.getLong(5)));
                memory.setMood(cursor.getString(6));
                memory.setMusicTrack(cursor.getString(7));
                memoryList.add(memory);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return memoryList;
    }

    public int updateMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, memory.getTitle());
        values.put(COLUMN_DESCRIPTION, memory.getDescription());
        values.put(COLUMN_IMAGE_PATH, memory.getImagePath());
        values.put(COLUMN_LOCATION, memory.getLocation());
        values.put(COLUMN_DATE, memory.getDate().getTime());
        values.put(COLUMN_MOOD, memory.getMood());
        values.put(COLUMN_MUSIC_TRACK, memory.getMusicTrack());

        return db.update(TABLE_MEMORIES, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(memory.getId())});
    }

    public void deleteMemory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEMORIES, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Memory getMemory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MEMORIES, null, COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Memory memory = new Memory();
            memory.setId(cursor.getInt(0));
            memory.setTitle(cursor.getString(1));
            memory.setDescription(cursor.getString(2));
            memory.setImagePath(cursor.getString(3));
            memory.setLocation(cursor.getString(4));
            memory.setDate(new java.util.Date(cursor.getLong(5)));
            memory.setMood(cursor.getString(6));
            memory.setMusicTrack(cursor.getString(7));
            cursor.close();
            db.close();
            return memory;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }

    public long saveTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESTINATION, trip.getDestination());
        values.put(COLUMN_START_DATE, trip.getStartDate());
        values.put(COLUMN_END_DATE, trip.getEndDate());
        values.put(COLUMN_NOTES, trip.getNotes());
        values.put(COLUMN_SUBTOTAL, trip.getSubtotal());
        values.put(COLUMN_DISCOUNT, trip.getDiscount());
        values.put(COLUMN_TOTAL, trip.getTotal());
        values.put(COLUMN_DATE_CREATED, System.currentTimeMillis());

        long tripId = db.insert(TABLE_TRIPS, null, values);
        db.close();
        return tripId;
    }

    public void saveTripActivities(long tripId, List<PredefinedActivity> activities) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (PredefinedActivity activity : activities) {
            if (activity.isSelected()) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_TRIP_ID, tripId);
                values.put(COLUMN_ACTIVITY_NAME, activity.getName());
                values.put(COLUMN_ACTIVITY_COST, activity.getCost());
                values.put(COLUMN_IS_SELECTED, 1);
                db.insert(TABLE_TRIP_ACTIVITIES, null, values);
            }
        }
        db.close();
    }

    public void saveTripExpenses(long tripId, List<CustomExpense> expenses) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (CustomExpense expense : expenses) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TRIP_ID, tripId);
            values.put(COLUMN_EXPENSE_DESCRIPTION, expense.getDescription());
            values.put(COLUMN_EXPENSE_COST, expense.getCost());
            db.insert(TABLE_TRIP_EXPENSES, null, values);
        }
        db.close();
    }

    public List<Trip> getAllTrips() {
        List<Trip> tripList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRIPS + " ORDER BY " + COLUMN_DATE_CREATED + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                Trip trip = new Trip();
                trip.setId(cursor.getInt(0));
                trip.setDestination(cursor.getString(1));
                trip.setStartDate(cursor.getString(2));
                trip.setEndDate(cursor.getString(3));
                trip.setNotes(cursor.getString(4));
                trip.setSubtotal(cursor.getDouble(5));
                trip.setDiscount(cursor.getDouble(6));
                trip.setTotal(cursor.getDouble(7));
                trip.setDateCreated(cursor.getLong(8));
                tripList.add(trip);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tripList;
    }

    public List<PredefinedActivity> getTripActivities(int tripId) {
        List<PredefinedActivity> activities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRIP_ACTIVITIES, null, COLUMN_TRIP_ID + " = ?",
                new String[]{String.valueOf(tripId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                PredefinedActivity activity = new PredefinedActivity(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTIVITY_NAME)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_ACTIVITY_COST))
                );
                activity.setSelected(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_SELECTED)) == 1);
                activities.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return activities;
    }

    public List<CustomExpense> getTripExpenses(int tripId) {
        List<CustomExpense> expenses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRIP_EXPENSES, null, COLUMN_TRIP_ID + " = ?",
                new String[]{String.valueOf(tripId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                CustomExpense expense = new CustomExpense(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_EXPENSE_COST))
                );
                expense.setId(cursor.getInt(0));
                expense.setTripId(tripId);
                expenses.add(expense);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return expenses;
    }
}