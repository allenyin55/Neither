package com.allen.neither;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class TopicResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_result);

        Intent intent = getIntent();

        String result = intent.getStringExtra("result");

        TextView t = (TextView)findViewById(R.id.showResult);
        t.setText("Your choice is "+result);

        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Results
        db.addResults(new ResultDataClass(result));

        /** Caution!! This is for DELETING all the data!!
         *db.deleteResult();
         *  */

        // get all results
        List<ResultDataClass> list = db.getAllResults();

        int yes=0;
        int no=0;
        int nei=0;

        for (int i = 0; i < list.size(); i++){

            yes += list.get(i).getYesResult();
            no += list.get(i).getNoResult();
            nei += list.get(i).getNeiResult();

        }

        PieChart pieChart = (PieChart) findViewById(R.id.topicResultChart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(yes, 0));
        entries.add(new Entry(no, 1));
        entries.add(new Entry(nei, 2));

        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Yes!");
        labels.add("Nah..");
        labels.add("Neither");

        PieData data = new PieData(labels, dataset);
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS); //
        pieChart.setDescription("Description");
        pieChart.setData(data);

        pieChart.animateY(5000);

        pieChart.saveToGallery("/sd/mychart.jpg", 85); // 85 is the quality of the image
    }
}
