# 介紹

"台北施工報你知"是一款可以告訴你台北市即時施工資訊的App，能讓你隨時隨地掌握今天台北哪邊有施工

其功能有：
  - 施工地圖總覽：在地圖上顯示你附近哪邊在施工
  - 施工資訊列表：列表顯示，讓你能快速知道今哪裡在施工

# 功能架構

透過HttpURLConnection將Data.Taipei所提供的API內容以Json格式取下來，再透過Google Map Api顯示在地圖上和利用listview的方式表格顯示

函式說明：
  -  [JSONParser.java]：HttpURLConnection 相關code
  - [MainActivity.java] ：將抓下來的json格式進行處理轉換，透過按鍵轉換到其他的Activity
  - [MapsActivity.java] ：將資料透過地標形式顯示在地圖上，按地標可顯示更多資訊。透過 map.setMyLocationEnabled即可定位使用者所在位置
  - [ListActivity.java]：將資料透過ListAdapter 放入listview中，長按item可顯示更多資訊
  - [TMToLatLon.java] , [TWD97.java] , [TMParameter.java] :由於政府提供的地點資料為TWD97制(x,y軸) ，這些函示用來進行轉換，變成 WGS84制(經緯度)

# 畫面截圖

#### Icon和主畫面

![icon+主畫面](https://github.com/msps9341012/kkbox/blob/master/app.jpg)    

#### 地圖

![地圖](https://github.com/msps9341012/kkbox/blob/master/map.png)   

#### 表單

![表單](https://github.com/msps9341012/kkbox/blob/master/list.jpg)   



[JSONParser.java]: <https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/JSONParser.java>
[ListActivity.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/ListActivity.java>
[MapsActivity.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/MapsActivity.java>
[MainActivity.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/MainActivity.java>
[TMToLatLon.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/TMToLatLon.java>
[TWD97.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/TWD97.java>
[TMParameter.java]:<https://github.com/msps9341012/kkbox/blob/master/app/src/main/java/com/example/imf_mbk1/kk/TMParameter.java>
