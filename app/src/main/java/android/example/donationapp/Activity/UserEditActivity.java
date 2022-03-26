package android.example.donationapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Calendar;

public class UserEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int PICK_IMAGE = 66 ;
    Spinner spinner;
    TextInputLayout nameUsereditbox, phoneUsereditbox, emailUsereditbox, passwordUsereditbox, addressUsereditbox, weightUsereditbox;
    TextInputEditText nameUserEdit, phoneUserEdit, emailUserEdit,passwordUserEdit, addressUserEdit, weightUserEdit;
    Button save;
    ImageView userEditBackButton, userPicEdit ,profileImage;
    CheckBox userEditDonate;
    TextView dobuseredit;

    String generatedFilePath;
    FirebaseFirestore firebaseFirestore;
    FirebaseStorage firebaseStorage;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    DocumentReference documentReference ;
    StorageReference storageReference;

    Uri imageUri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);

        nameUsereditbox = findViewById(R.id.user_name_box);
        phoneUsereditbox = findViewById(R.id.user_phone_box);
        emailUsereditbox = findViewById(R.id.user_email_box);
        passwordUsereditbox = findViewById(R.id.user_password_box);
        addressUsereditbox = findViewById(R.id.user_address_box);
        weightUsereditbox = findViewById(R.id.user_weight_box);

        nameUserEdit = findViewById(R.id.user_name_enter);
        phoneUserEdit = findViewById(R.id.user_phone_enter);
        emailUserEdit = findViewById(R.id.user_email_enter);
        passwordUserEdit = findViewById(R.id.user_password_enter);
        addressUserEdit = findViewById(R.id.user_address_enter);
        weightUserEdit = findViewById(R.id.user_weight_enter);

        save = findViewById(R.id.userSignUp_submit);
        userEditBackButton = findViewById(R.id.back_button_userSignUp);
        profileImage = findViewById(R.id.userSignUp_profile_pic);
        userPicEdit = findViewById(R.id.userSignUp_profile_pic_add);
        spinner = findViewById(R.id.user_gender_enter_spinner);
        userEditDonate = findViewById(R.id.user_donor_checkbox);
        dobuseredit = findViewById(R.id.user_dob_enter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());
        storageReference = firebaseStorage.getReference().child("images").child(currentUser.getUid());

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        dobuseredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UserEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayofMonth) {

                        dobuseredit.setText(dayofMonth+" / "+(month+1)+" / "+year);
                    }

                },year, day, month);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        userPicEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE);


                Toast.makeText(UserEditActivity.this, "Image Recieved", Toast.LENGTH_SHORT).show();
            }
        });

        userEditBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name , gender , phone, email ,password, address, weight,donor,Dob;
                name = nameUserEdit.getText().toString();
                String editedGender = spinner.getSelectedItem().toString();
                gender = editedGender;
                phone = phoneUserEdit.getText().toString();
                email = emailUserEdit.getText().toString();
                password = passwordUserEdit.getText().toString();
                address = addressUserEdit.getText().toString();
                weight = weightUserEdit.getText().toString();
                Dob = dobuseredit.getText().toString();


                if(name.isEmpty())
                {
                    nameUserEdit.setError("Enter Name");
                }

                if(phone.isEmpty())
                {
                    phoneUserEdit.setError("Enter Phone");
                }

                if(email.isEmpty())
                {
                    emailUserEdit.setError("Enter Email");
                }

                if(password.isEmpty())
                {
                    passwordUserEdit.setError("Enter Password");
                }

                if(address.isEmpty())
                {
                    addressUserEdit.setError("Enter Address");
                }

                if(weight.isEmpty())
                {
                    weightUserEdit.setError("Enter Weight");
                }
                boolean ischecked=userEditDonate.isChecked();
                if(ischecked)
                {
                    Toast.makeText(UserEditActivity.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserEditActivity.this, "Not Checked", Toast.LENGTH_SHORT).show();
                }

                donor = String.valueOf(ischecked);

                currentUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Email Updated", Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Email",e.getMessage());
                    }
                });

                currentUser.updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Password Updated" ,Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Password",e.getMessage());
                    }
                });

                documentReference.update("name",name).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Name Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating name",e.getMessage());
                    }
                });

                documentReference.update("email",email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Email Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating email",e.getMessage());
                    }
                });
                documentReference.update("address",address).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Address Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating address",e.getMessage());
                    }
                });

                documentReference.update("phoneNo",phone).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Contact Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Contact",e.getMessage());
                    }
                });
                documentReference.update("gender",gender).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Gender Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Gender",e.getMessage());
                    }
                });
                documentReference.update("weight",weight).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Weight Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Weight",e.getMessage());
                    }
                });

                documentReference.update("donor",donor).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Donor Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Weight",e.getMessage());
                    }
                });
                documentReference.update("dob",Dob).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(UserEditActivity.this,"Dob Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Dob",e.getMessage());
                    }
                });

                if(imageUri != null){

                    storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(UserEditActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    generatedFilePath = uri.toString();
                                    documentReference.update("imageURL",generatedFilePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(UserEditActivity.this, "Image Url Saved On Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(UserEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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