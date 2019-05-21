package com.team.bookTicket.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.team.bookTicket.pojo.Room;
import com.team.bookTicket.pojo.Room2;

public interface RoomDao {
	Room findById(Integer id);
	List<Room> all(@Param("offset") Integer offset,@Param("count") Integer count);
	/**
	 * 获得电影即将上映的所有房间
	 * @param movieID
	 * @return
	 */
	List<Room2> allAvailableOfMovie(Integer movieID);
	/**
	 * 某个时候上映的某部电影在某个房间内已售的座位
	 * @param roomID
	 * @param movieID
	 * @param playTime
	 * @return
	 */
	List<Room> soldSeatOfRoomInTime(@Param("roomID") Integer roomID,@Param("movieID") Integer movieID,@Param("playTime") LocalDateTime playTime);
	int add(Room room);
	int delete(Integer id);
	int update(Room room);
	
}
