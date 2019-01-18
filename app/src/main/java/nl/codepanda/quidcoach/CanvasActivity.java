package nl.codepanda.quidcoach;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import nl.codepanda.quidcoach.controllers.PlayersController;
import nl.codepanda.quidcoach.views.FieldView;

public class CanvasActivity extends AppCompatActivity {
    private FieldView fieldView;
    private Context context;
    private Point sizes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        context = this;

        fieldView = (FieldView) findViewById(R.id.fieldView);

        Display display = getWindowManager().getDefaultDisplay();
        sizes = new Point();
        display.getSize(sizes);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fieldView.drawPlayers(context, sizes);
                    }
                });

            }
        }, 0, 2000);
    }
}
