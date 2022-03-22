package uz.agesoft.universalkredit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecylerviewAdapter extends RecyclerView.Adapter<RecylerviewAdapter.RecylerviewHolder> {
    Context context;
    List<Mijozlar> mijozlarList = new ArrayList<>();

    List<Mijozlar> filtermijozlarvarogilist;
    List<Mijozlar> filtermijozlarvarogiRegioni;
    String a;

    public RecylerviewAdapter(Context context, List<Mijozlar> mijozlarList) {
        this.context = context;
        this.mijozlarList = mijozlarList;
        this.filtermijozlarvarogilist = mijozlarList;
        this.filtermijozlarvarogiRegioni = mijozlarList;
    }

    @NonNull
    @Override
    public RecylerviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item, parent, false);
        return new RecylerviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerviewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.id.setText(filtermijozlarvarogilist.get(position).getOyi());
        holder.familyaismsharif.setText(filtermijozlarvarogilist.get(position).getFamilyaIsmSharif());
        holder.kechikkansumma.setText(filtermijozlarvarogilist.get(position).getKechikkan_summasi());
        holder.manzili.setText(filtermijozlarvarogilist.get(position).getManzili());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Mijoz ma'lumotlari", "Izoh qo'shish"};
                builder.setTitle(filtermijozlarvarogilist.get(position).getFamilyaIsmSharif());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(context, DetailActivity.class);
                                intent.putExtra("id", filtermijozlarvarogilist.get(position).getId());
                                intent.putExtra("fam", filtermijozlarvarogilist.get(position).getFamilyaIsmSharif());
                                intent.putExtra("shrn", filtermijozlarvarogilist.get(position).getSHRN());
                                intent.putExtra("shartsanasi", filtermijozlarvarogilist.get(position).getShartnoma_sanasi());
                                intent.putExtra("tovarsummasi", filtermijozlarvarogilist.get(position).getTovar_summasi());
                                intent.putExtra("oldindantulovsummasi", filtermijozlarvarogilist.get(position).getOldindantulov_summasi());
                                intent.putExtra("biroyliktulovi", filtermijozlarvarogilist.get(position).getBir_oylik_tulovi());
                                intent.putExtra("jamitulagansummasi", filtermijozlarvarogilist.get(position).getJami_tulagan_summasi());
                                intent.putExtra("tulketgansana", filtermijozlarvarogilist.get(position).getTulab_ketgan_sanasi());
                                intent.putExtra("jamiqarzi", filtermijozlarvarogilist.get(position).getJami_qarzi());
                                intent.putExtra("kechsum", filtermijozlarvarogilist.get(position).getKechikkan_summasi());
                                intent.putExtra("telraq", filtermijozlarvarogilist.get(position).getTelefon_raqami());
                                intent.putExtra("ishjoy", filtermijozlarvarogilist.get(position).getIsh_joyi());
                                intent.putExtra("regioni", filtermijozlarvarogilist.get(position).getRegioni());
                                intent.putExtra("manzili", filtermijozlarvarogilist.get(position).getManzili());
                                intent.putExtra("oyi", filtermijozlarvarogilist.get(position).getOyi());
                                intent.putExtra("izoh", filtermijozlarvarogilist.get(position).getIzoh());
                                context.startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(context, TahrirlashOyna.class);
                                intent1.putExtra("position", position);
                                context.startActivity(intent1);
                                break;
                        }


                    }
                });


                builder.create().show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return filtermijozlarvarogilist.size();
    }

    public static final class RecylerviewHolder extends RecyclerView.ViewHolder {
        TextView id, familyaismsharif;
        TextView kechikkansumma, manzili;

        public RecylerviewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            familyaismsharif = itemView.findViewById(R.id.familyaismsharif);
            kechikkansumma = itemView.findViewById(R.id.kechikkansummasi);
            manzili = itemView.findViewById(R.id.manzili);

        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    filtermijozlarvarogilist = mijozlarList;
                } else {

                    List<Mijozlar> lstFiltered = new ArrayList<>();
                    for (Mijozlar row : mijozlarList) {
                        if (row.getFamilyaIsmSharif().toLowerCase().contains(key.toLowerCase())) {
                            lstFiltered.add(row);

                        }

                    }
                    filtermijozlarvarogilist = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtermijozlarvarogilist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtermijozlarvarogilist = (List<Mijozlar>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public Filter getFilter2() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    filtermijozlarvarogilist = mijozlarList;
                } else {

                    List<Mijozlar> lstFiltered = new ArrayList<>();
                    for (Mijozlar row : mijozlarList) {
                        if (row.getManzili().toLowerCase().contains(key.toLowerCase())) {
                            lstFiltered.add(row);
                        }
                    }
                    filtermijozlarvarogilist = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtermijozlarvarogilist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtermijozlarvarogilist = (List<Mijozlar>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public Filter getFilter3() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    filtermijozlarvarogilist = mijozlarList;
                } else {

                    List<Mijozlar> lstFiltered = new ArrayList<>();
                    for (Mijozlar row : mijozlarList) {
                        if (row.getManzili().toLowerCase().contains(key.toLowerCase())) {
                            lstFiltered.add(row);

                        }

                    }
                    filtermijozlarvarogilist = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtermijozlarvarogilist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtermijozlarvarogilist = (List<Mijozlar>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public Filter getFilter4() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String key = charSequence.toString();
                if (key.isEmpty()) {
                    filtermijozlarvarogilist = mijozlarList;
                } else {

                    List<Mijozlar> lstFiltered = new ArrayList<>();
                    for (Mijozlar row : mijozlarList) {
                        if (row.getRegioni().toLowerCase().contains(key.toLowerCase()) || row.getManzili().toLowerCase().contains(key.toLowerCase())) {

                            lstFiltered.add(row);

                        }

                    }
                    filtermijozlarvarogilist = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtermijozlarvarogilist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtermijozlarvarogilist = (List<Mijozlar>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
