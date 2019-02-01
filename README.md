# Stack Layout
A simple Android library to help you arrange views in a horizontal or vertical stack.


Please note that this library is currently supported on android versions 5.0(Lollipop) and above.

This library works best when all child views are of same size.
Using child views of different sizes may lead to unpredictable behaviour.

## Samples:
![Demo Horizontal LTR Stack](https://media.giphy.com/media/2Y9DBK8QDDMEJ213xp/giphy.gif)    ![Demo Vertical Stack](https://media.giphy.com/media/eB5EUZPpSGnI8Avuk9/giphy.gif)    ![Demo Horizontal RTL Stack](https://media.giphy.com/media/oFRwv9YGRKj16RAIAQ/giphy.gif) 

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
#### XML (Horizontal LTR Stack)
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

#### XML (Horizontal RTL Stack)
```xml
...
<com.susmit.stacklayout2.HorizontalStackLayout
    ...
    android:layoutDirection="rtl"
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

#### XML (Vertical Stack)
```xml
...
<com.susmit.stacklayout2.VerticalStackLayout
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