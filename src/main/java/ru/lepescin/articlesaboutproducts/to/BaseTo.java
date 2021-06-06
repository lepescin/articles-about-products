package ru.lepescin.articlesaboutproducts.to;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.lepescin.articlesaboutproducts.HasId;

@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseTo implements HasId {
    protected Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
