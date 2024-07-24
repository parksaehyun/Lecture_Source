package org.choongang.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode // 중복제거의 기준 // 기본키는 유일해야함(중복x)
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BoardViewId {
    private long seq;
    @Column(name = "_uid")
    private int uid;
}
