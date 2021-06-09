package ru.lepescin.articlesaboutproducts.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
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

    @ApiModelProperty(hidden = true)
    private LocalDateTime created;
}
