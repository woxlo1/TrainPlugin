# TrainPlugin

Minecraft 用プラグイン「TrainPlugin」のリポジトリです。

## 概要
このプラグインは、駅を作って電車を移動させる機能を提供します。

## 機能
- /station コマンドで駅の作成・削除・一覧表示
- /train コマンドで電車の出発・停止・情報確認
- Route クラスで駅のルート管理
- TrainManager で電車の管理
- ArmorStand を使用した簡易的な電車表現

## 使用方法
1. サーバーにプラグインを設置
2. /station で駅を作成
3. /train start で電車を出発
4. /train info で現在の駅と停止状態を確認
5. /train stop で電車を停止

## 開発について
- 本プロジェクトは ChatGPT と共同で設計・実装しました
- Gradle/Kotlin を使用
- Java 17 以上必須

## ライセンス
MIT License
