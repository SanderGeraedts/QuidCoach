package nl.codepanda.quidcoach.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import nl.codepanda.quidcoach.R;
import nl.codepanda.quidcoach.controllers.PlayersController;
import nl.codepanda.quidcoach.models.Player;

public class FieldView extends RelativeLayout {
    private final static float LAT_LEFT_UP = 51.44707298917047f;
    private final static float LON_LEFT_UP = 5.4383440000000345f;
    private final static float LAT_RIGHT_DOWN = 51.44653399903774f;
    private final static float LON_RIGHT_DOWN = 5.438819646341946f;

    private Bitmap mImage;

    private List<Player> players;
    private List<PlayerView> playerViews;

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

        playerViews = new ArrayList<>();
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

    public void drawPlayers(Context context, Point boundaries) {
        for (PlayerView playerView : playerViews) {
            this.removeView(playerView);
        }

        this.playerViews.clear();

        players = PlayersController.getPlayers();

        for (Player player : players) {
            drawPlayer(player, context, boundaries);
        }
    }

    private void drawPlayer(Player player, Context context, Point boundaries) {
        PlayerView playerView = new PlayerView(context);
        playerView.setPosition(player.getPosition());

        LayoutParams layoutParams = new LayoutParams(50, 50);
        layoutParams.setMargins(mapLatToX(player.getLatitude(), boundaries.x), mapLonToY(player.getLongitude(), boundaries.y), 0, 0);
        this.playerViews.add(playerView);

        this.addView(playerView, layoutParams);
    }

    public int mapLatToX(float lat, int width) {
        float dLat = Math.abs(LAT_RIGHT_DOWN - LAT_LEFT_UP);

        float dLatPlayer = Math.abs(lat - LAT_LEFT_UP);

        return Math.round(((width - 100) / dLat) * dLatPlayer);
    }

    public int mapLonToY(float lon, int height) {
        float dLon = Math.abs(LON_RIGHT_DOWN - LON_LEFT_UP);

        float dLonPlayer = Math.abs(lon - LON_LEFT_UP);

        return Math.round(((height - 100) / dLon) * dLonPlayer);
    }
}
