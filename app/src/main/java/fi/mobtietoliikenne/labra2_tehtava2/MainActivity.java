package fi.mobtietoliikenne.labra2_tehtava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static TextView tvOutput2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnStartAction).setOnClickListener(this);
        tvOutput2 = (TextView) findViewById(R.id.tvOutput2);
    }

    @Override
    public void onClick(View v) {
        AsynkTask task = new AsynkTask();
        task.execute();
    }
}
