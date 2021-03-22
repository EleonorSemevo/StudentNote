package com.memoire.studentnote.emplois;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.memoire.studentnote.EmploisFragment;
import com.memoire.studentnote.EnfantFragment;
import com.memoire.studentnote.InformationFragment;
import com.memoire.studentnote.MessageFragment;
import com.memoire.studentnote.ProfilFragment;
import com.memoire.studentnote.R;
import com.memoire.studentnote.ViewPagerAdapter;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;

import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class TableEmplois extends AppCompatActivity {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //   private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] tabIcons = {
            R.mipmap.eleve,
            R.mipmap.note,
            R.mipmap.message,
            R.mipmap.information,
            R.mipmap.avatar};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_table_emplois);

        //- Configure all views
//
//        this.configureToolBar();
//
//        this.configureDrawerLayout();
//
//        this.configureNavigationView();
//        idEcoleActuelle();


        viewPager = (ViewPager) findViewById(R.id.view_pager_emplois);
        setViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();


        //     {
        mDatabaseOpenHelper = new DatabaseOpenHelper(this);
        mdb= mDatabaseOpenHelper.getReadableDatabase();
        mDataWorker = new DatabaseDataWorker(mdb);
        //       }



    }


    private void setupTabIcons()
    {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LundiFragment(), "Lundi");
        adapter.addFragment(new MardiFragment(), "Mardi");
        adapter.addFragment(new MercrediFragment(),"Mercredi");
        adapter.addFragment(new JeudiFragment(),"Jeudi");
        adapter.addFragment(new VendrediFragment(),"Vendredi");
        //adapter.addFragment(new BookFragment(), "Book");
        viewPager.setAdapter(adapter);

    }


}