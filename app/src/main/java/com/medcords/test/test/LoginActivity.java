package com.medcords.test.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {


    // User name EditText field
    EditText userNameField;

    // Password EditText field
    EditText passwordField;

    // Login button
    Button loginButton;
    // SharedPreference to save the token
    SharedPreferences sharedPref;
    // Make an ApiInterface type object
    private ApiInterface apiInterface;
    // To save the token we get
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.saved_token), MODE_PRIVATE);
        String token = sharedPref.getString(getString(R.string.token), "");

        // Check if the token is still valid or not
        if (token != "") {
            checkTokenValidity(token);
        }


        setContentView(R.layout.activity_login);

        // Set the user name EditText field
        userNameField = (EditText) findViewById(R.id.user_name);

        // Set the password EditText field
        passwordField = (EditText) findViewById(R.id.password);

        // Set the login button
        loginButton = (Button) findViewById(R.id.login_Button);

        // Set up the sharedPreference object
        sharedPref = this.getSharedPreferences(getString(R.string.saved_token), MODE_PRIVATE);


        // Attach OnclickListener to the login button
        // When the login button is clicked call the api
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                callApi();

             /*   // Create a new intent to open {@link HomeActivity}
                Intent activityChangeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                //Start the new activity
                startActivity(activityChangeIntent);*/
            }
        });
    }

    private void checkTokenValidity(final String oldToken) {

        // Make a TokenInput type object to pass to the api call
        TokenInput tokenInput = new TokenInput();
        tokenInput.setToken(oldToken);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiInterface.getResponseWithToken(tokenInput);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                // If the token matches previos token then Start the HomeActivity
                if (loginResponse.getToken() == oldToken) {

                    // Create a new intent to open {@link HomeActivity}
                    Intent activityChangeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    //Start the new activity
                    startActivity(activityChangeIntent);
                }
                else {
                    // Save the token to null
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(getString(R.string.token), "");
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void callApi() {
        // Get the userName
        String userName = userNameField.getText().toString().trim();

        // Get the password
        String password = passwordField.getText().toString().trim();

        // Make a PasswordInput type object to pass to the api call
        PasswordInput passwordInput = new PasswordInput();
        passwordInput.setUserName(userName);
        passwordInput.setPassword(password);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiInterface.getResponseWithPassWord(passwordInput);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                Log.v("LoginActivity", Integer.toString(response.code()));
                token = loginResponse.getToken();

                // Save the token into shared preference
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.token), token);
                editor.commit();

                // Create a new intent to open {@link HomeActivity}
                Intent activityChangeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                //Start the new activity
                startActivity(activityChangeIntent);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
