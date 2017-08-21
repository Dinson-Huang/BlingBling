/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dinson.customview.weight.richeditor.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import dinson.customview.R;
import dinson.customview.weight.richeditor.widget.RichEditor;


public class SmileImageExpressionAdapter extends ArrayAdapter<String> {

    public SmileImageExpressionAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //convertView = View.inflate(getContext(), R.layout.smile_image_row_expression, null);
            convertView = new ImageView(getContext());
            convertView.setId(R.id.iv_img);

            int width =  dip2px(getContext(), 38);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, width);
            convertView.setLayoutParams(params);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_img);

        String filename = getItem(position);
        int resId = RichEditor.ParseIconResId(filename);
        imageView.setImageResource(resId);
        return convertView;
    }
    /**
     * dip转为PX
     */
    public static int dip2px(Context context, float dipValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

}
