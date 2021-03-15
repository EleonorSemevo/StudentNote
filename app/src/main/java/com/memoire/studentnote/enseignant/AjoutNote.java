package com.memoire.studentnote.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.memoire.studentnote.R;
import com.memoire.studentnote.classes.Enseignant;
import com.memoire.studentnote.classes.Enseigner;
import com.memoire.studentnote.classes.Etudier;
import com.memoire.studentnote.database.DataManager;
import com.memoire.studentnote.database.DatabaseDataWorker;
import com.memoire.studentnote.database.DatabaseOpenHelper;
import com.memoire.studentnote.database.DatabaseUtil;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import android.net.Uri;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.memoire.studentnote.database.DatabaseUtil.mDataManager;
import static com.memoire.studentnote.database.DatabaseUtil.mDataWorker;
import static com.memoire.studentnote.database.DatabaseUtil.mDatabaseOpenHelper;
import static com.memoire.studentnote.database.DatabaseUtil.mEnseignant;
import static com.memoire.studentnote.database.DatabaseUtil.mListeClasses;
import static com.memoire.studentnote.database.DatabaseUtil.mListeEcoles;
import static com.memoire.studentnote.database.DatabaseUtil.mListeMatiere;
import static com.memoire.studentnote.database.DatabaseUtil.mdb;

public class AjoutNote extends AppCompatActivity {
    Spinner mSpinnerClasse;
    Spinner mSpinnerType;
    Spinner mSpinnerDescription;
    Spinner mSpinnerMatiere;
    Spinner mSpinnerAnneeScolaire;
    EditText mEditTextDate;
    TextView mEditTextNomFichier;
    Button mButtonChoixFichier;
    Button mButtonAnnuler;
    Button mButtonEnregistrer;

    EditText output;;

    int PICKFILE_RESULT_CODE;
    Uri uri;
    Intent chooseFile;
    int mIdecole=0;
    int mIdclasse=0;
    int mIdmatiere=0;
    int mIdeleve=0;
    String mDescription="";
    String mType="";
    String mDateComposition ="";



    int mAnneeScolaire;


