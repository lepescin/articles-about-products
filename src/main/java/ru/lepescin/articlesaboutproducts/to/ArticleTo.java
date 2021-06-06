package ru.lepescin.articlesaboutproducts.to;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticleTo extends BaseTo {
    private String title;

    private String content;

    private LocalDateTime created;
}
