package com.weno.shop.entity;

import com.weno.shop.util.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Inquiry extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void updateInquiries(Inquiry resource){
        this.title = resource.getTitle();
        this.content = resource.getContent();
    }
}