    int numeroColumnMatricule;
    int numeroColumnNote;
    private String mMatriculeError;
    private boolean mFormulaireBon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_note);

        mSpinnerClasse = findViewById(R.id.spinner_classe);
        mEditTextNomFichier = findViewById(R.id.textview_nom_fichier);
        mButtonChoixFichier = findViewById(R.id.boutton_choix_fichier);
        mButtonAnnuler = findViewById(R.id.bouton_annuler);
        mButtonEnregistrer = findViewById(R.id.boutton_enregistrer);

        mSpinnerType = findViewById(R.id.spinner_type) ;
        mSpinnerDescription = findViewById(R.id.spinner_description);
        mSpinnerAnneeScolaire = findViewById(R.id.spinner_anne_scolaire);
        mEditTextDate = findViewById(R.id.editText_datecomposition);
        mSpinnerMatiere = findViewById(R.id.spinner_matiere);

        output = findViewById(R.id.output);
        mButtonChoixFichier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFichier(v);
            }
        });
        //
        if(!mdb.isOpen())
        {
            mDatabaseOpenHelper = new DatabaseOpenHelper(this);
            mdb= mDatabaseOpenHelper.getReadableDatabase();
            mDataWorker = new DatabaseDataWorker(mdb);
        }

        //
        initialiserFormulaire();

        mButtonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    validerFichierInsererNote();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                if(verifierChampFichier())
                {
                    Toast.makeText(AjoutNote.this, "Bien",Toast.LENGTH_LONG).show();


                }
                else
                {
                    Toast.makeText(AjoutNote.this, "Mauvais",Toast.LENGTH_LONG).show();
                }
            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK)
        {
            Log.d("code","mauvais code");
            mEditTextNomFichier.setText("Mauvais fichier, choisir un autre");
            mEditTextNomFichier.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        else
        {
            uri = data.getData();
            mEditTextNomFichier.setText(uri.toString());
            mEditTextNomFichier.setTextColor(Color.parseColor("#008577"));

            Log.d("code","uri: "+uri);

        }


    }

    public void annuler()
    {
        finish();
    }

    public void onReadClick(View view) throws FileNotFoundException {

        printlnToUser("reading XLSX file from resources");
        // InputStream stream = getResources().openRawResource(R.raw.test1);
        InputStream stream=getContentResolver().openInputStream(uri);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 0; r<rowsCount; r++) {
                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                for (int c = 0; c<cellsCount; c++) {
                    String value = getCellAsString(row, c, formulaEvaluator);
                    String cellInfo = "r:"+r+"; c:"+c+"; v:"+value;
                    printlnToUser(cellInfo);
                }
            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            printlnToUser(e.toString());
        }
    }

    private void printlnToUser(String str) {
        final String string = str;
        if (output.length()>8000) {
            CharSequence fullOutput = output.getText();
            fullOutput = fullOutput.subSequence(5000,fullOutput.length());
            output.setText(fullOutput);
            output.setSelection(fullOutput.length());
        }
        output.append(string+"\n");
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
            printlnToUser(e.toString());
        }
        return value;
    }

    protected String getStringFromDouble(Row row, int c, FormulaEvaluator formulaEvaluator)
    {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    int numericValue = ((int) cellValue.getNumberValue());
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
            printlnToUser(e.toString());
        }
        return value;
    }

    public void getFichier(View view)
    {
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);


    }



    public boolean verifierFormatFichier() throws FileNotFoundException {
        boolean isMatricule =false;
        boolean isNote = false ;
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
                    if(value.equalsIgnoreCase("Note") || value.equalsIgnoreCase("Notes"))
                    {
                        isNote = true;
                        numeroColumnNote = c;
                    }


                    if(value.equalsIgnoreCase("Matricules") || value.equalsIgnoreCase("Matricule"))
                    {
                        isMatricule = true;
                        numeroColumnMatricule = c;
                    }
                   // printlnToUser(cellInfo);
                }
            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            printlnToUser(e.toString());
        }

        if(isMatricule && isNote)
        {
            printlnToUser("Bonne entete");
            return true;
        }
        else
        {
            printlnToUser("Mauvaise entete");
            return false;
        }
    }


    public boolean fichierOkAvecMatricule() throws FileNotFoundException {
        InputStream stream=getContentResolver().openInputStream(uri);
        boolean fichierMauvais = false ;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 0; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                    String value = getStringFromDouble(row, numeroColumnMatricule, formulaEvaluator);
                    if(!matriculeExiste(value))
                    {
                        fichierMauvais=true;
                        Toast.makeText(AjoutNote.this,"M. "+value,Toast.LENGTH_LONG).show();
                        mMatriculeError = value;
                    }


            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            printlnToUser(e.toString());
        }

        return !fichierMauvais;

    }

    public boolean matriculeExiste(String matricule)
    {
        List<Etudier> liste_tudiers = DataManager.getInstance().getEtudiers();
        List<String> matricules= new ArrayList<>();
        for(int i=0;i<liste_tudiers.size();i++)
        {
            matricules.add(liste_tudiers.get(i).getMatricule());
        }

        if(matricules.contains(matricule))
            return true;

        return  false;
    }

    public void insererNote() throws FileNotFoundException {
        //Les données à recevoir depuis le formulaire
        int idecole=1;
        int idclasse=1;
        int idmatiere=1;
        int ideleve=1;
        String description=mSpinnerDescription.getSelectedItem().toString();
        String type= mSpinnerType.getSelectedItem().toString();
        String dateComposition =mEditTextDate.getText().toString();
     //   String matricule;




        InputStream stream=getContentResolver().openInputStream(uri);
        try {
            DataManager dm = DataManager.getInstance();
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int r = 1; r<rowsCount; r++) {
                Row row = sheet.getRow(r);

                String matricule = getCellAsString(row, numeroColumnMatricule, formulaEvaluator);
                String n= getCellAsString(row, numeroColumnNote, formulaEvaluator);
                Double note = Double.parseDouble(n);
                ideleve = mDataManager.getIdElveFromMatricule(idecole,matricule);
                mDataWorker.insertNote(idmatiere,ideleve,idclasse,idecole,type, dateComposition,description,mAnneeScolaire,note);
                Log.d("**********","#############"+n);
                Log.d("aaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            }
        } catch (Exception e) {
            /* proper exception handling to be here */
            printlnToUser(e.toString());
        }



    }

    public void enregistreNote()
    {
         mIdecole=0;
         mIdclasse=0;
         mIdmatiere=0;
         mIdeleve=0;
         mDescription = mSpinnerDescription.getSelectedItem().toString();
         mType = mSpinnerType.getSelectedItem().toString();
         mDateComposition = mEditTextDate.getText().toString();

        int mAnneeScolaire;
    }

    public void Enseignant()
    {
        String currentMail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        List<Enseignant> enseignants = new ArrayList<>();
        enseignants = DataManager.getInstance().getEnseignants();
        int id = -1;
        for(int i=0;i<enseignants.size();i++)
        {
            if(enseignants.get(i).getMail().equals(currentMail))
                mEnseignant = enseignants.get(i);
        }

    }

    public void idEcoleFromIdEnseignant()
    {
        List<Enseigner> enseigners= new ArrayList<>();
        enseigners = DataManager.getInstance().getEnseigners();

        for(int j=0;j<enseigners.size();j++)
        {
            if(enseigners.get(j).getIdEnseignant()==mEnseignant.getId())
                DatabaseUtil.mIdEcole = enseigners.get(j).getIdEcole();
        }
    }

    public void idMatiereFromIdEnseignant()
    {
        List<Enseigner> enseigners= new ArrayList<>();
        enseigners = DataManager.getInstance().getEnseigners();

        for(int k=0;k<enseigners.size();k++)
        {
            if(enseigners.get(k).getId()== mEnseignant.getId())
            {
                DatabaseUtil.mIdMatiere = enseigners.get(k).getIdMatiere();
            }
        }
    }

    public void initialiserFormulaire()
    {
        if(mDataManager==null)
        {
            mDataManager = DataManager.getInstance();
        }
        mListeEcoles = mDataManager.getListeEcoleEnseignant(mEnseignant.getId());
        Log.d("*************",mEnseignant.getId()+"");
        Log.d("############","********************************************");
        mListeMatiere = mDataManager.getListeMatieresEnseignant(mEnseignant.getId());
        mListeClasses = mDataManager.getListeClassesEnseignant(mEnseignant.getId());


        DataManager dataManager = DataManager.getInstance();
        //Spinner Description
        List<String> descriptions = new ArrayList<>();
        descriptions.add("Exercice");
        descriptions.add("Composition");
        descriptions.add("Devoir");
        descriptions.add("Interrogation");


        ArrayAdapter<String> desciptionAdapter = new ArrayAdapter<String>(AjoutNote.this, android.R.layout.simple_spinner_item, descriptions);
        desciptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerDescription.setAdapter(desciptionAdapter);
        //Spinner Type;

        List<String> types = new ArrayList<>();
        types.add("Semestre 1");
        types.add("Semestre 2");
        types.add("Trimestre 1");
        types.add("Trimestre 2");
        types.add("Trimestre 3");


        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(AjoutNote.this, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerType.setAdapter(typeAdapter);

        //Spinner Matiere
        List<String> nomMatiere = new ArrayList<>();

        for (int c=0; c<mListeMatiere.size();c++)
        {
            nomMatiere.add(mListeMatiere.get(c).getNom());
        }

        ArrayAdapter<String> nomMatiereAdapter = new ArrayAdapter<String>(AjoutNote.this, android.R.layout.simple_spinner_item, nomMatiere);
        nomMatiereAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMatiere.setAdapter(nomMatiereAdapter);

        //Spinner Classe
        List<String> nomClasses =new ArrayList<>();


        for(int e=0; e<mListeClasses.size();e++)
        {
            nomClasses.add(mListeClasses.get(e).getNom());
        }

        ArrayAdapter<String> classeAdapter = new ArrayAdapter<String>(AjoutNote.this, android.R.layout.simple_spinner_item, nomClasses);
        classeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerClasse.setAdapter(classeAdapter);

        //Spinner Année scolaire
        //List<String> annees = dataManager.getAnneesScolaire();
        List<String>  annees = new ArrayList<>();
        annees.add("2017");
        ArrayAdapter<String> anneeAdapter = new ArrayAdapter<String>(AjoutNote.this, android.R.layout.simple_spinner_item, annees);
        anneeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerAnneeScolaire.setAdapter(anneeAdapter);



    }


    public void verifierDate()
    {
        boolean estBon=false;
        String date =mEditTextDate.getText().toString();

    }

    public void validerFichierInsererNote() throws FileNotFoundException {
        mFormulaireBon = true;
        boolean matriculeOk= fichierOkAvecMatricule();
        boolean enteteOk = verifierFormatFichier();
        insererNote();
        //mDataWorker.insertNote(1,1,1,1,"Exercice","08/04/2021","semestre1",2020,15.0);

    }

    public boolean verifierChampFichier()
    {
        Boolean resultat = false;
        if(uri==null)
            resultat= false;
        else
            if(uri.toString().contains(".xlsx"))
                resultat= true;
            else
                resultat= false;
         return resultat;
    }




}