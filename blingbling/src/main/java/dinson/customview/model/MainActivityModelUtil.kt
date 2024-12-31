package dinson.customview.model

import dinson.customview.activity.*
import dinson.customview.entity.ClassBean

/**
 * 首页activity列表数据模型
 */
object MainActivityModelUtil {

    /**
     * 内容的数据集
     */
    val dataList: ArrayList<ClassBean> by lazy {
        arrayListOf(
            ClassBean(Paging3TestActivity::class.java, "WanAndroid",
                "WanAndroid is a BBS created by many passionate youth who love Android.",
                "FvqOjFv6Vt3uW1LHdCzNksREmv3S.webp"),
            ClassBean(_001WanAndroidActivity::class.java, "WanAndroid",
                "WanAndroid is a BBS created by many passionate youth who love Android.",
                "FvqOjFv6Vt3uW1LHdCzNksREmv3S.webp"),
            ClassBean(_002ZhihuTucaoListActivity::class.java, "ZhiHuDaily",
                "Zhihu is a question-and-answer website where questions are created, answered, edited and organized by the community of its users",
                "Fi8e4rUvYUC3tp5UzU6mUQh4SkTK.webp"),
            ClassBean(_003GankActivity::class.java, "JueJin.im",
                "JueJin.im is a community that helps developers grow. They are Hacker News for developers, designer news for designers, and medium for product managers.",
                "2d4bf309b03e8340cf0178b558b15dad.webp"),
            ClassBean(_004BiliBiliListActivity::class.java, "IJKPlayer",
                "Android video player based on FFmpeg n3.3, with MediaCodec, VideoToolbox support.",
                "FnFicLrus5vXqAw5oElFFXL1-zb3.webp"),
            ClassBean(_005QiNiuYunActivity::class.java, "QiNiuYun",
                "QiNiuYun is online storage service provided by the QiNiuYun Co.,Ltd, through the interface to the customers with unlimited online storage space",
                "Fg873GxilLa4Gxx78lloLg96Igvs.webp"),
            ClassBean(_006HMSCoreMainActivity::class.java, "HMS Core SDK",
                "Empowers your app with the full range of text, speech & language, vision, and face & body APIs to provide your users a brand new AI experience.",
                "FvqOjFv6Vt3uW1LHdCzNesREmv3B.webp"),
            ClassBean(_007ExchangeActivity::class.java, "Exchange Rate",
                "Support to exchange currency around the world",
                "FgoM0tniC1eDMUOLrI3-P89zk3yt.webp"),
            ClassBean(_008RichEditorActivity::class.java, "RichEditor",
                "Rich text editor which enables users to insert/delete emoji and text into edit-view.",
                "FkLRL9du9H1_jy2C1yFTmCGswQSZ.webp"),
            ClassBean(_009GoogleVRActivity::class.java, "Google VR",
                "Virtual reality for everyone.",
                "FuAVy3cV-_oNaNbqhidirKgpR_Yd.webp"),
            ClassBean(_010ParallaxActivity::class.java, "Parallax Animation Frame",
                "Virtual reality for everyone.",
                "FtnPm05owdbcJC_7mf53nbOcq2Gy.webp"),
            ClassBean(_011DiagonalLayoutActivity::class.java, "DiagonalLayout",
                "With Diagonal Layout explore new styles and approaches on material design",
                "FoPzP9JbDTqxMlhWCRvxPUo24IRn.webp"),
            ClassBean(_012QQNaviViewActivity::class.java, "QQNaviView",
                "Mimic QQ navigation bar",
                "FghQpL5VATXt6Wtlm575wiDseyvI.webp"),
            ClassBean(_013StepViewActivity::class.java, "StepView",
                "Step by step. Step indicator. Flow indicator",
                "Fm0fTIwMIZyoJZDpAOnARBLN0vCZ.webp"),
            ClassBean(_014HonorClockActivity::class.java, "HonorClock",
                "Step by step. Step indicator. Flow indicator",
                "Fknk1q5piAA00DmqxUUGEaK4OGyh.webp"),
            ClassBean(_015ExplosionFieldActivity::class.java, "ExplosionField",
                "explosive dust effect for views",
                "FlSTZDl1fCGGNNqrauOAm8hEqn7y.webp"),
            ClassBean(_016ParallaxImgViewActivity::class.java, "ParallaxImageView",
                "An imageView can auto scroll with device rotating.",
                "FgwMJZ8Y0KswSkjINvGnOgRR96u3.webp"),
            ClassBean(_017TetrisActivity::class.java, "Tetris",
                "Soviet tile-matching puzzle video game released in June 1984.",
                "FoiBsBwQR4vTQJ6A0FiHLaxcqjIK.webp"),
            ClassBean(_018NFCActivity::class.java, "NFC Tools",
                "Near-field communication (NFC) is a set of communication protocols that enable two electronic devices",
                "FuEdGMh6ReHpM2hTK0_MZ39xTjX1.webp"),
            ClassBean(_019GangedRecycleViewActivity::class.java, "GangedRecycleView",
                "Two RecycleViews Joint sliding …",
                "FnLyI2fmB2ykukTQfKY6uBTKVdjB.webp"),
            ClassBean(_020ShimmerActivity::class.java, "FaceBook-Shimmer",
                "Add a shimmering effect to any view in your app.",
                "FsghEYkztX6HGcQufyUmB9N-DvRI.webp"),
            ClassBean(_021LikeSmileViewActivity::class.java, "LikeSmileView",
                "Imitating ZuiWorld\\'s application of thumb up function",
                "FpoNvqVck6N_pe45kqlb4bB00JRE.jpg"),
            ClassBean(_022GoogleMapActivity::class.java, "GoogleMaps",
                "This\\'s a mapping demo app developed by me for the Android mobile operating systems, it uses Google Maps for its information.",
                "FrvJ7OlyyBDRleOYvBE07TRslRON.webp"),
            ClassBean(_023TheDaVinciCodeActivity::class.java, "TheDaVinciCode",
                "This\\'s a mapping demo app developed by me for the Android mobile operating systems, it uses Google Maps for its information.",
                "926c06b5c76999fa449263d72c1b9922.webp"),
            ClassBean(_024SpotlightActivity::class.java, "Spotlight",
                "Android Library that lights items for tutorials or walk-through etc",
                "FrTu8V2-Wu7nc2C4UIa_9-yp69ls.webp"),
            ClassBean(_025DaysMatterActivity::class.java, "CountDownTimer",
                "Android Library that lights items for tutorials or walk-through etc",
                "FkLRL9du9H1_jy2C1yFTmCdswQSZ.webp"),
            ClassBean(_026FiveChessActivity::class.java, "Five Chess",
                "Android Library that lights items for tutorials or walk-through etc",
                "FrTu8V2-W461c2C4UIa_9-yp69ls.webp"),
            ClassBean(_027MovieActivity::class.java, "AV",
                "Android Library that lights items for tutorials or walk-through etc",
                "FrvJ7OlyyBDRleOBvBE07TRslRON.webp"),
            ClassBean(_028NavigationActivity::class.java, "AV",
                "Android Library that lights items for tutorials or walk-through etc",
                "FrvJ7OlyyBDRleOBvBE07TRslRON.webp"),
            ClassBean(_029FloatingViewActivity::class.java, "FloatingView",
                "FloatingView can make the target view floating above the anchor view with cool animation",
                "FoswnjfkCoEaVY0Ijnnlur9BxYBl.webp"),
            ClassBean(TestActivity::class.java, "TestLayout",
                "An activity for testing.",
                "Fos8aWM3lzyxB621rEmzMhgTUn-p.webp")
        )
    }
}
