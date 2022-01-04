package com.example.waiterapp.activity.homepage;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.waiterapp.R;
import com.example.waiterapp.activity.addordering.AddOrderingActivity;
import com.example.waiterapp.activity.customer.CustomerActivity;
import com.example.waiterapp.activity.grouping.GroupingActivity;
import com.example.waiterapp.activity.product.ProductActivity;
import com.example.waiterapp.activity.submittedorder.SubmittedOrderActivity;
import com.example.waiterapp.database.DatabaseHelper;
import com.example.waiterapp.database.dao.CustomerDao;
import com.example.waiterapp.database.dao.GroupingDao;
import com.example.waiterapp.database.dao.ProductDao;
import com.example.waiterapp.database.dao.SubmitOrderDao;
import com.example.waiterapp.helper.App;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private CardView cardViewproduct,cardViewcustomer , cardViewgrouping , cardViewSubmittedOrdering;
    private ImageView add_order;
    private LinearLayout copy , share , upload, download , delete;
    private BarChart graph;
    private TextView num_product , num_customer , num_ordering , num_category ;
    private DatabaseHelper databaseHelper;
    private ProductDao productDao;
    private CustomerDao customerDao;
    private GroupingDao groupingDao;
    private SubmitOrderDao submitOrderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initDataBase();
        init();
        graph();
        on_click_cards();
        ordering();

    }

    private void initDataBase(){
        databaseHelper = App.getDatabase();
        productDao = databaseHelper.productDao();
        customerDao = databaseHelper.customerDao();
        groupingDao = databaseHelper.groupingDao();
        submitOrderDao = databaseHelper.submitOrderDao();
    }

    void init(){
        cardViewproduct = findViewById(R.id.products);
        cardViewcustomer=findViewById(R.id.customer);
        cardViewgrouping=findViewById(R.id.grouping);
        cardViewSubmittedOrdering = findViewById(R.id.submitted_orders);
        add_order = findViewById(R.id.add_order_ic);
        graph = (BarChart) findViewById(R.id.graf);
        num_product = findViewById(R.id.number_product);
        num_category = findViewById(R.id.number_category);
        num_ordering = findViewById(R.id.number_orderings);
        num_customer = findViewById(R.id.number_customer);
    }

    private void graph(){
        BarData barData;
        BarDataSet barDataSet;
        ArrayList barEntries;

        barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 0.5f));
        barEntries.add(new BarEntry(2f, 1.8f));
        barEntries.add(new BarEntry(3f, 1));
        barEntries.add(new BarEntry(4f, 3));
        barEntries.add(new BarEntry(5f, 1));
        barEntries.add(new BarEntry(6f, 4));
        barEntries.add(new BarEntry(7f, 3));

        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        graph.setData(barData);
        barDataSet.setColors(MY_COLOES);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        Description description = new Description();
        description.setEnabled(false);
        graph.setDescription(description);
        graph.setFitBars(true);
        graph.animateY(1000);

        XAxis xAxis = graph.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);


    }

    private static final int[] MY_COLOES = {
            rgb("#C1A49A"), rgb("#FF202E39"), rgb("#DDC8BF"), rgb("#404040")
    };


    void on_click_cards(){
        cardViewproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentP = new Intent(HomeActivity.this, ProductActivity.class);
                startActivity(intentP);
                overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
            }
        });
        cardViewcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC= new Intent(HomeActivity.this, CustomerActivity.class);
                startActivity(intentC);
                overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
            }
        });
        cardViewgrouping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentG= new Intent(HomeActivity.this, GroupingActivity.class);
                startActivity(intentG);
                overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
            }
        });

        cardViewSubmittedOrdering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSO = new Intent(HomeActivity.this , SubmittedOrderActivity.class);
                startActivity(intentSO);
                overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out);
            }
        });


    }


    void ordering(){

        add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                showBottomSheetDialog();
                Intent addorder= new Intent(HomeActivity.this, AddOrderingActivity.class);
                startActivity(addorder);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        num_product.setText(Integer.toString(productDao.getProductList().size()));
        num_customer.setText(Integer.toString(customerDao.getCustomerList().size()));
        num_category.setText(Integer.toString(groupingDao.getGroupingList().size()));
        num_ordering.setText(Integer.toString(submitOrderDao.getOrderList().size()));
    }
}