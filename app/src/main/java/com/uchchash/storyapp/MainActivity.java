package com.uchchash.storyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//=================== Home Page and Other Activities Code Start ====================
//===================================================================================
//Author Name: Muntasir Mahmud Rifat
//Author ID: 231110
// Works of Muntasir: Intent for Multiple Activities
//===================================================================================
//Author Name: Mahmudur Rahman
//Author ID: 231134
// Works of Muntasir: Database Design with Google Firestore and Backend Integration

//===================================================================================
//Author Name: Syeda Fahmida Farzana Rumky
//Author ID: 231115
// Works of Muntasir: Codes for Home page and Rating section

//===================================================================================
//Author Name: Musrat Mannan
//Author ID: 231118
// Works of Muntasir: Codes for Navigate Section


public class MainActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<String> title_lt, storylt;
    ArrayAdapter<String> adapter;
    MyStory myStory;

    // navigation drawer variable start
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    MenuItem mShare;
    // navigation drawer variable stop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("storyBook");
        myStory = new MyStory();
        title_lt = new ArrayList<>();
        storylt = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.demo, R.id.item_txt, title_lt);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title_lt.clear();
                storylt.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    myStory = ds.getValue(MyStory.class);
                    if (myStory != null) {
                        title_lt.add(myStory.getTitle());
                    }
                    if (myStory != null) {
                        storylt.add(myStory.getStory());
                    }
                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, NextActivity.class);
                        String p = storylt.get(position);
                        intent.putExtra("key", p);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//Navigation Drawer Code Start
        // Navagation Drawar
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
//developers code
        mShare = findViewById(R.id.mShare);

        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Drawar click event
        // Drawer item Click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mHome:
                        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.mShare:
                        Toast.makeText(MainActivity.this, "Developers", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DevelopersActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        break;
                }
                return false;
            }
        });

        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu);
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        // ------------------------
//        Navigation Drawer Code Stop
    }
}

//=================== Home Page and Other Activities Code Stop ====================