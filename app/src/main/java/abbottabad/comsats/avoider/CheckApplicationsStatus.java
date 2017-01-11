package abbottabad.comsats.avoider;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.os.Vibrator;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.rvalerio.fgchecker.AppChecker;

public class CheckApplicationsStatus extends Service implements View.OnClickListener {

    private String currentApp = null;
    private SharedPreferences sharedPreferences;
    private EditText ET_password;
    private WindowManager mWindowManager;
    private View view;
    private LinearLayout mLinear;

    public CheckApplicationsStatus() {
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, int startId) {
        String PREFERENCE_FILE_KEY = "abbottabad.comsats.avoider";

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mLinear =  new LinearLayout(getApplicationContext()) {

            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                    mWindowManager.removeView(view);
                }
                return super.dispatchKeyEvent(event);
            }
        };

        mLinear.setFocusable(true);
        view = LayoutInflater.from(CheckApplicationsStatus.this).inflate(R.layout.activity_lock_screen, mLinear);
        ET_password = (EditText) view.findViewById(R.id.ET_password);
        disableSoftKeyboard(ET_password);
        Button btn_1 = (Button) view.findViewById(R.id.btn_1);
        btn_1.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_2 = (Button) view.findViewById(R.id.btn_2);
        btn_2.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_3 = (Button) view.findViewById(R.id.btn_3);
        btn_3.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_4 = (Button) view.findViewById(R.id.btn_4);
        btn_4.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_5 = (Button) view.findViewById(R.id.btn_5);
        btn_5.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_6 = (Button) view.findViewById(R.id.btn_6);
        btn_6.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_7 = (Button) view.findViewById(R.id.btn_7);
        btn_7.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_8 = (Button) view.findViewById(R.id.btn_8);
        btn_8.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_9 = (Button) view.findViewById(R.id.btn_9);
        btn_9.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_0 = (Button) view.findViewById(R.id.btn_0);
        btn_0.setOnClickListener(CheckApplicationsStatus.this);
        Button btn_delete = (Button) view.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(CheckApplicationsStatus.this);

        sharedPreferences = this.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        AppChecker appChecker = new AppChecker();
        appChecker.other(new AppChecker.Listener() {
            @Override
            public void onForeground(String process) {
                if (sharedPreferences.getBoolean(process + "_IS_ON", false)) {
                    if (currentApp == null || !currentApp.equals(process)) {
                        currentApp = process;
                        mWindowManager.addView(view, params);
                    }
                } else if (!process.equalsIgnoreCase("abbottabad.comsats.avoider")) {
                    currentApp = process;
                }
            }
        }).timeout(500).start(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
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
        if (password.equals(sharedPreferences.getString("PASSWORD", "1234"))) {
            mWindowManager.removeView(view);
            ET_password.setText("");
        } else if (sharedPreferences.getBoolean("VIBRATE_IF_WRONG", false)) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(500);
            ET_password.setText("");
        }
    }

    public void disableSoftKeyboard(final EditText v) {
        v.setRawInputType(InputType.TYPE_NULL);
        v.setTextIsSelectable(true);
    }

}
