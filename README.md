# TooltipBubble

[![Release](https://jitpack.io/v/allybros/TooltipBubble.svg)]
(https://jitpack.io/allybros/TooltipBubble)

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

```
Bubble("Text", activity: Activity).showBubble(view: View)

```

- Add new background, animation, up or down anchors.

```
Bubble(     "Text", 
            activity,
            background = R.drawable.new_layout, 
            anchorDownStyle = R.drawable.new_down_nav,
            anchorTopStyle = R.drawable.new_top_nav,    
            animationStyle = R.anim.new_animation).showBubble(anchorView)

```




