package dinson.customview.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.CoordinatorLayout.Behavior;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import dinson.customview.utils.LogUtils;
import dinson.customview.weight.CircleImageView;

/**
 * 首页监听recycleview的滑动
 */
public class _003Behavior extends Behavior<CircleImageView> {


    private float mOriginHeight;
    private float mAvatorHeight;


    public _003Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 需要监听哪些控件或者容器的状态
     *
     * @param parent     父容器
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    /**
     * 当监听的view发生改变的时候回调
     * 可以在此方法里面做一些相应的联动效果
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        init(parent, child, dependency);
        LogUtils.e("-------------");
        if (child.getY() <= 0) {
            if (percent != 0) {
                ViewCompat.setScaleX(child, 0);
                ViewCompat.setScaleY(child, 0);
                child.setElevation(0);
                percent = 0;
                LogUtils.e("头像隐藏");
            }
            return false;
        } else if (child.getY() >= mAvatorHeight) {
            return false;
        }
        float percent = child.getY() / mOriginHeight;
        if (this.percent == percent) return false;
        this.percent = percent;
        ViewCompat.setScaleX(child, percent);
        ViewCompat.setScaleY(child, percent);
        LogUtils.e("percent=" + percent + " OriginHeight=" + mOriginHeight + " currentHeight=" + child.getY());
        return false;

    }

    private void init(CoordinatorLayout parent, CircleImageView child, View dependency) {
        if (mOriginHeight == 0) mOriginHeight = dependency.getY();
        if (mAvatorHeight == 0) mAvatorHeight = child.getY();
    }

    float percent = 0;
}
