package com.memoire.studentnote.emplois;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

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


    public static String ECOLE ="ECOLE";
    public static String CLASSE ="CLASSE";
    public static String ID_ECOLE= "ID_ECOLE";
    public static String ID_CLASSE="ID_CLASSE";
    public static int id_ecole;
    public static int id_classe;


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView mTextViewEcole;
    private TextView mTextViewClasse;


    //   private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int[] tabIcons = {
            R.mipmap.lundi,
            R.mipmap.mardi,
            R.mipmap.mercredi,
            R.mipmap.jeudi,
            R.mipmap.vendredi};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_table_emplois);
        mTextViewClasse = findViewById(R.id.emp_classe);
        mTextViewEcole = findViewById(R.id.tv_ecole);
        Intent intent = getIntent();
        id_classe= intent.getIntExtra(ID_CLASSE,0);
        id_ecole = intent.getIntExtra(ID_ECOLE,0);


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

        tabLayout = (TabLayout) findViewById(R.id.tabs_emplois);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        getEntete();


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
    public void getEntete()
    {
        Intent intent;
        intent = getIntent();
        mTextViewEcole.setText(intent.getStringExtra(ECOLE));
        mTextViewClasse.setText(intent.getStringExtra(CLASSE));
    }
}