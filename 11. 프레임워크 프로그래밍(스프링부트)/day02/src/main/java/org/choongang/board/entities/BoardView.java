package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.Data;

// 복합키 만들거임

@Data
@Entity
public class BoardView {
    @EmbeddedId
    private BoardViewId id;
}

/*
public class BoardView {
    @Id // 복합키
    private long seq;

    @Id // 복합키
    @Column(name = "_uid")
    private int uid;
}
 */
