package dinson.customview.weight._013stepview

import android.content.Context
import android.graphics.*
import android.util.TypedValue
import android.view.View
import dinson.customview.utils.LogUtils


/**
 * StepView的指示器
 */
class VerticalStepViewIndicator(context: Context, pos: Position, state: State,private val mSettings: StepViewSettings) : View(context) {

    //默认最小高度或者宽度
    private val mMinWidthOrHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics).toInt()

    private var mCompletedLineHeight: Float = 0.toFloat()//完成线的高度     definition completed line height
    /**
     * get圆的半径  get circle radius
     *
     * @return
     */
    var circleRadius: Float = 0.toFloat()
        private set//圆的半径  definition circle radius

    private var mCenterX: Float = 0.toFloat()//该View的X轴的中间位置
    private var mLeftY: Float = 0.toFloat()
    private var mRightY: Float = 0.toFloat()

    private var mLinePadding: Float = 0.toFloat()//两条连线之间的间距  definition the spacing between the two circles


    private var mPath: Path? = null
    private var mRect: Rect? = null

    private var mHeight: Int = 0//这个控件的动态高度    this view dynamic height


    private val mCompletedPaint by lazy {
        //完成paint
        Paint().apply {
            isAntiAlias = true
            color = mSettings.indicatorCompletedLineColor
            strokeWidth = 2f
            style = Paint.Style.FILL
        }
    }
    private val mUnCompletedPaint by lazy {
        //未完成Paint  definition mUnCompletedPaint
        Paint().apply {
            isAntiAlias = true
            color = mSettings.indicatorUnCompletedLineColor
            style = Paint.Style.STROKE
            strokeWidth = 2f
            pathEffect = DashPathEffect(floatArrayOf(8f, 8f, 8f, 8f), 1f)
        }
    }


    init {
        init()
    }


    /**
     * init
     */
    private fun init() {
        mPath = Path()

        //已经完成线的宽高 set mCompletedLineHeight
        mCompletedLineHeight = 0.05f * mMinWidthOrHeight
        //圆的半径  set mCircleRadius
        circleRadius = 0.28f * mMinWidthOrHeight
        //线与线之间的间距
        mLinePadding = 0.85f * mMinWidthOrHeight
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        LogUtils.i("VerticalStepViewIndicator onMeasure")
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)

        if (width < mMinWidthOrHeight) width = mMinWidthOrHeight
        if (height < mMinWidthOrHeight) height = mMinWidthOrHeight

        setMeasuredDimension(width, height)
        LogUtils.e(" $width :  $height")
    }

    /*override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.i(TAG_NAME, "onSizeChanged")
        mCenterX = (width / 2).toFloat()
        mLeftY = mCenterX - mCompletedLineHeight / 2
        mRightY = mCenterX + mCompletedLineHeight / 2
    }*/

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        LogUtils.i("VerticalStepViewIndicator onDraw")





/*
        //-----------------------画线-------draw line-----------------------------------------------
        for (i in 0 until mCircleCenterPointPositionList!!.size - 1) {
            //前一个CompletedXPosition
            val preCompletedXPosition = mCircleCenterPointPositionList!![i]
            //后一个CompletedXPosition
            val afterCompletedXPosition = mCircleCenterPointPositionList!![i + 1]

            if (i < mCompletingPosition)
            //判断在完成之前的所有点
            {
                //判断在完成之前的所有点，画完成的线，这里是矩形,很细的矩形，类似线，为了做区分，好看些
                if (mIsReverseDraw) {
                    canvas.drawRect(mLeftY, afterCompletedXPosition + circleRadius - 10, mRightY, preCompletedXPosition - circleRadius + 10, mCompletedPaint!!)
                } else {
                    canvas.drawRect(mLeftY, preCompletedXPosition + circleRadius - 10, mRightY, afterCompletedXPosition - circleRadius + 10, mCompletedPaint!!)
                }
            } else {
                if (mIsReverseDraw) {
                    mPath!!.moveTo(mCenterX, afterCompletedXPosition + circleRadius)
                    mPath!!.lineTo(mCenterX, preCompletedXPosition - circleRadius)
                    canvas.drawPath(mPath!!, mUnCompletedPaint!!)
                } else {
                    mPath!!.moveTo(mCenterX, preCompletedXPosition + circleRadius)
                    mPath!!.lineTo(mCenterX, afterCompletedXPosition - circleRadius)
                    canvas.drawPath(mPath!!, mUnCompletedPaint!!)
                }

            }
        }
        //-----------------------画线-------draw line-----------------------------------------------

        //-----------------------画图标-----draw icon-----------------------------------------------
        for (i in mCircleCenterPointPositionList!!.indices) {
            val currentCompletedXPosition = mCircleCenterPointPositionList!![i]
            mRect = Rect((mCenterX - circleRadius).toInt(), (currentCompletedXPosition - circleRadius).toInt(), (mCenterX + circleRadius).toInt(), (currentCompletedXPosition + circleRadius).toInt())
            if (i < mCompletingPosition) {
                mCompleteIcon!!.bounds = mRect!!
                mCompleteIcon!!.draw(canvas)
            } else if (i == mCompletingPosition && mCircleCenterPointPositionList!!.size != 1) {
                mCompletedPaint!!.color = Color.WHITE
                canvas.drawCircle(mCenterX, currentCompletedXPosition, circleRadius * 1.1f, mCompletedPaint!!)
                mAttentionIcon!!.bounds = mRect!!
                mAttentionIcon!!.draw(canvas)
            } else {
                mDefaultIcon!!.bounds = mRect!!
                mDefaultIcon!!.draw(canvas)
            }
        }
        //-----------------------画图标-----draw icon-----------------------------------------------*/


        mSettings.indicatorCompleteIcon.setBounds(0,0,mSettings.indicatorCompleteIcon.minimumWidth,mSettings.indicatorCompleteIcon.minimumHeight)
        mSettings.indicatorCompleteIcon.draw(canvas)

    }

    enum class Position {
        Start, Middle, End
    }

    enum class State {
        Completed, UnCompleted, Completing
    }
}
