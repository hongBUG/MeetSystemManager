package org.xu.service;

import java.util.List;

import org.xu.bean.MeetingRoom;
import org.xu.dao.MeetingRoomDao;

public class MeetingRoomService {
    private MeetingRoomDao mrDao = new MeetingRoomDao();
    
    public int insert(MeetingRoom mr) {
    	return mrDao.insert(mr);
    }
    
    public List<MeetingRoom> getAllMeetingRoom(){
    	return mrDao.getAllMeetingRoom();
    }
    
    public MeetingRoom getMeetingRoomById(int id) {
    	return mrDao.getMeetingRoomById(id);
    }
    
    public int update(MeetingRoom mr) {
    	return mrDao.update(mr);
    }
}
