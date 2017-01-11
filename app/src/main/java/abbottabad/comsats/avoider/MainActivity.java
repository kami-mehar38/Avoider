package abbottabad.comsats.avoider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This project Avoider is created by Kamran Ramzan on 10-Jan-17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ET_password;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";
        sharedPreferences = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean("LOCK_MY_APP", false)) {
            startActivity(new Intent(this, AppsActivity.class));
            finish();
        }
        ET_password = (EditText) findViewById(R.id.ET_password);
        disableSoftKeyboard(ET_password);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        Button btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        Button btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        Button btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        Button btn_0 = (Button) findViewById(R.id.btn_0);
        btn_0.setOnClickListener(this);
        Button btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
    }

    public void disableSoftKeyboard(final EditText v) {
        v.setRawInputType(InputType.TYPE_NULL);
        v.setTextIsSelectable(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0: {
                ET_password.append("0");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_1: {
                ET_password.append("1");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_2: {
                ET_password.append("2");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_3: {
                ET_password.append("3");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_4: {
                ET_password.append("4");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_5: {
                ET_password.append("5");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_6: {
                ET_password.append("6");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_7: {
                ET_password.append("7");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_8: {
                ET_password.append("8");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_9: {
                ET_password.append("9");
                if (ET_password.length() == 4)
                    buttonEnter();
                break;
            }
            case R.id.btn_delete: {
                if (ET_password.length() > 0) {
                    String password = ET_password.getText().toString().trim();
                    ET_password.setText(password.substring(0, password.length() - 1));
                }
                break;
            }
        }
    }

    private void buttonEnter() {
        String password = ET_password.getText().toString().trim();
        if (password.equals(sharedPreferences.getString("PASSWORDAPP", "1234"))) {
            startActivity(new Intent(MainActivity.this, AppsActivity.class));
            finish();
        } else if (sharedPreferences.getBoolean("VIBRATE_IF_WRONG", false)) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            ET_password.setText("");
        }
    }
}
