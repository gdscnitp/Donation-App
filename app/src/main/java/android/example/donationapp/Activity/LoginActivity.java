package android.example.donationapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText emailEnter, passwordEnter;
    TextView forgotpassword;
    Button loginbutton, userSignInbutton, ngoSignInbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEnter = findViewById(R.id.email_user_enter);
        passwordEnter = findViewById(R.id.password_entry_user);
        forgotpassword = findViewById(R.id.forget_password);
        loginbutton = findViewById(R.id.login_button);
        userSignInbutton = findViewById(R.id.user_signin_button);
        ngoSignInbutton = findViewById(R.id.ngo_signin_button);

        emailEnter.getText().toString();
        passwordEnter.getText().toString();

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this," ",Toast.LENGTH_SHORT).show();
            }
        });

        userSignInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this," ",Toast.LENGTH_SHORT).show();
            }
        });

        ngoSignInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, " ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}