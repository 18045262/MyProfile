package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Float GPA = Float.parseFloat(etGPA.getText().toString());
                int gender = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("name",name);
                prefEdit.putFloat("GPA",GPA);
                prefEdit.putInt("gender",gender);
                prefEdit.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        Float GPA = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name",name);
        prefEdit.putFloat("GPA",GPA);
        prefEdit.putInt("gender",gender);
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String msgName = prefs.getString("name", "");
        Float msgGPA = prefs.getFloat("GPA", 0);
        int msgRadio = prefs.getInt("Gender", 0);

        etName.setText(msgName);
        etGPA.setText(msgGPA + "");
        rgGender.check(msgRadio);
    }


}
