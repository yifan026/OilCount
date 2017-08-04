package com.example.yfc.stockcountapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class
        MainActivity extends AppCompatActivity {

//    private SQLiteDatabase db;
//    private MyDBHelper myDB;

    private DecimalFormat DF = new DecimalFormat("0.00");
    private float totalOil, standardPoint, disconutNum, priceNum;
    private int totalPriceNum, totalDiscountNum;

    private float priceSituationNum, discountSituationNum, resultSituationNum;
    private Button b;
    private EditText discount, price, totalOilNum, standardPointNum;
    private Cursor cursor;

    private TextView totalPrice, totalDiscountPrice, priceSituation, discountSituation, resultSituation, priceSituationCheck, discountSituationCheck, resultSituationCheck;
//    SimpleCursorAdapter adapter = null;

    private ListView lv1;

    private MyAdapter adapter;

    private ArrayList<OilData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_main);

//        myDB = new MyDBHelper(this, MyDBHelper.DATABASE_NAME, null, 1);
        ini();

        b.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

//                db = myDB.getWritableDatabase();
//                db.delete(MyDBHelper.table_name, null, null);

                try {

                    disconutNum = Float.valueOf(discount.getText().toString());

                    priceNum = Float.valueOf(price.getText().toString());

                    totalOil = Float.valueOf(totalOilNum.getText().toString());

                    standardPoint = Float.valueOf(standardPointNum.getText().toString());

                    totalPriceNum = Math.round(totalOil * priceNum);
                    totalDiscountNum = totalPriceNum - (Math.round(disconutNum * totalOil));

                    priceSituationNum = getRightPrice(totalOil, priceNum) - (float) Math.floor(getRightPrice(totalOil, priceNum));
                    discountSituationNum = disconutNum * totalOil - (float) Math.floor(disconutNum * totalOil);
                    resultSituationNum = priceSituationCompare(priceSituationNum) + discountSituationCompare(discountSituationNum);
                    oilCount();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, "請輸入數值", Toast.LENGTH_SHORT).show();

                }


//                String strToast = "新增成功!";//返回訊息

//                ContentValues cv = new ContentValues();

                try {

                    arrayList = new ArrayList<OilData>();


                    for (float allOilNum = 1.01f; allOilNum <= totalOil; allOilNum += 0.01f) {

                        priceSituationNum = getRightPrice(allOilNum, 1) * priceNum - (float) Math.floor((getRightPrice(allOilNum, 1) * priceNum));
                        discountSituationNum = disconutNum * getRightPrice(allOilNum, 1) - (float) Math.floor(disconutNum * getRightPrice(allOilNum, 1));
//                        priceSituationNum = getRightPrice(allOilNum, priceNum) - (float) Math.floor(getRightPrice(allOilNum, priceNum));
//                        discountSituationNum = getRightPrice(allOilNum, disconutNum) - (float) Math.floor(getRightPrice(allOilNum, disconutNum));
                        resultSituationNum = priceSituationCompare(priceSituationNum) + discountSituationCompare(discountSituationNum);

                        if (resultSituationNum > standardPoint) {

/*                            cv.put("OIL", floatFormat(allOilNum));
                            cv.put("PRICE_SITUATION", floatFormat(priceSituationCompare(priceSituationNum)));
                            cv.put("DISCOUNT_SITUATION", floatFormat(discountSituationCompare(discountSituationNum)));
                            cv.put("RESULT_SITUATION", floatFormat(resultSituationNum));*/

                            OilData oilData = new OilData(DF.format(allOilNum), floatFormat(resultSituationNum));

                            arrayList.add(oilData);
/*
                        cv.put("OIL", 1.14f);
                        cv.put("PRICE_SITUATION", priceSituationCompare(0.5f));
                        cv.put("DISCOUNT_SITUATION", discountSituationCompare(0.596f));
                        cv.put("RESULT_SITUATION", floatFormat(priceSituationCompare(0.5f)+discountSituationCompare(0.596f)));
*/

//                            Log.d("OIL", floatFormat(allOilNum) + "," + floatFormat(priceSituationCompare(priceSituationNum))
//                                            + "," + floatFormat(discountSituationCompare(discountSituationNum)) + "," + floatFormat(resultSituationNum)
//                            );

//                        try {

//                            db.insert(MyDBHelper.table_name, "", cv);

                        }
//                            cursor.requery();
//
//                        } catch (Exception e) {
//                            Toast.makeText(MainActivity.this,
//                                    "INSERT失敗原因:" + e.toString(), Toast.LENGTH_LONG)
//                                    .show();
//
//                        }

                    }

                    lv1 = (ListView) findViewById(R.id.mainActivity_list);

                    adapter = new MyAdapter(MainActivity.this, arrayList);

                    lv1.setAdapter(adapter);

                    Toast.makeText(MainActivity.this,
                            "成功獲得油價列表", Toast.LENGTH_LONG)
                            .show();


                    //寫進SQLite


                    //添加成功後返回行號，失敗後返回-1
/*                if (long1 == -1) {


                    Toast.makeText(MainActivity.this,
                            "新增失敗！", Toast.LENGTH_SHORT)
                            .show();
                }else{

                    Toast.makeText(MainActivity.this,
                            "已有"+Long.toString(long1)+"data", Toast.LENGTH_SHORT)
                            .show();

                }*/

//                    show();

                } catch (Exception e) {

                    Toast.makeText(MainActivity.this,
                            "新增失敗原因:" + e.toString(), Toast.LENGTH_LONG)
                            .show();
                }


