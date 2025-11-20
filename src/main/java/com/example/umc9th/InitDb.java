package com.example.umc9th;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.MemberMission;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.member.enums.State;
import com.example.umc9th.domain.member.enums.Status;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.store.entity.Region;
import com.example.umc9th.domain.store.entity.Store;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = Member.builder()
                    .email("test@example.com")
                    .social_id("social123")
                    .social_type("KAKAO")
                    .name("홍길동")
                    .gender(Gender.MALE)
                    .birth_date(LocalDateTime.of(1995, 1, 1, 0, 0))
                    .address("서울시 강남구")
                    .phone_number("010-1234-5678")
                    .point(100)
                    .state(State.ACTIVE)
                    .build();

            em.persist(member);

            Region region = Region.builder()
                    .name("서울시 강남구")
                    .build();

            em.persist(region);

            Store store = Store.builder()
                    .name("맛집")
                    .address("서울시 강남구 역삼동")
                    .region(region)
                    .build();

            em.persist(store);

            Mission mission1 = Mission.builder()
                    .content("첫번째 미션")
                    .score(100)
                    .deadline(LocalDateTime.now().plusDays(7))
                    .store(store)
                    .build();

            em.persist(mission1);

            Mission mission2 = Mission.builder()
                    .content("두번째 미션")
                    .score(200)
                    .deadline(LocalDateTime.now().plusDays(7))
                    .store(store)
                    .build();

            em.persist(mission2);

            Mission mission3 = Mission.builder()
                    .content("세번째 미션")
                    .score(150)
                    .deadline(LocalDateTime.now().plusDays(7))
                    .store(store)
                    .build();

            em.persist(mission3);

            Mission mission4 = Mission.builder()
                    .content("네번째 미션")
                    .score(220)
                    .deadline(LocalDateTime.now().plusDays(7))
                    .store(store)
                    .build();

            em.persist(mission4);

            MemberMission completedMission1 = MemberMission.builder()
                    .member(member)
                    .mission(mission1)
                    .status(Status.COMPLETE)
                    .build();

            em.persist(completedMission1);

            MemberMission completedMission2 = MemberMission.builder()
                    .member(member)
                    .mission(mission2)
                    .status(Status.COMPLETE)
                    .build();

            em.persist(completedMission2);

            MemberMission challengeMission1 = MemberMission.builder()
                    .member(member)
                    .mission(mission3)
                    .status(Status.CHALLENGE)
                    .build();

            em.persist(challengeMission1);
        }
    }
}
