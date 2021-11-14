package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ComplexColorCompat;

import java.util.List;

public class WordCustomAdapter extends ArrayAdapter<EnglishToBangla> {
    int mColorId;
    public WordCustomAdapter(Context context, int resource, List<EnglishToBangla> objects, int colorId) {
        super(context, resource, objects);
        mColorId=colorId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View listItemView = convertView; //listItemView points the linear layout on the list_item_layout

        if(listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        EnglishToBangla currentItem = getItem(position);

        TextView banglaTextView = listItemView.findViewById(R.id.banglaTextView);
        banglaTextView.setText(currentItem.getBanglaWord());

        TextView englishTextView = listItemView.findViewById(R.id.englishTextView);
        englishTextView.setText(currentItem.getEngWord());

        ImageView exampleImageView = listItemView.findViewById(R.id.exampleImage);
        if(currentItem.hasImage())
        {
            exampleImageView.setImageResource(currentItem.getImageResourceId());
            exampleImageView.setVisibility(View.VISIBLE);
        }
        else exampleImageView.setVisibility(View.GONE) ;

        View duolinearlayout = listItemView.findViewById(R.id.duoLinearLayout);
        int color= ContextCompat.getColor(getContext(),mColorId);
        duolinearlayout.setBackgroundColor(color);


        return listItemView;

    }
}
