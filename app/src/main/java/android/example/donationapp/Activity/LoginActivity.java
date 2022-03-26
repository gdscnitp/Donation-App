package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout emailbox, passwordbox;
    TextInputEditText emailEntry, passwordEntry;
    Button loginButton, signUpuser, signUpngo;
    TextView loginHeading, loginChangeView, forgetPassword;
    FirebaseFirestore firebaseFirestore ;
    FirebaseUser currUser;

    String ngoOrUser, sEmailAddress, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgetPassword = findViewById(R.id.forget_password);
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
            public void onClick(View v) {


                //Data Extraction and Validation
                sEmailAddress = emailEntry.getText().toString();
                sPassword = passwordEntry.getText().toString();

                if(sEmailAddress.isEmpty())
                {
                    emailEntry.setError("Enter E-Mail Address.");
                    return;
                }

                if(sPassword.isEmpty())
                {
                    passwordEntry.setError("Enter Password");
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(sEmailAddress, sPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Getting your Profile.", Toast.LENGTH_SHORT).show();
                        currUser = FirebaseAuth.getInstance().getCurrentUser();
                        final String user_id = currUser.getUid();

                        Task<DocumentSnapshot> userDetails = firebaseFirestore.collection("UserInformation").document(user_id).get();
                        userDetails.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                UserClass userInfo = documentSnapshot.toObject(UserClass.class);
                                if(userInfo != null) {
                                    ngoOrUser = userInfo.getDesignation();

                                    if ((ngoOrUser.equalsIgnoreCase("NGO")) && (loginHeading.getText().toString().equalsIgnoreCase("For NGO"))) {
                                        Toast.makeText(LoginActivity.this, "Success. Send to NGO Home", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(LoginActivity.this, NGOHomeActivity.class);
                                        startActivity(intent1);
                                        finish();
                                    } else if ((ngoOrUser.equalsIgnoreCase("User")) && (loginHeading.getText().toString().equalsIgnoreCase("For User"))) {
                                        Toast.makeText(LoginActivity.this, "Success. Send to User Home", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(LoginActivity.this, UserFragmentContainer.class);
                                        startActivity(intent1);
                                        finish();
                                    } else if ((ngoOrUser.equalsIgnoreCase("NGO")) && (loginHeading.getText().toString().equalsIgnoreCase("For User"))) {
                                        FirebaseAuth.getInstance().signOut();
                                        Toast.makeText(LoginActivity.this, "Login as NGO.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        FirebaseAuth.getInstance().signOut();
                                        Toast.makeText(LoginActivity.this, "Login as User.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Process dismissed.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Process dismissed.", Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

        signUpuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LoginActivity.this, UserSignupActivity.class);
                startActivity(intent1);
            }
        });

        signUpngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this, NGOSignUpActivity.class);
                startActivity(intent2);
            }
        });

        signUpuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent uIntent = new Intent(LoginActivity.this, UserSignupActivity.class);
                startActivity(uIntent);
            }
        });

        signUpngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nIntent = new Intent(LoginActivity.this, NGOSignUpActivity.class);
                startActivity(nIntent);
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

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sEmailAddress = emailEntry.getText().toString();

                if(sEmailAddress.isEmpty())
                {
                    emailEntry.setError("Enter E-Mail Address.");
                    return;
                }

                FirebaseAuth.getInstance().sendPasswordResetEmail(sEmailAddress).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(LoginActivity.this, "Email Sent to " + sEmailAddress + " .", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            Task<DocumentSnapshot> userDetails = firebaseFirestore.collection("UserInformation").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get();
            userDetails.addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    UserClass userClass = documentSnapshot.toObject(UserClass.class);
                    if(userClass != null) {
                        ngoOrUser = userClass.getDesignation();

                        if (ngoOrUser.equalsIgnoreCase("User")) {
                            Intent intent1 = new Intent(LoginActivity.this, UserFragmentContainer.class);
                            startActivity(intent1);
                            finish();
                        }

                        if (ngoOrUser.equalsIgnoreCase("NGO")) {
                            Intent intent1 = new Intent(LoginActivity.this, NGOHomeActivity.class);
                            startActivity(intent1);
                            finish();
                        }
                    }
                }
            });
        }
    }
}