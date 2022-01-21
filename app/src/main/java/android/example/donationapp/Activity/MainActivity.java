package android.example.donationapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.example.donationapp.Fragment.ChatFragment;
import android.example.donationapp.Fragment.HomeFragment;
import android.example.donationapp.Fragment.MyRequestFragment;
import android.example.donationapp.Fragment.NGOFragment;
import android.example.donationapp.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    View headerView;
    ImageView headerImageView;
    TextView headerTextView;
    Button headerProfileButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView =findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar_fragment_container);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);

        headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header_layout);
        headerImageView = (ImageView) headerView.findViewById(R.id.image_navigation_drawer);
        headerTextView = (TextView) headerView.findViewById(R.id.name_navigation_drawer);
        headerProfileButton = (Button) headerView.findViewById(R.id.profile_button_navigation_drawer);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle =  new ActionBarDrawerToggle(this , drawerLayout ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        headerProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeFragmentNavigationDrawer = new Intent(getApplicationContext() , HomeFragment.class);
                startActivity(homeFragmentNavigationDrawer);
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
                Intent NavigationDrawerHomeIntent = new Intent(this , HomeFragment.class);
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
