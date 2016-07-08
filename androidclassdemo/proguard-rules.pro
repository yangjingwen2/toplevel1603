# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-injars      libs   #配置当前需要导入的Jar文件路径
-outjars     bin/classes-processed.jar //jar文件的输出文件名称

-dontpreverify #不进行预先校验
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation* #保证使用注解注释的方法不被混淆

-dontoptimize
#JPUSH
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

#==================protobuf======================
-dontwarn com.google.**
-keep class com.google.protobuf.** {*;}


#保证某一个具体的类不被混淆
#-keep public class 包名.类名
#-keep 表示某些文件不被混淆
-keep public class * extends android.app.Activity #保证所有的Activity的子类不被混淆
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * implements java.io.Serializable
#保证View的子类的构造方法和所有set方法不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}


-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.androidxx.yangjw.androidclassdemo.R$*{
public static final int *;
}
