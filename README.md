"# RestaurantFindKun" 
  
# **〇予定**  
+ 3/12 ~ 3/15 : Github、UIデザイン、pluginなどの前準備  
+ 3/16 ~ 3/17 : GPS現在位置取得  
+ 3/18 ~ 3/18 : 情報表示用のパネル・検索用の画面仮作成  
+ 3/19 ~ 3/20 : APIを用いて情報を取得、パネルに表示  
+ 3/21 ~ 3/22 : 検索機能実装、パネルに表示  
+ 3/22 ~ 3/23 : 情報詳細画面実装  
+ 3/24 ~ 3/25 : 追加機能作成  
  
**□できれば**  
+ 検索条件の追加  
  - クーポン、ジャンル、予算など　　//一つできたら芋づる式にできそう  
+ マップの表示  
  - おそらくGoogleMapAPI　　//Flutterでマップ使ったのでハードルは高くはない  
  
# **〇作業進捗**  
+ **3/12**  
    - [x] 学校への就活状況のもろもろ連絡  
    - [x] APIなど事前の下調べ、並びにAndroidStudioのアプデなどの前準備  
    - ※感想：一次面接通過うれしい～～～～～けど実技テストこわ～～～い。でもでも準備ちゃんとやってる私はえらい。天才。  
+ **3/13**  
    - [x] Githubリポジトリ、Figmaページ作成  
    - [x] 昔に作ったデータを見つつ勉強  
    - ※感想：（゜▽゜）　＜ﾜｧ！めちゃくちゃ忘れてる～～～↑↑昔に作ったデータ残っててよかった～～↑↑  
+ **3/14**  
    - [x] テストプロジェクト作成して復讐①  
    - ※感想：見通しが立たないが多分なんとかなるというかなんとかするできる私はできる。がんばれ。  
+ **3/15**  
    - [x] テストプロジェクト作成して復習②  
    - ※感想：用事があったためあまり作業できず。  
+ **3/16**  
    - [x] 今までメモに書いてたものをGitHubのREADMEにお引越し  
    - [x] build.gradleの初期設定ざっくり  
    - [x] 現在位置情報取得の準備（権限付与について、メッセージについて）  
    - ※感想：Kaptを記載し忘れてせいでアプリが起動できず数時間をどぶに捨てる。逆に言えば分からなかった問題を1日もたてずに解決させた。天才。  
+ **3/17**  
    - [x] 権限許可可能に  
    - [x] 位置情報取得（可能であればリアルタイムで）  
    - ※感想：位置情報をとる方法で右往左往した。LocationManagerが非推奨になった？とからしいが調べてもうまく理解できなかった。頑張れ。
+ **3/17**
    - [ ] Component作成（パネル）
    - [ ] 検索画面作成
  
# **〇参考にしたサイト様**  
  + **JetpackCompose**  
    - 画像に対してのModifier  
      - https://developer.android.com/jetpack/compose/modifiers?hl=ja  
    - 画像の設定
      - https://engawapg.net/jetpack-compose/893/display-image/
    - Card
      - https://developer.android.com/jetpack/compose/components/card?hl=ja
    - Icon一覧
      - https://fonts.google.com/icons

  + **地図系**  
    - 権限付与の大体の流れ  
      - https://akira-watson.com/android/kotlin/permission-request.html  
    - ContextCompat  
      - https://developer.android.com/reference/androidx/core/content/ContextCompat  
    - 現在位置情報の取得
      - https://developer.android.com/develop/sensors-and-location/location/retrieve-current?hl=ja 

  + **plugin系**  
    - Hilt、Kapt  
      - https://developer.android.com/training/dependency-injection/hilt-android?hl=ja  

  + **Manifest系**  
    - アクティビティの自動作成（手動で書いたら何故かうまくいかなかったので）  
      - https://androidstudio.hatenablog.com/entry/2014/07/25/134600  
  
  + **あああ**  
    - アイコンの書き方
      - https://tech.mokelab.com/android/compose/composable/iconButton/display.html
    - ログ出力の方法  
      - https://appdev-room.com/android-log/  