//                new PreferentialList();

                //新增完成就關閉
                //MainActivity.this.finish();


                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(b.getWindowToken(), 0);


//                    Intent intent = new Intent(MainActivity.this, PreferentialList_Activity.class);

//                    intent.putExtra("standardPoint", standardPoint);

//                    startActivity(intent);


            }
        });


    }


    private void ini(){

        discount = (EditText) findViewById(R.id.discount_editText);
        price = (EditText) findViewById(R.id.price_editText);
        totalOilNum = (EditText) findViewById(R.id.totalOil_editText);
        standardPointNum = (EditText) findViewById(R.id.StandardPoint_editText);
        totalPrice = (TextView) findViewById(R.id.totalPriceResult_textView);
        totalDiscountPrice = (TextView) findViewById(R.id.afterCountResult_textView);
        priceSituation = (TextView) findViewById(R.id.myAdapter_priceSituation_textView);
        discountSituation = (TextView) findViewById(R.id.discountSituation_textView);
        resultSituation = (TextView) findViewById(R.id.myAdapter_resultSituation_textView);
        //situation check
        priceSituationCheck = (TextView) findViewById(R.id.priceSituationCheck_textView);
        discountSituationCheck = (TextView) findViewById(R.id.discountSituationCheck_textView);
        resultSituationCheck = (TextView) findViewById(R.id.resultSituationCheck_textView);
        b = (Button) findViewById(R.id.PreferentialList_button);


    }

    //顯示資料

    @SuppressWarnings("deprecation")
    private void show() {

        ListView lv1;

        MyAdapter adapter;

        ArrayList<OilData> arrayList = new ArrayList<OilData>();


//        db = openOrCreateDatabase(MyDBHelper.DATABASE_NAME,MODE_PRIVATE,null);

        //Cursor cursor = db.rawQuery("select _id, _id||'.'||name sname, phone from mytable",null);

//        TextView oil = (TextView) findViewById(R.id.myAdapter_oilTextView);
//
//        TextView res = (TextView) findViewById(R.id.myAdapter_resultSituation_textView);

//        db = myDB.getReadableDatabase();


        try {

//            cursor = db.rawQuery("select OIL,RESULT_SITUATION from " + MyDBHelper.table_name + " where RESULT_SITUATION > " + standardPoint, null);
//            cursor = db.rawQuery("select OIL,RESULT_SITUATION from " + MyDBHelper.table_name , null);

//        Cursor cursor = db.rawQuery("select * from "+MyDBHelper.table_name, null);


//            StringBuilder resultData = new StringBuilder("RESULT: \n");

            while (cursor.moveToNext()) {

                OilData oilData = new OilData(cursor.getString(0), cursor.getString(1));

                arrayList.add(oilData);

/*                String oilnum = cursor.getString(0);

                String result = cursor.getString(1);

                resultData.append(oilnum).append(",");

                resultData.append(result).append(", ");

                resultData.append("\n");*/

            }

/*            Toast.makeText(MainActivity.this,
                    resultData, Toast.LENGTH_LONG)
                    .show();*/

            try {

                if (cursor != null && cursor.getCount() >= 0) {

//            adapter =  new SimpleCursorAdapter(this,
//                    R.layout.page2_activity,
//                    cursor,
//                    new String[]{"OIL","RESULT_SITUATION"},
//                    new int[]{R.id.myAdapter_oilTextView, R.id.myAdapter_resultSituation_textView});
//

                    lv1 = (ListView) findViewById(R.id.mainActivity_list);

                    adapter = new MyAdapter(this, arrayList);

                    lv1.setAdapter(adapter);

                }


                Toast.makeText(MainActivity.this,
                        "成功獲得油價列表", Toast.LENGTH_LONG)
                        .show();

                cursor.close();

            } catch (Exception e) {

                Toast.makeText(MainActivity.this,
                        "Adapter失敗:" + e.toString(), Toast.LENGTH_LONG)
                        .show();
            }


        } catch (Exception e) {

            Toast.makeText(MainActivity.this,
                    "查詢失敗:" + e.toString(), Toast.LENGTH_LONG)
                    .show();


        }


    }

