package com.example.sting.swipeviewpager;

import android.animation.ArgbEvaluator;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         models = new ArrayList<>();
         models.add(new Model(R.drawable.brochure,"IMAGEN 1","1"));
         models.add(new Model(R.drawable.sticker,"IMAGEN 2","2"));
         models.add(new Model(R.drawable.poster,"IMAGEN 3","3"));
         models.add(new Model(R.drawable.namecard,"IMAGEN 4","4"));

         adapter = new Adapter(models,this);
         viewPager = findViewById(R.id.viewPager);
         viewPager.setAdapter(adapter);
         viewPager.setPadding(130,0,130,0);
         Integer[] color_temp = {getResources().getColor(R.color.color1),
                                 getResources().getColor(R.color.color2),
                                 getResources().getColor(R.color.color3),
                                 getResources().getColor(R.color.color4)};

         colors = color_temp;

         viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                 if(position < (adapter.getCount()-1) && position<(colors.length-1)){
                     viewPager.setBackgroundColor(
                             (Integer) argbEvaluator.evaluate(
                                     positionOffset,
                                     colors[position],
                                     colors[position+1]
                             )
                     );
                 }
                 else {
                     viewPager.setBackgroundColor(colors[colors.length-1]);
                 }
             }

             @Override
             public void onPageSelected(int i) {

             }

             @Override
             public void onPageScrollStateChanged(int i) {

             }
         });
    }
}
