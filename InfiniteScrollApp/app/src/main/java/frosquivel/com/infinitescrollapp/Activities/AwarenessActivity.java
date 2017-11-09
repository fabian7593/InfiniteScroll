package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import frosquivel.com.infinitescrollapp.R;
import su.levenetc.android.textsurface.Text;
import su.levenetc.android.textsurface.TextBuilder;
import su.levenetc.android.textsurface.TextSurface;
import su.levenetc.android.textsurface.animations.ChangeColor;
import su.levenetc.android.textsurface.animations.Circle;
import su.levenetc.android.textsurface.animations.Delay;
import su.levenetc.android.textsurface.animations.Parallel;
import su.levenetc.android.textsurface.animations.Rotate3D;
import su.levenetc.android.textsurface.animations.Sequential;
import su.levenetc.android.textsurface.animations.ShapeReveal;
import su.levenetc.android.textsurface.animations.SideCut;
import su.levenetc.android.textsurface.animations.Slide;
import su.levenetc.android.textsurface.animations.TransSurface;
import su.levenetc.android.textsurface.contants.Align;
import su.levenetc.android.textsurface.contants.Direction;
import su.levenetc.android.textsurface.contants.Pivot;
import su.levenetc.android.textsurface.contants.Side;

/**
 * Created by Fabian on 06/07/2017.
 * A class for show an animation with letters of xenofobia and other topics
 */

public class AwarenessActivity  extends AppCompatActivity {

