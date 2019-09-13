package br.com.example.futebol.presence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.List;

import br.com.example.database.PlayersDB;
import br.com.example.database.domain.Presence;
import br.com.example.futebol.R;

public class PresencaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ImageView imgCalendar;
    Button btnSelecionar;
    RecyclerView rcvPresenca;
    TextView txtNoResult;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenca);

        imgCalendar = findViewById(R.id.imgCalendar);

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        PresencaActivity.this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setTitle("Escolha uma data");
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.show(getFragmentManager(),"DatePickerDialog");
            }
        });

        btnSelecionar = findViewById(R.id.btnSelecionar);
        txtNoResult = findViewById(R.id.noResult);
        rcvPresenca = findViewById(R.id.rcvPresenca);



    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        PlayersDB db = new PlayersDB(getApplicationContext());
        String data = year + "/" + (monthOfYear+1) + "/" + dayOfMonth;
        List<Presence> list = db.buscarPresenca(data);

        if (!list.isEmpty()){

        }
    }
}
