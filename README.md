[![alt tag](https://raw.githubusercontent.com/fabian7593/InfiniteScroll/master/InfiniteScrollApp/Images/logo.png)](https://github.com/fabian7593/InfiniteScroll)

You can do a Endless scroll in ListView or RecyclerView with simple steps, witha  listener for do request to your web service.
<br>
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

<br>

# Contents

### Features
- - -
- [Features ScrollInfinite](#features-scrollinfinite)
- [Other Features](#other-features)

### Buy me a Coffee (Donate)
- [Donate](#donate)

### How to Start
- - -
- [Getting Started](#getting-started)
  - [Download Sources](#download-sources)
- [Setup](#setup)
  - [Add dependecies](#add-dependecies)
- [How To use](#how-to-use)
  - [Import library](#import-library)
  - [Starting](#starting)

### Footer Docs
- [Apache License](#license) 

<br><br>

## Features ScrollInfinite.
* **Working with RecyclerView or Listview** Either way, you can implement them.
* **Working with static or dynamic DataList** If you need to call any request with pagination and autoscrolling this library offer a simple possibility of implementing it, but if you need a simple call, withouth pagination, this library can also serve you.
* **Obtain a endless scroll** You can do it only with set the "setOnScrollListener" of the library.
* **Not need to implement dangerous permissions** Only need the ACCESS_NETWORK_STATE permission.
* **Set any ProgressBar** The progress bar is completely dynamical, you can only need to set this, with any type of progress bars that you needed.
* **Autoincrement of current page** If you need pagination for request the api, you only can set the current page, and the library is responsible for making the calling of "onLoadMoreData" event of the ScrollListener.
* **Status Management** This library offer the possibillity of know when the request is sucess or is failure with the override events in the interface of "InfiniteScrollInterface".
* **Offer Infinite Scroll Object** You need to instance this and set easy the attributes of your progress bar, the number of current page for call the api, the minimum number of row showing before call the next request.

#### Other Features
* A library completely OpenSource.
* Use best practice in POO.
* Minimun SDK 16+ API.
* Compile with Gradle.
* License Under Apache 2.0.
* The easiest possible integration.
* Quick and simple API.
* A good Internal Documentation.
* Apart from OpenSource, it's also free, and always will be.
* You can get the source code and run the example, and use the code as necessary.

<br>
<br>

### Donate

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=L25MKCRPR7TWY)

<br>
<br><br>

## Getting Started

### Download Sources
use git (sourcetree or others)
Remember Download the example for understand better the process

```bash
git clone https://github.com/fabian7593/InfiniteScroll.git
```

Download from [Here](https://github.com/fabian7593/InfiniteScroll/zipball/master)

Another type download by Bintray from    [ ![Download](https://api.bintray.com/packages/fabian7593/maven/InfiniteScroll/images/download.svg) ](https://bintray.com/fabian7593/maven/InfiniteScroll/_latestVersion)

 <br>

## Setup
#### Add dependecies
If you need an endless scroll for load data to pagination web service, this is your solution.
You need to download the project or import in your gradle, like this.

```bash
repositories {
    jcenter()
}

dependencies {
   compile 'github.frosquivel:infinitescroll:1.0.0'
}
```

If you have any problem with this dependence, because the library override any styles, colors or others, please change the last line for this code:

```bash
 compile('github.frosquivel:infinitescroll:1.0.0') {
        transitive = false;
    }
```
 
<br>

## How To use
<br>

### Import library
You need to import the library
```bash
import github.frosquivel.infinitescroll.Adapter.InfiniteScrollAdapter;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollImpl;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollInterface;
import github.frosquivel.infinitescroll.Logic.InfiniteListOnScrollListener;
import github.frosquivel.infinitescroll.Model.InfiniteScrollBuilder;
import github.frosquivel.infinitescroll.Model.InfiniteScrollObject;
import github.frosquivel.infinitescroll.Interface.InfiniteScrollInterface;
```
<br>

### Starting
To start with ScrollInfinite you need a simple ListView or RecyclerView.
For example, set this in activity_layout.xml like this:
```bash
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical">
    
     <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
</LinearLayout>
```

When you have the List view and your respective union in .java Activity, you need to set the listener of Infinite Scroll.
<br>

### InfiniteScrollObject new Instance
For instance the InfiniteScrollListener, you need ALWAYS instance first the InfiniteScrollObject, because is a constructor parameter of InfiniteScrollListener.

The InfiniteScrollObject has tree params to declare, in a builder pattern.
* **Activity** Is an Activity class. This is the only required parameter for instance the class.
* **setProgressBar** Is a ProgressBar class. You can set your respective Progress bar in this param, if you dont need any, you dont have problem to use the library.
* **setCurrentPage** Is an int primitive type. You can set the page of the request of web service, if you dont set any, the library set in default the first page.
* **setMinimunNumberRowLoadingMore** Is an int primitive type. The number of current page for call the api, the minimum number of row showing before call the next request, if you dont set any, the library set for default , 3 rows.

See the example of this implementation:
```bash
InfiniteScrollObject infiniteScrollObject = new InfiniteScrollBuilder(activity)
                .setProgressBar(yourProgressBar)
                .setCurrentPage(1)
                .setMinimunNumberRowLoadingMore(5)
                .build();
```
<br>

### Set the onScrollListener
You need to instance InfiniteListOnScrollListener or InfiniteRecyclerOnScrollListener.
And set this into onScrollListener, of the ListView or RecyclerView.
This methods implements the Override events of "onLoadMoreData", and return the next page of request (increment automatically), and the total item count.
You need to return the int of the number of response objects of the web services.

**Example ListView**
```bash
   listView.setOnScrollListener(new InfiniteListOnScrollListener(infiniteScrollObject) {
            @Override
            public int onLoadMoreData(int page, int totalItemsCount, ListView view) {
                //in this part you can entry the code for request the web service with the new number of page (if have pagination)
                //this request need to return a number of response object of the web service
                return resquestAPIMethod(page);
            }
        });
```
