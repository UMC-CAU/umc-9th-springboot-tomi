package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.domain.store.exception.StoreException;
import com.example.umc9th.domain.store.exception.code.StoreErrorCode;
import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    //ch 9 특정 가게 미션
    public MissionResDTO.StoreMissionListDTO findStoreMissions(Long storeId, Integer page){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));


        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findAllByStore(store, pageRequest);


        return MissionConverter.toStoreMissionListDTO(result);
    }

    //ch 9 내가 진행중인 미션
    public MissionResDTO.MyMissionListDTO findMyMissions(Long memberId, Integer page){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));


        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> result = missionRepository.findMyChallengeMissions(memberId, pageRequest);


        return MissionConverter.toMyMissionListDTO(result);
    }
}
