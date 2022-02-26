package android.example.donationapp.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

public class UserSignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputLayout nameBox, phoneBox, emailBox, passwordBox, addressBox, weightBox;
    TextInputEditText nameEnter, phoneEnter, emailEnter, passwordEnter, addressEnter, weightEnter;
    Button submit;
    Spinner spinner;
    ImageView backbutton, picEnter , profilePic;
    CheckBox donateCheck;
    TextView dob;
    Bitmap bitmap;
    Uri selectedImage;
    UserClass userInfo;
    String designation = "User",uId ,imageUrl = "null";
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage storage;
    DocumentReference documentReference;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        nameBox = findViewById(R.id.user_name_box);
        phoneBox = findViewById(R.id.user_phone_box);
        emailBox = findViewById(R.id.user_email_box);
        passwordBox = findViewById(R.id.user_password_box);
        addressBox = findViewById(R.id.user_address_box);
        weightBox = findViewById(R.id.user_weight_box);
        nameEnter = findViewById(R.id.user_name_enter);
        phoneEnter = findViewById(R.id.user_phone_enter);
        emailEnter = findViewById(R.id.user_email_enter);
        passwordEnter = findViewById(R.id.user_password_enter);
        addressEnter = findViewById(R.id.user_address_enter);
        weightEnter = findViewById(R.id.user_weight_enter);
        spinner = findViewById(R.id.user_gender_enter_spinner);
        backbutton = findViewById(R.id.back_button_userSignUp);
        picEnter = findViewById(R.id.userSignUp_profile_pic_add);
        profilePic = findViewById(R.id.userSignUp_profile_pic);
        donateCheck = findViewById(R.id.user_donor_checkbox);
        dob = findViewById(R.id.user_dob_enter);
        submit = findViewById(R.id.userSignUp_submit);



        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        progressDialog =  new ProgressDialog(UserSignupActivity.this);
        progressDialog.setTitle("Registering");
        progressDialog.setMessage("Please Wait");

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserSignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {

                        dob.setText(dayOfmonth+" / "+(month+1)+" / "+year);

                    }
                },year, month, day);
                    datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        String spinnerData = spinner.getSelectedItem().toString();
        Toast.makeText(UserSignupActivity.this, spinnerData, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEnter.getText().toString();
                String DOB =  dob.getText().toString();
                String phoneNo = phoneEnter.getText().toString();
                String email = emailEnter.getText().toString().trim();
                String gender = spinnerData;
                String address = addressEnter.getText().toString();
                String weight = weightEnter.getText().toString();
                String password = passwordEnter.getText().toString().trim();
                boolean Donor = donateCheck.isChecked();
                String donor ;
                if(Donor){
                    donor = "Yes";
                }else{
                    donor = "No";
                }

                if(name.isEmpty())
                {
                    nameEnter.setError("Enter Name");
                }

                if(DOB.isEmpty())
                {
                    dob.setError("Enter DOB");
                }

                if(gender.isEmpty())
                {
                    Toast.makeText(UserSignupActivity.this, "Choose Gender", Toast.LENGTH_SHORT).show();
                }
                if(phoneNo.isEmpty())
                {
                    phoneEnter.setError("Enter Phone Number");
                }
                if(email.isEmpty())
                {
                    emailEnter.setError("Enter Email");
                }

                if(passwordEnter.getText().toString().isEmpty())
                {
                    passwordEnter.setError("Enter Password");
                }

                if(address.isEmpty())
                {
                    addressEnter.setError("Enter Address");
                }
                if(weight.isEmpty())
                {
                    weightEnter.setError("Enter Weight");
                }
                if(Donor)
                {
                    Toast.makeText(UserSignupActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserSignupActivity.this, "Not Checked", Toast.LENGTH_SHORT).show();
                }


                Log.e("Info","name:" + name + "DOB:" + DOB + "email:" + email);
                Toast.makeText(UserSignupActivity.this, "name:" + name + "DOB:" + DOB , Toast.LENGTH_LONG).show();


                if(currentUser != null){
                    Toast.makeText(UserSignupActivity.this,"User already exists",Toast.LENGTH_SHORT);
                    Intent sendToUserFragmentContainer = new Intent(getApplicationContext(),UserFragmentContainer.class);
                    startActivity(sendToUserFragmentContainer);
                }
                else{
//                    firebaseFirestore.collection("UserInformation").document(uId).set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(UserSignupActivity.this,"Information Uploaded On Firebase",Toast.LENGTH_SHORT);
//                            if(selectedImage != null){
//                                storageReference =  storage.getReference().child("profilePic").child(uId);
//                                documentReference = firebaseFirestore.collection("UserInformation").document(uId);
//
//                                storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                    @Override
//                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                        Toast.makeText(UserSignupActivity.this, "Image Saved", Toast.LENGTH_SHORT).show();
//                                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                            @Override
//                                            public void onSuccess(Uri uri) {
//                                                imagePath = uri.toString();
//
//                                                documentReference.update("imageUrl",imagePath).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void aVoid) {
//                                                        Toast.makeText(UserSignupActivity.this, "Url Saved", Toast.LENGTH_SHORT).show();
//                                                        progressDialog.dismiss();
//                                                        Intent sendToUserFragmentContainer = new Intent(getApplicationContext(),UserFragmentContainer.class);
//
//                                                    }
//                                                }).addOnFailureListener(new OnFailureListener() {
//                                                    @Override
//                                                    public void onFailure(@NonNull Exception e) {
//                                                        Log.e("Exception:" , e.getMessage());
//                                                    }
//                                                });
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                                Log.e("Exception:" , e.getMessage());
//                                            }
//                                        });
//                                    }
//                                });
//                            }
//                            else{
//                                progressDialog.dismiss();
//                            }
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.e("Exception:" , e.getMessage());
//                        }
//                    });
                }

            }
        });


        picEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
try {
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        selectedImage = Objects.requireNonNull(data).getData();
                        bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), selectedImage);
                            profilePic.setImageBitmap(bitmap);
                            picEnter.setVisibility(View.GONE);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    someActivityResultLauncher.launch(intent);

}catch (Exception e){
    Log.e("launching intent", "onClick: " + e.getMessage() );

}

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

