package edu.uga.cs.project4;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    ProjectDBHelper projectDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        projectDBHelper=ProjectDBHelper.getInstance(this);

        // assigning ID of the toolbar to a variable
        toolbar = findViewById( R.id.toolbar );

        // using toolbar as ActionBar
        setSupportActionBar( toolbar );

        // Find our drawer view
        drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled( true );
        drawerToggle.syncState();

        // Connect DrawerLayout events to the ActionBarToggle
        drawerLayout.addDrawerListener( drawerToggle );

        // Find the drawer view
        navigationView = findViewById( R.id.nvView );
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem( menuItem );
                    return true;
                });

        readAndInsertQuestions();


    }

    public void selectDrawerItem( MenuItem menuItem ) {
        Fragment fragment = null;

        // Create a new fragment based on the used selection in the nav drawer
        switch( menuItem.getItemId() ) {
            case R.id.menu_new:
                fragment = new QuizFragment();
                break;
            case R.id.menu_review:
                fragment = new PastQuizesFragment();
                break;
            case R.id.menu_help:
                fragment = new HelpFragment();
                break;
            case R.id.menu_close:
                finish();
                return;
            default:
                return;
        }

        // Set up the fragment by replacing any existing fragment in the main activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace( R.id.fragmentContainerView, fragment).addToBackStack("main screen" ).commit();

        /*
        // this is included here as a possible future modification
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked( true );
        // Set action bar title
        setTitle( menuItem.getTitle());
         */

        // Close the navigation drawer
        drawerLayout.closeDrawers();






    }

    private void readAndInsertQuestions() {


        AsyncTask<String,Boolean> task=new AsyncTask<String,Boolean>() {
            @Override
            protected Boolean doInBackground(String... arguments)
            {


                if(! projectDBHelper.getQuestions().isEmpty()) //if there is data in database then dont do anything
                {
                    return false;
                }

                //otherwise read csv and insert data in database
                AssetManager assetManager = getAssets();
                InputStream is = null;
                try {
                    is = assetManager.open("state_capitals.csv");
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));


                try {
                 //State,Capital city,Second city,Third city,

                    String line =bufferedReader.readLine(); //Skiping first line

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        String state=stringTokenizer.nextToken();
                        String capital=stringTokenizer.nextToken();
                        String firstCity=stringTokenizer.nextToken();
                        String secondCity=stringTokenizer.nextToken();
                        String question="What is the capital of "+state+" ?";
                        Question questionObj=new Question();
                        questionObj.setQuestion(question);
                        questionObj.setCapital(capital);
                        questionObj.setState(state);
                        questionObj.setFirstCity(firstCity);
                        questionObj.setSecondCity(secondCity);
                        projectDBHelper.insertQuestion(questionObj);
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                    return false;
                }

                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {


                if(isSuccess)
                    Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_SHORT).show();

            }

        };

        task.execute();
    }


    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close );
    }

}
