package dinson.customview.weight._010parallaxsplash;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dinson.customview.utils.LogUtils;

/**
 * @author Dinson - 2017/10/9
 */
public class ParallaxFragment extends Fragment {
    //此Fragment上所有的需要实现视差动画的视图
    private List<View> parallaxViews = new ArrayList<View>();

    @Override
    public View onCreateView(LayoutInflater original, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        int layoutId = args.getInt("layoutId");
        int index = args.getInt("index");
        LogUtils.d("Parallax onCreateView call: inflater " + index);
        //1.布局加载器将布局加载进来了
        //2.解析创建布局上所有的视图
        //3.自己搞定创建视图的过程
        //4.获取视图相关的自定义属性的值
       // ParallaxLayoutInflater inflater = new ParallaxLayoutInflater(original, getActivity(), this);
        LayoutInflater inflater = original.cloneInContext(original.getContext());
        LayoutInflaterCompat.setFactory(inflater,new ParallaxFactory(inflater,this));
        return inflater.inflate(layoutId, null);
    }

    public List<View> getParallaxViews() {
        return parallaxViews;
    }
}
