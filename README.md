# TooltipBubble

[![](https://jitpack.io/v/allybros/tooltipbubble.svg)](https://jitpack.io/#allybros/tooltipbubble)

It's a simple Tooltip.

![image](https://user-images.githubusercontent.com/24457904/144897195-ff8a5eba-96c1-4a5c-89f2-003ae014ca91.gif)


## Installing

```
Add the code block to your project

    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
   }
   dependencies {
        implementation 'com.github.allybros:TooltipBubble:1.0'
   }
```


## How to use?

- It can be use only with text.

### Kotlin
```
Bubble("Text", activity: Activity).showBubble(view: View)
```
### Java

```
Bubble bubble = new Bubble("Text", activity: Activity, null, null, null, null);
bubble.showBubble(view: View);
```





- Add new background, animation, up or down anchors.
### Kotlin
```
Bubble(     "Text", 
            activity: Activity,
            background = R.drawable.new_layout, 
            anchorDownStyle = R.drawable.new_down_nav,
            anchorTopStyle = R.drawable.new_top_nav,    
            animationStyle = R.anim.new_animation).showBubble(view: View)
```

### Java
```
Bubble bubble = new Bubble("Text", activity, R.drawable.new_layout, R.drawable.new_top_nav, R.drawable.new_down_nav, R.anim.new_animation);
bubble.showBubble(view: View);
```




