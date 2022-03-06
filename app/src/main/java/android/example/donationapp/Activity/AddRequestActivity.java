package android.example.donationapp.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.example.donationapp.Model.RequestClass;
import android.example.donationapp.R;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class AddRequestActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView backbutton, addpic;
    TextInputLayout patientNameBox, patientGenderBox, patientAddressBox, patientContactBox, patientEmailBox, patientTitleBox, patientDescriptiionBox;
    TextInputEditText patientName, patientGender, patientAdddress, patientContact, patientEmail, requestTitle, requestDescription;
    Button submit;
    TextView dob;
    Spinner spinner;
    String blood_group, dateOfBirth;


    String generatedString;

    RequestClass request;


    String generatedFilePath = "";

    private Uri imageUri;



    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    FirebaseAuth firebaseAuth ;
    FirebaseUser currentUser;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;




    private static final int PICK_IMAGE = 1;

    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_request);

        patientNameBox = findViewById(R.id.patient_name_enter_box);
        patientGenderBox = findViewById(R.id.patient_gender_enter_box);
        patientAddressBox = findViewById(R.id.patient_address_enter_box);
        patientContactBox = findViewById(R.id.patient_contact_enter_box);
        patientEmailBox = findViewById(R.id.patient_email_enter_box);
        patientTitleBox = findViewById(R.id.patient_title_enter_box);
        patientDescriptiionBox = findViewById(R.id.patient_description_enter_box);

        patientName = findViewById(R.id.patient_name_enter);
        patientGender = findViewById(R.id.patient_gender_enter);
        patientAdddress = findViewById(R.id.patient_address_enter);
        patientContact = findViewById(R.id.patient_contact_enter);
        patientEmail = findViewById(R.id.patient_email_enter);
        requestTitle = findViewById(R.id.patient_title_enter);
        requestDescription = findViewById(R.id.patient_description_enter);

        backbutton = findViewById(R.id.back_button);
        addpic = findViewById(R.id.add_request_pic);
        submit = findViewById(R.id.request_submit_button);
        dob = findViewById(R.id.patient_dob_enter);
        spinner = findViewById(R.id.blood_group_spinner);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        //documentReference = FirebaseFirestore.getInstance().collection("Request").document(currentUser.getUid()).collection("random").document();
        //storageReference = storage.getReference().child("images").child(currentUser.getUid());




        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {
                        dob.setText(dayofMonth+ " / "+ (month+1)+ " /"+ year);
                        dateOfBirth = dayofMonth+ " / "+ (month+1)+ " / "+ year;

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        blood_group = spinner.getSelectedItem().toString();
        //Toast.makeText(AddRequestActivity.this, blood_group, Toast.LENGTH_SHORT).show();

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                if(!patientName.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientName.setError("Enter Name");
                    return;
                }
                if(!patientEmail.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientEmail.setError("Enter Email");
                    return;
                }
                if(!patientGender.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientGender.setError("Enter Gender");
                    return;
                }
                if(!patientAdddress.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    patientAdddress.setError("Enter Address");
                    return;
                }
                if(!requestTitle.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestTitle.setError("Enter Title");
                    return;
                }
                if(!requestDescription.getText().toString().isEmpty())
                {
                    Toast.makeText(AddRequestActivity.this, " ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestDescription.setError("Enter Description");
                    return;
                }


                int leftLimit = 48; // numeral '0'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 10;
                Random random = new Random();

                String generatedString = random.ints(leftLimit, rightLimit + 1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();



                if(imageUri != null)
                {
                    Log.e("Inside if condition", imageUri.toString());

                    storageReference = storage.getReference().child("images").child(generatedString);

                    storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Log.e("Image uploaded", "     .");
                            Toast.makeText(AddRequestActivity.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {




                                    generatedFilePath = uri.toString();
                                    Toast.makeText(AddRequestActivity.this, "Path is " + generatedFilePath, Toast.LENGTH_SHORT).show();

                                    Log.e("Path found", generatedFilePath);



                                    request = new RequestClass(generatedString, patientName.getText().toString(), patientGender.getText().toString(), dateOfBirth, blood_group, patientAdddress.getText().toString(), patientContact.getText().toString(), patientEmail.getText().toString(), requestTitle.getText().toString(), requestDescription.getText().toString(), generatedFilePath);

                                    Log.e("image through class", request.getImage().toString());


                                    firebaseFirestore.collection("Request").document(currentUser.getUid()).collection("random").document(generatedString).set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.e("Success", "Success");
                                            Toast.makeText(AddRequestActivity.this, "Request Sent.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("Failure", e.getMessage());
                                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    firebaseFirestore.collection("All Request").document(generatedString).set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Log.e("Success", "Success");
                                            Toast.makeText(AddRequestActivity.this, "Request Sent.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.e("Failure", e.getMessage());
                                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });



                                    /*documentReference.update("image", generatedFilePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(AddRequestActivity.this, "Image Url saved on firebase.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });*/


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else
                {
                    request = new RequestClass(generatedString, patientName.getText().toString(), patientGender.getText().toString(), dateOfBirth, blood_group, patientAdddress.getText().toString(), patientContact.getText().toString(), patientEmail.getText().toString(), requestTitle.getText().toString(), requestDescription.getText().toString(), " ");



                    firebaseFirestore.collection("Request").document(currentUser.getUid()).collection("random").document(generatedString).set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e("Success", "Success");
                            Toast.makeText(AddRequestActivity.this, "Request Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Failure", e.getMessage());
                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                    firebaseFirestore.collection("All Request").document(generatedString).set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e("Success", "Success");
                            Toast.makeText(AddRequestActivity.this, "Request Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Failure", e.getMessage());
                            Toast.makeText(AddRequestActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }











            }
        });


        addpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePicture();

            }

        });

    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK)
        {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}