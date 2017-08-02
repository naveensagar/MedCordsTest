package com.medcords.test.test.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.medcords.test.test.api.ApiClient;
import com.medcords.test.test.api.ApiInterface;
import com.medcords.test.test.model.LastLoginResponse;
import com.medcords.test.test.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    // Make an ApiInterface type object
    private ApiInterface apiInterface;


    TextView welcomeTextView;

    // SharedPreference object
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeTextView = (TextView) findViewById(R.id.welcome_message);


        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_token),MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.token), "");

        welcomeText(token);

    }
    private void welcomeText(String token) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        Call<LastLoginResponse> call = apiInterface.getLastLoginDetails(token);

        call.enqueue(new Callback<LastLoginResponse>() {
            @Override
            public void onResponse(Call<LastLoginResponse> call, Response<LastLoginResponse> response) {

                // If the response code is 401 than start {@LoginActivity}
                if (response.code() == 401) {
                    // Create a new intent to open {@link LoginActivity}
                    Intent activityChangeIntent = new Intent(HomeActivity.this, LoginActivity.class);
                    //Start the new activity
                    startActivity(activityChangeIntent);
                } else {
                    LastLoginResponse lastLoginResponse = response.body();
                    setWelcomeTextView(lastLoginResponse);
                }
            }

            @Override
            public void onFailure(Call<LastLoginResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, getString(R.string.failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setWelcomeTextView(LastLoginResponse lastLoginResponse) {
        if(lastLoginResponse != null) {
            String userName = lastLoginResponse.getUserName();
            String device = lastLoginResponse.getDevice();
            String lastLoginDate = lastLoginResponse.getLastLoginDate();

            String welcomeText = "Welcome "+ userName +", Your last login was at " + lastLoginDate +
                    " attempted from "+ device+".";
            welcomeTextView.setText(welcomeText);
        }

    }

}
