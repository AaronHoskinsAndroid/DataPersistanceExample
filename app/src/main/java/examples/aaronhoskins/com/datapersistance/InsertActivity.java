package examples.aaronhoskins.com.datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    //Declare views
    EditText etVin;
    EditText etMake;
    EditText etModel;
    EditText etYear;
    EditText etEngine;
    EditText etTransmission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etVin = findViewById(R.id.etVin);
        etMake = findViewById(R.id.etMake);
        etModel = findViewById(R.id.etModel);
        etYear = findViewById(R.id.etYear);
        etEngine = findViewById(R.id.etEngine);
        etTransmission = findViewById(R.id.etTransmission);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                final String vin = etVin.getText().toString();
                final String model = etModel.getText().toString();
                final String make = etMake.getText().toString();
                final String year = etYear.getText().toString();
                final String engine = etEngine.getText().toString();
                final String trans = etTransmission.getText().toString();

                if(!(vin.isEmpty() || model.isEmpty() || make.isEmpty() || year.isEmpty() || engine.isEmpty() || trans.isEmpty())) {
                    Cars carToInsert = new Cars(make, model, year, engine, trans, vin);
                    CarDatabaseHelper dbHelper = new CarDatabaseHelper(this);
                    dbHelper.insertCarIntoDatabase(carToInsert);
                }
                break;
            case R.id.btnGotoMain:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
