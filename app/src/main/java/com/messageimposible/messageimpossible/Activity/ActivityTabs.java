package com.messageimposible.messageimpossible.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.messageimposible.messageimpossible.Entity.EntityUsers;
import com.messageimposible.messageimpossible.Fragment.FragmentChats;
import com.messageimposible.messageimpossible.Fragment.FragmentContacts;
import com.messageimposible.messageimpossible.Fragment.FragmentInvites;
import com.messageimposible.messageimpossible.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ActivityTabs extends AppCompatActivity {

    private ViewPager viewPager;
    private PageAdapter pagerAdapter;

    private Button deletePic;
    private Button selectPic;
    private ImageView profile_Pic;
    private static final int PHOTO_PERFIL = 1;
    Bitmap bitmap;
    Uri uri;

    private Dialog dialog;


    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private StorageReference storageReference;
    private FirebaseStorage storage;

    private String USER_NAME;
    private String USER_EMAIL;
    private String USER_ID;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tab_viewer);

        pagerAdapter = new PageAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        deletePic = findViewById(R.id.deletePic);
        selectPic = findViewById(R.id.selectPic);
        defineView();



    }

    private void defineView(){
        profile_Pic = findViewById(R.id.profilePic);
    }


    public class PageAdapter extends FragmentPagerAdapter {

        private String[] tabTitles = new String[]{"CONTACTS", "CHATS", "INVITES"};

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){

                case 0:
                    fragment = new FragmentContacts();
                    break;
                case 1:
                    fragment = new FragmentChats();
                    break;
                case 2:
                    fragment = new FragmentInvites();
                    break;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int res_id = item.getItemId();

        if(res_id == R.id.action_search){

            //Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_LONG).show();

        }else if(res_id == R.id.action_addFriend){

            //Toast.makeText(getApplicationContext(), "add friend", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActivityAddFriend.class);
            i.putExtra("name", USER_NAME);
            i.putExtra("email", USER_EMAIL);
            i.putExtra("id", USER_ID);
            startActivity(i);

        }else if(res_id == R.id.action_profile){

            //Toast.makeText(getApplicationContext(), "add friend", Toast.LENGTH_LONG).show();
            showDialog();

        }
        else if (res_id == R.id.action_logout){

            mAuth.signOut();
            //Toast.makeText(getApplicationContext(), "Loged out", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, ActivityLogin.class);
            startActivity(i);
            finish();
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser!=null){

            DatabaseReference reference = database.getReference("users/"+currentUser.getUid());

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    EntityUsers user = dataSnapshot.getValue(EntityUsers.class);
                    USER_ID = currentUser.getUid();
                    USER_NAME = user.getUsername();
                    USER_EMAIL = user.getEmail();
                    //Toast.makeText(ActivityTabs.this, USER_NAME , Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else{



        }
    }


    public void showDialog(){

        dialog = new Dialog(ActivityTabs.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pic_profile);
        dialog.setTitle("My Custom Dialog");

        Button deletePic = (Button) dialog.findViewById(R.id.deletePic);
        Button selectPic = (Button) dialog.findViewById(R.id.selectPic);



        deletePic.setEnabled(true);
        selectPic.setEnabled(true);

        deletePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_LONG).show();

            }
        });

        selectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "select", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Select Profile Picture"),PHOTO_PERFIL);

            }
        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK) {
            Uri u = data.getData();
            storageReference = storage.getReference("profilePic");//imagenes_chat
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }

    }

}

