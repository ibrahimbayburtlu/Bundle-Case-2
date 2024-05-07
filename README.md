# Veri Üretme ve İşleme Uygulamaları

Bu proje, veri üretme, filtreleme ve işleme süreçlerini simüle eden bir dizi uygulamayı içerir. Aşağıda her bir uygulamanın ve sürecin nasıl çalıştığını ve gereksinimlerini açıklayan bir rehber bulunmaktadır.

## 1. Veri Üretme Uygulaması

Bu uygulama rastgele veri üretir ve belirli bir sokete yazar. Her üretilen veri, bir zaman damgası (timestamp), 0 ile 100 arasında rastgele bir tamsayı ve üstteki iki verinin MD5 hash'inin son 2 karakterinden oluşur. Veriler saniyede 5 kez üretilir.

### Gereksinimler

- Java programlama dili
- Java'nın Socket ve MessageDigest kütüphaneleri

### Kullanım

```bash
java VeriUretmeUygulamasi
```

## 2. Veri Filtreleme Uygulaması

Bu uygulama, belirli bir soketi dinler ve gelen verileri filtreler. İkinci alandaki değer 90'ın üstündeyse diğer alanlarla birlikte bir message queue'ye gönderilir. Değilse, veri bir dosyaya eklenir.

### Gereksinimler

- Java programlama dili
- Java'nın Socket ve File I/O kütüphaneleri

### Kullanım

```bash
java VeriFiltrelemeUygulamasi
```

## 3. Message Queue İşleme Uygulamaları

Bu uygulamalar, bir message queue'dan gelen verileri işler.

### 3.1. Message Queue'yi Database'e Yazma Uygulaması

Bu uygulama, bir message queue'dan gelen verileri bir veritabanı tablosuna yazar.

#### Gereksinimler

- Java programlama dili
- Java'nın Message Queue (örneğin, JMS) ve Database kütüphaneleri

#### Kullanım

```bash
java MQVeriDatabaseYazmaUygulamasi
```

### 3.2. Message Queue'yi MongoDB'ye Yazma Uygulaması

Bu uygulama, bir message queue'dan gelen verileri MongoDB koleksiyonuna yazar. Ardışık gelen hash alanları yalnız "99"dan büyükse, aynı kayıt içinde nested olarak saklanır. Değilse, yeni bir kayıt oluşturulur.

#### Gereksinimler

- Java programlama dili
- Java'nın Message Queue (örneğin, JMS) ve MongoDB kütüphaneleri

#### Kullanım

```bash
java MQVeriMongoDBYazmaUygulamasi
```

Bu README dosyası, proje kullanımı hakkında temel bilgileri içermektedir. Detaylı bilgi ve uygulama kodları için ilgili dosyalara bakınız.
