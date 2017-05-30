package com.usian.android_app_oschina.controller.activity.mine_activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jiyun.lenovo.roundorcirle.GlideCircleTransform;
import com.thoughtworks.xstream.XStream;
import com.usian.android_app_oschina.App;
import com.usian.android_app_oschina.R;
import com.usian.android_app_oschina.base.BaseActivity;
import com.usian.android_app_oschina.model.entity.UserInfoModel;
import com.usian.android_app_oschina.model.http.biz.minebus.ILoadMine;
import com.usian.android_app_oschina.model.http.biz.minebus.LoadMineImpl;
import com.usian.android_app_oschina.model.http.callback.NetworkCallback;
import com.usian.android_app_oschina.utils.NetUtils;
import com.usian.android_app_oschina.utils.SPUtils;
import com.usian.android_app_oschina.utils.ThreadUtils;

import butterknife.Bind;
import butterknife.OnClick;

public class MineUserInfo extends BaseActivity {

    @Bind(R.id.title_back)
    ImageView titleBack;
    @Bind(R.id.title_icon_name)
    TextView titleIconName;
    @Bind(R.id.iv_info_img)
    ImageView ivInfoImg;
    @Bind(R.id.tv_info_pinglun)
    TextView tvInfoPinglun;
    @Bind(R.id.title_icon_toolbar)
    LinearLayout titleIconToolbar;
    @Bind(R.id.iv_mima_userimg)
    ImageView ivMimaUserimg;
    @Bind(R.id.lin_mine_lin)
    LinearLayout linMineLin;
    @Bind(R.id.tv_mine_username)
    TextView tvMineUsername;
    @Bind(R.id.mine_user_info)
    RelativeLayout mineUserInfo;
    @Bind(R.id.user_jiaru_date)
    TextView userJiaruDate;
    @Bind(R.id.user_from)
    TextView userFrom;
    @Bind(R.id.user_pingtai)
    TextView userPingtai;
    @Bind(R.id.activity_mine_user_info)
    LinearLayout activityMineUserInfo;
    @Bind(R.id.mine_lingyu_wu)
    TextView mineLingyuWu;

    private Object uid;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_user_info;
    }

    @Override
    protected void initView() {
        userPingtai.setText("<无>");
        mineLingyuWu.setText("<无>");
        titleIconName.setText("我的资料");
        ivInfoImg.setVisibility(View.GONE);
        tvInfoPinglun.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        uid = SPUtils.getParam(App.getContext(), "uid", "");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void loadData() {

        ILoadMine iLoadLogin = new LoadMineImpl();
        iLoadLogin.getUserInfo(uid + "", new NetworkCallback() {
            @Override
            public void onSuccess(String result) {
                XStream xStream = new XStream();
                xStream.alias("oschina", UserInfoModel.class);
                xStream.alias("user", UserInfoModel.UserBean.class);
                xStream.alias("notice", UserInfoModel.NoticeBean.class);

                if (!NetUtils.isConnected(App.activity)) {
                    ThreadUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(App.activity, R.string.isNet, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    UserInfoModel o = (UserInfoModel) xStream.fromXML(result);
                    setMimeUserinfo(o);
                }

            }

            @Override
            public void onError(String errormsg) {

            }
        });
    }


    public void setMimeUserinfo(final UserInfoModel o) {
        ThreadUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(App.activity).load(o.getUser().getPortrait())
                        .transform(new GlideCircleTransform(App.activity))
                        .error(R.mipmap.ic_default_image)
                        .into(ivMimaUserimg);
                tvMineUsername.setText(o.getUser().getName());
                userJiaruDate.setText(o.getUser().getJointime());
                userFrom.setText(o.getUser().getFrom());
            }
        });


    }


    @OnClick(R.id.title_back)
    public void onViewClicked() {
        finish();
    }

}
