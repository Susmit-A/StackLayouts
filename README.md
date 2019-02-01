# Stack Layout
A simple library to help you arrange views in a horizontal or vertical stack.


Please note that this library is currently supported on android versions 5.0(Lollipop) and above.

This library works best when all child views are of same size.
Using child views of different sizes may lead to unpredictable behaviour.

Integration with existing project
---

### Setup

##### build.gradle (project)
```groovy
allprojects {
    repositories {
        ...
        maven {
            url 'https://jitpack.io'
        }
    }
}
```

#### build.gradle (app)
```groovy
dependencies {
    ...
    compile 'com.github.Susmit-A:StackLayouts:1.0.0'
}
```

### Basic Usage
#### XML (Horizontal Stack)
```xml
...
<com.susmit.stacklayout2.HorizontalStackLayout
    ...
    app:peekViewCount="5"
    app:peekSize="20"
    app:upperExtremeRotation="2"
    app:lowerExtremeRotation="2"
    app:previousViewVisibility="0.25">
    
    ...
    	Child Views
    ...
    
</com.susmit.stacklayout2.HorizontalStackLayout>
...
```