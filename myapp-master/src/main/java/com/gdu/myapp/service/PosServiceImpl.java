package com.gdu.myapp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.gdu.myapp.dto.PosDto;
import com.gdu.myapp.mapper.PosMapper;
import com.gdu.myapp.utils.MyPageUtils;

import jakarta.servlet.http.HttpServletRequest;

@Transactional
@Service
public class PosServiceImpl implements PosService {
  
  private final PosMapper posMapper;
  private final MyPageUtils myPageUtils;
  
  public PosServiceImpl(PosMapper posMapper, MyPageUtils myPageUtils) {
    this.posMapper = posMapper;
    this.myPageUtils = myPageUtils;
  }

  @Override
  public void loadPosList(HttpServletRequest request, Model model) {
    
    int total = posMapper.getPosCount();
    
    int display = 10;
    
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(opt.orElse("1"));
    
    myPageUtils.setPaging(total, display, page);
    
    Map<String, Object> map = Map.of("begin", myPageUtils.getBegin()
                                   , "end", myPageUtils.getEnd());
    
    List<PosDto> posList = posMapper.getPosByMap(map);
    
    model.addAttribute("beginNo", total - (page - 1) * display);
    model.addAttribute("posList", posList);
    model.addAttribute("paging", myPageUtils.getPaging(request.getContextPath() + "contents/admin/pos/list.do", null, display));

  }

}