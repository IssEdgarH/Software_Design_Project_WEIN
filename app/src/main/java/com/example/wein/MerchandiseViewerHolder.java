package com.example.wein;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MerchandiseViewerHolder extends RecyclerView.ViewHolder {

    Button mDeleteButton;
    TextView mMerchandiseInfo;

    public MerchandiseViewerHolder(@NonNull View itemView) {
        super(itemView);
        mDeleteButton = mDeleteButton.findViewById(R.id.delete_merch_button);
        mMerchandiseInfo = mMerchandiseInfo.findViewById(R.id.merchandise_info);
    }
}
