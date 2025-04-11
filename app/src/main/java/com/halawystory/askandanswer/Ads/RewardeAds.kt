
package com.halawystory.askandanswer.Ads

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.halawystory.askandanswer.MusicPlayer
import com.halawystory.askandanswer.MusicPlayerRoom
import com.halawystory.askandanswer.mvvm.model.ResumeGameWithAds
import com.halawystory.askandanswer.mvvm.model.StateAds
import com.halawystory.askandanswer.mvvm.viewmodel.QuestionsViewModel
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.UnityAdsShowOptions

class RewardeAds(val context: Context, val viewModel: QuestionsViewModel) {
    private var isShowing = false
    private var isLoaded = false

    // Define your Unity Ads Game ID
    private val gameId = "5670151" // Replace with your actual Unity Game ID
    private val adUnitId = "Rewarded_Android" // Replace with your Unity Ads placement ID
    private val testMode = true // Set to false for production

    init {
        // Initialize Unity Ads
        UnityAds.initialize(context, gameId,  object : IUnityAdsInitializationListener {
            override fun onInitializationComplete() {
                Log.d("UnityAds", "Unity Ads initialization complete")
                loadRewardedAd()
            }

            override fun onInitializationFailed(error: UnityAds.UnityAdsInitializationError, message: String) {
                Log.e("UnityAds", "Unity Ads initialization failed: [$error] $message")
            }
        })
    }

    fun loadRewardedAd() {
        if (!isLoaded) {
            Log.d("UnityAds", "Loading rewarded ad")

            UnityAds.load(adUnitId, object : IUnityAdsLoadListener {
                override fun onUnityAdsAdLoaded(placementId: String) {
                    Log.d("UnityAds", "Ad loaded successfully for $placementId")
                    isLoaded = true
                    viewModel.setRewardAdsInit(true)
                    // Instead of using StateAds.LOADED which doesn't exist in your enum
                    // We'll just log the success - your original code doesn't use this state
                    Log.d("UnityAds", "Ad ready to show")
                }

                override fun onUnityAdsFailedToLoad(
                    placementId: String,
                    error: UnityAds.UnityAdsLoadError,
                    message: String
                ) {
                    viewModel.setRewardAdsInit(true)

                    Log.e("UnityAds", "Ad failed to load for $placementId: [$error] $message")
                    isLoaded = false
                    // Not using StateAds.FAILED which doesn't exist in your enum
                }
            })
        }
    }

    fun showRewardedAd() {
        if (isLoaded) {
            val showListener = object : IUnityAdsShowListener {
                override fun onUnityAdsShowFailure(
                    placementId: String,
                    error: UnityAds.UnityAdsShowError,
                    message: String
                ) {
                    Log.e("UnityAds", "Ad show failed for $placementId: [$error] $message")
                    isShowing = false
                    isLoaded = false
                    loadRewardedAd() // Try to load a new ad
                }

                override fun onUnityAdsShowStart(placementId: String) {
                    MusicPlayer.pauseMusic()
                    MusicPlayerRoom.pauseMusic()
                    Log.d("UnityAds", "Ad show started for $placementId")
                    isShowing = true

                    if (viewModel.getStateAdsWhenUserLoseGame() == ResumeGameWithAds.UserClickAds) {
                        viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.UserClickAndShow)
                    }else{
                        viewModel.setStateAds(StateAds.SHOWADS)
                    }

                }

                override fun onUnityAdsShowClick(placementId: String) {
                    Log.d("UnityAds", "Ad show clicked for $placementId")
                }

                override fun onUnityAdsShowComplete(
                    placementId: String,
                    state: UnityAds.UnityAdsShowCompletionState
                ) {
                    Log.d("UnityAds", "Ad show completed for $placementId: $state")

                    // Check if the user should be rewarded
                    if (state == UnityAds.UnityAdsShowCompletionState.COMPLETED) {
                        // User watched the entire ad, give reward
                        // This is where you would handle the reward logic
                        Log.d("UnityAdsFFFFFFFFFFFFFFFFFFFFFFFF", "User should receive reward")

                        if (viewModel.getStateAdsWhenUserLoseGame() == ResumeGameWithAds.UserClickAndShow) {
                            viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.UserClickAndEndShowAds)
                        }else{
                            viewModel.setStateAds(StateAds.ENDSHOWADS)

                        }

                        isShowing = false
                        isLoaded = false
                        loadRewardedAd() // Load the next ad
                    }


                }
            }

