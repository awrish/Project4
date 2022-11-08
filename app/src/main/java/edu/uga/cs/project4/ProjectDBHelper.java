package edu.uga.cs.project4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is a SQLiteOpenHelper class, which Android uses to create, upgrade, delete an SQLite database
 * in an app.
 *
 * This class is a singleton, following the Singleton Design Pattern.
 * Only one instance of this class will exist.  To make sure, the
 * only constructor is private.
 * Access to the only instance is via the getInstance method.
 *
 * Used throughout the project to access the DB and perform actions upon it.
 */
public class ProjectDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "ProjectDBHelper";

    private static final String DB_NAME = "project.db";
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    /** Questions Table */
    public static final String TABLE_QUESTIONS = "questions";
    public static final String QUESTIONS_COLUMN_ID = "_id";
    public static final String QUESTIONS_COLUMN_Q = "q";
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
                + QUESTIONS_COLUMN_Q + " TEXT, "
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


    // Gets the Questions from the DB
    public List<Question> getQuestions()
    {
        db=this.getReadableDatabase();
        String[] Columns = {QUESTIONS_COLUMN_ID,QUESTIONS_COLUMN_Q,QUESTIONS_COLUMN_CAPITAL,QUESTIONS_COLUMN_FIRST,QUESTIONS_COLUMN_SECOND,QUESTIONS_COLUMN_STATE};
        List<Question> questionList=new ArrayList<>();
        Cursor cursor = db.query(TABLE_QUESTIONS, Columns, null, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            Question question=new Question(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            questionList.add(question);
        }

        List<Question> sixQuestionList=new ArrayList<>();

        if(!questionList.isEmpty())
        {
            int totalSize=questionList.size();

            while (sixQuestionList.size()!=6)
            {
                int randomIndex=new Random().nextInt(totalSize);
                Question randomQuestion=questionList.get(randomIndex);
                if(!sixQuestionList.contains(randomQuestion))
                {
                    sixQuestionList.add(randomQuestion);
                }

            }

        }

        return sixQuestionList;
    }

    //Inserts a single question into the Database
    public boolean insertQuestion(Question  question)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QUESTIONS_COLUMN_Q,question.getQuestion());
        cv.put(QUESTIONS_COLUMN_CAPITAL,question.getCapital());
        cv.put(QUESTIONS_COLUMN_FIRST,question.getFirstCity());
        cv.put(QUESTIONS_COLUMN_SECOND,question.getSecondCity());
        cv.put(QUESTIONS_COLUMN_STATE,question.getState());
        return  db.insert(TABLE_QUESTIONS, null, cv)>0;
    }


    /**
     * Gets the saved quiz results from previously taken quizzes.
     * @return
     */
    public List<Quiz> getSavedQuizResults()
    {
        db=this.getReadableDatabase();
        String[] Columns = {QUIZ_COLUMN_DATE,QUIZ_COLUMN_Q1,QUIZ_COLUMN_Q2,QUIZ_COLUMN_Q3,QUIZ_COLUMN_Q4,QUIZ_COLUMN_Q5,QUIZ_COLUMN_Q6,QUIZ_COLUMN_ANSWERED,QUIZ_COLUMN_RESULT};
        List<Quiz> quizArrayList=new ArrayList<>();
        Cursor cursor = db.query(TABLE_QUIZ, Columns, null, null, null, null, QUIZ_COLUMN_DATE+" DESC");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            Quiz quiz=new Quiz(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            quizArrayList.add(quiz);
        }


        return quizArrayList;
    }

    //Inserts a quiz result so it can be displayed in the 'Review prevous quizzes' tab
    public boolean insertQuizResult(Quiz  quiz)
    {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QUIZ_COLUMN_DATE,quiz.getDate());
        cv.put(QUIZ_COLUMN_Q1,quiz.getQ1());
        cv.put(QUIZ_COLUMN_Q2,quiz.getQ2());
        cv.put(QUIZ_COLUMN_Q3,quiz.getQ3());
        cv.put(QUIZ_COLUMN_Q4,quiz.getQ4());
        cv.put(QUIZ_COLUMN_Q5,quiz.getQ5());
        cv.put(QUIZ_COLUMN_Q6,quiz.getQ6());
        cv.put(QUIZ_COLUMN_RESULT,quiz.getScore());
        cv.put(QUIZ_COLUMN_ANSWERED,quiz.getQuestionAnswered());
        return  db.insert(TABLE_QUIZ, null, cv)>0;
    }


}
