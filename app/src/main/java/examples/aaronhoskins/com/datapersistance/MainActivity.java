package examples.aaronhoskins.com.datapersistance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView tvSharedPrefDisplay;
    EditText etUserInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE);
        tvSharedPrefDisplay = findViewById(R.id.tvSharedPrefDisplay);
        etUserInput = findViewById(R.id.etUserInput);
        displayValueInSharedPref();
    }

    private void storeToSharedPref(String valueToStore) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", valueToStore);
        editor.apply(); //Thread safe, no return, occurs at first chance
        //editor.commit(); //Not thread safe, Boolean Return, happens immediately
    }

    private void displayValueInSharedPref() {
        final String valueInPref = sharedPreferences.getString("key", "No Value Stored");
        tvSharedPrefDisplay.setText(valueInPref);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStoreValueToPref:
                final String userInput = etUserInput.getText().toString();
                if(!userInput.isEmpty()) {
                    storeToSharedPref(userInput);
                    displayValueInSharedPref();
                } else {
                    Toast.makeText(this, "No Value has been entered", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnInsertCars:
                startActivity(new Intent(this, InsertActivity.class));
                break;
            case R.id.btnViewAllCars:
                startActivity(new Intent(this, ListAllActivity.class));
                break;
        }
    }
}
