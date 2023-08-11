import Versions.activityCompose
import Versions.androidXCore
import Versions.androidXLifecycle
import Versions.coilVersion
import Versions.compose
import Versions.composeConstraintLayout
import Versions.composeLifecycle
import Versions.composeMaterial
import Versions.composeTooling
import Versions.daggerHilt
import Versions.hiltNavigationCompose
import Versions.navigationCompose
import Versions.leakCanaryVersion
import Versions.lottieCompose

object OtherDependencies {
    const val lottie: String = "com.airbnb.android:lottie-compose:$lottieCompose"
    const val coil: String ="io.coil-kt:coil-compose:$coilVersion"
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
    const val constraintLayout: String =
        "androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayout"
    const val composeNavigation: String =
        "androidx.navigation:navigation-compose:$navigationCompose"
}

object DaggerDependencies {
    const val dagger: String = "com.google.dagger:hilt-android:$daggerHilt"
    const val hiltNavigation: String =
        "androidx.hilt:hilt-navigation-compose:$hiltNavigationCompose"
    const val kaptCompiler: String = "com.google.dagger:hilt-compiler:$daggerHilt"
}

object DebugDependencies {
    const val uiTooling: String = "androidx.compose.ui:ui-tooling:$composeTooling"
    const val leakCanary: String =
        "com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion"
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
    const val osmdroid: String = "org.osmdroid:osmdroid-android:6.1.16"
}
