package smart.wm.smart_wm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Button btnLogout;


            getSupportActionBar().setTitle("Schedule Pickup");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            btnLogout = findViewById(R.id.btnLogout);

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPref sp =new SharedPref(activity_schedule.this);
                    sp.logout();
                    Intent i = new Intent(getApplicationContext(),this);
                    startActivity(i);
                    finish();
                }
            });
        }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            onBackPressed();
            return super.onOptionsItemSelected(item);
        }
    }
}