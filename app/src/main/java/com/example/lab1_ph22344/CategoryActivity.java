package com.example.lab1_ph22344;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab1_ph22344.Adapter.CatAdapter;
import com.example.lab1_ph22344.DAO.CatDAO;
import com.example.lab1_ph22344.DTO.CatDTO;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    CatDAO catDAO;
    ArrayList<CatDTO> listCat;
    ListView lvCat;
    CatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listCat = catDAO.getList();
        adapter = new CatAdapter(this, listCat);
        lvCat.setAdapter(adapter);

        if (listCat.isEmpty()) {
            Toast.makeText(this, "Không có thể loại nào!", Toast.LENGTH_SHORT).show();
        }
    }
}