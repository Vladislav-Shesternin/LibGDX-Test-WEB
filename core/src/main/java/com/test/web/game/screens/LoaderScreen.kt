//package com.test.web.game.screens
//
//import com.uxo.monaxa.game.GDXGame
//import com.uxo.monaxa.game.actors.main.AMainLoader
//import com.uxo.monaxa.game.manager.MusicManager
//import com.uxo.monaxa.game.manager.ParticleEffectManager
//import com.uxo.monaxa.game.manager.SoundManager
//import com.uxo.monaxa.game.manager.SpriteManager
//import com.uxo.monaxa.game.utils.Block
//import com.uxo.monaxa.game.utils.TIME_ANIM_SCREEN
//import com.uxo.monaxa.game.utils.actor.animHide
//import com.uxo.monaxa.game.utils.advanced.AdvancedScreen
//import com.uxo.monaxa.game.utils.advanced.AdvancedStage
//import com.uxo.monaxa.game.utils.region
//import com.uxo.monaxa.game.utils.runGDX
//import com.uxo.monaxa.util.log
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.launch
//
//class LoaderScreen(override val game: GDXGame) : AdvancedScreen() {
//
//    private val progressFlow     = MutableStateFlow(0f)
//    private var isFinishLoading  = false
//    private var isFinishProgress = false
//
//    private val aMainLoader by lazy { AMainLoader(this) }
//
//    override fun show() {
//        loadSplashAssets()
//        setBackBackground(game.assetsLoader.BACKGROUND.region)
//        super.show()
//        loadAssets()
//        collectProgress()
//    }
//
//    override fun render(delta: Float) {
//        super.render(delta)
//        loadingAssets()
//        isFinish()
//    }
//
//    override fun hideScreen(block: Block) {
//        aMainLoader.animHide(TIME_ANIM_SCREEN) {
//            block.invoke()
//        }
//    }
//
//    override fun AdvancedStage.addActorsOnStageUI() {
//        addPanelLoader()
//    }
//
//    // Actors ------------------------------------------------------------------------
//
//    private fun AdvancedStage.addPanelLoader() {
//        addAndFillActor(aMainLoader)
//    }
//
//    // Logic ------------------------------------------------------------------------
//
//    private fun loadSplashAssets() {
//        with(game.spriteManager) {
//            loadableAtlasList = mutableListOf(SpriteManager.EnumAtlas.LOADER.data)
//            loadAtlas()
//            loadableTexturesList = mutableListOf(SpriteManager.EnumTexture.LOADER_BACKGROUND.data)
//            loadTexture()
//        }
//        game.assetManager.finishLoading()
//        game.spriteManager.initAtlasAndTexture()
//    }
//
//    private fun loadAssets() {
//        with(game.spriteManager) {
//            loadableAtlasList = SpriteManager.EnumAtlas.entries.map { it.data }.toMutableList()
//            loadAtlas()
//            loadableTexturesList = SpriteManager.EnumTexture.entries.map { it.data }.toMutableList()
//            loadTexture()
//        }
//        with(game.musicManager) {
//            loadableMusicList = MusicManager.EnumMusic.entries.map { it.data }.toMutableList()
//            load()
//        }
//        with(game.soundManager) {
//            loadableSoundList = SoundManager.EnumSound.entries.map { it.data }.toMutableList()
//            load()
//        }
//        with(game.particleEffectManager) {
//            loadableParticleEffectList = ParticleEffectManager.EnumParticleEffect.entries.map { it.data }.toMutableList()
//            load()
//        }
//    }
//
//    private fun initAssets() {
//        game.spriteManager.initAtlasAndTexture()
//        game.musicManager.init()
//        game.soundManager.init()
//        game.particleEffectManager.init()
//    }
//
//    private fun loadingAssets() {
//        if (isFinishLoading.not()) {
//            if (game.assetManager.update(16)) {
//                isFinishLoading = true
//                initAssets()
//            }
//            progressFlow.value = game.assetManager.progress
//        }
//    }
//
//    private fun collectProgress() {
//        coroutine?.launch {
//            var progress = 0
//            progressFlow.collect { p ->
//                while (progress < (p * 100)) {
//                    progress += 1
//                    runGDX { aMainLoader.aLoader.setPercent(progress) }
//                    if (progress % 50 == 0) log("progress = $progress%")
//                    if (progress == 100) isFinishProgress = true
//
//                    //delay((20..65).shuffled().first().toLong())
//                }
//            }
//        }
//    }
//
//    private fun isFinish() {
//        if (isFinishProgress) {
//            isFinishProgress = false
//
////            game.musicUtil.apply { music = main.apply {
////                isLooping = true
////                coff      = 0.15f
////            } }
//
//            hideScreen {
//                game.navigationManager.navigate(GameScreen::class.java.name)
//            }
//        }
//    }
//
//
//}
