-keepattributes SourceFile,LineNumberTable
-ignorewarnings
-optimizations !method/inlining/*
-keepnames class wat.** { *; }
-keepnames class bakuen.** { *; }
-keep class * implements android.BinderInterface { *; }