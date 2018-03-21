package dinson.customview.activity

import android.os.Bundle
import android.support.v7.view.menu.MenuBuilder
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import dinson.customview.R
import dinson.customview._global.BaseActivity
import dinson.customview._global.ConstantsUtils
import dinson.customview.adapter._001WanAndroidMainListAdapter
import dinson.customview.api.WanAndroidApi
import dinson.customview.entity.wanandroid.WanAndArticle
import dinson.customview.http.HttpHelper
import dinson.customview.http.RxSchedulers
import dinson.customview.kotlin.error
import dinson.customview.kotlin.then
import dinson.customview.listener._001OnLikeViewClickListener
import dinson.customview.utils.SystemBarModeUtils
import dinson.customview.weight.dialog._001DialogLogin
import dinson.customview.weight.refreshview.CustomRefreshView
import kotlinx.android.synthetic.main.activity__002_zhihu_tucao_list.*

open class _001WanAndroidActivity : BaseActivity(), _001OnLikeViewClickListener {


    companion object {
        /**
         * 判断当前是否登录
         */
        fun isLogin() = HttpHelper.getCookie(ConstantsUtils.WANANDROID_DOMAIN)
            .filter { it.name() == "loginUserPassword" || it.name() == "loginUserName" }.count() == 2
    }

    private lateinit var mWanAndroidApi: WanAndroidApi
    private lateinit var mAdapter: _001WanAndroidMainListAdapter
    private val mData = ArrayList<WanAndArticle>()
    private var mPageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__001_wan_android)

        mWanAndroidApi = HttpHelper.create(WanAndroidApi::class.java)
        initUI()
    }

    private fun initUI() {
        SystemBarModeUtils.setPaddingTop(this, toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mAdapter = _001WanAndroidMainListAdapter(this, mData, this)

        flCustomRefreshView.setAdapter(mAdapter)
        flCustomRefreshView.setOnLoadListener(object : CustomRefreshView.OnLoadListener {
            override fun onRefresh() {
                getServiceData(true)
            }

            override fun onLoadMore() {
                getServiceData(false)
            }
        })
        flCustomRefreshView.isRefreshing = true
        flCustomRefreshView.setEmptyView("")
    }

    /**
     * 获取文章数据
     * @param isRefresh 是否刷新
     */
    private fun getServiceData(isRefresh: Boolean) {
        if (isRefresh) mPageIndex = 0
        mWanAndroidApi.getMainArticleList(mPageIndex).compose(RxSchedulers.io_main())
            .subscribe({
                flCustomRefreshView.complete()
                if (isRefresh) mData.clear()
                val article = it.data.datas
                if (article.isEmpty()) flCustomRefreshView.onNoMore()

                mData.addAll(article)
                mAdapter.notifyDataSetChanged()
                mPageIndex++
            }, {
                flCustomRefreshView.complete()
            })
    }

    /**
     * 收藏的点击事件
     */
    override fun onClick(likeView: CheckBox, dataBean: WanAndArticle) {
        if (!isLogin()) {
            likeView.isChecked = !likeView.isChecked
            _001DialogLogin(this).show()
            return
        }
        val observable = likeView.isChecked then mWanAndroidApi.addCollect(dataBean.id)
            ?: mWanAndroidApi.delCollectFromMainList(dataBean.id)
        observable.compose(RxSchedulers.io_main())
            .subscribe({}, {
                error(it.toString())
            })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu._001_main_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewClick@ when (item?.itemId) {
            R.id.action_like -> {
                val login = isLogin()
                if (!login) {
                    _001DialogLogin(this).show()
                    return@viewClick
                }
                _001WanAndroidLikeActivity.start(this)
            }
            R.id.action_search -> {
                error("search")
            }
            R.id.action_logout -> {

            }
            else -> {
            }
        }
        return true
    }

    /**
     * 显示item中的图片；
     */
    override fun onPrepareOptionsPanel(view: View?, menu: Menu?): Boolean {
        if (menu?.javaClass == MenuBuilder::class.java) {
            try {
                val m = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                m.isAccessible = true
                m.invoke(menu, true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return true
    }
}
