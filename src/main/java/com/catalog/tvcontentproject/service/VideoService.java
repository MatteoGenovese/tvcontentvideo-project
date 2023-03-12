package com.catalog.tvcontentproject.service;



import com.catalog.tvcontentproject.pojo.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//restringo i limiti del mio generic alla superclasse Video, se volessi restringere i limiti sia
//ad una classe e sia ad una interfaccia metterei <E extends classe & interfaccia>, l'ordine è importante
//non posso quindi fare <E extends interfaccia & classe>
@Service
@Slf4j
public class VideoService <E extends Video> {








}
