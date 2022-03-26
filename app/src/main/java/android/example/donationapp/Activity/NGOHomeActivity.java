package android.example.donationapp.Activity;

import android.content.Intent;
import android.example.donationapp.Fragment.NGOChatFragment;
import android.example.donationapp.Fragment.NGOHomeFragment;
import android.example.donationapp.Fragment.NGOMyEventsFragment;
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

public class NGOHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    View headerView;
    ImageView headerImageView;
    TextView headerTextView;
    Button headerProfileButton ,signOut;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngohome);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.ngo_fragment_container, new NGOHomeFragment()).commit();
        }

        bottomNavigationView = findViewById(R.id.ngo_home_bottom_navbar);
        navigationView = findViewById(R.id.ngo_navigation_view);
        drawerLayout = findViewById(R.id.ngo_drawer_layout);
        toolbar = findViewById(R.id.ngo_toolbar_container);

        setSupportActionBar(toolbar);

        headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header_layout);
        headerImageView = (ImageView) headerView.findViewById(R.id.image_navigation_drawer);
        headerTextView = (TextView) headerView.findViewById(R.id.name_navigation_drawer);
        headerProfileButton = (Button) headerView.findViewById(R.id.profile_button_navigation_drawer);
        signOut = findViewById(R.id.ngo_sign_out_drawer_layout);

        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null) {
            documentReference = firebaseFirestore.collection("UserInformation").document(currentUser.getUid());
        }

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this , drawerLayout ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent login = new Intent(NGOHomeActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    UserClass userClass =  documentSnapshot.toObject(UserClass.class);
                    if(userClass != null) {
                        Glide.with(NGOHomeActivity.this).load(userClass.getImageURL()).placeholder(R.drawable.header_image_navigation_drawer2);
                        headerTextView.setText(userClass.getName());
                    }
                }




            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = new NGOHomeFragment();
                switch (item.getItemId())
                {
                    case R.id.ngo_home:
                        selectedFragment = new NGOHomeFragment();
                        break;
                    case R.id.ngo_chat:
                        selectedFragment = new NGOChatFragment();
                        break;
                    case R.id.ngo_myEvent:
                        selectedFragment = new NGOMyEventsFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.ngo_fragment_container, selectedFragment).commit();
                return true;
            }
        });

        headerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(NGOHomeActivity.this,NGOProfileActivity.class );startActivity(profileIntent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}