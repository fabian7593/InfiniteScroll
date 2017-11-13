[![alt tag](https://raw.githubusercontent.com/fabian7593/InfiniteScroll/master/InfiniteScrollApp/Images/logo.png)](https://github.com/fabian7593/InfiniteScroll)

You can do a Endless scroll in ListView or RecyclerView with simple steps, with a listener for do request to your web service.

<br>

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/bbef86756e3541f49b6c1be714081137)](https://www.codacy.com/app/fabian7593/InfiniteScroll?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=fabian7593/InfiniteScroll&amp;utm_campaign=Badge_Grade)
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
  - [InfiniteScrollObject new Instance](#infinitescrollobject-new-instance)
  - [Set the onScrollListener](#set-the-onscrolllistener)
  - [Call the InfiniteScrollInterface](#call-the-infinitescrollinterface)
- [Current Example Explanation](#current-example-explanation)

### Footer Docs
- - -
- [Footer Document](#footer-document)
  - [Internal documentation](#internal-documentation)
  - [Preview of Example](#preview-of-example)
  - [Application that use InfiniteScroll](#application-that-use-infinitescroll)
  - [Suggestions](#suggestions)
- [Credits and contributors](#credits-and-contributors) 
  - [Contributors are welcome](#contributors-are-welcome)
- [References](#references) 
- [CountryAPI RestApi](#countryapi-restapi) 
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

<br><br>

### Donate

[![Donate](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=L25MKCRPR7TWY)

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
<br><br>

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
<br><br>

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
<br><br>

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

**Example RecyclerView**
You need to send the recycler view, the activity and the infiniteScrollObject
```bash
  recyclerView.addOnScrollListener(new InfiniteRecyclerOnScrollListener(recyclerView, activity, infiniteScrollObject) {
            @Override
            public int onLoadMore(int currentPage, int totalItemsCount) {
                return resquestAPIMethod(currentPage);
            }
        });
```
<br><br>

### Set the InfiniteScrollInterface
You need to set the InfiniteScrollInterface, for call the events of onSucess or onFailure.
You have the possibillity of implements "InfiniteScrollInterface" and import the required methods, or create an instance of this Interface and usage how that you need.

Example:
```bash
            InfiniteScrollInterface interfaceInfinite = new InfiniteScrollImpl() {

                //If the request is success and get data, call onSuccess event of this interface
                //The parameter is an object and you cast this object to another class type, that you need
                @Override
                public void onSuccess(Object responseModel) {
                    //set the response model in a static variable
                    responseModelStatic = (ResponseModel) responseModel;

                    //add into your adapter list of your recylcer view or list view, the new values response of web service
                   
                    //And refresh the listView, like this
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                adapter.notifyDataSetChanged();
                            }catch(Exception ex){}
                        }
                    });
                }

                //If the request have a failure, call this event
                @Override
                public void onFailure(String errorResponse){
                    Toast.makeText(activity, errorResponse, Toast.LENGTH_SHORT).show();
                }
            };

```
<br><br>

### Call the InfiniteScrollInterface
When you call the library for request you web service, you can do use, OkHttp, Retrofit, Volley, Async task etc...
when this class obtain the new data of response or get a failure you can need to call the methods of interface like this:
```bash
//instance the request and ok http client
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            //do the request
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(final Call call, IOException e) {
                          infiniteScrollInterface.onFailure(stackTrace); 
                        }

                        @Override
                        public void onResponse(Call call, final Response response) throws IOException {
                            infiniteScrollInterface.onSuccess(responseModel);  
                        }
                    });
```

<br><br><br>

## Current Example Explanation
You can view the current example in the code, if you download or clone the repository, in app folder you can view all code.
In this example the real important classes are:

* **Fragments/CountryListViewFragment** Have all implementation of InfiniteScroll library for listViews.
* **Fragments/CountryRecyclerViewFragment** Have all implementation of InfiniteScroll library for recyclerViews.
* **Classes/RequestApi** This class build the request for country api and call the interface events.
* **Models/ResponseModel** The object with response class structure.
* **Models/Country** The object with coutnry object structure.
All of other class is only for do the application, but they have no relationship with InfiniteScrollLibrary. 

We use the "CountryApi" for example of pagination web service, you can see this in:
https://github.com/fabian7593/CountryAPI

<br><br><br>

# Footer Document

## Internal documentation
All the code has a internal documentation for more explanation of this example.
<br><br>

## Preview of Example
Pay attention to how the scroll bar increases.

The progress bar is not shown because the variable of MinimunNumberRowLoadingMore has a high value (5), to prevent the user from noticing when more data is loaded.
<br>
![alt tag](https://raw.githubusercontent.com/fabian7593/InfiniteScroll/master/InfiniteScrollApp/Images/gif-infinitescroll.gif)
<br><br>

## Application that use InfiniteScroll
Feel free to contact me to add yours apps to this list.
<br><br>

## Suggestions
Infinite Scroll was created to make Android Devoloper's life easy. If you have any feedback please let us know in the issues by creating an issue with this format:
 
 - Write what your feedback is about and add the next "tag" including the square brackets [FEEDBACK]

Suggestions about how to improve the library or new features are welcome. Thanks for choosing us.
<br><br>

## References
We obtain references in:

https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews-and-RecyclerView
<br><br>

## CountryAPI RestApi
We use the "CountryApi" for example of pagination web service, you can see this in:

https://github.com/fabian7593/CountryAPI
<br><br>

## Credits and Contributors

**Author**
### [Fabián Rosales - Frosquivel Developer](https://github.com/fabian7593) : 
Infinite Scroll author...

[![alt tag](https://raw.githubusercontent.com/fabian7593/CountryAPI/master/Files/imgsReadme/github-logo.png)](https://github.com/fabian7593)
[![alt tag](https://raw.githubusercontent.com/fabian7593/CountryAPI/master/Files/imgsReadme/facebook.png)](https://www.facebook.com/fabian.rosales.509)
[![alt tag](https://raw.githubusercontent.com/fabian7593/CountryAPI/master/Files/imgsReadme/linkedin.png)](https://www.linkedin.com/in/fabian-rosales-esquivel-698893106/)
[![alt tag](https://raw.githubusercontent.com/fabian7593/CountryAPI/master/Files/imgsReadme/youtube.png)](https://www.youtube.com/channel/UCJnvvHb_vwMwbnZWplkHIfw)
<br><br>

## Contributors are welcome
The goal for InfiniteScroll is to allow Android Developers care about what is important, features not getting worry about something that should be trivial such as do endless scroll. There are many features and other issues waiting. If you would like to contribute please reach to us, or maybe be bold! Getting a surprise pull request is very gratifying.
<br><br><br>

# License
Copyright 2017 Fabian Rosales

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
<br><br>
