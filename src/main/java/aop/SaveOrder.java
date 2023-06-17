package aop;

import lombok.Data;

@Data
public class SaveOrder {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
