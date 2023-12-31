package com.hex.zz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hex.zz.Adapters.ItemAdapter;
import com.hex.zz.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class Mmt_choose_Activity extends AppCompatActivity {


        private RecyclerView recyclerView;
        private ItemAdapter itemAdapter;
        private List<Item> itemList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mmt_choose);

            // 初始化数据列表（itemList）
            itemList = new ArrayList<>();
            // 添加项目到itemList
            itemList.add(new Item(1, getResources().getDrawable(R.drawable.game_fish)));
            itemList.add(new Item(2, getResources().getDrawable(R.drawable.game_fish)));
            itemList.add(new Item(3, getResources().getDrawable(R.drawable.game_fish)));
            itemList.add(new Item(4, getResources().getDrawable(R.drawable.game_fish)));

            recyclerView = findViewById(R.id.mmt_recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            itemAdapter = new ItemAdapter(itemList);
            recyclerView.setAdapter(itemAdapter);
        }

    public List<Item> InitItemList() {
        return itemList;
    }
}