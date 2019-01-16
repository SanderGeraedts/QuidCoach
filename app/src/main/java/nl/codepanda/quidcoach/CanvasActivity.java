package nl.codepanda.quidcoach;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import nl.codepanda.quidcoach.controllers.PlayersController;
import nl.codepanda.quidcoach.views.FieldView;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        FieldView fieldView = (FieldView) findViewById(R.id.fieldView);

        fieldView.drawPlayers(this);
    }
}
