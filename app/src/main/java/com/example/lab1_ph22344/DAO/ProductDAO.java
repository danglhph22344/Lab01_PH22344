package com.example.lab1_ph22344.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1_ph22344.DTO.CatDTO;
import com.example.lab1_ph22344.DTO.ProductDTO;
import com.example.lab1_ph22344.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class ProductDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public ProductDAO (Context context) {
        dbHelper = new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  int AddRow (ProductDTO objProduct){
        ContentValues v = new ContentValues();
        v.put("name", objProduct.getName());
        int kq = (int) db.insert("tb_product", null, v);
        return kq;
    }

    public ArrayList<ProductDTO> getList(){
        ArrayList<ProductDTO> listProduct = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id, name, price, id_cat FROM tb_product ", null);
        if(c != null && c.getCount()>0){
            c.moveToFirst();
            do {
                int id = c.getInt(0);
                String name = c.getString(1);
                int price = c.getInt(2);
                int id_cat = c.getInt(3);

                ProductDTO objProduct = new ProductDTO();
                objProduct.setId(id);
                objProduct.setName(name);
                objProduct.setPrice(price);
                objProduct.setId_cat(id_cat);

                listProduct.add(objProduct);
            }while (c.moveToNext());

        }else{
            // log: không lấy được dữ liệu
            Log.d("zzzzzz", "ProductDAO::getList: Khong lay duoc du lieu");
        }
        return  listProduct;
    }

    public boolean updateRow(ProductDTO objProduct) {
        ContentValues v = new ContentValues();
        v.put("name", objProduct.getName());
        v.put("price", objProduct.getPrice());
        v.put("id_cat", objProduct.getId_cat());
        String [] dieu_kien = { String.valueOf(objProduct.getId()) };
        long kq = db.update("tb_product", v, "id = ?", dieu_kien);
        return kq > 0;
    }

    // hàm xóa
    public boolean deleteRow (ProductDTO objProduct) {
        String [] dieu_kien = { String.valueOf(objProduct.getId()) };
        long kq = db.delete("tb_product", "id = ?", dieu_kien);
        return kq>0;
    }
}