/*
    //取得備忘資料
    public String[] myNote() {

        Cursor cursor = db.rawQuery("select * from " + MyDBHelper.table_name + " where RESULT_SITUATION >" + standardPoint, null);
        //用陣列存資料
        String[] sNote = new String[cursor.getCount()];

        int rows_num = cursor.getCount();//取得資料表列數
        if (rows_num != 0) {
            cursor.moveToFirst();   //將指標移至第一筆資料
            for (int i = 0; i < rows_num; i++) {
                String strCr = cursor.getString(0);
                sNote[i] = strCr;

                cursor.moveToNext();//將指標移至下一筆資料
            }
        }
        cursor.close(); //關閉Cursor
        //dbHelper.close();//關閉資料庫，釋放記憶體，還需使用時不要關閉


        Toast.makeText(MainActivity.this,
                sNote[1], Toast.LENGTH_SHORT)
                .show();

        return sNote;
    }
*/

    private static float getRightPrice(float origionPrice, float saleOff) {

        BigDecimal bOrigionPrice = new BigDecimal(origionPrice);

        BigDecimal bSaleOff = new BigDecimal(saleOff);

        float rightPrice = bOrigionPrice.multiply(bSaleOff).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();

        return rightPrice;
    }


    private float priceSituationCompare(float f) {


        if (f > 0.499f) {
//check X
            return f - 1;

        } else {
//check V
            return f;
        }

    }

    private float discountSituationCompare(float f) {

        if (f < 0.499f) {
//check X
            return -f;
        } else {

//check V
            return 1 - f;
        }

    }


    private String floatFormat(float f) {

        return String.format("%.3f", f);
    }


/*    public void aboutApp(View view) {
        // 顯示訊息框，指定三個參數
        // Context：通常指定為「this」
        // String或int：設定顯示在訊息框裡面的訊息或文字資源
        // int：設定訊息框停留在畫面的時間
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_LONG).show();
    }*/


    private void oilCount() {

//        try {

         /*   discount = (EditText) findViewById(R.id.discount_editText);
            price = (EditText) findViewById(R.id.price_editText);
            totalOilNum = (EditText) findViewById(R.id.totalOil_editText);
            standardPointNum = (EditText) findViewById(R.id.StandardPoint_editText);
            totalPrice = (TextView) findViewById(R.id.totalPriceResult_textView);
            totalDiscountPrice = (TextView) findViewById(R.id.afterCountResult_textView);
            priceSituation = (TextView) findViewById(R.id.myAdapter_priceSituation_textView);
            discountSituation = (TextView) findViewById(R.id.discountSituation_textView);
            resultSituation = (TextView) findViewById(R.id.myAdapter_resultSituation_textView);
            //situation check
            priceSituationCheck = (TextView) findViewById(R.id.priceSituationCheck_textView);
            discountSituationCheck = (TextView) findViewById(R.id.discountSituationCheck_textView);
            resultSituationCheck = (TextView) findViewById(R.id.resultSituationCheck_textView);*/

        /*    disconutNum = Float.valueOf(discount.getText().toString());

            priceNum = Float.valueOf(price.getText().toString());

            totalOil = Float.valueOf(totalOilNum.getText().toString());

            standardPoint = Float.valueOf(standardPointNum.getText().toString());

            totalPriceNum = (int) (Math.round(totalOil * priceNum));
            totalDiscountNum = totalPriceNum - (int) (Math.round(disconutNum * totalOil));

            priceSituationNum = getRightPrice(totalOil, priceNum) - (float) Math.floor(getRightPrice(totalOil, priceNum));
            discountSituationNum = disconutNum * totalOil - (float) Math.floor(disconutNum * totalOil);
            resultSituationNum = priceSituationCompare(priceSituationNum) + discountSituationCompare(discountSituationNum);*/

//            Log.d("ps", Float.toString(priceSituationNum));
//            Log.d("ds", Float.toString(discountSituationNum));
//            Log.d("rs", Float.toString(resultSituationNum));


        //title price
        totalPrice.setText(Integer.toString(totalPriceNum));
        totalDiscountPrice.setText(Integer.toString(totalDiscountNum));

        //situation
        priceSituation.setText(floatFormat(priceSituationCompare(priceSituationNum)));
        discountSituation.setText(floatFormat(discountSituationCompare(discountSituationNum)));
        resultSituation.setText(floatFormat(resultSituationNum));


        if (resultSituationNum < 0) {
            resultSituationCheck.setText("X");

        } else {
            resultSituationCheck.setText("V");
        }

        if (priceSituationCompare(priceSituationNum) < 0) {
            priceSituationCheck.setText("X");
        } else {
            priceSituationCheck.setText("V");
        }

        if (discountSituationCompare(discountSituationNum) < 0) {
            discountSituationCheck.setText("X");
        } else {
            discountSituationCheck.setText("V");
        }


//        } catch (Exception e) {
//
//            Toast.makeText(this, "請輸入數值", Toast.LENGTH_SHORT).show();
//        }

//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(findViewById(R.id.Consult_button).getWindowToken(), 0);


    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    protected void onDestroy() {
        super.onDestroy();
//        cursor.close();  //我们在onCreate()中没有关闭游标，因为需要和ListView进行数据关联，关闭curosr，会导致List无数据，故在最后释放资源
//        db.close(); //断开和数据库的连接，释放相关资源
    }

}