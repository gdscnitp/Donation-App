    package android.example.donationapp.Activity;

    import androidx.activity.result.ActivityResult;
    import androidx.activity.result.ActivityResultCallback;
    import androidx.activity.result.ActivityResultLauncher;
    import androidx.activity.result.contract.ActivityResultContracts;
    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.app.Activity;
    import android.app.DatePickerDialog;
    import android.content.Intent;
    import android.example.donationapp.Model.UserClass;
    import android.example.donationapp.R;
    import android.graphics.Bitmap;
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
            ImageView backbutton, picEnter, profileImage;
            CheckBox donateCheck;
            TextView dob;
            boolean ischecked;

            FirebaseAuth firebaseAuth;
            String sName, sDob, sPhone, sEmail, sPassword, sDonor, sAddress, sWeight, sdesignation = "User", sImageUrl = "null", sGender, uID;
            FirebaseUser currenUser;
            UserClass userInfo;
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            Uri selectedImage;
            String imagePath;
            Bitmap bitmap;
            DocumentReference documentReference;
            private static int PICK_IMAGE = 1;
            StorageReference storageReference;

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
                                profileImage.setImageBitmap(bitmap);
                                picEnter.setVisibility(View.GONE);
                                Log.e("Inside OnActivity",bitmap.toString());

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

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
                profileImage = findViewById(R.id.userSignUp_profile_pic);
                backbutton = findViewById(R.id.back_button_userSignUp);
                picEnter = findViewById(R.id.userSignUp_profile_pic_add);
                donateCheck = findViewById(R.id.user_donor_checkbox);
                dob = findViewById(R.id.user_dob_enter);
                submit = findViewById(R.id.userSignUp_submit);


                firebaseAuth = FirebaseAuth.getInstance();

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


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        sName = nameEnter.getText().toString();
                        sDob = dob.getText().toString();
                        sPhone = phoneEnter.getText().toString();
                        sEmail = emailEnter.getText().toString();
                        sPassword = passwordEnter.getText().toString();
                        sAddress = addressEnter.getText().toString();
                        sWeight = weightEnter.getText().toString();
                        String spinnerData = spinner.getSelectedItem().toString();
                        sGender = spinnerData;


                        if(sName.isEmpty())
                        {
                            nameEnter.setError("Enter Name");
                        }
                        if(sDob.isEmpty())
                        {
                            dob.setError("Enter DOB");
                        }
                        if(sPhone.isEmpty())
                        {
                            phoneEnter.setError("Enter Phone Number");
                        }
                        if(sEmail.isEmpty())
                        {
                            emailEnter.setError("Enter Email");
                        }
                        if(sPassword.isEmpty())
                        {
                            passwordEnter.setError("Enter Password");
                        }
                        if(sAddress.isEmpty())
                        {
                            addressEnter.setError("Enter Address");
                        }
                        if(sWeight.isEmpty())
                        {
                            weightEnter.setError("Enter Weight");
                        }

                        ischecked=donateCheck.isChecked();
                        if(ischecked)
                        {
                            Toast.makeText(UserSignupActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(UserSignupActivity.this, "Not Checked", Toast.LENGTH_SHORT).show();
                        }


                sDonor = String.valueOf(ischecked);



                firebaseAuth.createUserWithEmailAndPassword(sEmail, sPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currenUser = FirebaseAuth.getInstance().getCurrentUser();
                        assert currenUser != null;
                        uID = currenUser.getUid();
                        Toast.makeText(UserSignupActivity.this, " ", Toast.LENGTH_SHORT).show();
                        userInfo = new UserClass(sName, sDob, sGender, sPhone, sEmail, sAddress, sWeight, sDonor, sdesignation, uID, sImageUrl);

                        firebaseFirestore.collection("UserInformation").document(currenUser.getUid()).set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UserSignupActivity.this, "Info Added to Firebase", Toast.LENGTH_SHORT).show();
                                if(selectedImage != null)
                                {
                                    Log.e("Selected Image",selectedImage.toString());

                                    storageReference = firebaseStorage.getReference().child("images").child(currenUser.getUid());
                                    documentReference = FirebaseFirestore.getInstance().collection("UserInformation").document(currenUser.getUid());
                                    storageReference.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            Toast.makeText(UserSignupActivity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                                            Log.e("On Success","Image stored");
                                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {

                                                    imagePath = uri.toString();
                                                    Toast.makeText(UserSignupActivity.this, "URL Generated", Toast.LENGTH_SHORT).show();
                                                    Log.e("URL Generation Success", imagePath);
                                                    documentReference.update("imageURL", imagePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                            Toast.makeText(UserSignupActivity.this,"URL Saved", Toast.LENGTH_SHORT).show();
                                                            Log.e("URL saving", " URL saved");

                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {

                                                            Toast.makeText(UserSignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                                                        }
                                                    });
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                    Toast.makeText(UserSignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UserSignupActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserSignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserSignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                Intent uRegister = new Intent(UserSignupActivity.this, UserFragmentContainer.class);
                startActivity(uRegister);

                    }
                });

                picEnter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);

                        someActivityResultLauncher.launch(intent);

                        Toast.makeText(UserSignupActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();
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
