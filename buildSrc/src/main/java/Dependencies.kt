import Versions.activityCompose
import Versions.androidXCore
import Versions.androidXJunit
import Versions.androidXLifecycle
import Versions.compose
import Versions.composeLifecycle
import Versions.composeMaterial
import Versions.composeTooling
import Versions.composeUITest
import Versions.daggerHilt
import Versions.espresso
import Versions.hiltNavigationCompose
import Versions.jUnit
import Versions.leakCanaryVersion
import Versions.navigation
import Versions.lottieCompose

object OtherDependencies {
    const val lottie = "com.airbnb.android:lottie-compose:$lottieCompose"
}

object AndroidXDependencies {
    const val core: String = "androidx.core:core-ktx:$androidXCore"
    const val lifecycle: String = "androidx.lifecycle:lifecycle-runtime-ktx:$androidXLifecycle"
    const val viewModel: String =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$androidXLifecycle"
    const val liveData: String = "androidx.lifecycle:lifecycle-livedata-ktx:$androidXLifecycle"
}

object ComposeDependencies {
    const val preview: String = "androidx.compose.ui:ui-tooling-preview:$composeTooling"
    const val material: String = "androidx.compose.material:material:$composeMaterial"
    const val activity: String = "androidx.activity:activity-compose:$activityCompose"
    const val lifecycleCompose: String =
        "androidx.lifecycle:lifecycle-runtime-compose:$composeLifecycle"
    const val composeUI: String = "androidx.compose.ui:ui:$compose"
    const val navigationCompose: String = "androidx.navigation:navigation-compose:$navigation"
}

object DaggerDependencies {
    const val dagger: String = "com.google.dagger:hilt-android:$daggerHilt"
    const val hiltNavigation: String =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose"
    const val kaptCompiler: String = "com.google.dagger:hilt-compiler:$daggerHilt"
}

object DebugDependencies {
    const val uiTooling: String = "androidx.compose.ui:ui-tooling:$composeTooling"
    const val uiTestManifest: String = "androidx.compose.ui:ui-test-manifest:$composeTooling"
    const val customView = "androidx.customview:customview:1.1.0"
    const val poolingContainer = "androidx.customview:customview-poolingcontainer:1.1.0"
    const val leakCanary: String =
        "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
}

object TestDependencies {
    const val junit: String = "junit:junit:$jUnit"
    const val junitExt: String = "androidx.test.ext:junit:$androidXJunit"
    const val espressoCore: String = "androidx.test.espresso:espresso-core:$espresso"
    const val composeUI: String = "androidx.compose.ui:ui-test-junit4:$composeUITest"
}

object FirebaseDependencies {
    const val bom: String = "com.google.firebase:firebase-bom:32.1.1"
    const val auth: String = "com.google.firebase:firebase-auth-ktx"
    const val firestore: String = "com.google.firebase:firebase-firestore-ktx"
    const val analytics: String = "com.google.firebase:firebase-analytics-ktx"
}

object RetrofitDependencies {
    const val retrofit: String = "com.squareup.retrofit2:retrofit:2.6.0"
    const val gsonConverter: String = "com.squareup.retrofit2:converter-gson:2.6.0"
}

object LocationServicesDependencies {
    const val locationService: String = "com.google.android.gms:play-services-location:21.0.1"
    const val osmdroid : String = "org.osmdroid:osmdroid-android:6.1.16"
}
