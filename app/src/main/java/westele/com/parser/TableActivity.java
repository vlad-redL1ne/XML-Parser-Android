package westele.com.parser;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;

import java.util.Arrays;
import java.util.List;

public class TableActivity extends Activity implements View.OnClickListener {

    List<String> mLinks = Arrays.asList("http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com", "http://www.google.com",
            "http://www.yahoo.com");

    List<String> mNames = Arrays.asList("google", "yahoo", "google", "yahoo",
            "google", "yahoo", "google", "yahoo", "google", "yahoo", "google",
            "yahoo", "google", "yahoo", "google", "yahoo", "google", "yahoo",
            "google", "yahoo", "google", "yahoo", "google", "yahoo", "google",
            "yahoo", "google", "yahoo", "google", "yahoo", "google", "yahoo",
            "google", "yahoo", "google", "yahoo", "google", "yahoo", "google",
            "yahoo");

    static final int ITEMS_PER_ROW = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TableLayout layout = new TableLayout(this);
        layout.setPadding(15, 15, 15, 15);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);

        layout.setLayoutParams(lp);
        layout.setStretchAllColumns(true);

        int index = 0;

        while (index < mNames.size()) {

            TableRow tr = new TableRow(this);

            for (int k = 0; k < ITEMS_PER_ROW ; k++) {
                if (index < mNames.size()) {
                    Button btn = new Button(this);
                    btn.setText(mNames.get(index));
                    btn.setTag(mLinks.get(index));
                    btn.setOnClickListener(this);
                    LayoutParams params = new LayoutParams(
                            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                    params.setMargins(15, 15, 15, 15);
                    btn.setLayoutParams(params);
                    tr.addView(btn);
                    index++;
                }
            }

            layout.addView(tr);

        }

        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        super.setContentView(scroll);
    }

    @Override
    public void onClick(View v) {
        String url = (String) v.getTag();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
