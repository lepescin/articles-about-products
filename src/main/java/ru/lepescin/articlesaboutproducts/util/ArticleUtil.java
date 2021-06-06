package ru.lepescin.articlesaboutproducts.util;

import ru.lepescin.articlesaboutproducts.model.Article;
import ru.lepescin.articlesaboutproducts.to.ArticleTo;

public class ArticleUtil {
    public static Article createNewFromTo(ArticleTo articleTo){
        return new Article(articleTo.getId(),articleTo.getTitle(),articleTo.getContent(),articleTo.getCreated());
    }
}
