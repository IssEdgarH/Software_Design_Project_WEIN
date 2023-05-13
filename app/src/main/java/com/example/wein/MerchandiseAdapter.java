package com.example.wein;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.wein.DB.AppDataBase;
import com.example.wein.DB.WeinDAO;

import java.util.List;

public class MerchandiseAdapter extends RecyclerView.Adapter<MerchandiseViewerHolder> {

    Context mContext;
    List<Merchandise> mAllMerchandise;

    private WeinDAO mWeinDAO;

    public MerchandiseAdapter(Context context, List<Merchandise> allMerchandise) {
        mContext = context;
        mAllMerchandise = allMerchandise;
    }

    @NonNull
    @Override
    public MerchandiseViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MerchandiseViewerHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_merchandise_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MerchandiseViewerHolder holder, int position) {
        holder.mMerchandiseInfo.setText(mAllMerchandise.get(position).getName());
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWeinDAO.delete(mWeinDAO.getMerchandiseById(mAllMerchandise.get(holder.getAbsoluteAdapterPosition()).getMerchandiseId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAllMerchandise.size();
    }
}
