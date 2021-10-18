object Version {
    const val gradleVersion = "7.0.1"
}

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:${Version.gradleVersion}"
}

object Kotlin {
    private const val kotlinVersion = "1.5.21"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val kotlinStd = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
}

object SonarQube {
    private const val sonarVersion = "3.3"
    const val sonarQubePlugin =
        "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:$sonarVersion"
}

object Detekt{
    private const val detektVersion = "1.18.1"
    const val detekt =
        "io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion"
}

object Modules {
    const val commons = ":core:commons"
    const val diModule = ":core:di"
    const val intentModule = ":core:intent"
    const val uiKit = ":core:uikit"
    const val dataModule = ":data"
    const val dataLocalModule = ":data_local"
    const val dataRemoteModule = ":data_remote"
    const val domainModule = ":domain"

    const val featureMain = ":features:main"
}

object AndroidCore {
    private const val androidCoreVersion = "1.6.0"
    private const val appCompatVersion = "1.3.1"
    private const val materialVersion = "1.4.0"
    private const val constraintLayoutVersion = "2.1.1"
    private const val legacySupportVersion = "1.0.0"
    const val androidCore = "androidx.core:core-ktx:$androidCoreVersion"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
    const val material = "com.google.android.material:material:$materialVersion"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    const val legacy = "androidx.legacy:legacy-support-v4:$legacySupportVersion"
}

object LifeCycle {
    private const val lifecycleVersion = "2.3.1"
    const val viewModelRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
    const val lifecycle = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
}

object Koin {
    private const val koinVersion = "3.1.2"
    const val koinCore = "io.insert-koin:koin-core:$koinVersion"
    const val koinAndroid = "io.insert-koin:koin-android:$koinVersion"
}

object AndroidNavigation {
    private const val navigationVersion = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
    const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion"
}

object Coroutines {
    private const val coroutinesVersion = "1.5.2"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
}

object Retrofit {
    private const val retrofitVersion = "2.9.0"
    private const val httpInterceptorVersion = "4.9.2"
    private const val okhttpVersion = "4.9.2"

    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$httpInterceptorVersion"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val gsonConvertFactory = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
}

object Shimmer{
    private const val shimmerVersion = "0.5.0"
    const val shimmer = "com.facebook.shimmer:shimmer:$shimmerVersion"
}

object Room {
    private const val roomVersion = "2.3.0"
    const val room = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
}

object TestDependencies {
    private const val jUnitVersion = "4.13.2"
    const val jUnit = "junit:junit:$jUnitVersion"
}


object AndroidTestDependencies {
    private const val jUnitVersion = "1.1.3"
    private const val espressoVersion = "3.4.0"
    const val jUnit = "androidx.test.ext:junit:$jUnitVersion"
    const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
}