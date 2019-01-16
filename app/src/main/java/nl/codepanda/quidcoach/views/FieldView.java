package nl.codepanda.quidcoach.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import nl.codepanda.quidcoach.R;
import nl.codepanda.quidcoach.controllers.PlayersController;
import nl.codepanda.quidcoach.models.Player;

public class FieldView extends View {
    private final static float LAT_LEFT_UP = 51.44707298917047f;
    private final static float LON_LEFT_UP = 5.4383440000000345f;
    private final static float LAT_RIGHT_DOWN = 51.44653399903774f;
    private final static float LON_RIGHT_DOWN = 5.438819646341946f;

    private Bitmap mImage;

    private List<Player> players;

    public FieldView(Context context) {
        super(context);

        init(null);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public FieldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        players = PlayersController.getPlayers();

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mImage = getResizedBitmap(mImage, getWidth(), getHeight());

                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {


                        postInvalidate();
                    }
                }, 2000, 500);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float imageLeft = (getWidth() - mImage.getWidth()) / 2;

        canvas.drawBitmap(mImage, imageLeft, 0, null);
    }

    private Bitmap getResizedBitmap(Bitmap bitmap, int width, int height) {
        Matrix matrix = new Matrix();

        RectF src = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF dst = new RectF(0, 0, width, height);

        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void drawPlayers(Context context) {
        for (Player player: players) {
//            drawPlayer(player, context);
        }
    }

//    private void drawPlayer(Player player, Context context) {
//        inflate(context, R.layout.view_player, this);
//    }

    private int mapLatToX(float lat) {
        float dLat = LAT_RIGHT_DOWN - LAT_LEFT_UP;
        float dX = mImage.getWidth();

        float dLatPlayer = lat - LAT_LEFT_UP;

        return Math.round((dX / dLat) * dLatPlayer);
    }

    private int mapLonToY(float lon) {
        float dLon = LON_RIGHT_DOWN - LON_LEFT_UP;
        float dY = mImage.getHeight();

        float dLonPlayer = lon - LON_LEFT_UP;

        return Math.round((dY / dLon) * dLonPlayer);
    }
}
