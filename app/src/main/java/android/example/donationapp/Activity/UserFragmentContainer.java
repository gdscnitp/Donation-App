package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.Fragment.ChatFragment;
import android.example.donationapp.Fragment.HomeFragment;
import android.example.donationapp.Fragment.MyRequestFragment;
import android.example.donationapp.Fragment.NGOFragment;
import android.example.donationapp.Model.UserClass;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFragmentContainer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    View headerView;
    ImageView headerImageView;
    TextView headerTextView;
    Button headerProfileButton, signOut;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment_container);

        bottomNavigationView =findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar_fragment_container);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        signOut = findViewById(R.id.sign_out_drawer_layout);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment()).commit();
        }


        headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header_layout);
        headerImageView = (ImageView) headerView.findViewById(R.id.image_navigation_drawer);
        headerTextView = (TextView) headerView.findViewById(R.id.name_navigation_drawer);
        headerProfileButton = (Button) headerView.findViewById(R.id.profile_button_navigation_drawer);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this , drawerLayout ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        if(currentUser != null) {
            documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());

            documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        UserClass userClass = documentSnapshot.toObject(UserClass.class);
                        if (userClass != null) {
                            Glide.with(UserFragmentContainer.this).load(userClass.getImageURL()).placeholder(R.drawable.header_image_navigation_drawer2);
                            headerTextView.setText(userClass.getName());
                        }
                    }


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
        headerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeFragmentNavigationDrawer = new Intent(getApplicationContext() , ProfileUserActivity.class);
                startActivity(homeFragmentNavigationDrawer);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent login = new Intent(UserFragmentContainer.this, LoginActivity.class);
                startActivity(login);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()){

                    case R.id.home :
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.chat :
                        selectedFragment = new ChatFragment();
                        break;
                    case R.id.my_request :
                        selectedFragment = new MyRequestFragment();
                        break;
                    case R.id.ngo :
                        selectedFragment = new NGOFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;

            }
        });

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
        super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

                case R.id.home_menu :
                Intent NavigationDrawerHomeIntent = new Intent(this , UserFragmentContainer.class);
                startActivity(NavigationDrawerHomeIntent);
                break;
                case R.id.blood_banks_menu :
                Intent NavigationDrawerBloodBankIntent = new Intent(this , BloodBankActivity.class);
                startActivity(NavigationDrawerBloodBankIntent);
                break;
                case R.id.donor_menu :
                Intent NavigationDrawerDonorIntent = new Intent(this , DonorActivity.class);
                startActivity(NavigationDrawerDonorIntent);
                break;
                case R.id.past_donation_menu :
                Intent NavigationDrawerPastDonationIntent = new Intent(this , PastDonationActivity.class);
                startActivity(NavigationDrawerPastDonationIntent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
