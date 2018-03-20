package test.net.atshq.imagetotextoct;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.tvText);

    }

    public void readImageClick(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.image1);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!textRecognizer.isOperational()){
            text.setText("no text found");
        }else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);

            StringBuilder sb = new StringBuilder();

            for(int i=0;i<items.size();i++){
                TextBlock bloc = items.valueAt(i);
                sb.append(bloc.getValue());
                sb.append("\n");
            }
            text.setText(sb.toString());
        }
    }
}
