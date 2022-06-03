package com.drivill.courier.activity.supportActivity;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.drivill.courier.R;

public class SupportBottomSheetDialog extends BottomSheetDialog {
    public SupportBottomSheetDialog(@NonNull Context context, String title, String content) {
        super(context);

        View view = View.inflate(context, R.layout.dialog_support_layout, null);
        this.setContentView(view);
     /*   ((View) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        ((View) view.getParent()).setPadding(30, 0, 30, 0);*/

        TextView titleTxt = view.findViewById(R.id.titleTxt);
        TextView contentTxt = view.findViewById(R.id.contentTxt);
        WebView webView = view.findViewById(R.id.webView);


        if (title != null) {
            titleTxt.setText(title);
        }
        if (content != null) {
            //contentTxt.setText(content);
            webView.loadData(content, "text/html", "UTF-8");
        }
    }
}
