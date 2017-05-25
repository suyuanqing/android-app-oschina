package com.usian.android_app_oschina.model.http.biz.comment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.model.entity.CommentModel;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.ThreadUtils;

/**
 * Created by 苏元庆 on 2017/5/25.
 */

public class SendNewsComment {

    private PopupWindow popupWindow;

    private SendNewsComment(){

    }

    private static SendNewsComment sendComment;
    public static SendNewsComment getInstance(){

        if (sendComment == null){
            synchronized (SendNewsComment.class){
                if (sendComment == null)
                    sendComment = new SendNewsComment();
            }
        }
        return sendComment;
    }

    public void popupView(final Context context, final String catalog, final String id, final String uid) {
        View view = LayoutInflater.from(context).inflate(R.layout.send_pinglun, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        final EditText edi = (EditText) view.findViewById(R.id.send_pinglun_edi);
        ImageView aiteimg = (ImageView) view.findViewById(R.id.send_pinglun_aite);
        final CheckBox checke = (CheckBox) view.findViewById(R.id.send_che);
        final Button sendBtn = (Button) view.findViewById(R.id.send_pinglun_btn);
        sendBtn.setEnabled(false);

        edi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                sendBtn.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!(edi.getText().toString().equals(""))){
                    sendBtn.setEnabled(true);
                }
            }

        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isPostToMyZone = 0;
                if (checke.isChecked()){
                    isPostToMyZone = 1;
                }else{
                    isPostToMyZone = 0;
                }
                String content = edi.getText().toString().trim();
                ILoadComment iLoadComment = new LoadCommentImpl();
                iLoadComment.pubNewsComment(catalog, id, uid, content, isPostToMyZone, new NetworkCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("SendNewsComment", result);
                        XStream xStream = new XStream();
                        xStream.alias("oschina", CommentModel.class);
                        xStream.alias("result", CommentModel.ResultBean.class);
                        final CommentModel o = (CommentModel) xStream.fromXML(result);

                        if (o.getResult().getErrorCode().equals("1")) {
                            ThreadUtils.runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    edi.setText("");
                                    Toast.makeText(context, R.string.pub_comment, Toast.LENGTH_SHORT).show();
                                    popupWindow.dismiss();
                                }
                            });
                        }else{
                            ThreadUtils.runOnUIThread(new Runnable() {
                                @Override
                                public void run() {
                                    edi.setText("");
                                    Toast.makeText(context, o.getResult().getErrorMessage(), Toast.LENGTH_SHORT).show();
                                    popupWindow.dismiss();
                                }
                            });
                        }
                    }

                    @Override
                    public void onError(String errormsg) {

                    }
                });
            }
        });
    }
}
