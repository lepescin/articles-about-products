Тестовое задание. Articles and products.
http://localhost:8080/swagger-ui.html

## ProductController
#### create new product
curl -s -i -X POST -d '{"name":"Product1", "description":"Description1", "price":"100"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/products
#### get product with id=1
curl -s http://localhost:8080/products/1
#### get all products
curl -s http://localhost:8080/products
#### delete product with id = 1
curl -s -X DELETE http://localhost:8080/products/1
#### update product with id = 1
curl -s -X PUT -d '{"name":"UpdatedProduct", "description":"UpdatedDescription", "price":"1000"}' -H 'Content-Type: application/json' http://localhost:8080/products/1
#### sort by name
curl -s http://localhost:8080/products/sort?param=name
#### sort by price
curl -s http://localhost:8080/products/sort?param=price
#### sort by quantity of articles
curl -s http://localhost:8080/products/sort?param=quantityofarticles
#### filter by price
curl -X GET "http://localhost:8080/products/filterbyprice?fromPrice=100&toPrice=1000" -H "accept: application/json"
#### filter by quantity of articles
curl -X GET "http://localhost:8080/products/filterbyquantityofarticles?fromQty=2&toQty=5" -H "accept: application/json"

## ArticleController
#### create new article
curl -s -i -X POST -d '{"title":"Article1", "content":"Text1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/products/1/articles
#### get all articles about product with id = 1
curl -s http://localhost:8080/products/1/articles
#### get article with id=1 about product with id = 1
curl -s http://localhost:8080/products/1/articles/1
#### delete article with id = 1 about product with id = 1
curl -s -X DELETE http://localhost:8080/products/1/articles/1
#### update article with id = 1 about product with id = 1
curl -s -X PUT -d '{"title":"UpdatedArticle", "content":"UpdatedText"}' -H 'Content-Type: application/json' http://localhost:8080/products/1/articles/1
#### sort by title
curl -s http://localhost:8080/products/articlesort?param=title
#### sort by datetime
curl -s http://localhost:8080/products/articlesort?param=datetime
#### filter by datetime
curl -X GET "http://localhost:8080/products/articlefilter?fromDateTime=2021-06-08T00:00:00&toDateTime=2021-06-08T23:59:00" -H "accept: application/json"