    private TextSurface textSurface;
    private Activity activity;
    private static final int C_H1 = 50;
    private static final int C_H2 = 45;
    private static final int C_H3 = 40;
    private static final int C_H4 = 35;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness);
        textSurface = (TextSurface) findViewById(R.id.text_surface);
        activity = this;
        textSurface.postDelayed(new Runnable() {
            @Override public void run() {
                play(textSurface, getAssets(), activity);
            }
        }, 1000);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        };

        Timer timer = new Timer("MyTimer");//create a new Timer
        timer.scheduleAtFixedRate(timerTask, 17800, 1);
    }

    public static void play(TextSurface textSurface, AssetManager assetManager, Activity activity) {

        final Typeface robotoBlack = Typeface.createFromAsset(assetManager, "font/NEOTERICBold.ttf");
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTypeface(robotoBlack);

        Text textAwareness01 = TextBuilder
                .create(activity.getString(R.string.awareness_01))
                .setPaint(paint)
                .setSize(C_H2)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.blue_500))
                .setPosition(Align.SURFACE_CENTER).build();

        Text textAwareness02 = TextBuilder
                .create(activity.getString(R.string.awareness_02))
                .setPaint(paint)
                .setSize(C_H2)
                .setAlpha(5)
                .setColor(ContextCompat.getColor(activity, R.color.deep_purple_500))
                .setPosition(Align.BOTTOM_OF, textAwareness01).build();

        Text textAwareness03 = TextBuilder
                .create(activity.getString(R.string.awareness_03))
                .setPaint(paint)
                .setSize(C_H2)
                .setAlpha(2)
                .setColor(ContextCompat.getColor(activity, R.color.indigo_500))
                .setPosition(Align.RIGHT_OF, textAwareness02).build();

        Text textAwareness04 = TextBuilder
                .create(activity.getString(R.string.awareness_04))
                .setPaint(paint)
                .setSize(C_H2)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.blue_500))
                .setPosition(Align.BOTTOM_OF, textAwareness03).build();

        Text textAwareness05 = TextBuilder
                .create(activity.getString(R.string.awareness_05))
                .setPaint(paint)
                .setSize(C_H4)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.purple_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness04).build();

        Text textAwareness06 = TextBuilder
                .create(activity.getString(R.string.awareness_06))
                .setPaint(paint)
                .setSize(C_H1)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.deep_purple_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness05).build();

        Text textAwareness07 = TextBuilder
                .create(activity.getString(R.string.awareness_07))
                .setPaint(paint)
                .setSize(C_H1)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.indigo_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness06).build();

        Text textAwareness08 = TextBuilder
                .create(activity.getString(R.string.awareness_08))
                .setPaint(paint)
                .setSize(C_H1)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.blue_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness07).build();

        Text textAwareness09 = TextBuilder
                .create(activity.getString(R.string.awareness_09))
                .setPaint(paint)
                .setSize(C_H1)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.indigo_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness08).build();

        Text textAwareness10 = TextBuilder
                .create(activity.getString(R.string.awareness_10))
                .setPaint(paint)
                .setSize(C_H1)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.deep_purple_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness09).build();

        Text textAwareness11 = TextBuilder
                .create(activity.getString(R.string.awareness_11))
                .setPaint(paint)
                .setSize(C_H3)
                .setAlpha(0)
                .setColor(ContextCompat.getColor(activity, R.color.purple_500))
                .setPosition(Align.BOTTOM_OF | Align.CENTER_OF, textAwareness10).build();


        textSurface.play(
                new Sequential(
                        ShapeReveal.create(textAwareness01, 250, SideCut.show(Side.LEFT), false),
                        new Parallel(ShapeReveal.create(textAwareness01, 600, SideCut.hide(Side.LEFT), false), new Sequential(Delay.duration(400), ShapeReveal.create(textAwareness01, 600, SideCut.show(Side.LEFT), false))),
                        new Parallel(new TransSurface(500, textAwareness02, Pivot.CENTER), ShapeReveal.create(textAwareness02, 1300, SideCut.show(Side.LEFT), false)),
                        Delay.duration(1000),
                        new Parallel(new TransSurface(750, textAwareness03, Pivot.CENTER), Slide.showFrom(Side.LEFT, textAwareness03, 750), ChangeColor.to(textAwareness03, 750,ContextCompat.getColor(activity, R.color.indigo_500))),
                        Delay.duration(1000),
                        new Parallel(TransSurface.toCenter(textAwareness04, 500), Rotate3D.showFromSide(textAwareness04, 750, Pivot.TOP)),
                        new Parallel(TransSurface.toCenter(textAwareness05, 500), Slide.showFrom(Side.TOP, textAwareness05, 500)),

                        Delay.duration(1200),
                        new Parallel(
                                new TransSurface(1500, textAwareness09, Pivot.CENTER),
                                new Sequential(
                                        Delay.duration(700),
                                        new Sequential(ShapeReveal.create(textAwareness06, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(textAwareness07, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        Delay.duration(700),
                                        new Sequential(ShapeReveal.create(textAwareness08, 500, Circle.show(Side.CENTER, Direction.OUT), false)),
                                        new Sequential(ShapeReveal.create(textAwareness09, 500, Circle.show(Side.CENTER, Direction.OUT), false))
                                )
                        ),
                        Delay.duration(1600),
                        new Parallel(
                                ShapeReveal.create(textAwareness10, 1500, Circle.show(Side.CENTER, Direction.IN), false),
                                new Sequential(Delay.duration(550), ShapeReveal.create(textAwareness11, 1500, Circle.show(Side.CENTER, Direction.IN), false))

                        ),

                        Delay.duration(1000),
                        new Parallel(
                                new Sequential(Delay.duration(300), ShapeReveal.create(textAwareness04, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(500), ShapeReveal.create(textAwareness05, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(700), ShapeReveal.create(textAwareness06, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(900), ShapeReveal.create(textAwareness07, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(1100), ShapeReveal.create(textAwareness08, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(1300), ShapeReveal.create(textAwareness09, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(1500), ShapeReveal.create(textAwareness10, 1500, SideCut.hide(Side.LEFT), true)),
                                new Sequential(Delay.duration(1700), ShapeReveal.create(textAwareness11, 1500, SideCut.hide(Side.LEFT), true))
                        )
                )
        );
    }
}
