package com.example.CapiWater;




import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ReportRepo extends JpaRepository<Report, Long>{
	
	@Query("SELECT sum(r.waterUsage) FROM Report r WHERE r.area.house.hid = :hid and r.date = :date")
	Optional<Integer> getDailySumUsage(long hid,Date date);	
	@Query("SELECT sum(r.waterUsage) FROM Report r WHERE r.area.house.hid = :hid and year(r.date) = year(:date) and month(r.date) = month(:date) ")
	Optional<Integer> getMonthlySumUsage(long hid,Date date);
	@Query("SELECT sum(r.waterUsage) FROM Report r WHERE r.area.house.hid = :hid and year(r.date) = year(:date)")
	Optional<Integer> getYearlySumUsage(long hid,Date date);
	@Query("SELECT new com.example.CapiWater.DailyResult(r.tap.name, r.area.name, sum(r.waterUsage), hour(r.time)) FROM Report r WHERE r.area.house.hid = :hid and r.date = :date group by r.tap.name,hour(r.date)")
	List<DailyResult> getDailyReport(long hid,Date date);
	@Query("SELECT new com.example.CapiWater.MonthlyResult(r.tap.name,  r.area.name, sum(r.waterUsage), day(r.time)) FROM Report r WHERE r.area.house.hid = :hid and year(r.date) = year(:date) and month(r.date) = month(:date) group by r.tap.name,day(r.date)")
	List<MonthlyResult> getMonthlyReport(long hid,Date date);
	@Query("SELECT new com.example.CapiWater.YearlyResult(r.tap.name, r.area.name, sum(r.waterUsage), month(r.time)) FROM Report r WHERE r.area.house.hid = :hid and year(r.date) = year(:date) group by r.tap.name,month(r.date)")
	List<YearlyResult> getYearlyReport(long hid,Date date);
	@Query("SELECT new com.example.CapiWater.RankResult(r.area.house.name, sum(r.waterUsage) as usage) FROM Report r where month(r.date) = month(:date) group by r.area.house.name order by usage")
	List<RankResult> getRanks(Date date);
}
