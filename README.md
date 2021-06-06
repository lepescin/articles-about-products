Тестовое задание. Articles and products.
http://localhost:8080/swagger-ui.html

##ProductController
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
##ArticleController
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
