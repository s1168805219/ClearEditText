带有清除按钮的EditText
![](http://i.imgur.com/e997mxY.gif)
Step 1. Add the JitPack repository to your build file 
> Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency
```
dependencies {
      compile 'com.github.s1168805219:ClearEditText:1.0'
}
```


