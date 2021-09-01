package com.dds.unidown.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dds.unidown.R;
import com.dds.unidown.adapter.WhatsappAdapter;
import com.dds.unidown.databinding.FragmentImageBinding;
import com.dds.unidown.model.WhatsappStatusModel;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class imageFragment extends Fragment {
    private FragmentImageBinding binding;
    private ArrayList<WhatsappStatusModel> list;
    private WhatsappAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false);

        list = new ArrayList<>();
        getData();

        binding.refresh.setOnRefreshListener( ()-> {
            list = new ArrayList<>();
            getData();
            binding.refresh.setRefreshing(false);
        });
        return binding.getRoot();
    }

    private void getData() {

        WhatsappStatusModel model;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/WhatsApp/Media/.statuses";
        File targetFileDirec = new File(targetPath);
        File[] allfiles = targetFileDirec.listFiles();


        Arrays.sort(allfiles, ((o1, o2) -> {
            if (o1.lastModified() > o2.lastModified()) return -1;
            else if (o1.lastModified() < o2.lastModified()) return +1;
            else return 0;

        }));
        for (int i = 0; i < allfiles.length; i++) {
            File file = allfiles[i];
            if (Uri.fromFile(file).toString().endsWith(".png") ||
                    Uri.fromFile(file).toString().endsWith(".jpg")) {
                        model = new WhatsappStatusModel("whats",+i,
                                Uri.fromFile(file),
                                allfiles[i].getAbsolutePath(),
                                file.getName());
                        list.add(model);

            }

        }


adapter = new WhatsappAdapter(list,getActivity());
        binding.imagwrecy.setAdapter(adapter);

    }
}