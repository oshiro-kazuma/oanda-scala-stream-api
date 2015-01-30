oanda-scala-stream-api
==================

OANDA REST APIにつないでみる

### 参考にしたもの

https://github.com/oanda/java-api-streaming

### セットアップ

必要に応じて変数を変更 [PriceStream.scala](src/main/scala/PriceStream.scala)

sandbox環境ならアカウント情報なしで接続できる

    domain
    access_token
    account_id
    instruments

### 動かし方
```
sbt "run-main PriceStream"
{"tick":{"instrument":"EUR_JPY","time":"2015-01-29T14:38:46.536478Z","bid":142.483,"ask":142.505}}
{"tick":{"instrument":"USD_JPY","time":"2015-01-29T14:38:46.652931Z","bid":114.867,"ask":114.881}}
{"tick":{"instrument":"USD_JPY","time":"2015-01-29T14:38:46.662718Z","bid":114.867,"ask":114.88}}
{"tick":{"instrument":"USD_JPY","time":"2015-01-29T14:38:47.241261Z","bid":114.864,"ask":114.879}}
{"tick":{"instrument":"EUR_JPY","time":"2015-01-29T14:38:47.258729Z","bid":142.483,"ask":142.503}}
```
