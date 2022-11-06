package edu.uga.cs.project4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProjectDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "ProjectDBHelper";

    private static final String DB_NAME = "project.db";
    private static final int DB_VERSION = 1;

    /** Questions Table */
    //maybe leave out the Question???
    public static final String TABLE_QUESTIONS = "questions";
    public static final String QUESTIONS_COLUMN_ID = "_id";
    //public static final String QUESTIONS_COLUMN_Q = "q";
    public static final String QUESTIONS_COLUMN_CAPITAL = "capital";
    public static final String QUESTIONS_COLUMN_FIRST = "first";
    public static final String QUESTIONS_COLUMN_SECOND = "second";
    public static final String QUESTIONS_COLUMN_STATE = "state";

    /** Quiz Table */
    public static final String TABLE_QUIZ = "quiz";
    public static final String QUIZ_COLUMN_ID = "_id";
    public static final String QUIZ_COLUMN_DATE = "date";
    public static final String QUIZ_COLUMN_Q1 = "q1"; //can column names have a number???
    public static final String QUIZ_COLUMN_Q2 = "q2";
    public static final String QUIZ_COLUMN_Q3 = "q3";
    public static final String QUIZ_COLUMN_Q4 = "q4";
    public static final String QUIZ_COLUMN_Q5 = "q5";
    public static final String QUIZ_COLUMN_Q6 = "q6";
    public static final String QUIZ_COLUMN_RESULT = "result";
    public static final String QUIZ_COLUMN_ANSWERED = "answered";

    //reference to only instance of helper
    private static ProjectDBHelper helperInstance;

    // A Create Table SQL statement to create a table for questions.
    // auto increment primary key is _id
    private static final String CREATE_QUESTIONS =
            "create table " + TABLE_QUESTIONS + " ("
                + QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + QUESTIONS_COLUMN_Q + " TEXT, "
                + QUESTIONS_COLUMN_CAPITAL + " TEXT, "
                + QUESTIONS_COLUMN_FIRST + " TEXT, "
                + QUESTIONS_COLUMN_SECOND + " TEXT, "
                + QUESTIONS_COLUMN_STATE + " TEXT "
            + ")"; //add ; after ) or not?

    // A Create Table SQL statement to create a table for the quiz.
    // each question is foreign key constraint to the questions table
    private static final String CREATE_QUIZ =
            "create table " + TABLE_QUIZ + " ("
                + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUIZ_COLUMN_DATE + " TEXT, "
                + QUIZ_COLUMN_Q1 + " TEXT, "
                + QUIZ_COLUMN_Q2 + " TEXT, "
                + QUIZ_COLUMN_Q3 + " TEXT, "
                + QUIZ_COLUMN_Q4 + " TEXT, "
                + QUIZ_COLUMN_Q5 + " TEXT, "
                + QUIZ_COLUMN_Q6 + " TEXT, "
                + QUIZ_COLUMN_RESULT + " TEXT, "
                + QUIZ_COLUMN_ANSWERED + " TEXT, "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q1 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + "), "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q2 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + "), "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q3 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + "), "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q4 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + "), "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q5 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + "), "
                + " FOREIGN KEY (" + QUIZ_COLUMN_Q6 + ") REFERENCES " + TABLE_QUESTIONS + "(" + QUESTIONS_COLUMN_ID + ") "
            + ")";


    /**
     * CREATE TABLE "Quiz" (
     * "id" INTEGER,
     * "date" INTEGER,
     * "q1" INTEGER,
     * "q2" INTEGER,
     * "q3" INTEGER,
     * "q4" INTEGER,
     * "q5" INTEGER,
     * "q6" INTEGER,
     * "result" INTEGER,
     * "answered" INTEGER,
     * PRIMARY KEY("id" AUTOINCREMENT),
     * FOREIGN KEY("q2") REFERENCES "Questions"("id"),
     * FOREIGN KEY("q3") REFERENCES "Questions"("id"),
     * FOREIGN KEY("q4") REFERENCES "Questions"("id"),
     * FOREIGN KEY("q1") REFERENCES "Questions"("id"),
     * FOREIGN KEY("q5") REFERENCES "Questions"("id")
     *
     * );
     */

    /**
     * Seperate table that isMemberOf the other two tables
     * Store the result, date, and QuizID
     * IDK if you would need this thought since the Quiz table should store all this info
     * @param context
     */

    // A Create Table SQL statement to create a table for quiz
    // auto increment primary key is _id
    // each question is a foreign key to the Questions table


    //private constructor, use getInstance() to access it
    private ProjectDBHelper( Context context ) { super( context, DB_NAME, null, DB_VERSION ); }

    // Access method to the single instance of the class.
    public static synchronized ProjectDBHelper getInstance( Context context ) {
        if (helperInstance == null) {
            helperInstance = new ProjectDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    // We must override onCreate method, which will be used to create the database if
    // it does not exist yet.
    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUESTIONS );
        db.execSQL( CREATE_QUIZ );
        //same for quiz table
        Log.d( DEBUG_TAG, "Table " + TABLE_QUESTIONS + " created" );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZ + " created" );
        //same for quiz table
    }

    // We should override onUpgrade method, which will be used to upgrade the database if
    // its version (DB_VERSION) has changed.  This will be done automatically by Android
    // if the version will be bumped up, as we modify the database schema.
    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_QUESTIONS );
        db.execSQL( "drop table if exists " + TABLE_QUIZ );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUESTIONS + " upgraded" );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZ + " upgraded" );
    }



}