            try {
                UnityAds.show(context as Activity, adUnitId, UnityAdsShowOptions(), showListener)
            } catch (e: Exception) {
                Log.e("UnityAds", "Exception during show: ${e.message}")
                Toast.makeText(context, "Error showing ad", Toast.LENGTH_SHORT).show()
                isLoaded = false
                loadRewardedAd() // Try to load a new ad
            }
        } else {
            Toast.makeText(context, "The rewarded ad wasn't ready yet", Toast.LENGTH_SHORT).show()
            loadRewardedAd() // Try to load a new ad
        }
    }
}













//package com.halawystory.ai.Ads
//
//import android.app.Activity
//import android.content.Context
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.lifecycle.LifecycleOwner
//import com.google.android.gms.ads.AdError
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.FullScreenContentCallback
//import com.google.android.gms.ads.LoadAdError
//import com.google.android.gms.ads.rewarded.RewardedAd
//import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
//import com.halawystory.ai.mvvm.model.ResumeGameWithAds
//import com.halawystory.ai.mvvm.model.StateAds
//import com.halawystory.ai.mvvm.viewmodel.QuestionsViewModel
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//
//class RewardeAds(val context: Context ,val viewModel: QuestionsViewModel) {
//    private var isShowing = false
//
//    private var rewardedAd: RewardedAd? = null
//
//    init {
//        // مراقبة الإعلان في ViewModel والتحديث عند التغيير
//        viewModel.isRewardedLoaded.observe(context as LifecycleOwner) { ad ->
//            rewardedAd = ad
//        }
//
//    }
//
//
//     fun loadRewardedAd() {
//
//
//                     if (rewardedAd==null) {
//
//                         val adRequest = AdRequest.Builder().build()
////                         Toast.makeText(context, "بدأ تحميل الاعلان", Toast.LENGTH_SHORT).show()
//
//                         RewardedAd.load(
//                             context,
//                             "ca-app-pub-8881659633429766/4030725837", // Test ad unit ID
//                             adRequest,
//                             object : RewardedAdLoadCallback() {
//                                 override fun onAdFailedToLoad(adError: LoadAdError) {
//                                     // Ad failed to load
//                                     viewModel.setRewardAdsInit(null)
//                                     Log.e("AdMob", "⚠️ فشل عرض الإعلان: ${adError.message}")
//
//
//                                 }
//
//                                 override fun onAdLoaded(ad: RewardedAd) {
//                                     Log.e("AdMob", "Loaded")
//
//                                     // Ad loaded successfully
//                                     viewModel.setRewardAdsInit(ad)
//                                     setupAdCallbacks() // ضبط الأحداث الخاصة بالإعلان
////                                     Toast.makeText(context, "اعلان تم تحميله", Toast.LENGTH_SHORT).show()
//                                 }
//                             }
//                     )
//                 }
////                     else
////                         Toast.makeText(context, "اعلان تم تحميله", Toast.LENGTH_SHORT).show()
//
//
//    }
//
//
//
//     fun showRewardedAd() {
//        val currentRewardedAd = rewardedAd
//        if (currentRewardedAd != null) {
//            currentRewardedAd.show(context as Activity) { rewardItem ->
//
//                //    get Gift Coin
//
//
//
//            }
//        } else {
//            Toast.makeText(context, "The rewarded ad wasn't ready yet", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//
//    // إعداد ردود الفعل عند عرض أو إغلاق الإعلان
//    private fun setupAdCallbacks() {
//        rewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//            override fun onAdShowedFullScreenContent() {
//                Log.d("AdMob", "🎬 الإعلان بدأ العرض الآن")
//
//                if (viewModel.getStateAdsWhenUserLoseGame()==ResumeGameWithAds.UserClickAds){
//
//                    viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.UserClickAndShow)
//
//                }
//
//
//                viewModel.setStateAds(StateAds.SHOWADS)
//                isShowing = true
////                Toast.makeText(context, "الاعلان ظاهر الان", Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onAdDismissedFullScreenContent() {
//                Log.d("AdMob", "✅ تم إغلاق الإعلان")
//
//                if (viewModel.getStateAdsWhenUserLoseGame()==ResumeGameWithAds.UserClickAndShow){
//                    viewModel.setStateAdsWhenUserLoseGame(ResumeGameWithAds.UserClickAndEndShowAds)
//
//                }
//
//                viewModel.setStateAds(StateAds.ENDSHOWADS)
//
////                Toast.makeText(context, "انتهي عرض الاعلان", Toast.LENGTH_SHORT).show()
//                isShowing = false
//                viewModel.setRewardAdsInit(null)
//                loadRewardedAd()  // إعادة تحميل إعلان جديد
//            }
//
//            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                Log.e("AdMob", "⚠️ فشل عرض الإعلان: ${adError.message}")
//            }
//        }
//    }
//
//}
