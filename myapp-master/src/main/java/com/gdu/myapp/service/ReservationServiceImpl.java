package com.gdu.myapp.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.myapp.dto.AssetDto;
import com.gdu.myapp.dto.ReservationDto;
import com.gdu.myapp.mapper.ReservationMapper;
import com.gdu.myapp.utils.MyPageUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationMapper reservationMapper;
	private final MyPageUtils myPageUtils;
	
	@Override
	public int addAsset(HttpServletRequest request) {
		String assetCode = request.getParameter("assetCode");
		String assetName = request.getParameter("assetName");
		String assetType = request.getParameter("assetType");
		String assetSubname = request.getParameter("assetSubname");
		String subasset = request.getParameter("subasset");
		
		AssetDto asset = AssetDto.builder()
							.assetCode(assetCode)
							.assetName(assetName)
							.assetType(assetType)
							.assetSubname(assetSubname)
							.subasset(subasset)
						.build();
		
		
		int addAssetResultCount = reservationMapper.addAsset(asset);
		return addAssetResultCount;
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> getAssetList(HttpServletRequest request) {

		int total = reservationMapper.getAssetCount();
	  	
	  	int display = 10;
	  	
	  	int page = Integer.parseInt(request.getParameter("page"));
	  
	  	myPageUtils.setPaging(total, display, page);
	  	
	  	Map<String, Object> map = Map.of("begin" , myPageUtils.getBegin()
	        , "end", myPageUtils.getEnd());
	  	 return new ResponseEntity<>(Map .of("assetList", reservationMapper.getAssetList(map)
	         							   , "totalPage", myPageUtils.getTotalPage())
	  			 						   , HttpStatus.OK);
	  }
	
	@Override
	public ResponseEntity<Map<String, Object>> reservationList(HttpServletRequest request) {

		int total = reservationMapper.reservationCount();
	  	
	  	int display = 10;
	  	
	  	int page = Integer.parseInt(request.getParameter("page"));
	  
	  	myPageUtils.setPaging(total, display, page);
	  	
	  	Map<String, Object> map = Map.of("begin" , myPageUtils.getBegin()
	        , "end", myPageUtils.getEnd());
	  	 return new ResponseEntity<>(Map .of("reservationList", reservationMapper.reservationList(map)
	         							   , "totalPage", myPageUtils.getTotalPage())
	  			 						   , HttpStatus.OK);
	  }
	
	@Override
	public ResponseEntity<Map<String, Object>> myReservationList(HttpServletRequest request) {

		int total = reservationMapper.myReservationCount();
	  	
	  	int display = 10;
	  	
	  	int page = Integer.parseInt(request.getParameter("page"));
	  
	  	myPageUtils.setPaging(total, display, page);
	  	
	  	Map<String, Object> map = Map.of("begin" , myPageUtils.getBegin()
	        , "end", myPageUtils.getEnd());
	  	 return new ResponseEntity<>(Map .of("myReservationList", reservationMapper.myReservationList(map)
	         							   , "totalPage", myPageUtils.getTotalPage())
	  			 						   , HttpStatus.OK);
	  }
	
	public List<Map<String, Object>> assetList() {
        return reservationMapper.assetList();
    }
	
	@Override
	public int addReservation(HttpServletRequest request) {
	    String empCode = request.getParameter("empCode");
	    String assetCode = request.getParameter("assetCode");
	    String startDatetimeStr = request.getParameter("startDatetime");
	    String endDatetimeStr = request.getParameter("endDatetime");
	    String reason = request.getParameter("reason");

	    Timestamp startDatetime = Timestamp.valueOf(startDatetimeStr);
	    Timestamp endDatetime = Timestamp.valueOf(endDatetimeStr);

	    ReservationDto reser = ReservationDto.builder()
	                                        .empCode(empCode)
	                                        .assetCode(assetCode)
	                                        .startDatetime(startDatetime)
	                                        .endDatetime(endDatetime)
	                                        .reason(reason)
	                                    .build();

		int addReservationResultCount = reservationMapper.addReservation(reser);
		return addReservationResultCount;
	}
	
	@Override
	public AssetDto getAsset(String assetCode) {
		return reservationMapper.getAsset(assetCode);
	}
	
	@Override
	public int modifyAsset(HttpServletRequest request) {
		String assetType = request.getParameter("assetType");
		String assetCode = request.getParameter("assetCode");
		String assetName = request.getParameter("assetName");
		String assetSubname = request.getParameter("assetSubname");
		String subasset = request.getParameter("subasset");
		
		AssetDto asset = AssetDto.builder()
							.assetType(assetType)
							.assetCode(assetCode)
							.assetName(assetName)
							.assetSubname(assetSubname)
							.subasset(subasset)
						.build();
		int modifyAssetResult = reservationMapper.modifyAsset(asset);
		
		return modifyAssetResult;
	}
	
	@Override
	public int removeAsset(String assetCode) {
		return reservationMapper.removeAsset(assetCode);
	}
	
	@Override
	public int removeReservation(int reservationNumber) {
		return reservationMapper.removeReservation(reservationNumber);
	}
	

	
}
	
