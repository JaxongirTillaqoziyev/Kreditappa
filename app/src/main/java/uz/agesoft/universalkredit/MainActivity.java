package uz.agesoft.universalkredit;
import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    String[] items = {"А.Жомий",
            "А.Кодирий",
            "А.Навоий",
            "А.Т.Хукандий",
            "А.Темур",
            "А.Умархон",
            "А.Чархий",
            "Абдусамад",
            "Авгон",
            "Амударё",
            "Аравон",
            "Арзик тепа",
            "Ахиллик",
            "Б.Эшон",
            "Барзанги",
            "Бахмалбоб",
            "Бекбутабек",
            "Бешкапа",
            "Бино",
            "Бирлик",
            "Бобур",
            "Куштегирмон",
            "Мирзабод",
            "Самарканд",
            "Чекмирзаобод",
            "Боги бустон",
            "Богиш",
            "Богишамол",
            "Богчачилик",
            "Бой бута",
            "Бой",
            "Бойдовул",
            "Бунёдкор",
            "Бурикум",
            "Буролди",
            "Бустонлик",
            "Бурчилик",
            "Г.Узаков",
            "Галаба",
            "Гертсина",
            "Гиштхона",
            "Гозёглик",
            "Гузар",
            "Гулбой",
            "Гулистон",
            "Гулистон куча",
            "Гумайли",
            "Далачилик",
            "Дангара кучаси",
            "Дерезлик",
            "Дехконобод",
            "Дилкаш",
            "Дилшод",
            "Довчар",
            "Доимобод",
            "Дустлик",
            "Ёркургон",
            "Эшонбобо",
            "Жар",
            "Жилва",
            "Жиякчилик",
            "Жонобод",
            "Жургун",
            "Закий Валидий",
            "Заргарлик",
            "Зариннсарой",
            "Зийнат",
            "Ибн Сино",
            "Илгор",
            "Ингичка",
            "Ислом",
            "Истикбол",
            "Истиклол",
            "Йилгинзор",
            "Какир",
            "Калдушон",
            "Капасарой",
            "Коракалпок",
            "Катта Амирабод",
            "Катта Аравон",
            "Катта бой буча",
            "Катта Ганжиравон",
            "Катта кургонча",
            "Катта минглар",
            "Катта Октепа",
            "Катта турк",
            "Катта Туртайгир",
            "Кашкар",
            "Кенгаш",
            "Кенагас",
            "Кизилмушт",
            "Киргиз",
            "Кирк",
            "Кирккетмон",
            "Кирклар",
            "Кичик Амирабод",
            "Кичик Аравон",
            "Кичик бой буча",
            "Кичик Ганжиравон",
            "Кичик Минглар",
            "Кичик Октепа",
            "Кичик Турк",
            "Кичик Туртайгир",
            "Кияли кургонча",
            "Конизар",
            "Конститутция",
            "Кора курпа",
            "Кора мулла",
            "Кудаш",
            "Кудук",
            "Кудуклик",
            "Кулбобо",
            "Кум",
            "Кум кияли",
            "Кунчибой кургонча",
            "Кунгирот",
            "Кургонча",
            "Курик",
            "Курикхона",
            "Курпабог",
            "Курхона",
            "Куш Куприк",
            "Кушкунок",
            "Лойихавий",
            "М.Йулдош",
            "Моварауннахар",
            "Майда йилгин",
            "Мангит",
            "Марзаллик",
            "Махрам",
            "Мехржон",
            "Минглар",
            "Мингтут",
            "Миндон обод",
            "Миртохир",
            "Мискарлик",
            "Митан",
            "Мукимий",
            "Мулкобод",
            "Муллабошмон",
            "Мунаввар",
            "Мусабек",
            "Мустакиллик",
            "Навбахор",
            "Жалоер",
            "Найманча Кашкар",
            "Найманча",
            "Найманча МФЙ",
            "Нодира",
            "Нурафшон",
            "Нурли йул",
            "Нурсух",
            "О.Кодирова",
            "Обод",
            "Овул",
            "Овчи",
            "Ок йул",
            "Ок кул",
            "Ок мачит",
            "Ок олтин",
            "Ок тепа",
            "Октепасой",
            "Олахамак",
            "Олмурод",
            "Олой",
            "Олтикуш",
            "Олти Урток",
            "Олтин водий",
            "Ораста",
            "Охта Тагоб",
            "Охункайнар",
            "Пахтакор",
            "Парвоз",
            "Парпашабоф",
            "Паски Тулаш",
            "Пахлавон",
            "Пишкарон МФЙ",
            "Полвонтош",
            "Пулаткургон",
            "Рахматилло",
            "Салом",
            "Санам",
            "Сангзор",
            "Сарбоз",
            "Сарбон",
            "Сирдарё",
            "Собиржон",
            "Сой буйи",
            "Сой сохил",
            "Сой шилдир",
            "Сочтепа",
            "Сулувкургон",
            "Т.Малик",
            "Тайпок",
            "Тангрикул",
            "Таптиксарой",
            "Таргова",
            "Тегирмонбоши",
            "Телиминг",
            "Телиминг Ок тепа",
            "Тинчлик",
            "Товуш",
            "Тоглик",
            "Тош",
            "Тошкенлигузар",
            "Тошкент",
            "Тошховуз",
            "Туйхона",
            "Туксонковун",
            "Тулабой",
            "Тумор",
            "Туркистон",
            "Туркман",
            "Турон",
            "Турсунобод",
            "Туртайгир",
            "У.Носир",
            "Увайсий",
            "Увайсий МФЙ",
            "Уймовит",
            "Улкансой",
            "Улугбек",
            "Урганжи",
            "Урдатаги",
            "Урикзор",
            "Уч уйли",
            "Ф.Хужаев",
            "Фирдавсий",
            "Фуркат",
            "Беш эчки",
            "Х.А.Валией",
            "Х.Олимжон",
            "Хабибий",
            "Хазиний",
            "Халкабод",
            "Хамза",
            "Хилол",
            "Хонакох",
            "Хонбоги",
            "Хонкурик",
            "Хужа",
            "Хужакент",
            "Хужамозор",
            "Хурмитан",
            "Чагали",
            "Чанг",
            "Чанкат",
            "Чашма",
            "Чек ер",
            "Чекмирзаобод",
            "Чинобод",
            "Чинор",
            "Чодакилик",
            "Чомоч",
            "Чорток",
            "Чорчаман",
            "Чорчинор",
            "Чувит",
            "Чулпон",
            "Чумбогиш",
            "Ш.Садий",
            "Шабада",
            "Шайхонпаён",
            "Шалдирамок",
            "Шарк массив",
            "Шеркадам",
            "Шикорбеги",
            "Шикоргох",
            "Широк",
            "Шодлик",
            "Шопулат",
            "Шохрухобод",
            "Шур",
            "Эзгулик",
            "Эсанак",
            "Эски",
            "Эски шилдир",
            "Эшон",
            "Яйпан",
            "Яккатут",
            "Ялтир",
            "Янги Замон",
            "Янги",
            "Янги чек",
            "Янги Чорсу",
            "Янгикургон",
            "Янгиобод",
            "Яхши ният",
            "Яшик"
    };
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapteritems;
    String[] itemregion = {"Багдод", "Бешарик", "Бувайда", "Фуркат", "ПОП", "Кукон", "Учкуприк", "Узбекистон", "Дангара"};
    AutoCompleteTextView autoCompleteTextViewregion;
    ArrayAdapter<String> adapteritemsregion;
    public static ArrayList<Mijozlar> mijozlarArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    Button smsjunatish;
    EditText searchview;
    CharSequence search = "";
    RecylerviewAdapter recylerviewAdapter;
    String url = "https://javadev.uz/asdfghjklopiutrewqa/retrieve.php";
    Button yangilash;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yangilash = findViewById(R.id.yangilash);
        progressBar =findViewById(R.id.progressbar);
        yangilash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveDataa();
            }
        });
        autoCompleteTextView = findViewById(R.id.autotanlashmanzil);
        adapteritems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoCompleteTextView.setAdapter(adapteritems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                recylerviewAdapter.getFilter2().filter(item);
            }
        });
        //////
        autoCompleteTextViewregion = findViewById(R.id.autotanlashregion);
        adapteritemsregion = new ArrayAdapter<String>(this, R.layout.list_region, itemregion);
        autoCompleteTextViewregion.setAdapter(adapteritemsregion);
        autoCompleteTextViewregion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                switch (position) {
                    case 0:
                        String url2 = "https://javadev.uz/asdfghjklopiutrewqa/bagdod.php";
                        StringRequest request1 = new StringRequest(Request.Method.POST, url2,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();


                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue1.add(request1);
                        break;
                    case 1:
                        String url3 = "https://javadev.uz/asdfghjklopiutrewqa/beshariq.php";
                        StringRequest request2 = new StringRequest(Request.Method.POST, url3,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                recylerviewAdapter.notifyDataSetChanged();


                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue2 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue2.add(request2);


                        break;
                    case 2:
                        String url4 = "https://javadev.uz/asdfghjklopiutrewqa/buvayda.php";
                        StringRequest request3 = new StringRequest(Request.Method.POST, url4,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    //           String Tug_yil = object.getString("tugilganYili");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue3 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue3.add(request3);
                        break;
                    case 3:
                        String url5 = "https://javadev.uz/asdfghjklopiutrewqa/furqat.php";
                        StringRequest request4 = new StringRequest(Request.Method.POST, url5,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue4 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue4.add(request4);


                        break;




                    case 4:
                        String url6 = "https://javadev.uz/asdfghjklopiutrewqa/pop.php";
                        StringRequest request5 = new StringRequest(Request.Method.POST, url6,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue5 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue5.add(request5);
                        break;
                    case 5:
                        String url7 = "https://javadev.uz/asdfghjklopiutrewqa/qoqon.php";
                        StringRequest request6 = new StringRequest(Request.Method.POST, url7,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    //           String Tug_yil = object.getString("tugilganYili");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    //             String lavozimi = object.getString("Lavozimi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue6 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue6.add(request6);
                        break;
                    case 6:
                        String url8 = "https://javadev.uz/asdfghjklopiutrewqa/uchkoprik.php";
                        StringRequest request7 = new StringRequest(Request.Method.POST, url8,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue7 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue7.add(request7);
                        break;
                    case 7:
                        String url9 = "https://javadev.uz/asdfghjklopiutrewqa/uzbekistan.php";
                        StringRequest request8 = new StringRequest(Request.Method.POST, url9,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            //   e.printStackTrace();
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue8 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue8.add(request8);
                        break;
                    case 8:
                        String url10 = "https://javadev.uz/asdfghjklopiutrewqa/dangara.php";
                        StringRequest request9 = new StringRequest(Request.Method.POST, url10,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        mijozlarArrayList.clear();
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String sucess = jsonObject.getString("success");
                                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                                            if (sucess.equals("1")) {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                for (int i = 0; i < jsonArray.length(); i++) {
                                                    JSONObject object = jsonArray.getJSONObject(i);
                                                    String id = object.getString("id");
                                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                    String SHRN = object.getString("SHRN");
                                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                                    String ishjoyi = object.getString("Ish_joyi");
                                                    String regioni = object.getString("Regioni");
                                                    String manzili = object.getString("Manzili");
                                                    String oyi = object.getString("oyi");
                                                    String izoh = object.getString("izoh");
                                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                    mijozlarArrayList.add(mijozlar2);
                                                }
                                                recylerviewAdapter.notifyDataSetChanged();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                            }
                        });
                        RequestQueue requestQueue9 = Volley.newRequestQueue(MainActivity.this);
                        requestQueue9.add(request9);
                        break;
                }
            }
        });

        smsjunatish = findViewById(R.id.smsjunatish);
        smsjunatish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                CharSequence[] dialogItem = {"Багдод", "Бешарик", "Бувайда", "Фуркат", "ПОП", "Кукон", "Учкуприк", "Узбекистон", "Дангара"};
                builder.setTitle("SMS jo'natish oynasi");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i) {
                            case 0:
                                String url2 = "https://javadev.uz/asdfghjklopiutrewqa/bagdod.php";
                                StringRequest request1 = new StringRequest(Request.Method.POST, url2,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            //           String Tug_yil = object.getString("tugilganYili");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);



                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        recylerviewAdapter.notifyDataSetChanged();


                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue1 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue1.add(request1);
                                break;
                            case 1:
                                String url3 = "https://javadev.uz/asdfghjklopiutrewqa/beshariq.php";
                                StringRequest request2 = new StringRequest(Request.Method.POST, url3,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);
                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();


                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue2 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue2.add(request2);


                                break;
                            case 2:
                                String url4 = "https://javadev.uz/asdfghjklopiutrewqa/buvayda.php";
                                StringRequest request3 = new StringRequest(Request.Method.POST, url4,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);
                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue3 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue3.add(request3);


                                break;

                            case 3:
                                String url5 = "https://javadev.uz/asdfghjklopiutrewqa/furqat.php";
                                StringRequest request4 = new StringRequest(Request.Method.POST, url5,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            //           String Tug_yil = object.getString("tugilganYili");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            //             String lavozimi = object.getString("Lavozimi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);
                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue4 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue4.add(request4);


                                break;

                            /////


                            case 4:
                                String url6 = "https://javadev.uz/asdfghjklopiutrewqa/pop.php";
                                StringRequest request5 = new StringRequest(Request.Method.POST, url6,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            //           String Tug_yil = object.getString("tugilganYili");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            //             String lavozimi = object.getString("Lavozimi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);
                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue5 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue5.add(request5);
                                break;
                            case 5:
                                String url7 = "https://javadev.uz/asdfghjklopiutrewqa/qoqon.php";
                                StringRequest request6 = new StringRequest(Request.Method.POST, url7,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            //           String Tug_yil = object.getString("tugilganYili");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            //             String lavozimi = object.getString("Lavozimi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);



                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue6 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue6.add(request6);
                                break;
                            case 6:
                                String url8 = "https://javadev.uz/asdfghjklopiutrewqa/uchkoprik.php";
                                StringRequest request7 = new StringRequest(Request.Method.POST, url8,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);



                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue7 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue7.add(request7);
                                break;
                            case 7:
                                String url9 = "https://javadev.uz/asdfghjklopiutrewqa/uzbekistan.php";
                                StringRequest request8 = new StringRequest(Request.Method.POST, url9,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);



                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue8 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue8.add(request8);
                                break;
                            case 8:
                                String url10 = "https://javadev.uz/asdfghjklopiutrewqa/dangara.php";
                                StringRequest request9 = new StringRequest(Request.Method.POST, url10,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                mijozlarArrayList.clear();
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String sucess = jsonObject.getString("success");
                                                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                                                    if (sucess.equals("1")) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        for (int i = 0; i < jsonArray.length(); i++) {
                                                            JSONObject object = jsonArray.getJSONObject(i);
                                                            String id = object.getString("id");
                                                            String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                                            String SHRN = object.getString("SHRN");
                                                            String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                                            String Tovar_summasi = object.getString("Tovar_summasi");
                                                            String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                                            String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                                            String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                                            String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                                            String Jami_qarzi = object.getString("Jami_qarzi");
                                                            String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                                            String Telefon_raqami = object.getString("Telefon_raqami");
                                                            String ishjoyi = object.getString("Ish_joyi");
                                                            String regioni = object.getString("Regioni");
                                                            String manzili = object.getString("Manzili");
                                                            String oyi = object.getString("oyi");
                                                            String izoh = object.getString("izoh");
                                                            SmsManager smsManager = SmsManager.getDefault();
                                                            smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);



                                                            Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                                            mijozlarArrayList.add(mijozlar2);
                                                        }
                                                        //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
                                                        recylerviewAdapter.notifyDataSetChanged();
                                                    }
                                                } catch (JSONException e) {
                                                    //   e.printStackTrace();
                                                    Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                RequestQueue requestQueue9 = Volley.newRequestQueue(MainActivity.this);
                                requestQueue9.add(request9);
                                break;
                        }


                    }
                });
                builder.create().show();
            }
        });
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        searchview = findViewById(R.id.qidirishsh);
        setTitle("Mijozlar Oynasi");
        recyclerView = (RecyclerView) findViewById(R.id.myListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recylerviewAdapter = new RecylerviewAdapter(this, mijozlarArrayList);
        recyclerView.setAdapter(recylerviewAdapter);
        retrieveDataa();
        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recylerviewAdapter.getFilter2().filter(charSequence);
                search = charSequence;
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
//    private void smsjunatish() {
//
//        StringRequest request = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        mijozlarArrayList.clear();
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String sucess = jsonObject.getString("success");
//                            JSONArray jsonArray = jsonObject.getJSONArray("data");
//                            if (sucess.equals("1")) {
//                                progressBar.setVisibility(View.INVISIBLE);
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject object = jsonArray.getJSONObject(i);
//                                    String id = object.getString("id");
//                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
//                                    //  String Tug_yil = object.getString("tugilganYili");
//                                    String SHRN = object.getString("SHRN");
//                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
//                                    String Tovar_summasi = object.getString("Tovar_summasi");
//                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
//                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
//                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
//                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
//                                    String Jami_qarzi = object.getString("Jami_qarzi");
//                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
//                                    String Telefon_raqami = object.getString("Telefon_raqami");
//                                    String ishjoyi = object.getString("Ish_joyi");
//                                    String regioni = object.getString("Regioni");
//                                    String manzili = object.getString("Manzili");
//                                    String oyi = object.getString("oyi");
//                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi);
//                                    mijozlarArrayList.add(mijozlar2);
//                                    SmsManager smsManager = SmsManager.getDefault();
////                                    smsManager.sendTextMessage(Telefon_raqami, null, FamilyaIsmSharif + " sizni UNIVERSAL KREDIT savdo do'konidan " + SHRN + " raqamli shartnomaga asosan " + Bir_oylik_tulovi + " to'lovingiz mavjud Kechikkan summangiz" + Kechikkan_summasi + " ni  tashkil qiladi sizdan to'lovni o'z vaqdida to'lashingizni iltimos qilamiz  UNIVERSAL KREDIT savdo do'koni", null, null);
//                                    smsManager.sendTextMessage(Telefon_raqami, null, "UNIVERSAL KREDIT savdo do'konidan sizda krediz to'lovingiz mavjud sizdan iltimos qilamizki Kreditningizni o'z vaqtida to'lang ", null, null);
//
//
//                                }
//                                //  recylerviewAdapter.setArrayListMijozlar(mijozlarArrayList);
//                                recylerviewAdapter.notifyDataSetChanged();
//
//
//                            }
//                        } catch (JSONException e) {
//                            //   e.printStackTrace();
//                            Toast.makeText(MainActivity.this, "Server bilan aloqa yoq", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Server Bilan aloqa yoq", Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(request);
//
//    }
    public void retrieveDataa() {
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        mijozlarArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String sucess = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (sucess.equals("1")) {


                               progressBar.setVisibility(View.INVISIBLE);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String FamilyaIsmSharif = object.getString("FamilyaIsmSharif");
                                    String SHRN = object.getString("SHRN");
                                    String Shartnoma_sanasi = object.getString("Shartnoma_sanasi");
                                    String Tovar_summasi = object.getString("Tovar_summasi");
                                    String Oldindantulov_summasi = object.getString("Oldindantulov_summasi");
                                    String Bir_oylik_tulovi = object.getString("Bir_oylik_tulovi");
                                    String Jami_tulagan_summasi = object.getString("Jami_tulagan_summasi");
                                    String Tulab_ketgan_sanasi = object.getString("Tulab_ketgan_sanasi");
                                    String Jami_qarzi = object.getString("Jami_qarzi");
                                    String Kechikkan_summasi = object.getString("Kechikkan_summasi");
                                    String Telefon_raqami = object.getString("Telefon_raqami");
                                    String ishjoyi = object.getString("Ish_joyi");
                                    String regioni = object.getString("Regioni");
                                    String manzili = object.getString("Manzili");
                                    String oyi = object.getString("oyi");
                                    String izoh = object.getString("izoh");

                                    Mijozlar mijozlar2 = new Mijozlar(id, FamilyaIsmSharif, SHRN, Shartnoma_sanasi, Tovar_summasi, Oldindantulov_summasi, Bir_oylik_tulovi, Jami_tulagan_summasi, Tulab_ketgan_sanasi, Jami_qarzi, Kechikkan_summasi, Telefon_raqami, ishjoyi, regioni, manzili, oyi,izoh);
                                    mijozlarArrayList.add(mijozlar2);
                                }
                                recylerviewAdapter.notifyDataSetChanged();


                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Internet bilan aloqa yoq", Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recylerviewAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}