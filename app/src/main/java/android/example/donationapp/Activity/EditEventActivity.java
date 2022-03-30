package android.example.donationapp.Activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.example.donationapp.R;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

public class EditEventActivity extends AppCompatActivity {

    TextInputLayout eventTitleBox, descriptionBox, timeBox, dateBox, addressBox, contactBox, emailBox;
    TextInputEditText eventTitleEdit, eventdescriptionEdit, eventAddressEdit, eventContactEdit, eventEmailEdit;
    String generatedString;
    TextView timeEdit, dateEdit;
    Button save;
    ImageView backbutton, imageedit,profileImage;
    String title , description, time, date , address ,email , contact;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    DocumentReference documentReference  , documentReferenceEvent , documentReferenceAllEvent;
    StorageReference storageReference;
    CollectionReference collectionReference;
    String generatedFilePath;
    int hour;
    int minutes;
    private static final int PICK_IMAGE = 76 ;
    Uri imageUri;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        eventTitleBox = findViewById(R.id.editEvent_edit_box);
        descriptionBox = findViewById(R.id.editEvent_description_box);
        timeBox = findViewById(R.id.editEvent_time_box);
        dateBox = findViewById(R.id.editEvent_date_box);
        addressBox = findViewById(R.id.editEvent_address_box);
        contactBox = findViewById(R.id.editEvent_contact_box);
        emailBox = findViewById(R.id.editEvent_email_box);
        profileImage = findViewById(R.id.editEvent_profile_pic);

        eventTitleEdit = findViewById(R.id.editEvent_edit);
        eventdescriptionEdit = findViewById(R.id.editEvent_description_edit);
        eventAddressEdit = findViewById(R.id.editEvent_address_edit);
        eventContactEdit = findViewById(R.id.editEvent_contact_edit);
        eventEmailEdit = findViewById(R.id.editEvent_email_edit);

        timeEdit = findViewById(R.id.editEvent_time_edit);
        dateEdit = findViewById(R.id.editEvent_date_edit);
        save = findViewById(R.id.editEvent_save);
        backbutton = findViewById(R.id.editEvent_back);
        imageedit = findViewById(R.id.editEvent_profile_pic_add);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        documentReference = firebaseFirestore.collection("Events").document(currentUser.getUid());
        storageReference = firebaseStorage.getReference().child("images").child(currentUser.getUid());
        collectionReference = firebaseFirestore.collection("Events").document(currentUser.getUid()).collection("random");
        generatedString = getIntent().getStringExtra("generatedString");
        documentReferenceEvent = collectionReference.document(generatedString);
        documentReferenceAllEvent = firebaseFirestore.collection("AllEvents").document(generatedString);

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        dateEdit.setText(dayOfmonth+ " / "+ (month+1)+ " /"+ year);

                    }
                },year, month, day);
                datePickerDialog.show();
            }


        });
        imageedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);


                Toast.makeText(EditEventActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    title = eventTitleEdit.getText().toString();
                    description = eventdescriptionEdit.getText().toString();
                    time = timeEdit.getText().toString();
                    date = dateEdit.getText().toString();
                    contact = eventContactEdit.getText().toString();
                    email = eventEmailEdit.getText().toString();
                    address = eventAddressEdit.getText().toString();

                if(title.isEmpty())
                {
                    eventTitleEdit.setError("Enter Event Title");
                }

                if(description.isEmpty())
                {
                    eventdescriptionEdit.setError("Enter Event Description");
                }

                if(time.isEmpty())
                {
                    timeEdit.setError("Select Time");
                }

                if(date.isEmpty())
                {
                    dateEdit.setError("Select Date");
                }

                if(address.isEmpty())
                {
                    eventAddressEdit.setError("Enter Event Address");
                }

                if(contact.isEmpty())
                {
                    eventContactEdit.setError("Enter Contact Number");
                }

                if(email.isEmpty())
                {
                    eventEmailEdit.setError("Enter Email");
                }

                documentReferenceEvent.update("eAddress", address).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Address Updated", "Address " + address);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating address", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceEvent.update("eTitle", title).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Title Updated", "Title " + title);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating title", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceEvent.update("eDescription", description).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Address Updated", "Address " + address);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating address", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceEvent.update("eTime", time).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Time Updated", "Time " + time);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating time", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceEvent.update("eContact", contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Contact Updated", "Contact " + contact);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating Contact", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceEvent.update("eEmail", email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Email Updated", "Email " + email);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating email", "Exception:" + e.getMessage());
                    }
                });



                documentReferenceAllEvent.update("eAddress", address).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Address Updated", "Address " + address);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating address", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceAllEvent.update("eTitle", title).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Title Updated", "Title " + title);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating title", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceAllEvent.update("eDescription", description).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Address Updated", "Address " + address);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating address", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceAllEvent.update("eTime", time).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Time Updated", "Time " + time);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating time", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceAllEvent.update("eContact", contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Contact Updated", "Contact " + contact);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating Contact", "Exception:" + e.getMessage());
                    }
                });
                documentReferenceAllEvent.update("eEmail", email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.e("Email Updated", "Email " + email);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While updating email", "Exception:" + e.getMessage());
                    }
                });

                if(imageUri != null){

                    storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(EditEventActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    generatedFilePath = uri.toString();
                                    documentReferenceEvent.update("eImageURL",generatedFilePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(EditEventActivity.this, "Image Url Saved On Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(EditEventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.e("Inside updating ImageUrl",e.getMessage());
                                        }
                                    });
                                    documentReferenceAllEvent.update("eImageURL",generatedFilePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(EditEventActivity.this, "Image Url Saved On Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(EditEventActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.e("Inside updating ImageUrl",e.getMessage());
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Inside download ImageUrl",e.getMessage());

                                }
                            });


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Inside uploading Image ",e.getMessage());
                        }
                    });
                }

                Intent intent = new Intent(EditEventActivity.this, NGOHomeActivity.class);
                startActivity(intent);

            }

        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourofDay, int minute) {

                hour = hourofDay;
                minutes = minute;
                timeEdit.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(EditEventActivity.this, onTimeSetListener, hour, minutes, true);

        timePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}