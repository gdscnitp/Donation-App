package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.R;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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

public class NGOEditActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 44;
    TextInputLayout editNamebox, editDescriptionbox, editAddressbox;
    TextInputLayout editEmailbox, editPasswordbox, editContactbox;
    TextInputEditText editNGOname, editNGOdescription, editNGOaddress, editNGOemail, editNGOcontact, editNGOpassword;
    ImageView backbutton, imageEditbutton ,profilePic;
    Button saveButton;

    String generatedFilePath;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    DocumentReference documentReference ;
    StorageReference storageReference;

    Uri imageUri;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngoedit);

        editNamebox = findViewById(R.id.name_edit_box);
        editDescriptionbox = findViewById(R.id.ngo_description_edit_box);
        editAddressbox = findViewById(R.id.ngo_address_edit_box);
        editEmailbox = findViewById(R.id.ngo_email_edit_box);
        editPasswordbox = findViewById(R.id.ngo_password_edit_box);
        editContactbox = findViewById(R.id.ngo_contact_edit_box);

        editNGOname = findViewById(R.id.ngo_name_edit);
        editNGOdescription = findViewById(R.id.ngo_description_edit);
        editNGOaddress = findViewById(R.id.ngo_address_edit);
        editNGOcontact = findViewById(R.id.ngo_contact_name_edit);
        editNGOemail = findViewById(R.id.ngo_email_edit);
        editNGOpassword = findViewById(R.id.ngo_password_edit);

        backbutton = findViewById(R.id.back_button);
        imageEditbutton = findViewById(R.id.ngo_profile_pic_add);
        saveButton = findViewById(R.id.ngo_edit_save);
        profilePic = findViewById(R.id.ngo_profile_pic);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
        documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());
        storageReference = FirebaseStorage.getInstance().getReference().child("images").child("ngo").child(currentUser.getUid());

        imageEditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickImage = new Intent();
                pickImage.setType("image/*");
                pickImage.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(pickImage,"Select Image"),PICK_IMAGE);
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
        finish();
         }


         });
            saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name , description ,address ,contact ,email , password;

                name = editNGOname.getText().toString();
                description = editNGOdescription.getText().toString();
                address = editNGOaddress.getText().toString();
                contact = editNGOcontact.getText().toString();
                email = editNGOemail.getText().toString();
                password = editNGOpassword.getText().toString();

                if(name.isEmpty())
                {
                    editNGOname.setError("Enter Name");
                }

                if(description.isEmpty())
                {
                    editNGOdescription.setError("Enter Description");
                }

                if(address.isEmpty())
                {
                    editNGOaddress.setError("Enter Address");
                }

                if(contact.isEmpty())
                {
                    editNGOcontact.setError("Enter Contact");
                }

                if(email.isEmpty())
                {
                    editNGOemail.setError("Enter Email");
                }

                if(password.isEmpty())
                {
                    editNGOpassword.setError("Enter Password");
                }

                currentUser.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(NGOEditActivity.this,"Email Updated", Toast.LENGTH_SHORT);
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
                        Toast.makeText(NGOEditActivity.this,"Password Updated" ,Toast.LENGTH_SHORT);
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
                        Toast.makeText(NGOEditActivity.this,"Name Updated",Toast.LENGTH_SHORT);
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
                        Toast.makeText(NGOEditActivity.this,"Email Updated",Toast.LENGTH_SHORT);
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
                        Toast.makeText(NGOEditActivity.this,"Address Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating address",e.getMessage());
                    }
                });
                documentReference.update("description",description).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(NGOEditActivity.this,"Address Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating address",e.getMessage());
                    }
                });
                documentReference.update("phoneNo",contact).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(NGOEditActivity.this,"Contact Updated",Toast.LENGTH_SHORT);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("While Updating Contact",e.getMessage());
                    }
                });

                if(imageUri != null){

                    storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(NGOEditActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                generatedFilePath = uri.toString();
                                    documentReference.update("imageURL",generatedFilePath).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(NGOEditActivity.this, "Image Url Saved On Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(NGOEditActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

                if(generatedFilePath != null){
                    Glide.with(NGOEditActivity.this).load(generatedFilePath).into(profilePic);}
                else{
                    Glide.with(NGOEditActivity.this).load(R.drawable.profile_avatar1).into(profilePic);

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
