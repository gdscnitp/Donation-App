package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout emailbox, passwordbox;
    TextInputEditText emailEntry, passwordEntry;
    Button loginButton, signUpuser, signUpngo;
    TextView loginHeading, loginChangeView;
    FirebaseFirestore firebaseFirestore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailbox = findViewById(R.id.email_user_enter);
        passwordbox = findViewById(R.id.password_entry_user);
        emailEntry = findViewById(R.id.email_entry_box);
        passwordEntry = findViewById(R.id.password_entry_box);
        loginButton = findViewById(R.id.login_button);
        signUpuser = findViewById(R.id.user_signin_button);
        signUpngo = findViewById(R.id.ngo_signin_button);
        loginHeading = findViewById(R.id.user_ngo_heading);
        loginChangeView = findViewById(R.id.login_as_NGO);
        firebaseFirestore =FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!emailEntry.getText().toString().isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"Email Entered", Toast.LENGTH_SHORT).show();
                    if(!passwordEntry.getText().toString().isEmpty())
                    {
                        Toast.makeText(LoginActivity.this,"Password Entered", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        passwordEntry.setError("Enter Password");
                    }
                }
                else
                {
                    emailEntry.setError("Enter Email");
                }
            }
        });

        signUpuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this," ", Toast.LENGTH_SHORT).show();
            }
        });

        signUpngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this," ", Toast.LENGTH_SHORT).show();
            }
        });

        loginChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginChangeView.getText().toString().equalsIgnoreCase("Login as NGO"))
                {
                    emailEntry.setText("");
                    passwordEntry.setText("");
                    loginHeading.setText("For NGO");
                    loginChangeView.setText("Login as User");
                }
                else
                    {
                    emailEntry.setText("");
                    passwordEntry.setText("");
                    loginHeading.setText("For User");
                    loginChangeView.setText("Login as NGO");
                }
            }
        });
    }
}