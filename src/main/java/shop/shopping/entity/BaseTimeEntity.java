package shop.shopping.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 추상클래스에 사용할 수 있으며 엔티티가 될 수 없고 상속을 통해서 사용해야만 합니다.
@EntityListeners(AuditingEntityListener.class)  // 어노테이션이 있으면 Entity의 변화를 감지해서 Audit을 확인합니다.
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}