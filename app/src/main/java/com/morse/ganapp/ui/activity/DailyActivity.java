package com.morse.ganapp.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.morse.ganapp.R;
import com.morse.ganapp.dagger.componet.DaggerDailyComponet;
import com.morse.ganapp.dagger.module.DailyModule;
import com.morse.ganapp.presenter.DailyPresenter;
import com.morse.ganapp.ui.interfaces.IDailyView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * auther：Morse
 * time：2016/9/5 16:45
 * Descripte：
 */
public class DailyActivity extends BaseActivity implements IDailyView {

    @BindView(R.id.daliy_backdrop)
    ImageView mDaliyBackdrop;
    @BindView(R.id.daliy_toolbar)
    Toolbar mDaliyToolbar;
    @BindView(R.id.daliy_collapsing)
    CollapsingToolbarLayout mDaliyCollapsing;
    @BindView(R.id.daliy_appbar)
    AppBarLayout mDaliyAppbar;
    @BindView(R.id.tv_net_error)
    TextView mTvNetError;
    @BindView(R.id.rl_net_error)
    RelativeLayout mRlNetError;
    @BindView(R.id.daily_text)
    TextView mDailyText;

    @Inject
    DailyPresenter presenter;
    private String[] dates;

    @Override
    protected void setLayout() {
        setContentView(R.layout.activity_daily);
    }

    @Override
    protected void beforeView() {
        super.beforeView();
        dates = getIntent().getStringArrayExtra("date");
    }

    @Override
    protected void afterView() {
        DaggerDailyComponet.builder().dailyModule(new DailyModule(this)).build().inject(this);
        presenter.getDaily(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
    }

    @Override
    public void onSuccess(String html) {
        mDailyText.setText(Html.fromHtml(html));
    }

    @Override
    public void onFailure() {
        mDailyText.setText("加载数据失败");
    }
}
