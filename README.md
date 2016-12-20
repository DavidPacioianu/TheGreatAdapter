# TheGreatAdapter

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-The%20Great%20Adapter-brightgreen.svg?style=flat)]()
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg)](https://android-arsenal.com/api?level=14)
[ ![Download](https://api.bintray.com/packages/davidpacioianu/maven/TheGreatAdapter/images/download.svg) ](https://bintray.com/davidpacioianu/maven/TheGreatAdapter/_latestVersion)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/DavidPacioianu/TheGreatAdapter/master/LICENSE) 

Multiple items adapter made easy, including headers and footers.


----------


### Preview in WallSnap
![](http://i.giphy.com/l4JyRhO1NJnPN2m3e.gif)


----------


### Gradle Dependency
```gradle
dependencies {
    compile 'com.pacioianu.david:the-great-adapter:0.1.0'
}
```


----------


### Usage

Simple usage:
```java
GreatAdapter adapter = new GreatAdapter.Builder(context).build();
```


----------


**The magic** of this adapter is that you can add any item, its class just has to implement the **GreatItem interface.**
> **Note:**

> - Every class that implements the *GreatItem* interface **must have a no-args constructor**.
> - *getItemType* method must return a unique integer for every class.
> - *getItemId* method must return a unique id for every instance of the class.
> - *getSpanSize* method return the span size of every instance of the class, to make them match the full span size of the layout manager, return -1.
> - If you just want to add a *one-time-use* view to your recyclerview, use the SimpleItemView class. You just pass it the view you want to show, without the need of creating a useless viewholder. ``` adapter.add(new SimpleItemView(yourView);```


----------


Full builder options list:
```java
new GreatAdapter.Builder(context)
        .enableLoadMore(true) // default false
        .loadItemsThreshold(2) // default 0
        .loadListener(yourLoadListener)
        .dividerSizeDp(2) // defaut 0
        .loadMoreView(yourCustomLoadMoreView)
        .loadFailedView(yourCustomLoadFailedView)
        .build();
```


----------


To add/get/remove any header or footer, use:
```java
adapter.addHeader(yourHeader);
adapter.addFooter(yourFooter);
adapter.getHeader(position);
adapter.getFooter(position);
adapter.removeHeader(yourHeader);
adapter.removeFooter(yourFooter);
```


----------


###Apps using the TheGreatAdapter
(feel free to send me your project)

Icon | Application
------------ | -------------
<img src="https://goo.gl/uebQIl" width="48" height="48" /> | [WallSnap](https://play.google.com/store/apps/details?id=com.pixelcan.wallsnap) <alt="width="40" height="40" />


----------


###Community
Looking for contributors, feel free to fork!


---------


License
--------

    Copyright 2016 David Păcioianu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
