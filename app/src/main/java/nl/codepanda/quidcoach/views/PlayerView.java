package nl.codepanda.quidcoach.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

import nl.codepanda.quidcoach.R;
import nl.codepanda.quidcoach.controllers.PlayersController;

public class PlayerView extends View {
    Paint mPaint;

    public PlayerView(Context context) {
        super(context);

        init(null);
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public PlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mPaint = new Paint();

        mPaint.setStrokeWidth(15f);

        if (set != null) {
            TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.PlayerView);

            switch (ta.getString(R.styleable.PlayerView_player_position)) {
                case "Chaser": {
                    mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorChaser, null));
                    break;
                }

                case "Keeper": {
                    mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorKeeper, null));
                    break;
                }

                case "Beater": {
                    mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorBeater, null));
                    break;
                }

                case "Seeker": {
                    mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorSeeker, null));
                    break;
                }

                default: {
                    mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorChaser, null));
                    break;
                }
            }

            ta.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = getMeasuredWidth();
        float height = getMeasuredHeight();

        canvas.drawLine(0, 0, width, height, mPaint);
        canvas.drawLine(width, 0, 0, height, mPaint);
    }

    public void setPosition(String position) {
        switch (position) {
            case "Chaser": {
                mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorChaser, null));
                break;
            }

            case "Keeper": {
                mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorKeeper, null));
                break;
            }

            case "Beater": {
                mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorBeater, null));
                break;
            }

            case "Seeker": {
                mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorSeeker, null));
                break;
            }

            default: {
                mPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.colorChaser, null));
                break;
            }
        }

        postInvalidate();
    }
}
