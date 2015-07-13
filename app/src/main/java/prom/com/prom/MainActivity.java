package prom.com.prom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;


public class MainActivity extends Activity implements View.OnClickListener{

    private Button mButtonLogin                         = null;
    private ProfileTracker profileTracker				= null;
    private CallbackManager callbackManager				= null;

    private RelativeLayout mLayoutProgress				= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initViews();
        initObjects();
        addListenersToViews();
    }

    private void initViews() {
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mLayoutProgress = (RelativeLayout) findViewById(R.id.layout_progress);
    }

    private void initObjects() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void addListenersToViews() {
        mButtonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_login:
                doLoginButtonAction();
                break;
        }
    }

    private void doLoginButtonAction() {
        mLayoutProgress.setVisibility(View.VISIBLE);
        ParseFacebookUtils.logInWithReadPermissionsInBackground(this, Arrays.asList("email", "user_about_me", "user_location", "public_profile"), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                mLayoutProgress.setVisibility(View.GONE);
                if (user == null) {
                    Toast.makeText(getApplicationContext(), "Uh oh. The user cancelled the Facebook login.", Toast.LENGTH_LONG).show();
                    Log.i("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Toast.makeText(getApplicationContext(), "User signed up and logged in through Facebook!", Toast.LENGTH_LONG).show();
                    Log.i("MyApp", "User signed up and logged in through Facebook!");
                } else {
                    Toast.makeText(getApplicationContext(), "User logged in through Facebook!", Toast.LENGTH_LONG).show();
                    Log.i("MyApp", "User logged in through Facebook!");
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
}
