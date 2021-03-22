package com.memoire.studentnote.emplois;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Matiere;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.idEcoleActuelle_enseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mListeClasses;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class AjoutEmplois extends AppCompatActivity {
    private Spinner  mSpinnerEcole;
    private Spinner  mSpinnerClasses;
    private Button   mButtonEnregistrer;
    private Button   mButtonAnnuler;
    private Button   mButtonChoisir;
    private TextView mTextViewResult;

    private boolean fichierValide= false;

    Uri uri;
    private Intent mChooseFile;
    private TextView mMTextViewError;
    int PICKFILE_RESULT_CODE;
    private ProgressDialog mProgressBar;
    private Handler mHandler;
    private int matiereColumnPositon;
    private int jourColumnPosition;
    private int heureDebutColumnPosition;
    private int heureFinColumnPosition;
    private boolean mColumnMatiereValide;
    private boolean mColumnJourValide;
    private boolean mColumnHeureDebutValide;
    private boolean mColumnFinValide;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ajout_emplois);

        mSpinnerEcole =findViewById(R.id.spinner_emp_ecoles);
        mSpinnerClasses = findViewById(R.id.spinner_emp_classes);
        mButtonEnregistrer = findViewById(R.id.button_emp_enregistrer);
        mButtonAnnuler = findViewById(R.id.button_emp_annuler);
        mButtonChoisir =  findViewById(R.id.button_emp_choix_fichier);
        mTextViewResult = findViewById(R.id.textView_emp_result);
        mMTextViewError = findViewById(R.id.textview_emp_error);



        mButtonChoisir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFichier(v);
            }
        });

        if(!mdb.isOpen())
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }

        //
        initialiserFormulaire();

        mButtonEnregistrer.setOnClickListener(new View.OnClickListener() {
            boolean f;
            @Override
            public void onClick(View v) {
                try {
                     f= verifierFormatFichier();
                    fichierOkAvecMatiere();
                    fichierOkAvecJour();
                    fichierOkAvecHeurs();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(f && mColumnHeureDebutValide && mColumnFinValide && mColumnMatiereValide && mColumnJourValide){
                    try {
                        enregistrerEmplois();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    //verifier fichier
                    if(!f)
                        printlnToUser("Problèmes dans les entêtes");
                        //verifier Matiere
                    else if (!mColumnMatiereValide)
                        printlnToUser("Il y a une matiere non inscrite");
                        //verifier Jour
                    else if(!mColumnJourValide)
                        printlnToUser("Jour mal écrite");
                        //verifier heure début
                    else if(!mColumnHeureDebutValide)
                        printlnToUser("Heure de dénut mal écrite");
                        //verifier heure de fin
                    else if(!mColumnFinValide)
                        printlnToUser("Heure Fin mal écrite");

                }


            }
        });

    }

    private void enregistrerEmplois() throws FileNotFoundException {
        int idClasse =mListeClasses.get(mSpinnerClasses.getSelectedItemPosition()).getId();
        int idecole = mListeEcoles.get(mSpinnerEcole.getSelectedItemPosition()).getId();


        InputStream stream=getContentResolver().openInputStream(uri);
        try {
            DataManager dm = DataManager.getInstance();
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            //Initialisation


            //progressBar set up
            mProgressBar = new ProgressDialog(this);
            mProgressBar.setCancelable(false);
            mProgressBar.setMessage("Enrégistrement en cours");
            mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressBar.setProgress(0);
            mProgressBar.setMax(100);
            double progressEvolution=0;
            final int tour = (100/rowsCount);
            mProgressBar.show();

//Cotrole du progressDialog en arrière plan
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (mProgressBar.getProgress() <= mProgressBar
                                .getMax()) {
                            Thread.sleep(200);
                            if(mHandler!=null)
                                mHandler.sendMessage(mHandler.obtainMessage());

                            if (mProgressBar.getProgress() == mProgressBar
                                    .getMax()) {

                                mProgressBar.dismiss();

                                AjoutEmplois.this.finish();
                                Toast.makeText(AjoutEmplois.this,"Enrégistrement réussi",Toast.LENGTH_LONG).show();

                            }
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }).start();


            for (int r = 1; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                String matiere = getCellAsString(row, matiereColumnPositon, formulaEvaluator);
                String jour= getCellAsString(row, jourColumnPosition, formulaEvaluator);
                int heure_deb = getCellAsInt(row, heureDebutColumnPosition, formulaEvaluator) ;
                int heure_fin = getCellAsInt(row, heureFinColumnPosition,formulaEvaluator);

                //ideleve = mDataManager.getIdElveFromMatricule(idEcoleActuelle_enseignant,matricule);



                mDataWorker.insertEmplois(idecole, idClasse, getIdMatiereDepuisNom(matiere),jour,heure_deb,heure_fin, Calendar.getInstance().get(Calendar.YEAR));
                progressEvolution = progressEvolution+tour;

                mHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        mProgressBar.incrementProgressBy(tour);
                    }
                };

            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK)
        {
            Log.d("code","mauvais code");
            mTextViewResult.setText("Mauvais fichier, choisir un autre");
            mTextViewResult.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        else
        {
            uri = data.getData();
            mTextViewResult.setText(uri.toString());
            mTextViewResult.setTextColor(Color.parseColor("#008577"));
            fichierValide=true;

            Log.d("code","uri: "+uri);

        }
    }

    public void initialiserFormulaire()
    {
        if(mDataManager==null)
        {
            mDataManager = DataManager.getInstance();
        }
        if(mListeEcoles.size()==0 || mListeEcoles==null)
            mListeEcoles = mDataManager.getListeEcoleEnseignant(mEnseignant.getId());


        //if(mListeClasses.size()==0 || mListeClasses==null)
            mListeClasses = mDataManager.getListeClassesEnseignant(mEnseignant.getId());

        //Enrégistrement spinner ecoles

        List<String> ecoles= new ArrayList<>();
        for(int e=0;e<mListeEcoles.size();e++)
        {
            ecoles.add(mListeEcoles.get(e).getNom());
        }
        Log.d("ecoles s",ecoles.toString());
        Log.d("aaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");



        ArrayAdapter<String> ecolesAdapter = new ArrayAdapter<String>(AjoutEmplois.this, android.R.layout.simple_spinner_item, ecoles);
        ecolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerEcole.setAdapter(ecolesAdapter);

        //Enrégistrement spinner classe

        List<String> classes = new ArrayList<>();
        for(int j=0;j<mListeClasses.size();j++)
        {
            classes.add(mListeClasses.get(j).getNom());
        }

        ArrayAdapter<String> classeAdapter = new ArrayAdapter<String>(AjoutEmplois.this, android.R.layout.simple_spinner_item, classes);
        classeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerClasses.setAdapter(classeAdapter);

    }

    public void getFichier(View view)
    {
        mChooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        mChooseFile.setType("*/*");
        mChooseFile = Intent.createChooser(mChooseFile, "Choose a file");
        startActivityForResult(mChooseFile, PICKFILE_RESULT_CODE);


    }

    public boolean verifierFormatFichier() throws FileNotFoundException {
        boolean isMatiere=false;
        boolean isJour = false ;
        boolean isDebut= false;
        boolean isFin= false;
        InputStream stream=getContentResolver().openInputStream(uri);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            if(rowsCount>1) {
                Row row = sheet.getRow(0);
                int cellsCount = row.getPhysicalNumberOfCells();
                for (int c = 0; c<cellsCount; c++) {
                    String value = getCellAsString(row, c, formulaEvaluator);
                    if(value.equalsIgnoreCase("Matiere") || value.equalsIgnoreCase("Matières") ||
                            value.equalsIgnoreCase("Matieres") || value.equalsIgnoreCase("Matière"))
                    {
                        isMatiere = true;
                        matiereColumnPositon = c;
                    }


                    if(value.equalsIgnoreCase("Jour") || value.equalsIgnoreCase("Jours"))
                    {
                        isJour = true;
                        jourColumnPosition = c;
                    }

                    if(value.equalsIgnoreCase("Heure debut") || value.equalsIgnoreCase("heure début"))
                    {
                        isDebut = true;
                        heureDebutColumnPosition = c;
                    }

                    if(value.equalsIgnoreCase("Heure fin"))
                    {
                        isFin = true;
                        heureFinColumnPosition = c;
                    }


                    // printlnToUser(cellInfo);
                }
            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }

        if(isMatiere && isJour && isFin && isDebut)
        {
            //printlnToUser("Bonne entete");
            return true;
        }
        else
        {
           // printlnToUser("Mauvaise entete");
            return false;
        }
    }

    protected String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        double date = cellValue.getNumberValue();
                        SimpleDateFormat formatter =
                                new SimpleDateFormat("dd/MM/yy");
                        value = formatter.format(HSSFDateUtil.getJavaDate(date));
                    } else {
                        value = ""+numericValue;
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;
                default:
            }
        } catch (NullPointerException e) {
            /* proper error handling should be here */
            e.printStackTrace();
        }
        return value;
    }

    private void printlnToUser(String str) {
      mMTextViewError.setText(str);
    }

    int getIdMatiereDepuisNom(String nom){
        List<Matiere> matieres = DataManager.getInstance().getMatiere();

        for(int f=0;f<matieres.size();f++)
        {
            if(matieres.get(f).getNom().equalsIgnoreCase(nom))
                return matieres.get(f).getId();
        }
        return -1;
    }

    public boolean fichierOkAvecMatiere() throws FileNotFoundException {
        mColumnMatiereValide = true;
        InputStream stream=getContentResolver().openInputStream(uri);
        //boolean fichierMauvais = false ;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 1; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                String value = getCellAsString(row, matiereColumnPositon, formulaEvaluator);
                if(getIdMatiereDepuisNom(value)==-1)
                {
                    mColumnMatiereValide=false;
                    Toast.makeText(AjoutEmplois.this,"M. "+value,Toast.LENGTH_LONG).show();

                }


            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }

        return mColumnMatiereValide;

    }

    public boolean fichierOkAvecJour() throws FileNotFoundException {
        mColumnJourValide = true;


        InputStream stream=getContentResolver().openInputStream(uri);
        //boolean fichierMauvais = false ;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 1; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                String value = getCellAsString(row, jourColumnPosition, formulaEvaluator);
                if((value.equalsIgnoreCase("Lundi") || value.equalsIgnoreCase("Mardi") ||
                        value.equalsIgnoreCase("Mercredi") || value.equalsIgnoreCase("Jeudi") ||
                        value.equalsIgnoreCase("Venderdi") || value.equalsIgnoreCase("Samedi"))==false )
                {
                    mColumnJourValide=false;
                    Toast.makeText(AjoutEmplois.this,"M. "+value,Toast.LENGTH_LONG).show();
                    return  mColumnJourValide;

                }


            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }

        return mColumnMatiereValide;

    }

    public void    fichierOkAvecHeurs() throws FileNotFoundException {
        mColumnHeureDebutValide = true;
        mColumnFinValide = true;


        InputStream stream=getContentResolver().openInputStream(uri);
        //boolean fichierMauvais = false ;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 1; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                int h_debut = getCellAsInt(row, heureDebutColumnPosition, formulaEvaluator);
                int h_fin = getCellAsInt(row, heureFinColumnPosition, formulaEvaluator);
                if( (h_debut>=0 && h_debut<=23)==false)
                {
                    mColumnHeureDebutValide=false;
                    break;
                }

                if( (h_fin>=0 && h_fin<=23)==false)
                {
                    mColumnFinValide =false;
                    break;
                }
                }
        } catch (Exception e) {
            /* proper exception handling to be here */
            e.printStackTrace();
        }


    }

    protected int getCellAsInt(Row row, int c, FormulaEvaluator formulaEvaluator) {
        int value;

            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            value = (int) cellValue.getNumberValue();


        return value;


    }

}