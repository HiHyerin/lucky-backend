package io.swyp.luckybackend.luckyDays.repository;

import io.swyp.luckybackend.luckyDays.domain.LcActivityEntity;
import io.swyp.luckybackend.luckyDays.dto.GetActivityListDto;
import io.swyp.luckybackend.luckyDays.dto.GetLcDayCyclDto;
import io.swyp.luckybackend.luckyDays.dto.GetLcDayDtlDto;
import io.swyp.luckybackend.luckyDays.dto.GetLcDayListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LcActivityRepository extends JpaRepository<LcActivityEntity, Long> {

    @Query("SELECT new io.swyp.luckybackend.luckyDays.dto.GetActivityListDto(a.category, a.activityNo, a.keyword) FROM LcActivityEntity a")
    List<GetActivityListDto> getActivityList();

    @Query("SELECT new io.swyp.luckybackend.luckyDays.dto.GetLcDayListDto(a.dtlNo, a.cycl.cyclNo, FUNCTION('DATEDIFF', a.dDay, FUNCTION('CURDATE')), a.dDay, a.dtlOrder) " +
            "FROM LcDayDtlEntity a " +
            "WHERE a.dDay >= FUNCTION('CURDATE')" +
            "AND a.user.userNo = :userNo")
    List<GetLcDayListDto> getLcDayList(long userNo);

    @Query("SELECT new io.swyp.luckybackend.luckyDays.dto.GetLcDayListDto( a.dtlNo, a.cycl.cyclNo, FUNCTION('DATEDIFF', a.dDay, FUNCTION('CURDATE')), a.dDay, a.dtlOrder) " +
            "FROM LcDayDtlEntity a " +
            "WHERE a.dDay < FUNCTION('CURDATE')" +
            "AND a.user.userNo = :userNo")
    List<GetLcDayListDto> getLcDayListByHist(long userNo);

    @Query("SELECT new io.swyp.luckybackend.luckyDays.dto.GetLcDayDtlDto(a.dDay, a.activityNm, b.activityInfo, a.review, a.image) " +
            "FROM LcDayDtlEntity a " +
            "JOIN a.activity b " +
            "WHERE a.dtlNo = :dtlNo")
    GetLcDayDtlDto getLcDayDetail(int dtlNo);

    @Query("SELECT new io.swyp.luckybackend.luckyDays.dto.GetLcDayCyclDto(a.startDt, a.endDt, a.period, a.count, a.exptDt)" +
            "FROM LcDayCycleEntity a " +
            "WHERE a.cyclNo = :cyclNo")
    GetLcDayCyclDto getLcDayCyclInfo(int cyclNo);
}
