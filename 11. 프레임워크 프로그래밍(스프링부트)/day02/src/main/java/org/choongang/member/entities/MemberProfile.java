package org.choongang.member.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class MemberProfile {
    @Id @GeneratedValue
    private Long seq;
    private String profileImage;
    private String status;

    @ToString.Exclude
    @OneToOne(mappedBy = "profile") // mappedBy : 관계의 주인 명시
    private Member member;

}
