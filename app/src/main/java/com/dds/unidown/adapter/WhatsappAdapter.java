package com.dds.unidown.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dds.unidown.R;
import com.dds.unidown.databinding.WhatsappItemLayoutBinding;
import com.dds.unidown.model.WhatsappStatusModel;
import com.dds.unidown.Util;

import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WhatsappAdapter extends RecyclerView.Adapter<WhatsappAdapter.ViewHolder> {
    private ArrayList<WhatsappStatusModel> list;
    private Context context;
    private LayoutInflater inflater;
    private String saveFilePath = Util.RootDirectoryhatsapp+"/";

    public WhatsappAdapter(ArrayList<WhatsappStatusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public WhatsappAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new ViewHolder(DataBindingUtil.inflate(inflater,
                R.layout.whatsapp_item_layout,parent,false));
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull @NotNull WhatsappAdapter.ViewHolder holder, int position) {


        WhatsappStatusModel item = list.get(position);
        if(item.getUri().toString().endsWith(".mp4")){
            holder.binding.playBtn.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.playBtn.setVisibility(View.GONE);

        }
        Glide.with(context).load(item.getPath()).into(holder.binding.statusImg);

        holder.binding.down.setOnClickListener(v ->{
            Util.createfilefolder();
            final String path = item.getPath();
            final File file = new File(path);
            File destnpath = new File(saveFilePath);

            try {
                FileUtils.copyFileToDirectory(file, destnpath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Toast.makeText(context, "Saved to :"+saveFilePath, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        WhatsappItemLayoutBinding  binding;


        public ViewHolder(WhatsappItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
