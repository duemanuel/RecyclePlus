package br.com.hackathongdg.recycleplus.login;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.IOException;

import br.com.hackathongdg.recycleplus.R;
import br.com.hackathongdg.recycleplus.products.ProductsActivity;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 42;
    private static final String SERVER_CLIENT_ID = "75823728596-6n45tkcpc1tlj20o99okshb3e3rtgtkk.apps.googleusercontent.com";

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton mSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();

        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShouldResolve = true;
                mGoogleApiClient.connect();

                Toast.makeText(v.getContext(), "Signing in...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;

        // Show the signed-in UI
        onGoogleSignIn();
    }

    private void onTokenSentToServer() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);

        finish();
    }

    private void onGoogleSignIn() {
        new GetIdTokenTask().execute();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                showSignInError(connectionResult.getErrorMessage());
            }
        } else {
            showSignedOutUI();
        }
    }

    private void showSignedOutUI() {
        // Signed out UI is this own activity - no need to do anything.
    }

    private void showSignInError(String message) {
        Toast.makeText(this, "Não foi possível fazer login.\n" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    private class GetIdTokenTask extends AsyncTask<Void, Void, Pair<String, String>> {

        private ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = ProgressDialog.show(LoginActivity.this, null, "Aguarde...", true, false);
        }

        @Override
        protected Pair<String, String> doInBackground(Void... params) {
            String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
            Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
            String scopes = "audience:server:client_id:" + SERVER_CLIENT_ID; // Not the app's client ID.
            try {
                String token = GoogleAuthUtil.getToken(getApplicationContext(), account, scopes);
                return new Pair<>(accountName, token);
            } catch (IOException e) {
                Log.e(TAG, "Error retrieving ID token.", e);
                return null;
            } catch (GoogleAuthException e) {
                Log.e(TAG, "Error retrieving ID token.", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(Pair<String, String> result) {
            Log.i(TAG, "ID pair: " + result);
            if (result != null) {
                Log.i(TAG, "ID token: " + result.second);
                // Successfully retrieved ID Token

                final ParseUser user = new ParseUser();
                final String email = result.first;
                final String password = result.second;
                final String username = result.first.substring(0, result.first.indexOf('@'));
                user.setEmail(email);
                user.setUsername(username);
                user.setPassword(password);
                user.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e != null) {
                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    mProgressDialog.dismiss();
                                    if (e == null) {
                                        onTokenSentToServer();
                                    } else {
                                        Log.e(TAG, "Could not send ID token: " + e.getMessage());
                                        showSignInError(e.getMessage());
                                    }
                                }
                            });
                        } else {
                            mProgressDialog.dismiss();
                            onTokenSentToServer();
                        }
                    }
                });

            } else {
                mProgressDialog.dismiss();
                // There was some error getting the ID Token
                showSignInError("Não conseguimos obter os dados de sua conta no Google.");
            }
        }

    }

}
