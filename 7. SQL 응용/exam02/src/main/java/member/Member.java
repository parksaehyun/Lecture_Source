package member;

import lombok.*;

import java.time.LocalDateTime;

//@EqualsAndHashCode
//@Getter @Setter @ToString
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    private String userId;
    private String userNm;
    private String email;
    private LocalDateTime regDt;
}
