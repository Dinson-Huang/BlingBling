package dinson.customview.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.KeyEvent
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import dinson.customview.R
import dinson.customview._global.BaseActivity
import dinson.customview.adapter._002ZhihuListAdapter
import dinson.customview.adapter._003PicSetListAdapter
import dinson.customview.api.GankApi
import dinson.customview.db.model.ZhihuTucao
import dinson.customview.http.HttpHelper
import dinson.customview.http.RxSchedulers
import dinson.customview.kotlin.logd
import dinson.customview.kotlin.loge
import dinson.customview.kotlin.toast
import dinson.customview.manager.GlideSimpleLoader
import dinson.customview.utils.AESUtils
import dinson.customview.utils.StringUtils
import dinson.customview.utils.SystemBarModeUtils
import dinson.customview.weight._003weight.DecorationLayout
import dinson.customview.weight.imagewatcher.ImageWatcherHelper
import dinson.customview.weight.recycleview.OnRvItemClickListener
import dinson.customview.weight.recycleview.RvItemClickSupport
import dinson.customview.weight.refreshview.CustomRefreshView
import kotlinx.android.synthetic.main.activity__003_pic_set_list.*
import java.util.regex.Pattern


class _003PicSetListActivity : BaseActivity() {

    private var mData = ArrayList<Uri>()
    private lateinit var mAdapter: _003PicSetListAdapter
    private lateinit var mApi: GankApi
    private var mId: String = ""

    companion object {
        private const val EXTRA_ID = "id"
        private const val EXTRA_TITLE = "title"
        fun start(context: Context, id: String, title: String) {
            val intent = Intent(context, _003PicSetListActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            intent.putExtra(EXTRA_TITLE, title)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__003_pic_set_list)

        mId = intent?.extras?.getString(EXTRA_ID) ?: ""
        mApi = HttpHelper.create(GankApi::class.java)
        initUI()

    }

    private fun initUI() {
        SystemBarModeUtils.setPaddingTop(this, toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        val title: CharSequence = intent?.extras?.getString(EXTRA_TITLE) ?: ""
        supportActionBar!!.title = title
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        mAdapter = _003PicSetListAdapter(mData)

        flCustomRefreshView.setAdapter(mAdapter)
        flCustomRefreshView.recyclerView.layoutManager = GridLayoutManager(this, 2)
        flCustomRefreshView.setOnLoadListener(object : CustomRefreshView.OnLoadListener {

            override fun onRefresh() {
                getServiceData()
            }

            override fun onLoadMore() {
            }
        })
        flCustomRefreshView.isRefreshing = true
        flCustomRefreshView.setEmptyView("")
        flCustomRefreshView.loadMoreEnable = false
        RvItemClickSupport.addTo(flCustomRefreshView.recyclerView)
            .setOnItemClickListener(OnRvItemClickListener { _, _, pos ->
                decorationLayout.attachImageWatcher(mPicHelper)
                mPicHelper.show(mData, pos)
            })
    }

    /**
     * 获取服务器数据
     */
    private fun getServiceData() {
        if (StringUtils.isEmpty(mId)) {
            "ID is none. Please go back.".toast()
        }
        mApi.loadPicSetById(mId)
            .compose(RxSchedulers.io_main())
            .map {
                AESUtils.decodeCBCSync(it.content, "M7z8I9t0U", "0809040409090708")
            }
            .subscribe({ data ->
                mData.clear()
                val pattern = "http(.*?)(jpg|jpeg|png)"
                val matcher = Pattern.compile(pattern).matcher(data)
                while (matcher.find()) {
                    mData.add(Uri.parse(matcher.group()))
                }
                mAdapter.notifyDataSetChanged()
            }, {
                it.printStackTrace()
                flCustomRefreshView.complete()
            }, {
                flCustomRefreshView.complete()
            }).addToManaged()
    }

    private val decorationLayout by lazy {   DecorationLayout(this)}

    private val mPicHelper by lazy {
        ImageWatcherHelper.with(this, GlideSimpleLoader())
            .setOtherView(decorationLayout)
            .addOnPageChangeListener(decorationLayout)
    }


    override fun onBackPressed() {
        if (!mPicHelper.handleBackPressed()) {
            super.onBackPressed()
        }
    }
}
