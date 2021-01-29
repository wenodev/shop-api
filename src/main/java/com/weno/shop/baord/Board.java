package com.weno.shop.baord;

import com.weno.shop.util.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void updateBoards(Board resource){
        this.title = resource.getTitle();
        this.content = resource.getContent();
    }

}
