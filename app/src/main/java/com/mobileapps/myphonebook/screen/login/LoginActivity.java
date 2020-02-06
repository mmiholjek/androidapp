package com.mobileapps.myphonebook.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.mobileapps.myphonebook.R;
import com.mobileapps.myphonebook.screen.home.HomeActivity;
import com.mobileapps.myphonebook.util.Validator;
import com.mobileapps.myphonebook.util.ViewUtil;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    // UI components
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private SignInButton signin;
    private GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;

    /**
     * Lifecycle methods
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        setupView();
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        addTextWatchers();
    }


    @Override
    protected void onStop() {
        super.onStop();
        removeTextWatchers();
    }

    /**
     * Private methods
     */
    private void setupView() {
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> submitForm());
    }

    private void submitForm() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (!Validator.isValidEmail(email)) {
            showInvalidCredentialsMessage();
            return;
        }
        if (!Validator.isValidPassword(password)) {
            showInvalidCredentialsMessage();
            return;
        }
        loginViewModel.loginUser(email, password).observe(this, user -> {
            if (user == null) {
                showInvalidCredentialsMessage();
                return;
            }
            Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        });
    }

    private void showInvalidCredentialsMessage() {
        LinearLayout loginActivityLayout = findViewById(R.id.loginActivityLayout);
        Snackbar.make(loginActivityLayout, getString(R.string.error_invalid_credentials), Snackbar.LENGTH_SHORT).show();
    }

    private void addTextWatchers() {
        emailEditText.addTextChangedListener(emailTextWatcher);
        passwordEditText.addTextChangedListener(passwordTextWatcher);
    }

    private void removeTextWatchers() {
        emailEditText.removeTextChangedListener(emailTextWatcher);
        passwordEditText.removeTextChangedListener(passwordTextWatcher);
    }

    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            TextInputLayout emailInputLayout = findViewById(R.id.emailInputLayout);
            if (Validator.isValidEmail(editable.toString())) {
                emailInputLayout.setErrorEnabled(false);
                return;
            }
            emailInputLayout.setError(getString(R.string.error_email));
            ViewUtil.requestFocus(getWindow(), emailEditText);
        }
    };

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            TextInputLayout passwordInputLayout = findViewById(R.id.passwordInputLayout);
            if (Validator.isValidPassword(editable.toString())) {
                passwordInputLayout.setErrorEnabled(false);
                return;
            }
            passwordInputLayout.setError(getString(R.string.error_password));
            ViewUtil.requestFocus(getWindow(), passwordEditText);
        }
    };
}
