package uz.agesoft.universalkredit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<Mijozlar> arrayListMijozlar = new ArrayList();
    Context context;
    public OnItemClickListener onItemClickListener;
    List<Mijozlar> sortMijozlar = new ArrayList();



    interface OnItemClickListener {
        void onItemClick(View view, Mijozlar mijozlar, int i);
    }

    public MyAdapter(Context context2, List<Mijozlar> list,OnItemClickListener onItemClickListener) {
        this.context = context2;
        this.onItemClickListener = onItemClickListener;
    }

    public void setArrayListMijozlar(List<Mijozlar> arrayListMijozlar2) {
        arrayListMijozlar = arrayListMijozlar2;
        sortMijozlar.addAll(arrayListMijozlar2);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.id.setText(sortMijozlar.get(position).getId());
        holder.familyaismsharif.setText(sortMijozlar.get(position).getFamilyaIsmSharif());
 
        holder.bind(sortMijozlar.get(position));
    }

    public int getItemCount() {
        return sortMijozlar.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,familyaismsharif;
        
        public ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id);
            familyaismsharif = itemView.findViewById(R.id.familyaismsharif);
        }
        public void bind(Mijozlar mijozlar) {
        }
    }

    public void filter(String charText) {
        String charText2 = charText.toLowerCase();
        this.sortMijozlar.clear();
        if (charText2.isEmpty()) {
            sortMijozlar.addAll(arrayListMijozlar);
        } else {
            for (Mijozlar mijozlar : arrayListMijozlar) {
                if (mijozlar.getFamilyaIsmSharif().toLowerCase().contains(charText2)) {
                    sortMijozlar.add(mijozlar);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filterr(String charTextt) {
        String charTextt2 = charTextt.toLowerCase();
        this.sortMijozlar.clear();
        if (charTextt2.isEmpty()) {
            this.sortMijozlar.addAll(this.arrayListMijozlar);
        } else {
            for (Mijozlar mijozlar : this.arrayListMijozlar) {
                if (mijozlar.getManzili().toLowerCase(Locale.getDefault()).contains(charTextt2)) {
                    this.sortMijozlar.add(mijozlar);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }
}



