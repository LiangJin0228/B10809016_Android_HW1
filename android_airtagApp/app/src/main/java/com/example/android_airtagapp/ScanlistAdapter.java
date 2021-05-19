package com.example.android_airtagapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ScanlistAdapter extends RecyclerView.Adapter<ScanlistAdapter.ViewHolder> {

    interface OnButtonClickHandler {
        void onButtonClick(String key, BluetoothLE value);
    }

    private HashMap<String, BluetoothLE> bluetoothLEHashMap;
    private OnButtonClickHandler mButtonClickHandler;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Initialize ViewHolder
        private final TextView tv_mac;
        private final TextView tv_signal;
        private final Button btn_detail;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_mac = itemView.findViewById(R.id.tv_mac);
            tv_signal = itemView.findViewById(R.id.tv_rssi);
            btn_detail = itemView.findViewById(R.id.btn_detail);
        }

        @Override
        public void onClick(View v) {
            // getAdapterPosition to call data in Hashmap and pass into mButtonClickHandler
            int wrapperPosition = getAdapterPosition();
            String key = getKeyInHashMap(bluetoothLEHashMap, wrapperPosition);
            BluetoothLE value = getValueInHashMap(bluetoothLEHashMap, wrapperPosition);
            mButtonClickHandler.onButtonClick(key, value);
        }

        public TextView getTv_mac() {
            return tv_mac;
        }

        public TextView getTv_signal() {
            return tv_signal;
        }

        public Button getBtn_detail() {
            return btn_detail;
        }

    }

    public ScanlistAdapter(HashMap<String, BluetoothLE> dataHashMap, OnButtonClickHandler mButtonClickHandler) {
        this.bluetoothLEHashMap = dataHashMap;
        this.mButtonClickHandler = mButtonClickHandler;
    }

    @NonNull
    @NotNull
    @Override
    public ScanlistAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scan_indiv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScanlistAdapter.ViewHolder holder, int position) {
        // When dataset changed or scrolling, onBindViewHolder called
        // It causes many onClickListener created and leads to latency and slow performance

        // Use ViewHolder to implement ONE listener is better than above method
        holder.getBtn_detail().setOnClickListener(holder);

        String key = getKeyInHashMap(bluetoothLEHashMap, position);
        BluetoothLE value = getValueInHashMap(bluetoothLEHashMap, position);

        holder.getTv_mac().setText(key);
        holder.getTv_signal().setText(value.getRssi());

    }

    @Override
    public int getItemCount() {
        // Ensure not to be 0
        return bluetoothLEHashMap.size();
    }

    public String getKeyInHashMap(HashMap<String, BluetoothLE> bluetoothLEHashMap, int position) {
        int index = 0;
        for (Map.Entry<String, BluetoothLE> entry : bluetoothLEHashMap.entrySet()) {
            if (index == position) {
                return entry.getKey();
            }
            index ++;
        }
        return null;
    }

    public BluetoothLE getValueInHashMap(HashMap<String, BluetoothLE> bluetoothLEHashMap, int position) {
        int index = 0;
        for (Map.Entry<String, BluetoothLE> entry : bluetoothLEHashMap.entrySet()) {
            if (index == position) {
                return entry.getValue();
            }
            index ++;
        }
        return null;
    }
}