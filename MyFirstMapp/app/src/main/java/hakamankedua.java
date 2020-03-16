import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfirstmapp.MainActivity;
import com.example.myfirstmapp.R;.android.app.Activity;

public class hakamankedua {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentpam = new Intent(MainActivity.this, linearlayout.class);
                startActivity(intentpam);
            }
        });
}

    private void setContentView(int activity_main) {
    }
