package uz.agesoft.universalkredit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class DetailActivity extends AppCompatActivity {

    TextView tv_id;
    TextView tv_familya_ism_sharif;
    TextView tv_shartnoma_raqami;
    TextView tv_shartnoma_sanasi;
    TextView tv_bir_oylik_tulovi;
    TextView tv_ish_joyi;
    TextView tv_jami_qarzi;
    TextView tv_jami_tulagan_summasi;
    TextView tv_kechikkan_summasi;
    TextView tv_manzili;
    TextView tv_oldindan_tulov_summasi;
    TextView tv_regioni;
    TextView tv_telefon_raqami;
    TextView tv_tovar_summasi;
    TextView tv_tulab_ketgan_sanasi;
    TextView tv_oyi;
    TextView tv_izoh;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String id = getIntent().getStringExtra("id");
        String fam = getIntent().getStringExtra("fam");
        String shrn = getIntent().getStringExtra("shrn");
        String shartsanasi = getIntent().getStringExtra("shartsanasi");
        String tovarsummasi = getIntent().getStringExtra("tovarsummasi");
        String oldindantulovsummasi = getIntent().getStringExtra("oldindantulovsummasi");
        String biroyliktulovi = getIntent().getStringExtra("biroyliktulovi");
        String jamitulagansummasi = getIntent().getStringExtra("jamitulagansummasi");
        String tulketgansana = getIntent().getStringExtra("tulketgansana");
        String jamiqarzi = getIntent().getStringExtra("jamiqarzi");
        String kechsum = getIntent().getStringExtra("kechsum");
        String telraq = getIntent().getStringExtra("telraq");
        String ishjoy = getIntent().getStringExtra("ishjoy");
        String regioni = getIntent().getStringExtra("regioni");
        String manzili = getIntent().getStringExtra("manzili");
        String oyi = getIntent().getStringExtra("oyi");
        String izoh = getIntent().getStringExtra("izoh");

        setTitle("Mijoz ma'lumotlari bo'limi");
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_familya_ism_sharif = (TextView) findViewById(R.id.familyaismshariffff);
        tv_shartnoma_raqami = (TextView) findViewById(R.id.txt_shartnoma_raqami);
        tv_shartnoma_sanasi = (TextView) findViewById(R.id.txt_shartnoma_sanasi);
        tv_tovar_summasi = (TextView) findViewById(R.id.txt_tovar_summasi);
        tv_oldindan_tulov_summasi = (TextView) findViewById(R.id.txt_oldindan_tulov_summasi);
        tv_bir_oylik_tulovi = (TextView) findViewById(R.id.txt_bir_oylik_tulovi);
        tv_jami_tulagan_summasi = (TextView) findViewById(R.id.txt_jami_tulagan_summasi);
        tv_tulab_ketgan_sanasi = (TextView) findViewById(R.id.txt_tulab_ketgan_sanasi);
        tv_jami_qarzi = (TextView) findViewById(R.id.txt_jami_qarzi);
        tv_kechikkan_summasi = (TextView) findViewById(R.id.txt_kechikkan_summasi);
        tv_telefon_raqami = (TextView) findViewById(R.id.txt_telefon_raqami);
        tv_ish_joyi = (TextView) findViewById(R.id.txt_ish_joyi);
        tv_regioni = (TextView) findViewById(R.id.txt_regioni);
        tv_manzili = (TextView) findViewById(R.id.txt_manzili);
        tv_oyi = (TextView) findViewById(R.id.txt_oyi);
        tv_izoh = (TextView) findViewById(R.id.txt_izoh);



        tv_id.setText("ID:" + id);
        tv_familya_ism_sharif.setText("FIO:" + fam);
        tv_shartnoma_raqami.setText("Shartnoma raqami:" + shrn);
        tv_shartnoma_sanasi.setText("Shartnoma sanasi:" + shartsanasi);
        tv_tovar_summasi.setText("Tovar summasi:" + tovarsummasi);
        tv_oldindan_tulov_summasi.setText("Oldindan to'lov summasi:" + oldindantulovsummasi);
        tv_bir_oylik_tulovi.setText("1 oylik to'lovi:" + biroyliktulovi);
        tv_jami_tulagan_summasi.setText("Jami to'lagan summasi:" + jamitulagansummasi);
        tv_tulab_ketgan_sanasi.setText("To'lab ketgan sanasi:" + tulketgansana);
        tv_jami_qarzi.setText("Jami qarzi:" + jamiqarzi);
        tv_kechikkan_summasi.setText("Kechikkan summasi:" + kechsum);
        tv_telefon_raqami.setText(telraq);
        tv_ish_joyi.setText("Ish joyi:" + ishjoy);
        tv_regioni.setText("Regioni:" + regioni);
        tv_manzili.setText("Manzili:" + manzili);
        tv_oyi.setText("Kechikkan oyi:" + oyi);
        tv_izoh.setText("Izohi:" + izoh);

        tv_telefon_raqami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tv_telefon_raqami.getText().toString()));
                startActivity(intent);
            }
        });
    }
}
