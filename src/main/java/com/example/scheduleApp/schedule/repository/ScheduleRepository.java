package com.example.scheduleApp.schedule.repository;

import com.example.scheduleApp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
