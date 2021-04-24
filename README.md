# Casa do codigo

## Run
Building and running the application locally
```
$> ./mvnw spring-boot:run 
```

## Usage
The following will demonstrate the usage of the HTTP endpoints and example responses.

- Registration of a new author:
```
$> curl localhost:8080/authors -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"name":"Venkat Subramaniam","email":"venkats@agiledeveloper.com","description":"Venkat Subramaniam is an award-winning author."}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 18 Nov 2020 08:55:06 GMT

{"id":1,"name":"Venkat Subramaniam","email":"venkats@agiledeveloper.com","description":"Venkat Subramaniam is an award-winning author.","createdAt":"2020-11-18T08:55:06.021131Z"}
``` 

- Registration of a category:
```
$> curl localhost:8080/categories -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"name": "Computers & Technology"}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 18 Nov 2020 09:11:57 GMT

{"id":1,"name":"Computers & Technology"}
``` 

- Create a new book:
```
$> curl localhost:8080/books -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"title":"Programming Kotlin","abstract":"Lorem ipsum dolor sit amet.","summary":"Cras quis cursus magna. Duis pretium placerat libero, id feugiat.","price":39.74,"pages":462,"isbn":"978-1680506358","publishedAt":"2021-10-01","categoryId":1,"authorId":1}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 18 Nov 2020 22:44:58 GMT

{"id":1,"title":"Programming Kotlin","abstract":"Lorem ipsum dolor sit amet.","summary":"Cras quis cursus magna. Duis pretium placerat libero, id feugiat.","price":39.74,"pages":462,"isbn":"978-1680506358","publishedAt":"2021-10-01","category":{"id":1,"name":"Computers & Technology"},"author":{"id":1,"name":"Venkat Subramaniam","email":"venkats@agiledeveloper.com","description":"Venkat Subramaniam is an award-winning author.","createdAt":"2020-11-18T22:44:30.693565Z"}}
``` 

- Display list of books to make it easier to know the id:
```
$> curl localhost:8080/books -i

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 19 Nov 2020 00:48:18 GMT

[{"id":1,"title":"Programming Kotlin"}]
``` 

- Display a book's detail page:
```
$> curl localhost:8080/books/1 -i

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 19 Nov 2020 00:52:33 GMT

{"title":"Programming Kotlin","abstract":"Lorem ipsum dolor sit amet.","summary":"Cras quis cursus magna. Duis pretium placerat libero, id feugiat.","price":39.74,"pages":462,"isbn":"978-1680506358","publishedAt":"2021-10-01","author":{"name":"Venkat Subramaniam","description":"Venkat Subramaniam is an award-winning author."}}
``` 

- Create a new country:
```
$> curl localhost:8080/countries -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"name":"USA"}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 19 Nov 2020 03:23:10 GMT

{"id":1,"name":"USA"}
``` 

- Create a new state that belongs to an existing country:
```
$> curl localhost:8080/states -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"name":"Texas","countryId":1}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 19 Nov 2020 03:44:49 GMT

{"id":1,"name":"Texas","country":{"id":1,"name":"USA"}}
``` 

- Create a new coupon:
```
$> curl localhost:8080/coupons -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"code":"DkNXLkBt","discount":0.25,"validUntil":"2021-10-05"}'

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Fri, 20 Nov 2020 19:34:26 GMT

{"id":1,"code":"DkNXLkBt","discount":0.25,"validUntil":"2021-10-05"}
``` 

- Starting a new payment flow:
```
$> curl localhost:8080/purchases -i -XPOST \
  -H 'Content-Type: application/json' \
  -d '{"email":"john.doe@email.com","name":"John","surname":"Doe","document":"042.432.604-35","address":"123 Fake St.","complement":"Apt 23","city":"Houston","countryId":1,"stateId":1,"phone":"(98) 3954-2633","cep":"65072-872","couponCode":"DkNXLkBt","order":{"amount":39.74,"items":[{"bookId":1,"quantity":1}]}}'

HTTP/1.1 201
Location: http://localhost:8080/purchases/1
Content-Length: 0
Date: Fri, 20 Nov 2020 18:51:23 GMT

``` 

- Display purchase's detail page:
```
$> curl localhost:8080/purchases/1 -i

HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Mon, 23 Nov 2020 22:17:25 GMT

{"id":1,"email":"john.doe@email.com","name":"John","surname":"Doe","document":"042.432.604-35","address":"123 Fake St.","complement":"Apt 23","city":"Houston","country":"USA","state":"Texas","phone":"(98) 3954-2633","cep":"65072-872","items":[{"bookTitle":"Programming Kotlin","quantity":1,"price":39.74,"amount":39.74}],"existsCoupon":true,"discount":9.94,"netAmount":29.80,"status":"DEFERRED"}
``` 

