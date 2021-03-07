package com.memoire.studentnote.enseignant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.memoire.studentnote.R;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

public class AjoutNote extends AppCompatActivity {
    Spinner mSpinner;
    TextView mNomFichier;
    Button mChoixFichier;
    Button mAnnuler;
    Button mEnregistrer;

    EditText output;;

    int PICKFILE_RESULT_CODE;
    Uri uri;
    Intent chooseFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_note);

        mSpinner = findViewById(R.id.spinner_fichier);
        mNomFichier = findViewById(R.id.textview_nom_fichier);
        mChoixFichier = findViewById(R.id.boutton_choix_fichier);
        mAnnuler = findViewById(R.id.bouton_annuler);
        mEnregistrer = findViewById(R.id.boutton_enregistrer);

        output = findViewById(R.id.output);
        mChoixFichier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFichier(v);
            }
        });

        mEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onReadClick(v);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

//        getFichier(mChoixFichier);
//        try {
//            onReadClick(mEnregistrer);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK)
        {
            Log.d("code","mauvais code");
            mNomFichier.setText("Mauvais fichier, choisir un autre");
            mNomFichier.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        else
        {
            uri = data.getData();
            mNomFichier.setText(uri.toString());
            mNomFichier.setTextColor(Color.parseColor("#008577"));

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

    public void getFichier(View view)
    {
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);


    }
}